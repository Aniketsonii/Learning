package Learning.Java.OOPS;

public class classes {
    int x = 5;
    int y = 30;
    final int z = 99;
    //Final keyword is modifier 
    public static void main(String[] args) {
        // creating object of the class "classes"
        classes obj1 = new classes();
        classes obj2 = new classes();
        System.out.println("Value of 'x' in obj1 is : "+obj1.x);
        System.out.println("Value of 'x' in obj2 is : "+obj2.x);

        method met = new method();

        System.out.println(met.x);

        obj1.y = 40;
        System.out.println(obj1.y);
        System.out.println(obj2.y);

        // obj1.z = 100;
        System.out.println(obj1.z);
    }

}
