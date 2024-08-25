package Learning.Java.Basics;
public class arrays {

    public static void main(String[] args) {
        String[] cars = {"TATA","volvo","honda","lambo"};
        System.out.println(cars[0]);
        cars[0] = "Aniket";
        System.out.println(cars[0]);
        System.out.println("len " + cars.length);

        for (int i = 0; i < cars.length; i++) {
            System.out.println(i + " : " + cars[i]);
        }

        for (String string : cars) {
            System.out.println(string);
        }

        int[] nums = {1,2,3,4,5};
        System.out.println(nums[0]);
        nums[0]=9;
        System.out.println(nums[0]);
        System.out.println("len " + nums.length);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(i + " : " + nums[i]);
        }

        for (int i : nums) {
            System.out.println(i);
        }

        System.out.println("multidimensional");
        // multidimensional array
        int[][] multiArray = {{1,2}, {3,4}};
        System.out.println(multiArray[0][0]);

        for (int o = 0; o < multiArray.length; o++) {
            System.out.println("++++");
            for  (int r = 0; r < multiArray[o].length; r++ ) {
                System.out.println(multiArray[o][r]);
            }
        }

        for (int[] is : multiArray) {
            for (int j : is) {
                System.out.println(j);   
            }
        }
    }
}