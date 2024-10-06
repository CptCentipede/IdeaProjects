import java.awt.*;

public class Test {
    public static void main (String[] args)
    {
        int x = 1;
        int y = 2;

        System.out.println(isEven(x));
        System.out.println(isEven(y));
        System.out.println(isOdd(x));
        System.out.println(isOdd(y));
        System.out.println(isOdd2(x));
        System.out.println(isOdd2(y));
        System.out.println(isOdd3(x));
        System.out.println(isOdd3(y));
    }

    public static boolean isEven(int x){
        if(x%2==0)
            return true;
        else
            return false;
    }

    public static boolean isOdd(int x){
        while(x != 1 && x != 0){
            x = x/2;
        }
        return x==1;
    }
    public static boolean isOdd2(int x){
        if(x%2==1)
            return true;
        else
            return false;
    }
    public static boolean isOdd3(int x){
        return x%2==1;
    }
}
