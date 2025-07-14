import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_수열편집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, L;
        List<Integer> list;

        int testcase = Integer.parseInt(br.readLine());
        for (int T = 1; T <= testcase; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String c = st.nextToken();
                int idx = 0;
                int value = 0;
                switch (c) {
                    case "I":
                        idx = Integer.parseInt(st.nextToken());
                        value = Integer.parseInt(st.nextToken());
                        list.add(idx, value);
                        break;
                    case "D":
                        idx = Integer.parseInt(st.nextToken());
                        list.remove(idx);
                        break;
                    default:
                        idx = Integer.parseInt(st.nextToken());
                        value = Integer.parseInt(st.nextToken());
                        list.set(idx, value);
                        break;
                }
            }

            try {
                System.out.printf("#%d %d\n", T, list.get(L));
            } catch (IndexOutOfBoundsException e) {
                System.out.printf("#%d %d\n", T, -1);
            }
        }

    }
}
