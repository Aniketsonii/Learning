package Learning.Java.OOPS;

import static Learning.Java.util.*;

abstract class Innertogive {
    int x = 10, y = 20; // instance variables
    public void display() { 
        System.out.println("x = "+x+ " y = "+y); 
    }

    public static void stat() {
        System.out.print("Static Method called");   
        
    }

    public String fname = "John";
    public int age = 24;
    public abstract void study(); // abstract method
    
}

final class Finl{
    final String finl= "this is a final String to give";
}

public class togive extends Innertogive{

    public void study(){
        System.out.println("Studying Java!");
    }


    public String pub = "Public String";
    private String pvt = "Private String";
    protected String prot = "Protected String";

    public void pub(){
        System.out.println("public method to give");
    }

    protected void ptd(){
        System.out.println("protected method to give");
    }

    private void pvt(){
        System.out.println("private method to give");
    }

    public static void main(String[] args) {
        togive tg = new togive();
        line();
        tg.pub();
        tg.pvt();
        tg.ptd();
        line();
        System.out.println(tg.pvt);
        System.out.println(tg.prot);
        System.out.println(tg.pub);
        line();
    }
    
}
