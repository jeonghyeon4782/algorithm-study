import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class SWEA_SocialMedia {

    class Post {
        int pID;
        int uID;
        int timestamp;
        int likeCnt = 0;

        public Post(int pID, int uID, int timestamp) {
            this.pID = pID;
            this.uID = uID;
            this.timestamp = timestamp;
        }
    }

    class User {
        ArrayList<Post> postList = new ArrayList<>();
        ArrayList<User> followList = new ArrayList<>();
        PriorityQueue<Post> recentPostList = new PriorityQueue<>((a, b) -> {
            if (a.likeCnt != b.likeCnt) return b.likeCnt - a.likeCnt;
            return b.timestamp - a.timestamp;
        });
        int idx = 0;
    }

    static User[] userArr;
    static Post[] postArr = new Post[100001];

    public void init(int N) {
        userArr = new User[N + 1];
        for (int i = 1; i <= N; i++) {
            userArr[i] = new User();
            userArr[i].followList.add(userArr[i]);
        }
    }

    public void follow(int uID1, int uID2, int timestamp) {
        userArr[uID1].followList.add(userArr[uID2]);
    }

    public void makePost(int uID, int pID, int timestamp) {
        postArr[pID] = new Post(pID, uID, timestamp);
        userArr[uID].postList.add(postArr[pID]);
        userArr[uID].recentPostList.offer(postArr[pID]);
    }

    public void like(int pID, int timestamp) {
        postArr[pID].likeCnt++;
        User writer = userArr[postArr[pID].uID];
        writer.recentPostList.remove(postArr[pID]);
        writer.recentPostList.offer(postArr[pID]);
    }

    public int[] getFeed(int uID, int timestamp, int[] pIDList) {
        PriorityQueue<Post> pq1 = new PriorityQueue<>((a, b) -> {
            if (a.likeCnt != b.likeCnt) return b.likeCnt - a.likeCnt;
            return b.timestamp - a.timestamp;
        });

        PriorityQueue<Post> pq2 = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);

        for (User user : userArr[uID].followList) {
            updateRecentList(user, timestamp);

            int cnt = 0;
            HashSet<Integer> added = new HashSet<>();
            List<Post> tempRecent = new ArrayList<>();

            for (int i = 0; i < 10 && !user.recentPostList.isEmpty(); i++) {
                Post post = user.recentPostList.poll();
                tempRecent.add(post);
                pq1.offer(post);
                added.add(post.pID);
                cnt++;
            }

            user.recentPostList.addAll(tempRecent);

            if (cnt < 10) {
                for (int i = user.postList.size() - 1; i >= 0 && cnt < 10; i--) {
                    Post post = user.postList.get(i);
                    if (timestamp - post.timestamp > 1000 && !added.contains(post.pID)) {
                        pq2.offer(post);
                        cnt++;
                    }
                }
            }
        }

        int idx = 0;
        while (idx < 10 && !pq1.isEmpty()) {
            pIDList[idx++] = pq1.poll().pID;
        }
        while (idx < 10 && !pq2.isEmpty()) {
            pIDList[idx++] = pq2.poll().pID;
        }

        return pIDList;
    }

    private void updateRecentList(User user, int timestamp) {
        for (int i = user.idx; i < user.postList.size(); i++) {
            Post now = user.postList.get(i);
            if (timestamp - now.timestamp > 1000) {
                user.recentPostList.remove(now);
                user.idx = i + 1;
            } else {
                break;
            }
        }
    }
}
