package cn.com.jd.config;

/**
 * Created by jihaixiao on 2016/9/9.
 *
 * 配置中心：监听配置数据是否改变
 *
 *
 */
public interface ConfigChangedListener {

    /**
     * 处理数据变更
     * @param key
     * @param data
     */
    public void handleDataChange(String key,String data);


}
