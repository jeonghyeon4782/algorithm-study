import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 문자 배열 크기
        int m = Integer.parseInt(st.nextToken()); // 비밀번호 길이 제한
        String s = br.readLine();
        char[] dna = s.toCharArray(); // 문자 배열
        st = new StringTokenizer(br.readLine());
        int count = 0;
        int[] acgt = new int[4];    // A C G T 문자의 갯수 제한
        int[] cnt = new int[4];     // 비밀번호에 대한 문자 수 배열
        boolean chk = true;
        
        for (int i = 0; i < 4; i++) {
            acgt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            if (dna[i] == 'A') {cnt[0]++;}
            else if (dna[i] == 'C') {cnt[1]++;}
            else if (dna[i] == 'G') {cnt[2]++;}
            else if (dna[i] == 'T') {cnt[3]++;}
        }

        for (int j = 0; j < 4; j++) {
            if (cnt[j] < acgt[j]) {
                chk = false;
            }
        }

        if (chk == true) {
            count++;
        }

        for (int i = m; i < n; i++) {
            chk = true;
            if (dna[i - m] == 'A') {cnt[0]--;}
            else if (dna[i - m] == 'C') {cnt[1]--;}
            else if (dna[i - m] == 'G') {cnt[2]--;}
            else if (dna[i - m] == 'T') {cnt[3]--;}

            if (dna[i] == 'A') {cnt[0]++;}
            else if (dna[i] == 'C') {cnt[1]++;}
            else if (dna[i] == 'G') {cnt[2]++;}
            else if (dna[i] == 'T') {cnt[3]++;}

            for (int j = 0; j < 4; j++) {
                if (cnt[j] < acgt[j]) {
                    chk = false;
                }
            }

            if (chk == true) {
                count++;
            }
        }
        System.out.println(count);
    }
}
