import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5658 {
    static int N, K;
    static char[] nums;
    static Set<Integer> numsList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            nums = new char[N];
            numsList = new HashSet<>();
            String s = br.readLine();
            for (int i = 0; i < s.length(); i++) {
                nums[i] = s.charAt(i);
            }
            for (int i = 0; i < N / 4 + 1; i++) {
                if (i != 0) {
                    char temp = nums[nums.length - 1];
                    for (int j = nums.length - 1; j > 0; j--) {
                        nums[j] = nums[j - 1];
                    }
                    nums[0] = temp;
                }
                int idx = 0;
                for (int j = 0; j < nums.length; j += nums.length / 4) {
                    numsList.add(Integer.parseInt(String.valueOf(nums).substring(j, j + (nums.length / 4)), 16));
                }
            }
            ArrayList<Integer> numsArrayList = new ArrayList<>(numsList);
            Collections.sort(numsArrayList, Collections.reverseOrder());
            System.out.printf("#%d %d\n", test, numsArrayList.get(K - 1));
        }
    }
}