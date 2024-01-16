import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        n++;
        int m = Integer.parseInt(st.nextToken());;
        int[][] nums = new int[n][n];
        int[][] sumNums = new int[n][n];
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
                sumNums[i][j] = sumNums[i - 1][j] + sumNums[i][j - 1] - sumNums[i - 1][j - 1] + nums[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            int answer = sumNums[ex][ey] - sumNums[sx - 1][ey] - sumNums[ex][sy - 1] + sumNums[sx - 1][sy - 1];
            System.out.println(answer);
        }
    }
}
