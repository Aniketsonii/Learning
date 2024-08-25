import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PlacementMain
 */
public class PlacementMain {

    private Map<String, Float> myMap;

    // Getter method
    public Map<String, Float> getMyMap() {
        return myMap;
    }

    // Setter method
    public void setMyMap(Map<String, Float> myMap) {
        this.myMap = myMap;
    }

    // Method to check if the given college name is in the map
    // If found, return the corresponding value; otherwise, return -1
    public float findCollegeRating(String collegeName) {
        Float rating = myMap.get(collegeName);
        return (rating != null) ? rating : -1;
    }

    // Method to filter records based on a requirement
    // In this example, it returns a list of colleges with placement values greater than 4
    public List<String> findCollegesWithhighestRating() {
        List<String> filteredColleges = new ArrayList<>();

        for (Map.Entry<String, Float> entry : myMap.entrySet()) {
            if (entry.getValue() > 4) {
                filteredColleges.add(entry.getKey());
            }
        }

        return filteredColleges;
    }

}