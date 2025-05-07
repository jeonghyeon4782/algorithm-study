import java.util.*;

class PG_64064 {

    int answer = 0;
    List<List<String>> possibleList;
    Set<Set<String>> setList;
    int userSize, banSize;

    public int solution(String[] user_id, String[] banned_id) {

        // 1. setting
        userSize = user_id.length;
        banSize = banned_id.length;
        possibleList = new ArrayList<>();
        setList = new HashSet<>();
        for (int i = 0; i < banSize; i++) {
            possibleList.add(new ArrayList<>());
        }

        // 2. 가능한 불량 아이디 찾기
        for (int i = 0; i < banSize; i++) {
            for (int j = 0; j < userSize; j++) {
                if (banned_id[i].length() != user_id[j].length()) continue;
                boolean flag = true;
                for (int k = 0; k < banned_id[i].length(); k++) {
                    if (banned_id[i].charAt(k) == '*') continue;
                    if (banned_id[i].charAt(k) != user_id[j].charAt(k)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    possibleList.get(i).add(user_id[j]);
                }
            }
        }

        // 3. dfs(백트래킹)
        dfs(0, new HashSet<>());

        for (List<String> list : possibleList) {
            System.out.println(list);
        }

        return answer;
    }

    public void dfs(int depth, Set<String> set) {

        if (depth == banSize) {
            if (!setList.contains(set)) {
                setList.add(new HashSet<>(set));
                ++answer;
            }
            return;
        }

        for (String s : possibleList.get(depth)) {
            if (!set.contains(s)) {
                set.add(s);
                dfs(depth + 1, set);
                set.remove(s);
            }
        }
    }
}