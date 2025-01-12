import java.util.Scanner;

public class BJ_1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] scores = new int[n];
        int maxScore = 0;
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
            maxScore = Math.max(maxScore, scores[i]);
        }
        double answer = 0.0;
        for (int i = 0; i < n; i++) {
            answer += (double) scores[i] / maxScore * 100;
        }
        answer = answer / n;
        System.out.println(answer);
    }
}
