import java.util.Scanner;

// 通过前缀和的方式，遍历一遍并维护一个当前最大的前缀和和当前最小的前缀和
public class NC235948 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        long[] preSum = new long[n + 1];
        long maxSum = Long.MIN_VALUE;
        long minPreSum = 0;

        for (int i = 0; i < n; i++) {
            int num = input.nextInt();
            preSum[i + 1] = preSum[i] + num;

            maxSum = Math.max(maxSum, preSum[i + 1] - minPreSum);

            minPreSum = Math.min(minPreSum, preSum[i + 1]);
        }

        System.out.println(maxSum);
        input.close();
    }
}