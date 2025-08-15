import java.util.HashMap;
import java.util.Map;

class SWEA_problem2 {

    int[] wall; // 벽 상태
    Map<Integer, Integer> map = new HashMap<>();    // 매핑 맵
    int wallCnt;    // 벽의 길이

    void init() {
        // 변수 초기화
        wall = new int[100001];
        map.clear();
        wallCnt = 0;
    }

    void makeWall(int mHeights[]) {
        // 벽 추가
        for (int i = 0; i < 5; i++) {
            wall[++wallCnt] = mHeights[i];
        }

        // 맵 추가
        int s = Math.max(1, wallCnt - 8);
        int e = wallCnt - 4;

        for (int i = s; i <= e; i++) {
            int hash = hashing(i, wall, false);
            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }
    }

    int matchPiece(int mHeights[]) {
        int answer = 0;

        // 만약 현재 벽 길이가 0이면 -1 리턴
        if (wallCnt <= 0) return -1;

        // 조각 뒤집기
        int temp = mHeights[0];
        mHeights[0] = mHeights[4];
        mHeights[4] = temp;
        temp = mHeights[1];
        mHeights[1] = mHeights[3];
        mHeights[3] = temp;

        // 조각을 해싱
        int pieceHash = hashing(0, mHeights, true);

        // 만약 매핑이 안된다면 -1 리턴
        if (!map.containsKey(pieceHash) || map.get(pieceHash) <= 0) return -1;

        // 오른쪽 부터 매핑 탐색
        for (int i = wallCnt - 4; i >= 1; i--) {
            int findHash = hashing(i, wall, false);

            // 만약 매핑이 된다면?
            if (pieceHash == findHash) {
                answer = i;

                // map에서 해당 부분 -1하기
                int s = Math.max(1, answer - 4);
                int e = Math.min(answer + 4, wallCnt - 4);
                for (int j = s; j <= e; j++) {
                    int removeHash = hashing(j, wall, false);
                    map.put(removeHash, Math.max(map.get(removeHash) - 1, 0));
                }

                // 벽 이동 시키기
                for (int j = answer + 5; j <= wallCnt; j++) {
                    wall[j - 5] = wall[j];
                }
                wallCnt -= 5;

                // // map에서 해당 부분 +1 하기
                s = Math.max(1, answer - 4);
                e = Math.min(answer - 1, wallCnt);
                for (int j = s; j <= e; j++) {
                    int addHash = hashing(j, wall, false);
                    map.put(addHash, map.getOrDefault(addHash, 0) + 1);
                }

                return answer;
            }
        }

        return -1;
    }

    int hashing(int idx, int[] arr, boolean flag) {
        int[] hashArr = new int[4];
        int hash = 0;
        int base = 21;

        for (int i = 0; i <= 3; i++) {
            hashArr[i] = arr[idx + i] - arr[idx + i + 1];
            if (flag) hashArr[i] = -hashArr[i];
            hashArr[i] += 10;
        }

        for (int i = 0; i < 4; i++) {
            hash = hash * base + hashArr[i];
        }

        return hash;
    }
}
