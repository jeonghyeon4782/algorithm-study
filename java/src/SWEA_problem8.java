import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

class SWEA_problem8 {

    int studentSize = 0;
    int[][] student; // [인덱스][학년, 성별, 점수]
    Map<Integer, Integer> mapper = new HashMap<>(); // key : ID, value : 인덱스
    TreeMap<Integer, TreeSet<Integer>>[] treeMap;   // 학년+성별 별로 점수 → ID 집합

    public void init() {
        studentSize = 0;
        student = new int[20001][3];
        mapper.clear();
        treeMap = new TreeMap[6];
        for (int i = 0; i < 6; i++) {
            treeMap[i] = new TreeMap<>();
        }
    }

    public int add(int mId, int mGrade, char mGender[], int mScore) {
        // 만약 동일한 ID가 이미 존재하면 먼저 삭제
        if (mapper.containsKey(mId)) {
            remove(mId);
        }

        // ID → 인덱스 매핑
        mapper.put(mId, ++studentSize);
        int idx = mapper.get(mId);

        // 성별 int 변환
        int gender = getGender(mGender);

        // 학생 추가
        student[idx][0] = mGrade;
        student[idx][1] = gender;
        student[idx][2] = mScore;

        // treeMap에 추가
        int mapId = getMapId(mGrade, gender);
        treeMap[mapId].computeIfAbsent(mScore, k -> new TreeSet<>()).add(mId);

        // 조건에 맞으면서 가장 점수가 높은 treeSet
        TreeSet<Integer> top = treeMap[mapId].lastEntry().getValue();

        // ID가 가장 큰 값을 반환
        return top.last();
    }

    public int remove(int mId) {
        // 없는 학생이면 0 반환
        if (!mapper.containsKey(mId)) {
            return 0;
        }

        // id로 학생 정보 가져오기
        int idx = mapper.get(mId);
        int grade = student[idx][0];
        int gender = student[idx][1];
        int score = student[idx][2];

        // mapper에서 제거
        mapper.remove(mId);

        // student 배열 초기화 (안 해도 동작엔 문제 없지만 안전하게)
        student[idx][0] = student[idx][1] = student[idx][2] = 0;

        // treeMap에서 제거
        int mapId = getMapId(grade, gender);
        TreeSet<Integer> set = treeMap[mapId].get(score);
        if (set != null) {
            set.remove(mId);
            if (set.isEmpty()) {
                treeMap[mapId].remove(score);
            }
        }

        // 남은 학생이 없으면 0 반환
        if (treeMap[mapId].isEmpty()) {
            return 0;
        }

        // 조건에 맞으면서 가장 점수가 낮은 treeSet
        TreeSet<Integer> bottom = treeMap[mapId].firstEntry().getValue();
        if (bottom.isEmpty()) return 0;

        return bottom.first();
    }

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        int answerId = 0;
        int answerScore = Integer.MAX_VALUE;

        // 성별 배열 변환
        int[] genderArr = new int[mGenderCnt];
        for (int i = 0; i < mGenderCnt; i++) {
            genderArr[i] = getGender(mGender[i]);
        }

        // 모든 학년과 성별 조합 확인
        for (int g = 0; g < mGradeCnt; g++) {
            for (int k = 0; k < mGenderCnt; k++) {
                int mapId = getMapId(mGrade[g], genderArr[k]);
                if (treeMap[mapId].isEmpty()) continue;

                // mScore 이상인 학생 중 최소 점수 찾기
                Map.Entry<Integer, TreeSet<Integer>> entry = treeMap[mapId].ceilingEntry(mScore);
                if (entry == null) continue;

                int curScore = entry.getKey();
                int curId = entry.getValue().first(); // 가장 작은 ID

                if (curScore < answerScore || (curScore == answerScore && curId < answerId)) {
                    answerScore = curScore;
                    answerId = curId;
                }
            }
        }
        return answerId;
    }

    // 학년+성별을 통해 mapId 리턴
    public int getMapId(int grade, int gender) {
        if (grade == 1) return gender == 1 ? 0 : 1;
        if (grade == 2) return gender == 1 ? 2 : 3;
        return gender == 1 ? 4 : 5; // grade == 3
    }

    StringBuilder sb = new StringBuilder();

    // 성별 문자열을 int로 변환
    private int getGender(char[] mGender) {
        sb.setLength(0);
        for (char c : mGender) {
            if (c == '\0') break;
            sb.append(c);
        }
        return sb.toString().equals("male") ? 1 : 2;
    }
}
