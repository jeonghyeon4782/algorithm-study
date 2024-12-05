import java.io.*;
import java.sql.Time;
import java.util.*;

public class Main {

    static class Time implements Comparable<Time>{
        int s, e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }

        public int compareTo(Time o) {
            return this.e - o.e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Time[] times = new Time[n];
        int answer = 1;

        for (int i = 0; i < n; i++) {
            times[i] = new Time(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(times);
        int end = times[0].e;

        for (int i = 1; i < times.length; i++) {
            if (times[i].s >= end) {
                answer++;
                end = times[i].e;
            }
        }

        System.out.println(answer);
    }
}
