package Learning.Java.Basics;
public class typeCasting {
    public static void main(String[] args) {
    int myInt = 9;
    double myDouble = myInt; // Automatic casting: int to double

    System.out.println(myInt);      // Outputs 9
    System.out.println(myDouble);

    double yourDouble = 9.443;
    int yourInt = (int)yourDouble;   // Explicit casting: double to int
    System.out.println(yourInt);     // Outputs 9
    System.out.println(yourDouble);     

    }
}
