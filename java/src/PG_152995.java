import java.util.*;

class PG_152995 {

    List<Score> scoreList;

    class Score implements Comparable<Score> {
        int a, b, idx;

        public Score(int a, int b, int idx) {
            this.a = a;
            this.b = b;
            this.idx = idx;
        }

        public int compareTo(Score o) {
            if (this.a != o.a) return Integer.compare(o.a, this.a);
            return Integer.compare(this.b, o.b);
        }
    }

    public int solution(int[][] scores) {
        scoreList = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            scoreList.add(new Score(scores[i][0], scores[i][1], i));
        }
        Collections.sort(scoreList);
        int maxA = scoreList.get(0).a;
        int maxB = scoreList.get(0).b;
        Iterator<Score> iter = scoreList.iterator();
        while (iter.hasNext()) {
            Score now = iter.next();
            if (now.a < maxA && now.b < maxB) {
                maxB = Math.max(maxB, now.b);
                if (now.idx == 0) return -1;
                iter.remove();
                continue;
            }
            maxB = Math.max(maxB, now.b);
        }
        idxSort(scoreList);
        int target = scoreList.get(0).a + scoreList.get(0).b;
        int cnt = 0;
        for (int i = 1; i < scoreList.size(); i++) {
            int sum = scoreList.get(i).a + scoreList.get(i).b;
            if (target < sum) ++cnt;
        }

        return cnt + 1;
    }

    public void idxSort(List<Score> list) {
        Collections.sort(list, (a, b) -> {
            return Integer.compare(a.idx, b.idx);
        });
    }
}