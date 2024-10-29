import java.io.*;

public class ST_6280 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int t = 2;

        for (int i = 0; i < n; i++) {
            t = t * 2 - 1;
        }

        System.out.println((int)Math.pow(t, 2));
    }
}