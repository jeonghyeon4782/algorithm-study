import java.util.*;

class PG_340210 {

    static int minN;
    static List<Integer> possibleN;
    static String[] answer;

    public String[] solution(String[] expressions) {

        // 최소 진법 찾기
        searchMinN(expressions);
        System.out.println("최소 진법은 " + minN + "입니다.");

        // 가능한 진법 찾기
        searchPossibleN(expressions);
        System.out.print("가능한 진법은 ");
        for (int n : possibleN) {
            System.out.print(n + " ");
        }

        // X값 계산하여 result 만들기
        solve(expressions);

        return answer;
    }

    // X값 계산하여 result 만들기
    public void solve(String[] expressions) {
        int idx = 0;
        for (String s : expressions) {
            if (s.charAt(s.length() - 1) == 'X') {
                String[] expression = s.split(" ");
                String num = calculation(expression[0], expression[1], expression[2], possibleN.get(0));
                boolean flag = false;   // 다르면 true;
                for (int i = 1 ; i < possibleN.size(); i++) {
                    int n = possibleN.get(i);
                    String nNum = calculation(expression[0], expression[1], expression[2], n);
                    if (!num.equals(nNum)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    answer[idx] = expression[0] + " " + expression[1] + " " + expression[2] + " = " + "?";
                } else {
                    answer[idx] = expression[0] + " " + expression[1] + " " + expression[2] + " = " + num;
                }
                idx++;
            }
        }
    }

    // 가능한 진법 찾기
    public void searchPossibleN(String[] expressions) {
        possibleN = new ArrayList<>();  // 가능한 진법만 담는 리스트
        int[] nCount = new int[10]; // 1부터 9진법까지 가능한 갯수
        int nonXCount = 0;  // X가 아닌 수식의 갯수
        for (String s : expressions) {
            if (s.charAt(s.length() - 1) == 'X') continue;
            String[] expression = s.split(" ");
            nonXCount++;
            for (int i = minN; i < 10; i++) {
                if(calculation(expression[0], expression[1],
                        expression[2], i).equals(expression[4])) {
                    nCount[i]++;
                }
            }
        }
        for (int i = minN; i <= 9; i++) {
            if (nCount[i] == nonXCount) {
                possibleN.add(i);
            }
        }
        answer = new String[expressions.length - nonXCount];
    }

    // 진법 계산기
    public String calculation(String a, String op, String b, int n) {
        int nA = Integer.parseInt(a, n);
        int nB = Integer.parseInt(b, n);
        int sum = 0;
        if (op.equals("+")) {
            sum = nA + nB;
        } else {
            sum = nA - nB;
        }
        String answer = Integer.toString(sum, n);
        return answer;
    }

    // 최소 진법 찾기
    public void searchMinN(String[] expressions) {
        minN = Integer.MIN_VALUE;
        for (String s : expressions) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '+' || c == '-' || c == 'X' || c == ' ' || c == '=') continue;
                minN = Math.max(minN, c - '0');
            }
        }
        minN += 1;
    }

}