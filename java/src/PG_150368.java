import java.util.Arrays;

public class PG_150368 {
    static int[] input;
    static int maxSubscriber, maxAmount;

    public static int[] solution(int[][] users, int[] emoticons) {
        input = new int[emoticons.length];
        subset(0, users, emoticons);
        int[] answer = {maxSubscriber, maxAmount};
        return answer;
    }

    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        solution(users, emoticons);
        System.out.println(maxSubscriber + " " + maxAmount);
    }

    public static void subset(int cnt, int[][] users, int[] emoticons) {
        if (cnt == emoticons.length) {
            int subscriber = 0;
            int amount = 0;
            for (int[] user : users) {
                int sum = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (user[0] <= input[i]) {
                        sum += emoticons[i] * ((100 - input[i]) * 0.01);
                    }
                }
                if (sum >= user[1]) {
                    subscriber++;
                } else {
                    amount += sum;
                }
            }
            if (maxSubscriber < subscriber) {
                maxSubscriber = subscriber;
                maxAmount = amount;
            }  else if (maxSubscriber == subscriber && maxAmount < amount) {
                maxSubscriber = subscriber;
                maxAmount = amount;
            }
            return;
        }

        input[cnt] = 10;
        subset(cnt + 1, users, emoticons);
        input[cnt] = 20;
        subset(cnt + 1, users, emoticons);
        input[cnt] = 30;
        subset(cnt + 1, users, emoticons);
        input[cnt] = 40;
        subset(cnt + 1, users, emoticons);
    }
}