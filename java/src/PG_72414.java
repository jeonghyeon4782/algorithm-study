import java.util.*;

class PG_72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        PriorityQueue<Integer> spq = new PriorityQueue<>();
        PriorityQueue<Integer> epq = new PriorityQueue<>();

        // 재생시간과 광고시간을 초 단위로 변환
        int end = toSeconds(play_time);
        int advTime = toSeconds(adv_time);

        // 시간별 시청자 수 기록
        int[] people = new int[end + 1];

        // 로그 파싱 및 우선순위 큐 삽입
        for (String log : logs) {
            String[] times = log.split("-");
            int start = toSeconds(times[0]);
            int finish = toSeconds(times[1]);

            spq.offer(start);
            epq.offer(finish);
        }

        // 시간별 시청자 수 계산
        int count = 0;
        for (int i = 0; i <= end; i++) {
            while (!spq.isEmpty() && spq.peek() == i) {
                spq.poll();
                count++;
            }
            while (!epq.isEmpty() && epq.peek() == i) {
                epq.poll();
                count--;
            }
            people[i] = count;
        }

        // 슬라이딩 윈도우를 사용하여 광고 삽입 최적 시간 찾기
        long maxViewTime = 0;
        long currentViewTime = 0;

        // 초기 구간 (0초 ~ advTime) 합산
        for (int i = 0; i <= advTime; i++) {
            currentViewTime += people[i];
        }

        long maxStart = 0;
        maxViewTime = currentViewTime;

        // 슬라이딩 윈도우 탐색
        for (int i = advTime + 1; i <= end; i++) {
            currentViewTime += people[i];
            currentViewTime -= people[i - advTime];

            if (currentViewTime > maxViewTime) {
                maxViewTime = currentViewTime;
                maxStart = i - advTime + 1;
            }
        }

        return formatTime((int) maxStart);
    }

    // HH:MM:SS -> 초 단위 변환
    public int toSeconds(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return (h * 3600) + (m * 60) + s;
    }

    // 초 단위를 HH:MM:SS 형식으로 변환
    public String formatTime(int sec) {
        int h = sec / 3600;
        int m = (sec % 3600) / 60;
        int s = sec % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
