package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon14501 {

    private static int n;
    private static int[] terms;
    private static int[] profits;
    private static int max = 0;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        terms = new int[n + 1];
        profits = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            terms[i] = Integer.parseInt(st.nextToken());
            profits[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, 0);

        System.out.println(max);
    }

    public static void dfs(int day, int sum) {
        // day가 n + 1일이 되었다면 최대값 갱신
        if (day == n + 1) {
            max = Math.max(max, sum);
            return;
        }

        // 해당 날짜 스케줄을 하는 경우와 안하는 경우 2가지로 탐색합니다.
        dfs(day + 1, sum);
        // 해당 날짜 스케줄에 일하려면 일할 수 있는지 isPos() 메서드로 확인합니다.
        if (isPos(day)) {
            visited[day] = true;
            dfs(day + 1, sum + profits[day]);
            visited[day] = false;
        }
    }

    public static boolean isPos(int day) {
        // 만약에 해당 날짜 스케줄이 N일 보다 더 오래 걸리는지 확인합니다.
        if (day + terms[day] - 1 > n) {
            return false;
        }
        // 이전 날짜에 잡은 스케줄 중에 day 날에도 일하는 스케줄이 있는지 확인합니다.
        for (int i = 1; i < day; i++) {
            if (visited[i]) {
                if(i + terms[i] - 1 >= day) {
                    return false;
                }
            }
        }

        return true;
    }
}
