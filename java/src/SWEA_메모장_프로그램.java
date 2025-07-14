import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class SWEA_메모장_프로그램 {

    ArrayList<LinkedList<Character>> memoList; // 메모장 상태
    int R, C;   // 현재 커서의 위치
    ArrayList<int[]> alphaCnt;   // 각 행의 알파벳 수
    int w, h;  // 메모장의 너비
    int lastH;  // 글자가 있는 마지막 행

    void init(int H, int W, char mStr[]) {
        R = 0;
        C = 0;
        w = W;
        h = H;
        memoList = new ArrayList<>();
        alphaCnt = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            memoList.add(new LinkedList<>());
            alphaCnt.add(new int[26]);
        }
        int idx = 0;
        boolean flag = false;
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (mStr[idx] == '\0') {
                    flag = true;
                    break;
                }
                char ch = mStr[idx++];
                memoList.get(r).add(ch);
                alphaCnt.get(r)[ch - 'a']++;
            }
            if (flag) {
                lastH = r;
                return;
            }
        }
        lastH = H - 1;
    }

    void insert(char mChar) {
        memoList.get(R).add(C, mChar);
        alphaCnt.get(R)[mChar - 'a']++;

        // 만약 사이즈가 넘친다면?
        if (memoList.get(R).size() > w) {
            char value = memoList.get(R).removeLast();
            alphaCnt.get(R)[value - 'a']--;
            int idx = R + 1;
            while (true) {
                memoList.get(idx).addFirst(value);
                alphaCnt.get(idx)[value - 'a']++;
                if (memoList.get(idx).size() <= w) break;
                value = memoList.get(idx).removeLast();
                alphaCnt.get(idx)[value - 'a']--;
                ++idx;
            }
            lastH = Math.max(lastH, idx);
        }
        C++;
        if (C >= w) {
            C = 0;
            R++;
        }
    }

    char moveCursor(int mRow, int mCol) {
        mRow -= 1;
        mCol -= 1;
        if (mRow > lastH || memoList.get(mRow).size() < mCol) {
            R = lastH;
            C = memoList.get(lastH).size();
            return '$';
        }
        R = mRow;
        C = mCol;
        return memoList.get(R).get(C);
    }

    int countCharacter(char mChar) {
        int answer = 0;
        LinkedList<Character> list = memoList.get(R);
        ListIterator<Character> iterator = list.listIterator(C);
        while (iterator.hasNext()) {
            char c = iterator.next();
            if (c == mChar) ++answer;
        }
        for (int r = R + 1; r <= lastH; r++) {
            answer += alphaCnt.get(r)[mChar - 'a'];
        }
        return answer;
    }
}