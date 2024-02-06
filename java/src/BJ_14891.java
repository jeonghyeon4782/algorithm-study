import java.util.*;

public class BJ_14891 {

    static int[][] arr;
    static boolean[] isPossible;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[5][8];
        for (int i = 1; i < 4; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            isPossible = new boolean[5];
            int n = sc.nextInt();
            int d= sc.nextInt();
            isValid(n, d);
            System.out.println(Arrays.toString(isPossible));
        }
    }

    public static void isValid(int n, int d) {

        if (arr[1][2] != arr[2][6]) isPossible[1] = true;
        if (arr[2][6] != arr[1][2] && arr[2][2] != arr[3][6]) isPossible[2] = true;
        if (arr[3][6] != arr[2][2] && arr[3][2] != arr[4][6]) isPossible[3] = true;
        if (arr[4][6] != arr[3][2]) isPossible[4] = true;
        isPossible[n] = true;
    }

//    public static void turn(int n, int d) {
//        if (n == 1 || n == 4) {
//            return;
//        }
//
//
//
//        turn(n - 1, (d == 1) ? -1 : 1);
//        turn(n + 1, (d == 1) ? -1 : 1);
//    }
}
