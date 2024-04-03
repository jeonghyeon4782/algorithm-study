package com.problem.BOJ;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_2239 {
	static int[][] map;
	static List<Point> zeroList;
	static boolean endFlag = false;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		zeroList = new ArrayList<Point>();
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j) - '0';
				if (map[i][j] == 0) zeroList.add(new Point(i, j));
			}
		}
		
		dfs(0);
	}

	public static void dfs(int depth) {
		if (endFlag) return;
		if (depth == zeroList.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			endFlag = true;
			return;
		}
		
		Point now = zeroList.get(depth);
		
		for (int i = 1; i <= 9; i++) {
			if (check(now.x, now.y, i)) {
				map[now.x][now.y] = i;
				dfs(depth + 1);
				map[now.x][now.y] = 0;
			}
		}
	}
	
	public static boolean check(int r, int c, int key) {
		for (int i = 0; i < 9; i++) {
			if (map[r][i] == key) return false;
		}
		
		for (int i = 0; i < 9; i++) {
			if (map[i][c] == key) return false;
		}
		
		return getSquareidx(r, c, key);
	}
	
	public static boolean getSquareidx(int r, int c, int key) {
		if (0 <= r && r < 3 && 0 <= c && c < 3) return squareCheck(0, 3, 0, 3, key);
		else if (0 <= r && r < 3 && 3 <= c && c < 6) return squareCheck(0, 3, 3, 6, key);
		else if (0 <= r && r < 3 && 6 <= c && c < 9) return squareCheck(0, 3, 6, 9, key);
		 
		else if (3 <= r && r < 6 && 0 <= c && c < 3) return squareCheck(3, 6, 0, 3, key);
		else if (3 <= r && r < 6 && 3 <= c && c < 6) return squareCheck(3, 6, 3, 6, key);
		else if (3 <= r && r < 6 && 6 <= c && c < 9) return squareCheck(3, 6, 6, 9, key);
		 
		else if (6 <= r && r < 9 && 0 <= c && c < 3) return squareCheck(6, 9, 0, 3, key);
		else if (6 <= r && r < 9 && 3 <= c && c < 6) return squareCheck(6, 9, 3, 6, key);
		else return squareCheck(6, 9, 6, 9, key);
	}
	
	public static boolean squareCheck(int sr, int er, int sc, int ec, int key) {
		for (int i = sr; i < er; i++) {
			for (int j = sc; j < ec; j++) {
				if (map[i][j] == key) return false;
			}
		}
		return true;
	}
}
