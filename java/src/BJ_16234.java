package com.problem.BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static List<Point> contryList;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		while (true) {
			flag = false;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(new Point(i, j));
						move();
					}
				}
			}

			if (!flag) {
				System.out.println(result);
				break;
			}

			result++;
		}

	}

	// 인구 이동
	private static void move() {
		int population = 0;
		for (Point p : contryList) {
			population += map[p.x][p.y];
		}
		population = (int) population / contryList.size();
		for (Point p : contryList) {
			map[p.x][p.y] = population;
		}
	}

	public static void bfs(Point point) {
		Queue<Point> q = new LinkedList<Point>();
		contryList = new ArrayList<Point>(); // 연합 국가 리스트
		visited[point.x][point.y] = true;
		contryList.add(point);
		q.offer(point);

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.x + dr[d];
				int nc = now.y + dc[d];

				if (isValid(nr, nc) && !visited[nr][nc] && Math.abs(map[now.x][now.y] - map[nr][nc]) >= L
						&& Math.abs(map[now.x][now.y] - map[nr][nc]) <= R) {
					flag = true;
					visited[nr][nc] = true;
					Point np = new Point(nr, nc);
					contryList.add(np);
					q.offer(np);
				}
			}
		}
	}

	public static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
