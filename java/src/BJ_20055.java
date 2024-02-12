import java.util.Arrays;
import java.util.Scanner;

public class BJ_20055 {
    static int[] durability;    // 내구도
    static boolean[] robots;    // 로봇의 유무
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        durability = new int[n * 2];
        robots = new boolean[n * 2];
        for (int i = 0; i < 2 * n; i++) {
            durability[i] = sc.nextInt();
        }

        int cnt = 0;

        while (true) {
            cnt++;
            rotate();
            move();
            if (durability[0] != 0) {
                robots[0] = true;
                durability[0]--;
            }
            if (chk() >= k) {
                System.out.println(cnt);
                break;
            }
        }
    }

    public static void rotate() {
        int temp = durability[n * 2 - 1];
        for (int i = n * 2 - 1; i > 0; i--) {
            if (i < n) {
                robots[i] = robots[i - 1];
            }
            durability[i] = durability[i - 1];
        }
        robots[0] = false;
        robots[n - 1] = false;
        durability[0] = temp;
    }

    public static void move() {
        for (int i = n - 1; i > 0; i--) {
            if (!robots[i] && robots[i - 1] && durability[i] >= 1) {
                robots[i] = robots[i - 1];
                robots[i - 1] = false;
                durability[i]--;
            }
        }
        robots[n - 1] = false;
    }

    public static int chk() {
        int count = 0;
        for (int i = 0; i < n * 2; i++) {
            if (durability[i] == 0) count++;
        }
        return count;
    }
}
