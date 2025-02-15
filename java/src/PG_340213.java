class PG_340213 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_int = timeToInt(video_len);
        int pos_int = timeToInt(pos);
        int op_start_int = timeToInt(op_start);
        int op_end_int = timeToInt(op_end);

        if (op_start_int <= pos_int && pos_int <= op_end_int) {
            pos_int = op_end_int;
        }

        for (String command : commands) {
            if (command.equals("next")) {
                pos_int += 10;
                if (pos_int > video_len_int) pos_int = video_len_int;
            } else {
                pos_int -= 10;
                if (pos_int < 0) pos_int = 0;
            }
            if (op_start_int <= pos_int && pos_int <= op_end_int) {
                pos_int = op_end_int;
            }
        }

        return secondToTime(pos_int);
    }

    // 문자열을 초로 리턴하는 메서드
    private int timeToInt(String time) {
        String[] str = time.split(":");
        int m = Integer.parseInt(str[0]);
        int s = Integer.parseInt(str[1]);

        return m * 60 + s;
    }

    private String secondToTime(int second) {
        int m = second / 60;
        int s = second % 60;

        return String.format("%02d:%02d", m, s);
    }
}