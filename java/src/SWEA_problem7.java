import java.util.Arrays;
import java.util.PriorityQueue;

class SWEA_problem7 {

    int[] tree; // 세그먼트 트리
    int leafIdx;    // 리프 노드의 시작 인덱스
    int roadSize;   // 도로의 수
    int[] edgeCnt;  // 도로가 가진 간선의 수
    int[] population;   // 각 도시의 인구 수
    // 도로 번호, 이동 시간
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        if (a[1] != b[1]) return b[1] - a[1];
        return a[0] - b[0];
    });

    void init(int N, int mPopulation[]) {
        // 변수 초기화
        roadSize = N - 1;
        int h = 1;
        while (h < roadSize) {
            h *= 2;
        }
        leafIdx = h;
        tree = new int[h * 2];
        edgeCnt = new int[roadSize];
        Arrays.fill(edgeCnt, 1);
        population = mPopulation;
        pq.clear();

        // 세그먼트 트리 리프 노드 초기화
        for (int i = 0; i < N - 1; i++) {
            int time = (population[i] + population[i + 1]) / edgeCnt[i];    // i번 도로의 이동 시간
            tree[leafIdx + i] = time;
            pq.offer(new int[]{i, time});   // PQ에 도로 번호와 이동 시간 추가
        }

        // 세그먼트 트리 완성
        for (int i = leafIdx - 1; i >= 1; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        return;
    }

    int expand(int M) {
        int answer = 0;

        // M번 반복
        for (int i = 1; i <= M; i++) {
            int[] now = pq.poll();  // 가장 우선순위가 높은 도로 꺼내기
            edgeCnt[now[0]]++;  // 간선을 추가
            int time = (population[now[0]] + population[now[0] + 1]) / edgeCnt[now[0]];    // 도로의 이동 시간
            updateTree(now[0], time);   // 세그먼트 트리 갱신
            pq.offer(new int[]{now[0], time});  // pq에 변화 된 값을 다시 추가

            // M번째 값을 리턴
            if (i == M) answer = time;
        }

        return answer;
    }

    int calculate(int mFrom, int mTo) {
        // 트리 인덱스로 변환
        int fromIdx = leafIdx + mFrom;
        int toIdx = leafIdx + mTo;

        // 만약 from이 더 크다면 to와 값을 바꾸기
        if (fromIdx > toIdx) {
            int temp = fromIdx;
            fromIdx = toIdx;
            toIdx = temp;
        }

        // 만약 도로가 하나면 바로 리턴해주기
        toIdx -= 1;
        if (fromIdx == toIdx) return tree[fromIdx];

        // 세그먼트 트리 합을 구해서 리턴해주기
        int sum = 0;
        while (fromIdx <= toIdx) {
            if (fromIdx % 2 == 1) {
                sum += tree[fromIdx];
                fromIdx++;
            }
            if (toIdx % 2 == 0) {
                sum += tree[toIdx];
                toIdx--;
            }
            fromIdx /= 2;
            toIdx /= 2;
        }

        return sum;
    }

    int divide(int mFrom, int mTo, int K) {
        int s = 0;
        int e = 0;
        int answer = 0;

        for (int i = mFrom; i <= mTo; i++) {
            s = Math.max(s, population[i]); // 값 들중 최댓값이 s
            e += population[i]; // 모두 더한 값이 e
        }

        while (s <= e) {
            int m = (s + e) / 2;

            if (getGroup(mFrom, mTo, m, K)) {
                // 만약 m으로 상한 선을 정하고 그룹 수가 K 이하라면?
                e = m - 1;
                answer = m;
            } else {
                // 만약 m으로 상한 선을 정하고 그룹 수가 K 초과라면?
                s = m + 1;
            }
        }

        return answer;
    }

    // m을 상한선으로 잡을 때 그룹의 수가 k이하 인지 확인
    private boolean getGroup(int from, int to, int m, int k) {
        int cnt = 0;
        int sum = 0;

        for (int i = from; i <= to; i++) {
            if (population[i] > m) return false;
            if (sum + population[i] > m) {
                ++cnt;
                sum = population[i];
            } else {
                sum += population[i];
            }
        }
        if (sum > 0) cnt++;

        return cnt <= k;
    }

    // i번 도로를 time으로 갱신하는 메서드
    private void updateTree(int i, int time) {
        int nIdx = leafIdx + i; // 트리에서의 인덱스
        int diff = time - tree[nIdx];   // 변화 값

        while (nIdx >= 1) {
            tree[nIdx] += diff;
            nIdx /= 2;
        }
    }
}