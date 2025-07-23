import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_SegmentTree_연습1 {

    static long[] sumTree, maxTree, minTree;
    static int treeSize;
    static int leafStartIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        for (int T = 1; T <= test; T++) {
            sb.append("#").append(T).append(" ");
            // 1. setting
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int k = 0;
            int value = 1;
            while (value < n) {
                value *= 2;
                k++;
            }
            treeSize = (int) (2 * Math.pow(2, k));
            leafStartIdx = (int) Math.pow(2, k);
            sumTree = new long[treeSize];
            maxTree = new long[treeSize];
            minTree = new long[treeSize];
            Arrays.fill(minTree, Long.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                sumTree[leafStartIdx + i] = num;
                maxTree[leafStartIdx + i] = num;
                minTree[leafStartIdx + i] = num;
            }

            // 2. 트리 초기 셋팅
            setSumTree();
            setMaxTree();
            setMinTree();

            // 3. 쿼리문 실행
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int order = Integer.parseInt(st.nextToken());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                switch (order) {
                    case 0:
                        changeMaxTree(n1, n2);
                        changeMinTree(n1, n2);
                        break;
                    case 1:
                        sb.append(getMax(n1, n2 - 1) - getMin(n1, n2 - 1)).append(" ");
                        break;
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 합 트리 초기 세팅
    private static void setSumTree() {
        for (int i = leafStartIdx - 1; i >= 1; i--) {
            sumTree[i] = sumTree[i * 2] + sumTree[i * 2 + 1];
        }
    }

    // 최댓값 트리 초기 세팅
    private static void setMaxTree() {
        for (int i = leafStartIdx - 1; i >= 1; i--) {
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
    }

    // 최솟값 트리 초기 세팅
    private static void setMinTree() {
        for (int i = leafStartIdx - 1; i >= 1; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
        }
    }

    // 합 트리 값 변경
    private static void changeSumTree(int idx, int value) {
        int nIdx = leafStartIdx + idx;
        long diff = value - sumTree[idx];
        while (nIdx > 0) {
            sumTree[nIdx] += diff;
            nIdx /= 2;
        }
    }

    // 최댓값 트리 값 변경
    private static void changeMaxTree(int idx, int value) {
        int nIdx = leafStartIdx + idx;
        maxTree[nIdx] = value;
        nIdx /= 2;
        while (nIdx > 0) {
            maxTree[nIdx] = Math.max(maxTree[nIdx * 2], maxTree[nIdx * 2 + 1]);
            nIdx /= 2;
        }
    }

    // 최솟값 트리 값 변경
    private static void changeMinTree(int idx, int value) {
        int nIdx = leafStartIdx + idx;
        minTree[nIdx] = value;
        nIdx /= 2;
        while (nIdx > 0) {
            minTree[nIdx] = Math.min(minTree[nIdx * 2], minTree[nIdx * 2 + 1]);
            nIdx /= 2;
        }
    }

    // 구간 합 구하기
    private static long getSum(int s, int e) {
        int ns = leafStartIdx + s;
        int ne = leafStartIdx + e;
        long answer = 0;
        while (ns <= ne) {
            if (ns % 2 == 1) {
                answer += sumTree[ns];
                ++ns;
            }
            if (ne % 2 == 0) {
                answer += sumTree[ne];
                --ne;
            }
            ns /= 2;
            ne /= 2;
        }
        return answer;
    }

    // 구간 최댓값 구하기
    private static long getMax(int s, int e) {
        int ns = leafStartIdx + s;
        int ne = leafStartIdx + e;
        long answer = 0;
        while (ns <= ne) {
            if (ns % 2 == 1) {
                answer = Math.max(answer, maxTree[ns]);
                ++ns;
            }
            if (ne % 2 == 0) {
                answer = Math.max(answer, maxTree[ne]);
                --ne;
            }
            ns /= 2;
            ne /= 2;
        }
        return answer;
    }

    // 구간 최솟값 구하기
    private static long getMin(int s, int e) {
        int ns = leafStartIdx + s;
        int ne = leafStartIdx + e;
        long answer = Long.MAX_VALUE;
        while (ns <= ne) {
            if (ns % 2 == 1) {
                answer = Math.min(answer, minTree[ns]);
                ++ns;
            }
            if (ne % 2 == 0) {
                answer = Math.min(answer, minTree[ne]);
                --ne;
            }
            ns /= 2;
            ne /= 2;
        }
        return answer;
    }

    // 현재 트리 상태 출력
    private static void showTree() {
        System.out.println("===================== 현재 트리 상태 =====================");
        System.out.println("합 트리");
        System.out.println(Arrays.toString(sumTree));
        System.out.println("최대 트리");
        System.out.println(Arrays.toString(maxTree));
        System.out.println("최소 트리");
        System.out.println(Arrays.toString(minTree));
    }
}
