package json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 16-9-2.
 */
public class JsonTest {

    public static void main(String[] args) throws IOException {
        List<JSONEntity> jsonEntityList = new ArrayList<JSONEntity>();
        JSONEntity jsonEntity = new JSONEntity("1234", UUID.randomUUID().toString());
        jsonEntityList.add(jsonEntity);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(jsonEntityList));
    }

}
