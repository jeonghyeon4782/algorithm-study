import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_1715 {
    static int N, result;
    static PriorityQueue<Integer> cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int card = Integer.parseInt(br.readLine());
            cards.offer(card);
        }
        while (cards.size() > 1) {
            int first = cards.poll();
            int second = cards.poll();
            int sumCard = first + second;
            result += sumCard;
            cards.offer(sumCard);
        }
        System.out.println(result);
    }
}
