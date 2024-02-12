import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_17471 {

    static int n, answer;
    static int[][] map;
    static int[] population;
    static boolean[] isSelected, chk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        isSelected = new boolean[n + 1];
        map = new int[n + 1][n + 1];
        population = new int[n + 1];
        answer = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            for (int j = 0; j < end; j++) {
                map[i][Integer.parseInt(st.nextToken())] = 1;
            }
        }
        generateSubSet(1);
        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }

    // 부분 조합 만들기
    public static void generateSubSet(int cnt) {
        if (cnt == n + 1) {
            List<Integer> selected = new ArrayList<>();     // 선택된 선거구
            List<Integer> unSelected = new ArrayList<>();   // 선택되지 않은 선거구
            for (int i = 1; i < n + 1; i++) {
                if (isSelected[i]) selected.add(i);
                else unSelected.add(i);
            }
            if (check(selected) && check(unSelected)) {
                answer = Math.min(Math.abs(sumPopulation(selected) - sumPopulation(unSelected)), answer);
            }
            return;
        }

        isSelected[cnt] = true;
        generateSubSet(cnt + 1);
        isSelected[cnt] = false;
        generateSubSet(cnt + 1);
    }

    //  분리 된 선거구의 총 인원수 반환
    public static int sumPopulation(List<Integer> list) {
        int sumNum = 0;
        for (int i : list) {
            sumNum += population[i];
        }
        return sumNum;
    }

    // 지역끼리 연결되어 있는지 chk 배열을 통해서 확인
    public static boolean check(List<Integer> list) {
        chk = new boolean[n + 1];
        if (list.size() == 0) return false;
        bfs(list);
        for (int i : list) {
            if (!chk[i]) return false;
        }
        return true;
    }

    public static void bfs(List<Integer> list) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(list.get(0));
        chk[list.get(0)] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : list) {
                if (!chk[i] && map[now][i] == 1) {
                    q.offer(i);
                    chk[i] = true;
                }
            }
        }
    }

}
