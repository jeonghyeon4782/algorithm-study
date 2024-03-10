import java.util.*;

public class PG_42579 {
    static class music implements Comparable<music>{
        int idx, playCnt;
        public music(int idx, int playCnt) {
            this.idx = idx;
            this.playCnt = playCnt;
        }
        @Override
        public int compareTo(music o) {
            if (this.playCnt != o.playCnt) return o.playCnt - this.playCnt;
            return this.idx - o.idx;
        }
    }

    public static void main(String[] args) {
        String[] genres = {"jazz", "rap", "hiphop","jazz","jazz", "hiphop"};
        int[] plays = {100,1000,50,100,50,500};
        System.out.println(Arrays.toString(solution(genres, plays)));
    }

    private static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> musicCnt = new HashMap<>();
        Map<String, List<music>> musics = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if(!musicCnt.containsKey(genres[i])) {
                musicCnt.put(genres[i], plays[i]);
            } else {
                int newCnt = musicCnt.get(genres[i]) + plays[i];
                musicCnt.put(genres[i], newCnt);
            }
            if (!musics.containsKey(genres[i])) {
                musics.put(genres[i], new ArrayList<>());
            }
            musics.get(genres[i]).add(new music(i, plays[i]));
        }

        List<String> keySet = new ArrayList<>(musicCnt.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return musicCnt.get(o2).compareTo(musicCnt.get(o1));
            }
        });
        for (String g : keySet) {
            Collections.sort(musics.get(g));
            if (musics.get(g).size() == 1) {
                result.add(musics.get(g).get(0).idx);
            } else {
                for (int i = 0; i < 2; i++) {
                    result.add(musics.get(g).get(i).idx);
                }
            }
        }
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
