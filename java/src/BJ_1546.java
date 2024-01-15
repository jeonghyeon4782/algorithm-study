import java.util.Scanner;

public class BJ_1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxScore = Integer.MIN_VALUE;
        int[] scores = new int[n];
        double answer = 0.0;
        for (int i = 0; i < scores.length; i++) {
            scores[i] = sc.nextInt();
            if (scores[i] > maxScore) {maxScore = scores[i];}
        }
        for (int i = 0; i < scores.length; i++) {
            answer += scores[i] * 1.0 / maxScore * 100;
        }
        System.out.println(answer / n);
    }
}
