package Learning.Java.Basics;
public class ifelse {
    public static void main(String[] args) {
        int x = 5;
        int y = 3;

        if (x>y) {
            System.out.println(true);
        }else{
            System.out.println(false);
        }

        if (x<y) {
            System.out.println(true);
        }else if(x>y){
            System.out.println(false);
        }else{
            System.out.println(true);
        }

        int res = 20;
        System.out.println((res>30?"hello":"World"));
    }
    
}
