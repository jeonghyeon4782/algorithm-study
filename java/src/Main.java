import java.util.Arrays;

public class Main {

    static int[] nums = {1, 3, 11, 2, 7, 99, 100};

    public static void main(String[] args) {
        Arrays.sort(nums);
        binarySearch(6);
    }

    public static void binarySearch(int target) {
        int left = 0;
        int right = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == nums[mid]) {
                System.out.println(nums[mid] + "가 " + mid + "번째에 있습니다.");
                return;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else  {
                left = mid + 1;
            }
        }
    }
}