import java.util.Arrays;
import java.util.Scanner;

public class BJ_1244 {
    static int[] switches;

    private static void turnOnOff(int idx) {
        if (switches[idx] == 0) switches[idx] = 1;
        else switches[idx] = 0;
    }

    private static void change(int gender, int idx) {
        if (gender == 1) {
            for (int i = idx; i < switches.length; i++) {
                if (i % idx == 0) turnOnOff(i);
            }
        } else {
            turnOnOff(idx);
            int s = idx - 1;
            int e = idx + 1;
            while (s > 0 && e < switches.length) {
                if (switches[s] == switches[e]) {
                    turnOnOff(s);
                    turnOnOff(e);
                    s--;
                    e++;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switches = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            switches[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int gender = sc.nextInt();
            int idx = sc.nextInt();
            change(gender, idx);
        }
        for (int i = 1; i < n + 1; i++) {
            System.out.print(switches[i] + " ");
            if (i % 20 == 0) System.out.println();
        }
    }
}
