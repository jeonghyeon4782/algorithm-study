import java.io.*;
import java.util.*;

public class ST_6247 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cars);
        for (int i = 0; i < q; i++) {
            int target = Integer.parseInt(br.readLine());
            int idx = binarySearch(cars, target);
            if (idx == -1 || idx == 0 || idx == cars.length - 1) {
                System.out.println(0);
            } else {
                int l = idx;
                int r = cars.length - 1 - idx;
                System.out.println(l * r);
            }
        }
    }

    public static int binarySearch(int[] cars, int target) {
        int s = 0;
        int e = cars.length - 1;

        while (s <= e) {
            int m = (s + e) / 2;

            if (cars[m] == target) return m;

            if (cars[m] > target) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        return -1;
    }
}