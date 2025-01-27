import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = br.readLine().split("-");
        long answer = calculate(sArr[0]);
        for (int i = 1; i < sArr.length; i++) {
            answer -= calculate(sArr[i]);
        }
        System.out.println(answer);
    }

    private static long calculate(String s) {
        String[] sArr = s.split("\\+");
        long answer = 0;
        for (String num : sArr) {
            answer += Long.parseLong(num);
        }
        return answer;
    }
}
