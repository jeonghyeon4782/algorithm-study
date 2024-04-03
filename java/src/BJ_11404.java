package com.problem.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11404 {
	
	static int n, m;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		
		// map 초기화
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == j) continue;
				map[i][j] = 100_000_000;
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[from][to] = Math.min(map[from][to], weight);
		}
		
		for (int k = 1; k < n + 1; k++) {			// 경유지
			for (int i = 1; i < n + 1; i++) {		// 출발지
				for (int j = 1; j < n + 1; j++) {	// 도착지
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		for (int i = 1; i < n + 1; i++) { 
			for (int j = 1; j < n + 1; j++) {
				if (map[i][j] != 100_000_000) System.out.print(map[i][j] + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
}
