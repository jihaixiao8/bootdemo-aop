package cn.com.jd.config;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jihaixiao on 2016/9/9.
 *
 * properties文件基础操作工具类
 *
 *
 */
public class PropertiesUtil {

    /**
     *
     * 从指定路径的properties文件读取指定key的value
     *
     * @param filePath  文件路径
     * @param key       要读取的key
     * @return
     * @throws IOException
     */
    public static String readValue(String filePath,String key) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
        } finally {
            if (in != null){
                in.close();
            }
        }
        return props.getProperty(key);
    }


    /**
     *
     * 读取执行路径的properties文件，将内容全部转换为map
     *
     *
     * @param filePath  文件路径
     * @return
     * @throws IOException
     */
    public static Map<String,String> readProperties(String filePath) throws IOException {
        Properties props = new Properties();
        InputStream in = null;
        Map<String,String> map = new HashMap<String, String>();
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            for (Map.Entry<Object,Object> entry : props.entrySet()){
                String key = entry.getKey().toString().trim();
                String value = entry.getValue().toString().trim();
                map.put(key,value);
            }
        } finally {
            if (in != null){
                in.close();
            }
        }
        return map;
    }

    /**
     *
     * 将 map中的key-value批量写入执行路径的properties文件
     *
     * @param filePath  文件路径
     * @param parameters  要写入的key-value
     * @throws IOException
     */
    public static void writeProperties(String filePath,Map<String,String> parameters) throws IOException {
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
        InputStream in = null;
        OutputStream out = null;
        Properties props = new Properties();
        try {
            in = new FileInputStream(file);
            props.load(in);

            out = new FileOutputStream(file);
            for (Iterator<String> it = parameters.keySet().iterator();it.hasNext(); ){
                String key = it.next();
                props.setProperty(key,parameters.get(key).trim());
            }
            props.store(out,"Config Center SnapShot");
        } finally {
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("a", "11");
        parameters.put("b", "22d                   ");
        parameters.put("c", "33");
        writeProperties("D:\\test\\test.properties", parameters);

        Map<String, String> result = readProperties("D:\\test\\test.properties");
        System.out.println(result);

        System.out.println(readValue("D:\\test\\test.properties","b"));
    }

}
