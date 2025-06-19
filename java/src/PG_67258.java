import java.util.*;

class PG_67258 {
    public int[] solution(String[] gems) {

        // 1. setting
        int[] answer = new int[2];
        int N;  // 보석의 종류 갯수
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        N = set.size();
        Map<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);

        // 2. 투포인터
        int s = 0;
        int e = 0;
        int minLen = Integer.MAX_VALUE;

        while (s <= e) {
            // 모든 종류를 포함한 경우
            if (map.size() == N) {
                if (e - s < minLen) {
                    minLen = e - s;
                    answer[0] = s + 1;
                    answer[1] = e + 1;
                }

                int count = map.get(gems[s]);
                if (count > 1) {
                    map.put(gems[s], count - 1);
                } else {
                    map.remove(gems[s]);
                }
                s++;
            } else {
                e++;
                if (e >= gems.length) break;
                map.put(gems[e], map.getOrDefault(gems[e], 0) + 1);
            }
        }

        return answer;
    }
}