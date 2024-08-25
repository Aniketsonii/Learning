package Learning.Java.TEST;

public class javastatictest {

    /**
     * Innerjavastatictest
     */
    public static class Innerjavastatictest {
        public String info;
        public int fxelements;
    }

    public static void main(String[] args) {
        Control();
    }
    
    public static Innerjavastatictest meth1(){
        Innerjavastatictest obj = new Innerjavastatictest();
        obj.info = "Hello";
        obj.fxelements = 123;
        
        return obj;
    }

    public static String Control(){
        Innerjavastatictest obj = meth1();
        System.out.println(obj.info);
        System.out.println(obj.fxelements);
        return "Control";
    }


}
