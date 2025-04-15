import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BJ_2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            strList.add(str);
        }
        strList = strList.stream().distinct().collect(Collectors.toList());
        int firstIdx = -1;
        int secondIdx = -1;
        int maxCnt = 0;
        for (int i = 0; i < strList.size(); i++) {
            for (int j = i + 1; j < strList.size(); j++) {
                int cnt = 0;
                int firstLen = strList.get(i).length();
                int secondLen = strList.get(j).length();
                for (int k = 0; k < Math.min(firstLen, secondLen); k++) {
                    if (strList.get(i).charAt(k) != strList.get(j).charAt(k)) {
                        break;
                    }
                    ++cnt;
                }
                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    firstIdx = i;
                    secondIdx = j;
                }
            }
        }
        System.out.println(strList.get(firstIdx));
        System.out.println(strList.get(secondIdx));
    }
}
