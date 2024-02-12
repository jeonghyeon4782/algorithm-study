import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BJ_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int k = 0; k < t; k++) {
            String order = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> dq = new LinkedList<>();
            boolean reverse = false;
            boolean error = false;
            String s = br.readLine();
            String[] input = s.substring(1, s.length() - 1).split(",");

            if (n != 0) {
                for (int i = 0; i < input.length; i++) {
                    dq.offerLast(Integer.parseInt(input[i]));
                }
            }

            for (int i = 0; i < order.length(); i++) {
                char c = order.charAt(i);
                if (c == 'R') {
                    reverse = !reverse;
                } else {
                    if (dq.isEmpty()) {
                        error = true;
                        break;
                    }
                    if (reverse) {
                        dq.pollLast();
                    } else {
                        dq.pollFirst();
                    }
                }
            }

            if (error) {
                System.out.println("error");
            } else {
                if (dq.isEmpty()) {
                    System.out.println("[]");
                } else {
                    int size = dq.size();
                    StringBuilder sb = new StringBuilder("[");
                    for (int i = 0; i < size - 1; i++) {
                        if (reverse) {
                            sb.append(dq.pollLast()).append(",");
                        } else {
                            sb.append(dq.pollFirst()).append(",");
                        }
                    }
                    if (reverse) {
                        sb.append(dq.pollLast());
                    } else {
                        sb.append(dq.pollFirst());
                    }
                    sb.append("]");
                    System.out.println(sb.toString());
                }

            }
        }
    }
}
