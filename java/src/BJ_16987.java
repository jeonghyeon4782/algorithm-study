import java.util.Scanner;

public class BJ_16987 {
    static class Egg {
        int s, w;
        boolean flag;

        Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
        @Override
        public String toString() {
            return "Egg [s=" + s + ", w=" + w + ", flag=" + flag + "]";
        }
    }

    static int n;
    static Egg[] eggs;
    static int[] arr;
    static int maxCnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        eggs = new Egg[n];
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            eggs[i] = new Egg(sc.nextInt(), sc.nextInt());
        }
        dfs(0);
        System.out.println(maxCnt);
    }

    private static void dfs(int idx) {

        int cnt = 0;
        for (Egg egg : eggs) {
            if (egg.flag) cnt++;
        }

        if (idx == n) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        if (eggs[idx].flag || cnt == eggs.length - 1) {
            dfs(idx + 1);
        } else {
            for (int i = 0; i < n; i++) {
                if (idx == i || eggs[i].flag)
                    continue;
                beatingEgg(idx, i);
                dfs(idx + 1);
                revert(idx, i);
            }
        }
    }

    private static void beatingEgg(int x1, int x2) {
        Egg egg1 = eggs[x1];
        Egg egg2 = eggs[x2];

        egg1.s -= egg2.w;
        egg2.s -= egg1.w;

        if (egg1.s <= 0)
            egg1.flag = true;
        if (egg2.s <= 0)
            egg2.flag = true;
    }

    private static void revert(int x1, int x2) {
        Egg egg1 = eggs[x1];
        Egg egg2 = eggs[x2];

        egg1.s += egg2.w;
        egg2.s += egg1.w;

        if (egg1.s > 0)
            egg1.flag = false;
        if (egg2.s > 0)
            egg2.flag = false;
    }
}
