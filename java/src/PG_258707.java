class PG_258707 {

    public int solution(int coin, int[] cards) {
        int answer = solve(coin, cards);
        return answer;
    }

    public int solve(int coin, int[] cards) {
        int round = 0;
        int n = cards.length;
        int pair = 0;   // n + 1 짝의 갯수
        int subPair = 0;
        boolean[] myCard = new boolean[n + 1];  // 나의 카드 배열
        boolean[] drawedCard = new boolean[n + 1];  // 뽑힌 카드 배열
        int idx = n / 3;    // 뽑을 카드 인덱스
        for (int i = 0; i < n / 3; i++) {
            myCard[cards[i]] = true;
        }
        for (int i = 1; i <= n / 2; i++) {
            if (myCard[i] && myCard[n + 1 - i]) {
                ++pair;
            }
        }

        while (true) {
            ++round;
            if (idx == n) break;
            for (int i = 0; i < 2; i++) {
                int card = cards[idx++];
                drawedCard[card] = true;

                if (myCard[n + 1 - card] && coin > 0) {
                    ++pair;
                    --coin;
                }

                if (!myCard[n + 1 - card] && drawedCard[n + 1 - card]) {
                    ++subPair;
                }

            }
            if (pair > 0) {
                --pair;
            } else {
                if (subPair > 0 && coin >= 2) {
                    --subPair;
                    coin -= 2;
                } else {
                    break;
                }
            }
        }

        return round;
    }
}