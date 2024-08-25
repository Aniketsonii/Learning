package Learning.Java.OOPS;

public class modifiers {
    
    public void pub(){
        System.out.println("Public Method ~ modifiers class");
    }
    
    private void pvt(){
        System.out.println("private method ~ modifiers class");
    }
    
    
    public static void main(String[] args) {
        modifiers cls = new modifiers();
        cls.pub();
        cls.pvt();

        methods mtd = new methods(100, "asdas");
        mtd.pub();
        mtd.ptd();
        System.out.println(mtd.x);
        System.out.println(mtd.name);
        System.out.println("public ^");
        System.out.println(mtd.A);
        System.out.println(mtd.Byte);
        System.out.println("protected ^");
        // Error: Can't access Private method and Attributes from outside the Class
        // System.out.println(mtd.isok);
        // System.out.println(mtd.id);
        // mtd.pvt();
        mtd.aniket();

    }

}