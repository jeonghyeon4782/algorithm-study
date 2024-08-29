import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15686 {

    static List<Point> houses;
    static List<Point> chickens;
    static int[][] map;
    static Point[] input;
    static int N, M, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses = new ArrayList<Point>();
        chickens = new ArrayList<Point>();
        map = new int[N][N];
        input = new Point[M];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) houses.add(new Point(i, j));
                else if (map[i][j] == 2) chickens.add(new Point(i, j));
            }
        }

        comb(0, 0);
        System.out.println(result);
    }

    public static void comb(int cnt, int start) {

        if (cnt == M) {
            int sumDist = 0;
            for (Point house : houses) {
                int minDist = Integer.MAX_VALUE;
                for (Point chicken : input) {
                    int dist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                    minDist = Math.min(minDist, dist);
                }
                sumDist += minDist;
            }
            result = Math.min(sumDist, result);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            input[cnt] = chickens.get(i);
            comb(cnt + 1, i + 1);
        }
    }
}
