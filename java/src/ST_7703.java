import java.io.*;
import java.util.*;

public class ST_7703 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String t = st.nextToken();

            int index1 = s.indexOf('x');
            int index2 = s.indexOf('X');
            int index = 0;

            if (index1 != -1) {
                index = index1;
            }
            if (index2 != -1) {
                index = index2;
            }

            char c = t.charAt(index);

            if (97 <= c && c <= 122) {
                System.out.print((char) (c - 32));
            } else {
                System.out.print(c);
            }
        }

    }
}