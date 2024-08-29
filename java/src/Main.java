import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N, R;
    static int[] nums, input;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        isSelected = new boolean[N];
        nums = new int[N]; // 수들을 담을 숫자
        input = new int[R]; // 출력할 숫자 보관

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

//        순열(0);
        조합(0, 0);
    }

    public static void 조합(int cnt, int start) {

        if (cnt == R) {
            System.out.println(Arrays.toString(input));
            return;
        }

        for (int i = start; i < N; i++) {
            input[cnt] = nums[i];
            조합(cnt + 1, i + 1);
        }

    }

    public static void 순열(int cnt) {

        if (cnt == R) {
            System.out.println(Arrays.toString(input));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            input[cnt] = nums[i];
            isSelected[i] = true;
            순열(cnt + 1);
            isSelected[i] = false;
        }

    }
}