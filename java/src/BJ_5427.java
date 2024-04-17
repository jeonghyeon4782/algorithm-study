package com.problem.BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427 {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int test = Integer.parseInt(br.readLine());
		for (int t = 0; t < test; t++) {
			Queue<Point> fires = new LinkedList<>();
			Queue<Point> humans = new LinkedList<>();
			int result = 0;
			boolean possible = false;
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			char[][] map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String s = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*') fires.offer(new Point(i, j));
					else if (map[i][j] == '@') humans.offer(new Point(i, j));
				}
			}
			while (!possible && !humans.isEmpty()) {
				result++;
				// 불 번지기
				for (int i = 0, size = fires.size(); i < size; i++) {
					Point fireNow = fires.poll();
					for (int d = 0; d < 4; d++) {
						int nr = fireNow.x + dr[d];
						int nc = fireNow.y + dc[d];
						
						if (isValid(nr, nc) && map[nr][nc] != '#' && map[nr][nc] != '*') {
							map[nr][nc] = '*';
							fires.offer(new Point(nr, nc));
						}
					}
				}
				// 사람 이동
				for (int i = 0, size = humans.size(); i < size; i++) {
					Point humanNow = humans.poll();
					if (humanNow.x == 0 || humanNow.x == R - 1 || humanNow.y == 0 || humanNow.y == C - 1) {
						possible = true;
						break;
					}
					for (int d = 0; d < 4; d++) {
						int nr = humanNow.x + dr[d];
						int nc = humanNow.y + dc[d];
						
						if (isValid(nr, nc) && map[nr][nc] == '.') {
							map[nr][nc] = '@';
							humans.offer(new Point(nr, nc));
						}
					}
				}
			}
			System.out.println((possible) ? result : "IMPOSSIBLE");
		}
	}
	
	public static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}