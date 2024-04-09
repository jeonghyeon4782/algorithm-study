package com.problem.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1238 {
	static class Node implements Comparable<Node> {
		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int N, M, X;	// 정점의 갯수, 간선의 갯수, 파티 장소
	static int[] dist;
	static boolean[] visited;
	static int[] result;
	static ArrayList<ArrayList<Node>> graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		result = new int[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, weight));
		}
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist, Integer.MAX_VALUE);
			Arrays.fill(visited, false);
			dijkstra(i);
			if (i == X) {
				for (int j = 1; j <= N; j++) {
					if (j == X) continue;
					result[j] += dist[j];
				}
			} else {
				result[i] += dist[X];
			}
		}
		int max = 0;
		for (int n : result) {
			if (max < n) max = n;
		}
		System.out.println(max);
	}

	public static void dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[s] = 0;
		pq.add(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (!visited[now.v]) visited[now.v] = true;
			
			if (now.v == X && s != X) break;
			
			for (Node next : graph.get(now.v)) {
				if (!visited[next.v] && now.weight + next.weight < dist[next.v]) {
					dist[next.v] = now.weight + next.weight;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
	}
}
