package parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void getOrderedParamList(){
        String query = "select * from products where name=:name and category=:category";

        Map<String, Object> map = new HashMap<>();
        map.put("name","samsung");
        map.put("category","Mobile");

        List<?> orderParamList = parser.getOrderParamList(query, map);

        Assert.assertEquals("samsung",orderParamList.get(0).toString());
        Assert.assertEquals("Mobile",orderParamList.get(1).toString());
    }

}