package com.problem.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2112 {
	static int D, W, K, result;
	static int[][] map, mapCopy;
	static int[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int T = 1; T <= test; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			map = new int[D][W];
			mapCopy = new int[D][W];
			input = new int[D];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					mapCopy[i][j] = map[i][j];
				}
			}
			if (check()) {
				System.out.printf("#%d %d\n", T, 0);
				continue;
			}
			subset(0, 0);
			System.out.printf("#%d %d\n", T, result);
		}
	}

	// 성능 검사
	public static boolean check() {
		for (int c = 0; c < W; c++) {
			boolean flag = false;
			for (int r = 0; r <= D - K; r++) {
				int cnt = 0;
				int num = mapCopy[r][c];
				for (int k = 0; k < K; k++) {
					if (num == mapCopy[r + k][c]) {
						++cnt;
					}
				}
				if (cnt == K) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}

	// 부분집합
	public static void subset(int depth, int count) {
		if (count >= result) return;
		if (depth == D) {
			int changeCnt = 0;
			copy();
			for (int i = 0; i < D; i++) {
				if (input[i] != -1) {
					++changeCnt;
					change(i, input[i]);
				}
			}
			if (check()) {
				result = Math.min(result, changeCnt);
			}
			return;
		}

		input[depth] = -1; // 변경 X
		subset(depth + 1, count);
		input[depth] = 0; // 0으로 변경
		subset(depth + 1, count + 1);
		input[depth] = 1; // 1로 변경
		subset(depth + 1, count + 1);
	}

	// 맵 복사
	public static void copy() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				mapCopy[i][j] = map[i][j];
			}
		}
	}

	// 행 바꾸기
	public static void change(int r, int num) {
		for (int i = 0; i < W; i++) {
			mapCopy[r][i] = num;
		}
	}
}
