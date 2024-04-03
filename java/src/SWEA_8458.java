package com.problem.SWEA;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_8458 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1 ,1};
	static int N;
	static Point[] points;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		points = new Point[N];
		for (int i = 0; i < N; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
		}
		
		System.out.println(bfs(new Point(-3, 0)));
	}

	public static int bfs(Point point) {
		int idx = 1;
		Queue<Point> q = new LinkedList<Point>();
		q.offer(point);
		
		while (q.size() != 0) {
			for (int i = 0; i < q.size(); i++) {
				Point now = q.poll();
				if (point.x == 0 && point.y == 0) return idx - 1;
				for (int d = 0; d < 4; d++) {
					int nr = now.x + dr[d] * idx;
					int nc = now.y + dc[d] * idx;
					
					if (Math.abs(nr) > idx && Math.abs(nc) > idx) {
						q.offer(new Point(nr, nc));
					}
				}
			}
			++idx;
		}
			
		return -1;
	}
}
