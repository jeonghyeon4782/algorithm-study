import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SWEA_문자열교집합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int T = 1; T <= test; T++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            int answer = 0;
            Set<String> set = new HashSet<>();
            Set<String> set1 = new HashSet<>();
            Set<String> set2 = new HashSet<>();
            for (int i = 0; i < n1; i++) {
                String s = sc.next();
                set.add(s);
                set1.add(s);
            }
            for (int i = 0; i < n2; i++) {
                String s = sc.next();
                set.add(s);
                set2.add(s);
            }
            for (String s : set) {
                if (set1.contains(s) && set2.contains(s)) answer++;
            }
            System.out.printf("#%d %d\n", T, answer);
        }
    }
}
