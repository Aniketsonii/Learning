package Learning.Java.OOPS;

public class method {
    int x = 5;
    public static void main(String[] args){
       myMethod("fuck",32);
       myMethod("aniket",20);
       myMethod("okay",45);
       myMethod("tmkoc",65);

       System.out.println(sum(32, 44));
       System.out.println(sum(54, 34));

       isAge(29);
       isAge(12);

       {
        int x = 300;
        System.out.println(x); 
       }
       System.out.println("asda");

        System.out.println(add(23,53));
        System.out.println(add(12.31, 45.4));

        System.out.println(recursion(30,200));

    }

    public static void myMethod(String adder, int aso){
        System.out.println("helper "+adder + "is " + aso);
    }

    public void callme(){
        System.out.println("babe call me when you are free");
    }

    public static int sum(int x, int y){
        return  x+y;
    }

    public static void isAge(int age){
        if(age>=18){
            System.out.println("You are an adult");
        }else{
            System.out.println("You are a minor");
        }
            
    }
    
    public static int add(int x, int y){
        return x+y;
    }

    public static double add(double x,double y) {
        return x + y;
    }

    public static int recursion(int x,  int y){
        if (y>x) {
            return x + recursion(x,y-1);
        } else {
            return 0;
        }
    }
    
}