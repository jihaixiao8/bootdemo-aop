package cn.com.jd.config;

/**
 * Created by jihaixiao on 2016/9/12.
 */
public interface ZKConnectionListener {

    public void  syncConnected();


    public void expired();


    public void disconnected();

}
