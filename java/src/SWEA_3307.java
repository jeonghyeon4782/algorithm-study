package com.problem.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307 {
	static int[] dp, nums;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			N = Integer.parseInt(br.readLine());
			dp = new int[N + 1];
			nums = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			int count = 0;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (nums[i] > dp[count]) {
					dp[++count] = nums[i];
				} else {
					idx = binarySearch(0, count, nums[i]);
					dp[idx] = nums[i];
				}
			}
			
			System.out.printf("#%d %d", t, count);
		}
	}
	
	public static int binarySearch(int s, int e, int key) {
		while (s < e) {
			int m = (s + e) / 2;
			if (dp[m] < key) s = m + 1;
			else e = m;
		}
		return e;
	}
}
