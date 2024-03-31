import java.util.Scanner;

public class BJ_2869 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int V = sc.nextInt();
        if (V - A == 0) {
            System.out.println(1);
        } else {
            if ((V - A) / (A - B) == 0) {
                System.out.println(2);
            } else if ((V - A) % (A - B) != 0) {
                System.out.println((V - A) / (A - B) + 2);
            } else {
                System.out.println((V - A) / (A - B) + 1);
            }
        }
    }
}
