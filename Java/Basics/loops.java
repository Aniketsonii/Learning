package Learning.Java.Basics;
public class loops {
    public static void main(String[] args) {
        int i = 0;
        while (i < 2) {
            System.out.println("habibi");
            i++;
        }

        int j =0;
        do{
            System.out.println("world");
            j++;
        }while(j < 10);

        for (int k = 0; k < 2; k = k+2) {
            System.out.println("did");
            System.out.println(i);
        }

        for (int l = 0; l <= 5; l++) {
            System.out.println(j);
            for (int m = 0; m < 6; m++) {
                System.out.println(m+"helper");
            }
        }

        String[] cars = {"volvo","BMW","Audi","VolksWagan"};
        for (String string : cars) {
            System.out.println(string);
        }

        for (int m = 0; m < 20;m++) {
            if(m == 4){
                System.out.println("continue");
                continue;
            }
            if(m == 7){
                System.out.println("break");
                break;
            }
            System.out.println(m);
        }

        int n =20;
        while (n < 2) {
            if (n==5) {
                n++;
                System.out.println("continue"+n);
                continue;
            }
            if (n==8) {
                System.out.println("break"+n);
                break;                
            }
            n++;
        }
    }
}
