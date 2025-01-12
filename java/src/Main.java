import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] map = new int[n];
        int answer = 0;
        String s = sc.next();
        for (int i = 0; i < n; i++) {
            map[i] = s.charAt(i) - '0';
        }
        for (int num : map) {
            answer += num;
        }
        System.out.println(answer);
    }
}
