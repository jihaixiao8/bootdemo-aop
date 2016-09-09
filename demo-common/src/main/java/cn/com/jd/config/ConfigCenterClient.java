package cn.com.jd.config;

/**
 * Created by jihaixiao on 2016/9/9.
 */
public interface ConfigCenterClient {

    public String get(String key);

    public void registerConfigChangedListener(String key, ConfigChangedListener configChangedListener);

}
