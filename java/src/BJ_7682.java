import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_7682 {

    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 1, 0, -1};
    static char[][] map;
    static int on, xn, bingoO, bingoX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();
            if (s.equals("end")) break;

            map = new char[3][3];
            int idx = 0;
            on = 0;
            xn = 0;
            bingoO = 0;
            bingoX = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = s.charAt(idx++);
                }
            }

            countBingo();
//            System.out.println("on : " + on + " xn : " + xn + " bingoO : " + bingoO + " bingoX : " + bingoX);
            if (solve()) System.out.println("valid");
            else System.out.println("invalid");
        }
    }

    private static boolean solve() {
        if (on > xn) return false;
        if (Math.abs(on - xn) > 1) return false;
        if (bingoO >= 1 && bingoX >= 1) return false;
        if (bingoO == 0 && bingoX == 0 && on + xn != 9) return false;
        if (bingoO > 0 && xn > on) return false;
        if (bingoX > 0 && xn <= on) return false;
        return true;
    }

    private static void countBingo() {

        // 각각의 갯수 구하기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'O') on++;
                else if (map[i][j] == 'X') xn++;
            }
        }

        // 빙고 갯수 구하기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = map[i][j];

                if (c == '.') continue;

                // 방향
                for (int d = 0; d < 4; d++) {
                    int count = 1;
                    for (int cnt = 1; cnt < 3; cnt++) {
                        int nr = i + dr[d] * cnt;
                        int nc = j + dc[d] * cnt;

                        if (!(0 <= nr && nr < 3 && 0 <= nc && nc < 3)) break;
                        if (c != map[nr][nc]) break;

                        ++count;
                    }

                    if (c == 'O' && count == 3) bingoO++;
                    else if (c == 'X' && count == 3) bingoX++;
                }
            }
        }
    }
}
