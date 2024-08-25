package Learning.Java.Basics;
public class operators {

    public static void main(String[] args) {

        // Arithmetic Operators 
        int x = 10;
        int y = 20;

        int add = x+y;
        int sub = x-y;
        int mul = x*y;
        int div = x/y;
        int mod = x%y;
        int inc = ++x;
        int dec = --y;

        System.out.println(add);
        System.out.println(sub);
        System.out.println(mul);
        System.out.println(div);
        System.out.println(mod);
        System.out.println(inc);
        System.out.println(dec);

        //Java Assignment Operators

        int a = 10;
        System.out.println("a"+a);
        a += 5;
        System.out.println("a+=5,"+a);
        a -= 3;
        System.out.println("a-=3,"+a);
        a *= 2;
        System.out.println("a*=2,"+a);
        a /= 2;
        System.out.println("a/=2,"+a);
        a %= 7;
        System.out.println("a%=7,"+a);
        a &= 4;
        System.out.println("a&=4,"+a);
        a |= 8;
        System.out.println("a|=8,"+a);
        a >>= 1;
        System.out.println("a>>=1,"+a);
        a <<= 1;
        System.out.println("a<<=1,"+a);
        a ^= 9;
        System.out.println("a^=9," +a);


        //Java Comparison Operators

        int k = 100;
        int j = 200;

        System.out.println(k>j);
        System.out.println(k<j);
        System.out.println(k==j);
        System.out.println(k!=j);
        System.out.println(k>=j);
        System.out.println(k<=j);

        int comp = 5;
        System.out.println("comp");
        System.out.println(comp > 3 && comp < 10);
        System.out.println(comp > 3 || comp < 10);

        System.out.println("Logical operators: ");
        System.out.println(454%10);

    }
}