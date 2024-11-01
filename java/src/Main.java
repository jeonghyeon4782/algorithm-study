import java.util.Arrays;

public class Main {

    static int[] nums = {1, 3, 11, 2, 7, 99, 100};
    static int n = 7;
    static int m = 3;
    static int[] input = new int[3];
    static boolean[] isSelected = new boolean[n];

    public static void main(String[] args) {
        comb(0);
    }

    public static void comb(int cnt) {
        if (cnt == n) {
            System.out.println(Arrays.toString(isSelected));
            return;
        }

        isSelected[cnt] = true;
        comb(cnt + 1);
        isSelected[cnt] = false;
        comb(cnt + 1);
    }
}