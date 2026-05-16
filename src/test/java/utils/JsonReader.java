package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.Tree;

import java.io.File;
import java.io.IOException;

public class JsonReader
{
    private static JsonNode jsonNode;
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\loginData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String parent, String key)
    {
        return jsonNode.get(parent).get(key).asText();
    }
}
