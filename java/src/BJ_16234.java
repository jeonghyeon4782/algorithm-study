import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16234 {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, L, R, total;
	static int[][] map;
	static boolean[][] visited;
	static List<Point> moveList;

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int answer = 0;
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new boolean[N][N];
			boolean flag = false;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						total = map[r][c];
						moveList = new ArrayList<>();
						moveList.add(new Point(r, c));
						visited[r][c] = true;
						bfs(new Point(r, c));
						if (moveList.size() > 1) {
							movePeople();
							flag = true;
						}
					}
				}
			}

			if (!flag) break;
			++answer;
		}

		System.out.println(answer);
	}

	private static void movePeople() {
		int newPeo = total / moveList.size();
		for (Point p : moveList) {
			map[p.r][p.c] = newPeo;
		}
	}

	private static void bfs(Point point) {
		Queue<Point> q = new LinkedList<>();
		q.offer(point);

		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];

				if (isValid(nr, nc) && !visited[nr][nc] && L <= Math.abs(map[now.r][now.c] - map[nr][nc]) && Math.abs(map[now.r][now.c] - map[nr][nc]) <= R) {
					q.offer(new Point(nr, nc));
					moveList.add(new Point(nr, nc));
					visited[nr][nc] = true;
					total += map[nr][nc];
				}
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

