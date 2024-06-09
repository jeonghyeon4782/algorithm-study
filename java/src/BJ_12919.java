import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919 {
    static String from, to;
    static int found = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        from = br.readLine();
        to = br.readLine();

        dfs(new StringBuilder(to));
        System.out.println(found);
    }

    public static void dfs(StringBuilder sb) {
        if (found == 1) return; // 이미 찾았다면 더 이상 탐색하지 않음
        if (sb.toString().equals(from)) {
            found = 1;
            return;
        }
        if (sb.length() < from.length()) return;

        if (sb.charAt(0) == 'B') {
            StringBuilder newSb = new StringBuilder(sb);
            newSb.deleteCharAt(0).reverse();
            dfs(newSb);
        }

        if (sb.charAt(sb.length() - 1) == 'A') {
            StringBuilder newSb = new StringBuilder(sb);
            newSb.deleteCharAt(sb.length() - 1);
            dfs(newSb);
        }
    }
}
