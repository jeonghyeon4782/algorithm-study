import java.util.*;

class PG_92344 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int R = board.length;
        int C = board[0].length;
        int[][] temp = new int[R + 1][C + 1];

        for (int[] s : skill) {
            int degree = (s[0] == 1) ? -s[5] : s[5];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            temp[r1][c1] += degree;
            temp[r1][c2 + 1] -= degree;
            temp[r2 + 1][c1] -= degree;
            temp[r2 + 1][c2 + 1] += degree;
        }

        for (int r = 0; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                temp[r][c] += temp[r][c - 1];
            }
        }

        for (int c = 0; c <= C; c++) {
            for (int r = 1; r <= R; r++) {
                temp[r][c] += temp[r - 1][c];
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                board[r][c] += temp[r][c];
                if (board[r][c] <= 0) ++answer;
            }
        }

        answer = R * C - answer;

        return answer;
    }
}