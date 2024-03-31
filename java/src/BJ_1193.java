import java.util.Scanner;

public class BJ_1193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int row = 1;
        int col = 1;
        int cnt = 1;

        // 만약 n == 1 일경우
        if (cnt == n) {
            System.out.printf("%d/%d", row, col);
            return;
        }

        while (true) {
            if (row == 1) {
                ++cnt; ++col;
                if (cnt == n) {
                    System.out.printf("%d/%d", row, col);
                    return;
                }
                for (int i = 0, size = col - 1; i < size; i++) {
                    ++cnt; ++row; --col;
                    if (cnt == n) {
                        System.out.printf("%d/%d", row, col);
                        return;
                    }
                }
            }
            else {
                ++cnt; ++row;
                if (cnt == n) {
                    System.out.printf("%d/%d", row, col);
                    return;
                }
                for (int i = 0, size = row - 1; i < size; i++) {
                    ++cnt; --row; ++col;
                    if (cnt == n) {
                        System.out.printf("%d/%d", row, col);
                        return;
                    }
                }
            }
        }
    }
}
