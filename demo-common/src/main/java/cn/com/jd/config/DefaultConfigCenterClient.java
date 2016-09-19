package cn.com.jd.config;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jihaixiao on 2016/9/12.
 */
public class DefaultConfigCenterClient extends DefaultZKListener implements ConfigCenterClient{



    private Map<String,String> localCache = new ConcurrentHashMap<String, String>();

    private volatile int status = Status.VIRGIN;



    @Override
    public String get(String key) {
        if (StringUtils.isBlank(key))
            throw new IllegalArgumentException("key can not be empty");

        String value = localCache.get(key);

        if (status == Status.FAILOVER || StringUtils.isNotBlank(value)){
            return  value;
        }

        return null;

    }

    @Override
    public void registerConfigChangedListener(String key, ConfigChangedListener configChangedListener) {

    }

    class Status{

        private static final int VIRGIN = 0;

        private static final int INITIALIZED = 1;

        private static final int FAILOVER = -1;
    }

}
