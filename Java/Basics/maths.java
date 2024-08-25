package Learning.Java.Basics;
public class maths {
    public static void main(String[] args) {
        System.out.println(Math.max(3,7));
        System.out.println(Math.min(3,7));
        System.out.println(Math.sqrt(64));
        System.out.println(Math.abs(-4.7));
        System.out.println(Math.random());
        int randint = (int)(Math.random()*101);
        System.out.println(randint);
        System.out.println(Math.addExact(100, 200));
        System.out.println(Math.decrementExact(2000));
        System.out.println(Math.floor(9.4));
        System.out.println(Math.abs(11));


        int number = -15;
        int absoluteValue = (number < 0) ? -number : number;
        System.out.println("Absolute Value: " + absoluteValue);
    }
}
