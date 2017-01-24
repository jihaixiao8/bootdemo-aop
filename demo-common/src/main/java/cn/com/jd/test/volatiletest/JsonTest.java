package cn.com.jd.test.volatiletest;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by jihaixiao on 2016/9/23.
 */
public class JsonTest {

    private static ObjectMapper OM = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        System.out.println(1 >>> 2);
    }

    public static String generate() throws IOException {
        Person1 person = new Person1();
        person.setId(12L);
        person.setName("liqianlong");
        person.setFlag(1);
        return OM.writeValueAsString(person);
    }

}
