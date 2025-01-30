import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2138 {

    static int N, firstTurnOnCnt, firstTurnOffCnt;
    static boolean[] firstTurnOn;
    static boolean[] firstTurnOff;
    static boolean[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        firstTurnOn = new boolean[N];
        firstTurnOff = new boolean[N];
        target = new boolean[N];
        char[] arr = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            if (arr[i] == '1') {
                firstTurnOn[i] = true;
                firstTurnOff[i] = true;
            }
        }
        firstTurnOn[0] = !firstTurnOn[0];
        firstTurnOn[1] = !firstTurnOn[1];
        firstTurnOnCnt = 1;
        char[] targetArr = br.readLine().toCharArray();
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if (arr[i] != targetArr[i]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (targetArr[i] == '1') {
                target[i] = true;
            }
        }

        for (int i = 1; i < N; i++) {
            if (firstTurnOn[i - 1] != target[i - 1]) {
                firstTurnOn[i - 1] = !firstTurnOn[i - 1];
                firstTurnOn[i] = !firstTurnOn[i];
                if (i != N - 1) {
                    firstTurnOn[i + 1] = !firstTurnOn[i + 1];
                }
                firstTurnOnCnt++;
            }
            if (firstTurnOff[i - 1] != target[i - 1]) {
                firstTurnOff[i - 1] = !firstTurnOff[i - 1];
                firstTurnOff[i] = !firstTurnOff[i];
                if (i != N - 1) {
                    firstTurnOff[i + 1] = !firstTurnOff[i + 1];
                }
                firstTurnOffCnt++;
            }
        }

        if (firstTurnOff[N - 1] != target[N - 1]) {
            firstTurnOffCnt = -1;
        }
        if (firstTurnOn[N - 1] != target[N - 1]) {
            firstTurnOnCnt = -1;
        }

        if (firstTurnOffCnt == -1 && firstTurnOnCnt == -1) {
            System.out.println(-1);
        } else if (firstTurnOffCnt == -1) {
            System.out.println(firstTurnOnCnt);
        } else if (firstTurnOnCnt == -1) {
            System.out.println(firstTurnOffCnt);
        } else {
            System.out.println(Math.min(firstTurnOffCnt, firstTurnOnCnt));
        }
    }
}
