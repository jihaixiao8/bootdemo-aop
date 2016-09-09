package cn.com.jd.config.impl;

import cn.com.jd.config.ConfigCenterClient;
import cn.com.jd.config.ConfigChangedListener;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jihaixiao on 2016/9/9.
 */
public class ConfigListener implements ConfigChangedListener{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String prefix = "";

    private String clazzpath;

    private Class<?> clazz;

    private List<String> switchKeyList;

    private ConfigCenterClient configCenterClient;


    @PostConstruct
    public void init() throws ClassNotFoundException {
        try {
            clazz = Class.forName(clazzpath);
            switchKeyList = new ArrayList<String>();
            for (Field field : clazz.getDeclaredFields()){
                try {
                    String key = prefix + field.getName();
                    switchKeyList.add(key);
                    configCenterClient.registerConfigChangedListener(key,this);
                    logger.debug("configChangedListener register success,key:{}",key);
                    String value = configCenterClient.get(key);
                    handleDataChange(key, value);
                    logger.debug("key init success,key:{}",key);
                } catch (Exception e) {
                    logger.error("init key error,field:{}",new Object[]{field,e});
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error("class not found error, classPath:{}", clazzpath);
            throw e;
        } catch (Exception e){
            logger.error("init methdo error", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void handleDataChange(String key, String data) {
        try {
            if (switchKeyList.contains(key)){
                if (StringUtils.isNotBlank(data)){
                    Field field = clazz.getDeclaredField(getFieldName(key));
                    Object value = ConvertUtils.convert(data,field.getType());
                    field.set(null,value);
                    logger.info("data change success,key{},data:{}",new Object[]{key,data});
                }else{
                    logger.warn("data is empty, key:{}, set:{}", key, data);
                }
            } else {
                logger.warn("switch key is not suitable, key:{}, set:{}", key, data);
            }
        } catch (Exception e) {
            logger.error("handle data change error,key:{},data{}",new Object[]{key,data,e});
        }
    }

    @Required
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Required
    public void setClazzpath(String clazzpath) {
        this.clazzpath = clazzpath;
    }

    @Required
    public void setConfigCenterClient(ConfigCenterClient configCenterClient) {
        this.configCenterClient = configCenterClient;
    }

    private String getFieldName(String key){
        if (StringUtils.isBlank(prefix)){
            return key;
        }
        if (key.startsWith(prefix)){
            return key.substring(prefix.length(),key.length());
        }else {
            return key;
        }
    }

}
