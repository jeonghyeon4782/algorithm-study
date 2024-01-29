import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2564 {
    static int x, y, n;
    static int[] locations; // 상점 위치

    static int distanceCalculation(int direction, int distance) {
        if (direction == 1) {
            return x + y + (x - distance);
        } else if (direction == 2) {
            return distance;
        } else if (direction == 3) {
            return x * 2 + y + distance;
        } else {
            return x + (y - distance);
        }
    }

    static int distanceCalculation(int now) {
        int l = x * 2 + y * 2;
        int answer = 0;
        for (int d : locations) {
            if (d > now) {
                answer += Math.min(d - now, now + l - d);
            } else if (d < now) {
                answer += Math.min(now - d, l - now + d);
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()); // 가로
        y = Integer.parseInt(st.nextToken()); // 세로
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 상점 수
        locations = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()); // 방향
            int distance = Integer.parseInt(st.nextToken()); // 거리
            locations[i] = distanceCalculation(direction, distance);
        }

        st = new StringTokenizer(br.readLine());
        int nowDirection = Integer.parseInt(st.nextToken()); // 현재 방향
        int nowDistance = Integer.parseInt(st.nextToken()); // 현재 거리
        int now = distanceCalculation(nowDirection, nowDistance);

        System.out.println(distanceCalculation(now));
    }
}
