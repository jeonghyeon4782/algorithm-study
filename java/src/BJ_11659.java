import java.util.Scanner;

public class BJ_11659 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] sumNums = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int num = sc.nextInt();
            sumNums[i] = sumNums[i - 1] + num;
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(sumNums[e] - sumNums[s - 1]);
        }
    }
}
