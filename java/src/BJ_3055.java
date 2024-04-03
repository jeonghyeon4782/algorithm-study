package com.problem.BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055 {
	static int R, C;
	static int[] dr = {-1, 1 ,0 ,0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static Queue<Point> water;		// 물
	static Queue<Point> hedgehog;	// 고슴도치
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		water = new LinkedList<Point>();
		hedgehog = new LinkedList<Point>();
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '*') water.offer(new Point(i, j));
				else if (map[i][j] == 'S') hedgehog.offer(new Point(i, j));
			}
		}
		int result = bfs();
		System.out.println(result == 0 ? "KAKTUS" : result);
	}

	public static int bfs() {
		int minute = 1;
		while(true) {
			// 물 퍼뜨리기
			for (int i = 0, size = water.size();  i < size; i++) {
				Point now = water.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.x + dr[d];
					int nc = now.y + dc[d];
					
					if (isValid(nr, nc) && map[nr][nc] != '*' && map[nr][nc] != 'D' && map[nr][nc] != 'X') {
						water.offer(new Point(nr, nc));
						map[nr][nc] = '*';
					}
				}
			}
			
			for (int i = 0, size = hedgehog.size(); i < size; i++) {
				Point now = hedgehog.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.x + dr[d];
					int nc = now.y + dc[d];
					
					if (isValid(nr, nc) && map[nr][nc] == 'D') {
						return minute;
					}
					
					if (isValid(nr, nc) && map[nr][nc] != '*' && map[nr][nc] != 'X' && map[nr][nc] != 'S') {
						hedgehog.offer(new Point(nr, nc));
						map[nr][nc] = 'S';
					}
				}
			}
			
			++minute;
			if (hedgehog.size() == 0) {
				return 0;
			}
			
		}
	}

	public static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C; 
	}
}
 