import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919 {

    static String from, to;
    static boolean flag;    // ture : 찾음, false : 못찾음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        from = br.readLine();
        to = br.readLine();
        StringBuilder sb = new StringBuilder(to);

        dfs(sb);

        System.out.println(flag ? 1 : 0);
    }

    private static void dfs(StringBuilder sb) {
        if (sb.toString().equals(from)) {
            flag = true;
            return;
        }

        if (sb.length() > 1 && sb.charAt(0) == 'B') {
            StringBuilder newSb = new StringBuilder(sb);
            newSb.deleteCharAt(0);
            newSb.reverse();
            dfs(newSb);
        }

        if (sb.length() > 1 && sb.charAt(sb.length() - 1) == 'A') {
            StringBuilder newSb = new StringBuilder(sb);
            newSb.deleteCharAt(sb.length() - 1);
            dfs(newSb);
        }
    }
}
