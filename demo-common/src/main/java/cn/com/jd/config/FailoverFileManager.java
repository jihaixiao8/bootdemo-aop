package cn.com.jd.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jihaixiao on 2016/9/9.
 *
 * 故障转移 数据存储文件管理器
 *
 */
public class FailoverFileManager {

    private String storeFilePath;

    private static final String FILE_NAME = "failover.properties";

    public Map<String,String> load() throws IOException {
        return PropertiesUtil.readProperties(getDataFilePath());
    }

    public String getDataFilePath() {
        return storeFilePath + File.separator + FILE_NAME;
    }

    public void setStoreFilePath(String storeFilePath) {
        this.storeFilePath = storeFilePath;
    }
}
