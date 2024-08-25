package Learning.Java.TEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexxx2 {
    public static void main(String[] args) {
        String input = "[{\"name\":\"EDIT\",\"type\":\"String\"},{\"name\":\"Editasd2\",\"type\":\"String\"}]";
        String regex = "\\{\"name\":\"([^\"]+)\",\"type\":\"([^\"]+)\"\\}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<Map<String, String>> keyValueList = new ArrayList<>();

        while (matcher.find()) {
            String name = matcher.group(1); // Extract value for "name"
            String type = matcher.group(2); // Extract value for "type"

            // Store in a map for each match
            Map<String, String> keyValueMap = new HashMap<>();
            keyValueMap.put("name", name);
            keyValueMap.put("type", type);

            keyValueList.add(keyValueMap); // Add map to list
        }

        // Print the list of maps for verification
        for (Map<String, String> map : keyValueList) {
            System.out.println("Stored in map: name = " + map.get("name") + ", type = " + map.get("type"));
        }
    }
    
}
