package com.problem.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_5972 {
	static class Node implements Comparable<Node> {
		int v;	// 정점
		int weight;	// 가중치
		
		Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int N, M;	// 정점 수, 간선 수
	static ArrayList<ArrayList<Node>> streetList;
	static int[] dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		streetList = new ArrayList<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i <= N; i++) {
			streetList.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d1 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			streetList.get(d1).add(new Node(d2, weight));
			streetList.get(d2).add(new Node(d1, weight));
		}
		dijkstra(1);
		System.out.println(dist[N]);
	}

	public static void dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[s] = 0;
		pq.add(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (!visited[now.v]) visited[now.v] = true;
			
			for (Node next : streetList.get(now.v)) {
				if (!visited[next.v] && now.weight + next.weight < dist[next.v]) {
					dist[next.v] = now.weight + next.weight;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
	}
}
