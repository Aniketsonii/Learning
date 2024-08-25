package Learning.Java.Basics;
public class data{
    public static void main(String[] args){
        int a = 200000;
        byte b = 20;
        String c = "Hello World";
        char d = 'A';
        float  e = 3.14f;
        double f = 6.789;
        short g = 30000;
        long h = 1000000000000000L;
        boolean j = true;



        float f1 = 35e3f;
        double d1 = 12E4d;
        char myVar1 = 65, myVar2 = 66, myVar3 = 67;
        
        System.out.println(myVar1);
        System.out.println(myVar2);
        System.out.println(myVar3);
        System.out.println(f1);
        System.out.println(d1);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(j);
    }

    public void testNonStatic(){
        data.test();
        return;
    }

    public static void test(){
        System.out.println("hello");
    }
}