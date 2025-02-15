import java.util.*;

class PG_250137 {

    class Attack {
        int time, damage;

        public Attack(int time, int damage) {
            this.time = time;
            this.damage = damage;
        }
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        Queue<Attack> attackQueue = new LinkedList<>();
        for (int i = 0; i < attacks.length; i++) {
            attackQueue.offer(new Attack(attacks[i][0], attacks[i][1]));
        }
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        int cnt = 0;
        int time = 0;
        int maxHealth = health;

        while (!attackQueue.isEmpty()) {
            ++time;

            if (attackQueue.peek().time == time) {
                Attack attack = attackQueue.poll();
                health -= attack.damage;
                cnt = 0;

                if (health <= 0) {
                    return -1;
                }

                continue;
            }

            ++cnt;
            health += x;

            if (cnt == t) {
                health += y;
                cnt = 0;
            }

            if (health > maxHealth) {
                health = maxHealth;
            }
        }
        return health;
    }
}