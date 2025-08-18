import java.util.*;

class SWEA_problem5 {

    class Word implements Comparable<Word> {
        String str;
        int cnt;

        public Word(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }

        public int compareTo(Word o) {
            if (this.cnt != o.cnt) return o.cnt - this.cnt;
            return this.str.compareTo(o.str);
        }
    }

    class Node {
        Node parent;
        Node[] children = new Node[26];
        Set<String> searchStrList = new HashSet<>();
    }

    class Tree {
        private final Node root = new Node();

        public void add(char[] arr, int plus) {
            Node now = root;
            String s = charToString(arr);

            if (!StringToId.containsKey(s)) {
                StringToId.put(s, ++wordCnt);
                idToString[wordCnt] = s;
            }

            for (char c : arr) {
                if (c == '\0') break;
                now.searchStrList.add(s);
                if (now.children[c - 'a'] == null) {
                    now.children[c - 'a'] = new Node();
                }
                now = now.children[c - 'a'];
            }

            now.searchStrList.add(s);
            int mId = StringToId.get(s);
            int unionId = find(mId);
            unionCnt[unionId] += plus;
        }

        public int[] recommend(char[] arr) {
            String target = charToString(arr);
            Node now = root;
            int[] answer = new int[2];
            int depth = 0;

            for (char c : arr) {
                if (c == '\0') break;

                Set<String> nowSearch = now.searchStrList;
                treeSet.clear();

                for (String s : nowSearch) {
                    int mId = StringToId.get(s);
                    int unionId = find(mId);
                    int viewCnt = unionCnt[unionId];
                    treeSet.add(new Word(s, viewCnt));
                }

                int rank = 1;
                for (Word w : treeSet) {
                    if (w.str.equals(target)) {
                        answer[0] = depth;
                        answer[1] = rank;

                        int mId = StringToId.get(target);
                        int rootId = find(mId);
                        unionCnt[rootId]++;

                        return answer;
                    }
                    rank++;
                    if (rank > 5) break;
                }

                now = now.children[c - 'a'];
                depth++;
            }

            Set<String> nowSearch = now.searchStrList;
            treeSet.clear();

            for (String s : nowSearch) {
                int mId = StringToId.get(s);
                int unionId = find(mId);
                int viewCnt = unionCnt[unionId];
                treeSet.add(new Word(s, viewCnt));
            }

            int rank = 1;
            for (Word w : treeSet) {
                if (w.str.equals(target)) {
                    answer[0] = depth;
                    answer[1] = rank;

                    int mId = StringToId.get(target);
                    int rootId = find(mId);
                    unionCnt[rootId]++;

                    return answer;
                }
                rank++;
                if (rank > 5) break;
            }

            return answer;
        }

        int relate(char[] arr1, char[] arr2) {
            int mId1 = StringToId.get(charToString(arr1));
            int mId2 = StringToId.get(charToString(arr2));
            int root1 = find(mId1);
            int root2 = find(mId2);
            int size = unionCnt[root1] + unionCnt[root2];
            if (union(root1, root2)) {
                int newRoot = find(root1);
                unionCnt[newRoot] = size;
                return size;
            }
            return -1;
        }

        char[] rank(char[] mPrefix, int mRank, char[] mReturnStr) {
            Node now = root;

            for (char c : mPrefix) {
                if (c == '\0') break;
                now = now.children[c - 'a'];
            }

            Set<String> nowSearch = now.searchStrList;
            treeSet.clear();

            for (String s : nowSearch) {
                int mId = StringToId.get(s);
                int unionId = find(mId);
                int viewCnt = unionCnt[unionId];
                treeSet.add(new Word(s, viewCnt));
            }

            int rank = 1;
            for (Word w : treeSet) {
                if (rank == mRank) {
                    for (int i = 0; i < w.str.length(); i++) {
                        mReturnStr[i] = w.str.charAt(i);
                    }
                    mReturnStr[w.str.length()] = '\0';
                    return mReturnStr;
                }
                rank++;
                if (rank > 5) break;
            }

            return mReturnStr;
        }
    }

    Tree tree;
    int wordCnt;
    Map<String, Integer> StringToId = new HashMap<>();
    String[] idToString;
    int[] parents;
    int[] unionCnt;
    TreeSet<Word> treeSet = new TreeSet<>();

    void init() {
        tree = new Tree();
        wordCnt = 0;
        StringToId.clear();
        idToString = new String[8001];
        parents = new int[8001];
        unionCnt = new int[8001];
        initParent();
        return;
    }

    void search(char mStr[], int mCount) {
        tree.add(mStr, mCount);
        return;
    }

    Solution.Result recommend(char mStr[]) {
        Solution.Result res = new Solution.Result();
        int[] answer = tree.recommend(mStr);
        res.mOrder = answer[0];
        res.mRank = answer[1];
        return res;
    }

    int relate(char mStr1[], char mStr2[]) {
        return tree.relate(mStr1, mStr2);
    }

    void rank(char mPrefix[], int mRank, char mReturnStr[]) {
        mReturnStr = tree.rank(mPrefix, mRank, mReturnStr);
        return;
    }

    void initParent() {
        for (int i = 0; i <= 8000; i++) {
            parents[i] = i;
        }
    }

    int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private String charToString(char[] arr) {
        int len = 0;
        while (len < arr.length && arr[len] != '\0') {
            len++;
        }
        return new String(arr, 0, len);
    }
}
