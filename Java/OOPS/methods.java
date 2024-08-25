package Learning.Java.OOPS;

public class methods {
    public int x;
    public String name;
    private Long id = 10000000000L;
    private boolean isok = false;
    protected char A = 'A';
    protected byte Byte = 12;



    //constructor 
    public methods(int y,String z){
        x = y;
        name =z;

    }

    public void aniket(){
        System.out.println("~ Aniket Soni");
    }
    
    static void Aniket(){
        System.out.println("~ Aniket Soni");
    }

    public void message(){
        System.out.println("the law tips of the affair company does around the");
    }

    public void value(int value){
        System.out.println("the value is : "+value);
    }

    public void pub(){
        System.out.println("Public Method");
    }
    
    private void pvt(){
        System.out.println("private method");
    }

    protected void ptd(){
        System.out.println("protected method");
    }


    public static void main(String[] args){
        methods help = new methods(100,"mustang");
        help.message();
        help.value(100);
        Aniket();

        method meth2 = new method();
        meth2.callme();

        //constructor
        methods cstr = new methods(100,"dinchak");
        System.out.println(cstr.x);
        System.out.println(cstr.name);
        cstr.pub();
        cstr.pvt();
        System.out.println(cstr.id);
        System.out.println(cstr.isok);

        Aniket();
    }
}
