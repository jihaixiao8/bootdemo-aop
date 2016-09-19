package cn.com.jd.config;

/**
 * Created by jihaixiao on 2016/9/12.
 *
 * zk节点监听器
 *
 */
public interface ZKDataListener {

    /**
     *
     * 节点被创建触发
     *
     * @param path
     * @param data
     */
    public void handleDataCreated(String path,Object data);


    /**
     *
     * 节点数据改变触发
     *
     * @param path
     * @param data
     */
    public void handleDataChanged(String path,Object data);


    /**
     * 节点被删除触发
     * @param path
     * @param data
     */
    public void handleDataDeleted(String path,Object data);

}
