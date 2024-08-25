package Learning.Java.Basics;
public class switchs {
    public static void main(String[] args) {
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("1 one");
                break;
            case 2:
                System.out.println("2 two");
                break;
            case 3:
                System.out.println("3 three");
                break;
            case 4:
                System.out.println("4 four");
                break;
            default:
                System.out.println("Not a valid number!");
                break;
        }
    }
}
