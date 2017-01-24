package com.jd.test.hah;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jihaixiao on 2017/1/12.
 */
public class RedisUtil {

    private static Map<String,String> configMap;

    static {
        try {
            configMap = loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl(){
        return configMap.get("url");
    }


    public static Map<String,String> loadProperties() throws IOException {
        Map<String,String> map = new HashMap<>();
        Properties props = new Properties();
        InputStream in = RedisUtil.class.getClassLoader().getResourceAsStream("common.properties");
        try {
            props.load(in);
            for (Map.Entry<Object,Object> entry : props.entrySet()){
                String key = entry.getKey().toString().trim();
                String value = entry.getValue().toString().trim();
                map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return map;
    }

}
