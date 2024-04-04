package com.problem.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1486 {

	static int N, B;
	static int[] heights;
	static int minDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int T = 1; T <= test; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원 수
			B = Integer.parseInt(st.nextToken()); // 선반의 높이
			heights = new int[N];
			minDiff = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(heights);
			find(0, 0);
			System.out.printf("#%d %d\n", T, minDiff);
		}
	}

	public static void find(int idx, int sum) {
		if (sum >= B) { 
			minDiff = Math.min(minDiff, sum - B);
			return;
		}
		if (idx == N)
			return; 
		find(idx + 1, sum + heights[idx]);
	}
}
