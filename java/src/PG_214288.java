import java.util.*;

class PG_214288 {

    int[] input;    // 각 유형의 상담원 수
    PriorityQueue<Integer> pqList[];    // 각 유형의 손님 상태
    int K, N;   // K : 유형 수, N : 상담원 수
    int answer;

    public int solution(int k, int n, int[][] reqs) {

        // 변수 초기화
        input = new int[k];
        K = k;
        N = n;
        answer = Integer.MAX_VALUE;
        pqList = new PriorityQueue[k];
        for (int i = 0; i < k; i++) {
            pqList[i] = new PriorityQueue<>();
        }

        // 모든 조합 만들기
        comb(0, n, reqs);

        return answer;
    }

    public void comb(int cnt, int remain, int[][] reqs) {

        // 기저 조건
        if (cnt == K) {
            if (remain == 0) {

                // 총 대기시간
                int wait = 0;

                // pq 초기화
                for (int i = 0; i < K; i++) {
                    pqList[i].clear();
                }

                // 손님 리스트 시작
                for (int[] req : reqs) {
                    int start = req[0]; // 요청 시간
                    int dur = req[1];   // 진행 시간
                    int type = req[2] - 1;  // 타입

                    // 해당 유형의 pq에서 끝 시간이 start 이하인 요소 제거
                    while (!pqList[type].isEmpty() && pqList[type].peek() <= start) {
                        pqList[type].poll();
                    }

                    // 만약 pq가 꽉 찼다면?
                    if (pqList[type].size() >= input[type]) {
                        // 가장 빨리 끝나는 사람 꺼내기
                        int end = pqList[type].poll();
                        wait += (end - start);
                        pqList[type].offer(end + dur);
                    }

                    // 자리가 있다면?
                    else {
                        pqList[type].offer(start + dur);
                    }
                }

                answer = Math.min(answer, wait);
            }
            return;
        }

        // 유도 조건
        for (int i = 1; i <= remain; i++) {
            input[cnt] = i;
            comb(cnt + 1, remain - i, reqs);
        }
    }
}