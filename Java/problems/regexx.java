package Learning.Java.TEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexx {
    public static void main(String[] args) {
        List<Map<String, String>> ArgumentsArray = new ArrayList<>();
        String input = "[{\"name\":\"EDIT\",\"type\":\"String\"},{\"name\":\"Edit2\",\"type\":\"String\"}]";
        String regex = "\\{\"name\":\"[A-Za-z0-9]+\",\"type\":\"[A-Za-z]+\"\\}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        String ArgumentsSubstring = input.substring(input.indexOf('[') + 1, input.indexOf(']'));
        // String Substring = ArgumentsSubstring.substring(ArgumentsSubstring.indexOf('{') + 1, ArgumentsSubstring.indexOf('}'));

        System.out.println(ArgumentsSubstring);
        // System.out.println(Substring);

        String[] argsa = ArgumentsSubstring.split(",");
        	for (int i = 0; i < argsa.length; i++) {
        	    argsa[i] = argsa[i].trim();
        	}
            System.out.println(argsa);

        // Construct a JSON array for parameters
				Pattern pattern1 = Pattern.compile("\\s+");
        		for (String arg : argsa) {
        	    	String[] argParts = pattern1.split(arg);
        	    	String type = argParts[0];
        	    	String name = argParts[1].replaceAll("[^a-zA-Z0-9]", "");
        	    	Map<String,String> argsObject = new HashMap<>();
        	    	argsObject.put("name", name);
        	    	argsObject.put("type", type);
        	    	ArgumentsArray.add(argsObject);
        		}
        
        System.out.println(ArgumentsSubstring);
        
        
        System.out.println("\n");
        while (matcher.find()) {
            System.out.println("Found match: " + matcher.group());
        }
    }
}
