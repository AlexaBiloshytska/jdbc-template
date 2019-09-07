package com.alexa.jdbc.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public String getPlaceHolderQuery(String query, Map<String, ?> params) { // TODO: test if initial query changed
        for (String paramName : params.keySet()) {
            query = query.replaceFirst(":" + paramName, "?");
        }
        return query;
    }

    public List<?> getOrderParamList(String query, Map<String, ?> params) {

        List<Object> list = new ArrayList<>();

        Pattern pattern = Pattern.compile(":(\\w+)");
        Matcher matcher = pattern.matcher(query);

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            list.add(params.get(placeholder));
        }
        return list;
    }
}
