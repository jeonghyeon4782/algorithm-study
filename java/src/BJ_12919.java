import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12919 {
    static StringBuilder S, T;
    static boolean isExsist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());
        dfs(T);
        System.out.println(isExsist ? 1 : 0);
    }

    public static void dfs(StringBuilder sb) {
        if (sb.toString().equals(S.toString())) {
            isExsist = true;
            return;
        }
        if (sb.length() == 0 || isExsist) {
            return;
        }

            dfs(new StringBuilder(sb.substring(1)));
            dfs(new StringBuilder(sb.substring(1)).reverse());
    }
}
