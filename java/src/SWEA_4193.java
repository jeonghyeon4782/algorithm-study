package com.problem.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4193 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, startR, startC, endR, endC;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int T = 1; T <= test; T++) {
			StringTokenizer st = null;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endR = Integer.parseInt(st.nextToken());
			endC = Integer.parseInt(st.nextToken());

			System.out.printf("#%d %d\n", T, bfs());
		}
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { startR, startC, 0 });
		visited[startR][startC] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (now[0] == endR && now[1] == endC) {
				return now[2];
			}
			q.offer(new int[] { now[0], now[1], now[2] + 1 });
			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 1)
					continue;
				if (map[nr][nc] == 2 && (now[2] + 1) % 3 != 0)
					continue;
				q.offer(new int[] { nr, nc, now[2] + 1 });
				visited[nr][nc] = true;
			}
		}
		return -1;
	}

	public static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
