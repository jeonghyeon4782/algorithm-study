import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long result = 0;
        while (st.hasMoreTokens()) {
            result += stringToInt(st.nextToken());
        }
        System.out.println(result);
    }

    public static long stringToInt(String s) {
        return Long.parseLong(s);
    }
}