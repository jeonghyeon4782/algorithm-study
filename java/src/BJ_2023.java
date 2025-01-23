import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ_2023 {

    static StringBuilder answer;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        answer = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            dfs(new StringBuilder(String.valueOf(i)), 1);
        }
        System.out.println(answer.toString());
    }

    private static void dfs(StringBuilder now, int depth) {
        if (!isValid(now)) return;
        if (depth == N) {
            answer.append(now).append("\n");
            return;
        }
        for (int i = 1; i <= 9; i++) {
            dfs(now.append(String.valueOf(i)), depth + 1);
            now.deleteCharAt(now.length() - 1);
        }
    }

    private static boolean isValid(StringBuilder sb) {
        int num = Integer.parseInt(sb.toString());
        if (num == 1) return false;
        for (int i = 2; i <= (int)Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
