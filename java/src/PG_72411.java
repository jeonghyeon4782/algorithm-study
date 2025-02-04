import java.util.*;

class PG_72411 {

    static Map<String, Integer> map;
    static List<String> result;
    static char[] arr;
    static int maxCnt;

    public String[] solution(String[] orders, int[] course) {
        result = new ArrayList<>();

        for (int r : course) {
            map = new HashMap<>();
            maxCnt = 0;
            for (String order : orders) {
                if (order.length() < r) continue;
                arr = order.toCharArray();
                Arrays.sort(arr);
                comb(r, 0, new StringBuilder());
            }
            if (maxCnt == 1) continue;
            for (String key : map.keySet()) {
                if (map.get(key) == maxCnt) {
                    result.add(key);
                }
            }
        }

        Collections.sort(result);
        String[] answer = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public void comb(int r, int start, StringBuilder sb) {

        if (sb.length() == r) {
            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), 1);
                maxCnt = Math.max(maxCnt, 1);
            } else {
                int cnt = map.get(sb.toString());
                map.put(sb.toString(), cnt + 1);
                maxCnt = Math.max(maxCnt, cnt + 1);
            }

            return;
        }

        for (int i = start; i < arr.length; i++) {
            sb.append(arr[i]);
            comb(r, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}