import java.util.Scanner;

public class BJ_14719 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int[] blocks = new int[W];
        for (int i = 0; i < W; i++) {
            blocks[i] = sc.nextInt();
        }

        int result = 0;
        int maxBlock = 0;
        int cnt = 0;

        for (int i = 0; i < W; i++) {
            if (maxBlock == 0 && blocks[i] == 0) continue;
            if (maxBlock == 0 && blocks[i] != 0) {
                maxBlock = blocks[i];
                continue;
            }
            if (maxBlock <= blocks[i]) {
                maxBlock = 0;
                continue;
            } else {
                result += maxBlock - blocks[i];
            }
        }
        System.out.println(result);
    }
}
