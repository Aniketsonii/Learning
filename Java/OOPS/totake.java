package Learning.Java.OOPS;
import static Learning.Java.util.*;

public class totake{
    
    public static void main(String[] args) {
        // To take
        line();
        togive tg = new togive();
        tg.pub();
        tg.ptd();
        // cant take pvt methods 
        // tg.pvt();
        line();
        System.out.println(tg.pub);
        System.out.println(tg.prot);
        // cant take pvt Attributes
        // System.out.println(tg.pvt);
        line();
        System.out.println("// cannot call final class : Finl fm = new Finl();");
        // cannot call final class
        // Finl fm = new Finl();
        line();
        System.out.println("//abstract classes method can only be called by extending the class:  public class totake extends Innertogive\n");
        tg.stat();
        line();
        //cannot create objects of abstract classes :  Innertogive inn = new Innertogive();
        // Innertogive inn = new Innertogive();
        // inn.display();
        System.out.println("//cannot create objects of abstract classes :  Innertogive inn = new Innertogive();");
        line();
        System.out.println("accessing abstract class methods --- ");
        System.out.println(tg.fname);
        System.out.println(tg.x);
        tg.study(); // calling abstract method
        line();



    }
}
