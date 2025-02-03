import java.util.*;

class PG_72412 {

    static Map<String, List<Integer>> map;
    static boolean[] isSelected;
    static int[] answer;

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        map = new HashMap<>();
        isSelected = new boolean[4];

        for (String s : info) {
            String[] infoArr = s.split(" ");
            comb(0, infoArr);
        }

        for (String s : map.keySet()) {
            Collections.sort(map.get(s));
        }

        int idx = 0;
        for (String q : query) {
            String[] qArr = q.split(" and | ");
            StringBuilder sb = new StringBuilder();
            sb.append(qArr[0]).append(qArr[1]).append(qArr[2]).append(qArr[3]);
            binarySearch(idx++, sb.toString(), Integer.parseInt(qArr[4]));
        }

        return answer;
    }

    private void binarySearch(int idx, String str, int score) {
        List<Integer> list = map.get(str);
        if (list == null) {
            answer[idx] = 0;
            return;
        }
        int s = 0;
        int e = list.size() - 1;

        while (s <= e) {
            int m = (s + e) / 2;
            int mScore = list.get(m);

            if (mScore < score) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        answer[idx] = list.size() - s;
    }

    private void comb(int cnt, String[] arr) {
        if (cnt == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                if (isSelected[i]) {
                    sb.append(arr[i]);
                } else {
                    sb.append("-");
                }
            }
            String s = sb.toString();
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(Integer.parseInt(arr[4]));
            return;
        }

        isSelected[cnt] = false;
        comb(cnt + 1, arr);
        isSelected[cnt] = true;
        comb(cnt + 1, arr);
    }
}