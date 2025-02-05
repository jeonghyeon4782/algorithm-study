import java.util.*;

class PG_70129 {
    public int[] solution(String s) {

        int total = 0;
        int count = 0;
        char[] arr = s.toCharArray();

        while (true) {

            if (arr.length <= 1 && arr[0] == '1') {
                break;
            }

            int cnt = 0;
            for (char c : arr) {
                if (c == '0') ++cnt;
            }

            total += cnt;
            ++count;

            arr = String.valueOf(Integer.toString(arr.length - cnt, 2)).toCharArray();
        }

        return new int[] {count, total};
    }
}