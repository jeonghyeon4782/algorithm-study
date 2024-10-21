public class PG_43165 {

    static int answer, targetNum, len;
    static boolean[] visited;
    static int[] nums;

    public int solution(int[] numbers, int target) {
        len = numbers.length;
        answer = 0;
        targetNum = target;
        visited = new boolean[len];
        nums = numbers;

        dfs(0, 0);

        return answer;
    }

    public void dfs(int depth, int sumNum) {

        if(depth == len) {
            if(sumNum == targetNum) {
                answer++;
            }
            return;
        }

        dfs(depth + 1, sumNum + nums[depth]);
        dfs(depth + 1, sumNum - nums[depth]);
    }

}
