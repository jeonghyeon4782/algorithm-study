import java.util.*;

class SWEA_problem4 {

    class Part {
        int e;  // 끝 주소
        int mId;    // 파일 아이디

        public Part(int e, int mId) {
            this.e = e;
            this.mId = mId;
        }
    }

    class Piece {
        int s;  // 시작 주소
        int e;  // 끝 주소

        public Piece(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    TreeMap<Integer, Integer> empty = new TreeMap<>();  // 메모리의 빈 곳
    TreeMap<Integer, Part> use = new TreeMap<>();   // 메모리의 사용 중인 곳
    Map<Integer, List<Piece>> map = new HashMap<>();    //  key : mId, value : 파일 조각
    List<Piece> temp = new ArrayList<>();   // 파일 추가 시 사용 될 조각 리스트
    Set<Integer> set = new HashSet<>(); // count 메서드에서 아이디 중복에 사용 될 set

    public void init(int N) {
        // 변수 초기화
        empty.clear();
        use.clear();
        map.clear();
        empty.put(1, N);
        return;
    }

    public int add(int mId, int mSize) {
        int emptySize = 0;  // 빈 공간

        for (Map.Entry<Integer, Integer> entry : empty.entrySet()) {
            int s = entry.getKey();
            int e = entry.getValue();
            int size = e - s + 1;
            emptySize += size;
            if (emptySize >= mSize) break;
        }

        // 파일 추가 불가능 -1 리턴
        if (emptySize < mSize) return -1;

        int remain = mSize;
        temp.clear();
        map.computeIfAbsent(mId, key -> new ArrayList<>());
        int answer = empty.firstKey();

        // 파일 추가
        Iterator<Map.Entry<Integer, Integer>> iter = empty.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            int s = entry.getKey();
            int e = entry.getValue();
            int size = e - s + 1;

            if (size > remain) {
                // 빈 칸을 쪼개고 일부만 사용
                temp.add(new Piece(s + remain, e));
                use.put(s, new Part(s + remain - 1, mId));
                map.get(mId).add(new Piece(s, s + remain - 1));
            } else {
                // 빈 칸 전부 사용
                use.put(s, new Part(e, mId));
                map.get(mId).add(new Piece(s, e));
            }

            iter.remove();
            remain -= size;
            if (remain <= 0) break;
        }

        // 빈 공간 추가
        for (Piece piece : temp) {
            empty.put(piece.s, piece.e);
        }

        return answer;
    }

    public int remove(int mId) {

        if (!map.containsKey(mId)) return 0;

        int answer = map.get(mId).size();
        List<Piece> now = map.get(mId);

        for (Piece piece : now) {
            addEmpty(piece.s, piece.e);
            use.remove(piece.s);
        }

        now.clear();
        return answer;
    }

    public int count(int mStart, int mEnd) {
        set.clear();

        Map.Entry<Integer, Part> floor = use.floorEntry(mStart);
        if (floor != null && floor.getValue().e >= mStart) {
            set.add(floor.getValue().mId);
        }

        for (Map.Entry<Integer, Part> entry : use.subMap(mStart, true, mEnd, true).entrySet()) {
            set.add(entry.getValue().mId);
        }

        return set.size();
    }


    // 빈 공간을 앞 뒤를 찾아 압축하여 넣는 메서드
    private void addEmpty(int s, int e) {
        Map.Entry<Integer, Integer> floor = empty.floorEntry(s);
        Map.Entry<Integer, Integer> celling = empty.ceilingEntry(s);

        if (floor != null && floor.getValue() + 1 == s) {
            s = floor.getKey();
            empty.remove(floor.getKey());
        }

        if (celling != null && celling.getKey() - 1 == e) {
            e = celling.getValue();
            empty.remove(celling.getKey());
        }

        empty.put(s, e);
    }
}