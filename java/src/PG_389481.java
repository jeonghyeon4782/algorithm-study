import java.util.*;

class PG_389481 {

    Set<String> set;

    public String solution(long n, String[] bans) {
        set = new HashSet<>(Arrays.asList(bans));
        String answer = binarySearch(n, bans);
        return answer;
    }

    public String binarySearch(long n, String[] bans) {
        long s = 1;
        long e = (long) Math.pow(10, 15);

        while (s <= e) {
            long m = (s + e) / 2;
            String beforeString = getString(m); // m번째 주문
            long idx = deleteString(beforeString, bans);  // 앞에 삭제 한 주문의 수
            if (m - idx == n) {
                if (set.contains(beforeString)) {
                    s = m + 1;
                } else {
                    return beforeString;
                }
            }
            else if (m - idx > n) e = m - 1;
            else s = m + 1;
        }

        return null;
    }

    public long deleteString(String str, String[] bans) {
        long cnt = 0;
        for (String ban : bans) {
            if (str.length() > ban.length()) ++cnt;
            else if (str.length() == ban.length()) {
                if (str.compareTo(ban) > 0) ++cnt;
            }
        }
        return cnt;
    }

    public String getString(long m) {
        StringBuilder sb = new StringBuilder();

        while (m > 0) {
            long a = m / 26;
            long b = m % 26;
            char ch;
            if (b == 0) {
                ch = 'z';
                m = a - 1;
            } else {
                ch = (char) ('a' + (b - 1));
                m = a;
            }
            sb.append(ch);
        }

        sb.reverse();
        return sb.toString();
    }
}