import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyClass {
    private Map<String, Float> myMap;

    // Constructor
    public MyClass(Map<String, Float> myMap) {
        this.myMap = myMap;
    }

    // Getter method
    public Map<String, Float> getMyMap() {
        return myMap;
    }

    // Setter method
    public void setMyMap(Map<String, Float> myMap) {
        this.myMap = myMap;
    }

    // Example method to print the contents of the map
    public void printMap() {
        for (Map.Entry<String, Float> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method to add records based on user input
    public void addRecords() {
        Scanner scanner = new Scanner(System.in);

        // Get the number of records to be added
        System.out.print("Enter the number of records to be added: ");
        int numRecords = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Add records to the map based on user input
        for (int i = 0; i < numRecords; i++) {
            System.out.print("Enter the record (collegeName : value): ");
            String record = scanner.nextLine();
            String[] parts = record.split(":");
            if (parts.length == 2) {
                String collegeName = parts[0].trim();
                float value = Float.parseFloat(parts[1].trim());
                myMap.put(collegeName, value);
            }
        }
    }

    // Method to search for the highest value and print the names of colleges with that value
    public void searchAndPrintHighest() {
        Scanner scanner = new Scanner(System.in);

        // Get the college name to be searched
        System.out.print("Enter the College name to be searched: ");
        String collegeNameToSearch = scanner.nextLine();

        // Search for the college name in the map
        Float value = myMap.get(collegeNameToSearch);

        // Print the value of the college name or a message if not found
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("No colleges with the name found.");
        }

        // Search for the highest value
        float highestValue = -1;
        for (Float val : myMap.values()) {
            if (val > highestValue) {
                highestValue = val;
            }
        }

        // Print the names of colleges with the highest value, excluding the searched college name
        System.out.println("The names of colleges with the highest value are:");
        for (Map.Entry<String, Float> entry : myMap.entrySet()) {
            if (entry.getValue() == highestValue && !entry.getKey().equals(collegeNameToSearch)) {
                System.out.println(entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of MyClass with an empty map
        MyClass myClassInstance = new MyClass(new HashMap<>());

        // Add records based on user input
        myClassInstance.addRecords();

        // Print the contents of the map using the class method
        myClassInstance.printMap();

        // Search for the college name and print the value or a message if not found
        myClassInstance.searchAndPrintHighest();
    }
}
