package com.problem.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014 {
	static int N, X, result;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int T = 1; T <= test; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			result = 0;
			int[] input = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 가로 체크
			for (int i = 0; i < N; i++) {
				if (check(map[i]))
					result++;
			}
			// 세로 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					input[j] = map[j][i];
				}
				if (check(input))
					result++;
			}
			System.out.printf("#%d %d\n", T, result);
		}
	}

	public static boolean check(int[] arr) {
		int num = arr[0];
		int cnt = 0; // 연속된 갯수
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			if (Math.abs(arr[i] - num) > 1)
				return false;

			if (arr[i] == num) {
				cnt++;
			} else if (arr[i] > num) { // 커질 경우
				if (flag) {
					if (cnt < X * 2)
						return false;
					flag = false;
				} else {
					if (cnt < X)
						return false;
				}
				num = arr[i];
				cnt = 1;
			} else { // 작아 질 경우
				if (flag) {
					if (cnt < X)
						return false;
				}
				num = arr[i];
				cnt = 1;
				flag = true;
			}
		}
		if (flag) {
			if (cnt < X)
				return false;
		}
		return true;
	}
}
