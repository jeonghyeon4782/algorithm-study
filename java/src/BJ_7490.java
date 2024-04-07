import java.util.Scanner;

public class BJ_7490 {

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int T = 0; T < test; T++) {
            N = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            sb.append(1);
            dfs(1, sb);
            System.out.println();
        }
    }

    public static void dfs(int num, StringBuilder sb) {
        if (num == N) {
            if (calc(sb) == 0) {
                System.out.println(sb);
            }
            return;
        }

        StringBuilder newSb = new StringBuilder(sb.toString());
        dfs(num + 1, newSb.append(" ").append(num + 1));
        newSb = new StringBuilder(sb.toString());
        dfs(num + 1, newSb.append("+").append(num + 1));
        newSb = new StringBuilder(sb.toString());
        dfs(num + 1, newSb.append("-").append(num + 1));
    }

    public static int calc(StringBuilder sb) {
        int num  = 0;
        int result = 0;
        char op = '+';
        String s = sb.toString();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                if (op == '+') {
                    result += num;
                } else {
                    result -= num;
                }
                num = 0;
                op = '+';
            } else if (s.charAt(i) == '-') {
                if (op == '+') {
                    result += num;
                } else {
                    result -= num;
                }
                num = 0;
                op = '-';
            } else if ((s.charAt(i) == ' ')) {
                num *= 10;
            } else {
                num += (s.charAt(i) - '0');
            }
        }
        if (op == '+') {
            result += num;
        } else {
            result -= num;
        }
        return result;
    }
}
