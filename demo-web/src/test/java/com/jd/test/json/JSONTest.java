package com.jd.test.json;

import com.jd.test.base.BaseTest;
import com.jd.test.json.dto.Student;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jihaixiao on 2016/9/6.
 */
public class JSONTest extends BaseTest{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJson() throws IOException {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("name","jihaixiao");
        map.put("age",26);
        HashMap<String,Object> map1 = new HashMap<String, Object>();
        map1.put("name","ting");
        map1.put("age",23);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        list.add(map1);
        String json = objectMapper.writeValueAsString(list);
        System.out.println(json);

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,HashMap.class);

        List<Map<String,Object>> list11 = objectMapper.readValue(json,javaType);
    }

}
