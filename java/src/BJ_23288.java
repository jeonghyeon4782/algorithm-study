package com.problem.BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_23288 {
	// 0 : 오른쪽, 1 : 아래쪽, 2 : 왼쪽, 3 : 위쪽
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int N, M, K, result; // 세로, 가로, 이동 횟수, 점수의 합
	static int nowR, nowC, dist; // 현재 R, 현재 C, 이동 방향
	static int[][] dice = { { 0, 2, 0 }, { 4, 1, 3 }, { 0, 5, 0 }, { 0, 6, 0 } };// 주사위 >> 아랫면은 [3][1]
	static int[][] map; // 지도

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) move();
		System.out.println(result);
	}

	public static void move() {
		int nr = nowR + dr[dist];
		int nc = nowC + dc[dist];
		int empty = 0;
		// 만약 이동할 수 없는 칸이라면 방향을 반대쪽으로 이동
		if (!isValid(nr, nc)) dist = (dist + 2) % 4;

		// 주사위 변화
		switch (dist) {
		case 0:
			empty = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = empty;
			break;
		case 1:
			empty = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = empty;
			break;
		case 2:
			empty = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = empty;
			break;

		case 3:
			empty = dice[3][1];
			dice[3][1] = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = empty;
			break;
		}

		// 현재 방향 이동시키기
		nowR = nowR + dr[dist];
		nowC = nowC + dc[dist];
		// 점수 구하기
		result += bfs(nowR, nowC);
		changeDist();
	}

	// bfs로 점수 구하기
	private static int bfs(int r, int c) {
		int count = 1;
		int value = map[r][c];	// value랑 같아야 함
		boolean[][] visited = new boolean[N][M];	// 방문 배열 초기화
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		visited[r][c] = true;	// 방문 체크
		
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.x + dr[d];
				int nc = now.y + dc[d];
				
				if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == value) {
					q.offer(new Point(nr, nc));
					++count;
					visited[nr][nc] = true;
				}
			}
		}
		
		return count * value;
	}

	// 주사위가 벗어나는지 여부 확인
	public static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	// 방향 변화
	public static void changeDist() {
		int bottom = dice[3][1];
		int now = map[nowR][nowC];
		if (bottom > now) {
			dist = (dist + 1) % 4;
		} else if (bottom < now) {
			if (dist == 0) {
				dist = 3;
			} else {
				dist -= 1;
			}
		} 
	}
}
