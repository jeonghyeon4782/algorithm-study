import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ_11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cost = sc.nextInt();
        int answer = 0;
        Integer[] coins = new Integer[N];
        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
        }
        Arrays.sort(coins, Collections.reverseOrder());
        for (int coin : coins) {
            answer += cost / coin;
            cost %= coin;
            if (cost == 0) break;
        }

        System.out.println(answer);
    }
}
