package com.problem.SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_8382 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int nowR, nowC, targetR, targetC;
	static boolean[][][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int T = 1; T <= test; T++) {
			nowR = sc.nextInt();
			nowC = sc.nextInt();
			targetR = sc.nextInt();
			targetC = sc.nextInt();
			visited = new boolean[201][201][4];

			System.out.printf("#%d %d\n", T, bfs(nowR, nowC));
		}
	}

	public static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c, -1, 0 });
		visited[r + 100][c + 100][0] = true;
		visited[r + 100][c + 100][1] = true;
		visited[r + 100][c + 100][2] = true;
		visited[r + 100][c + 100][3] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == targetR && now[1] == targetC)
				return now[3];
			for (int d = 0; d < 4; d++) {
				if ((now[2] == 0 && d == 0) || (now[2] == 0 && d == 1))
					continue;
				else if ((now[2] == 1 && d == 0) || (now[2] == 1 && d == 1))
					continue;
				else if ((now[2] == 2 && d == 2) || (now[2] == 2 && d == 3))
					continue;
				else if ((now[2] == 3 && d == 2) || (now[2] == 3 && d == 3))
					continue;
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if (nr >= -100 && nr <= 100 && nc >= -100 && nc <= 100 && !visited[nr + 100][nc + 100][d]) {
					q.offer(new int[] { nr, nc, d, now[3] + 1 });
					visited[nr + 100][nc + 100][d] = true;
				}
			}
		}
		return -1;
	}
}
