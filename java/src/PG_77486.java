import java.util.*;

class PG_77486 {

    Map<String, String> referMap;
    Map<String, Integer> revenueMap;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        referMap = new HashMap<>();
        revenueMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            referMap.put(enroll[i], referral[i]);
            revenueMap.put(enroll[i], 0);
        }

        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            int total = amount[i] * 100;
            distribute(name, total);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = revenueMap.get(enroll[i]);
        }

        return answer;
    }

    private void distribute(String name, int income) {
        if (name.equals("-") || income < 1) return;

        int give = income / 10; // 추천인에게 주는 금액
        int keep = income - give;   // 내가 가질 금액

        revenueMap.put(name, revenueMap.get(name) + keep);
        distribute(referMap.get(name), give);
    }
}