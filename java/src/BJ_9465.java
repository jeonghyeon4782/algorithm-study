package com.problem.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_9465 {
	static int N;
	static int[][] sticker;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int test = Integer.parseInt(br.readLine());
		for (int T = 0; T < test; T++) {
			N = Integer.parseInt(br.readLine());
			sticker = new int[2][N + 1];
			dp = new int[2][N + 1];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			
			for (int i = 2; i <= N; i++) {
				for (int j = 0; j < 2; j++) {
					int target = (j == 0) ? 1 : 0;	// 반대방향
					dp[j][i] = Math.max(dp[target][i - 1], Math.max(dp[0][i - 2], dp[1][i - 2])) + sticker[j][i];
				}
			}
			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}
	}
}
