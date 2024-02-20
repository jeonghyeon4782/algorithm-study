import java.util.Scanner;

public class BJ_10870 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(fibo(sc.nextInt() + 1));
    }

    public static int fibo(int x) {
        if (x == 1) return 0;
        if (x == 2) return 1;
        return fibo(x - 1) + fibo(x - 2);
    }
}
