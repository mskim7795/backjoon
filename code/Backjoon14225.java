package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon14225 {

    static int n;
    static int[] arr;
    // 최대 값은 100000 * 20이기 때문에 넉넉하게 설정
    static int[] resArr = new int[2500000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        // 체크 되지 않은 가장 작은 자연수 값을 구합니다.
        for (int i = 1; i < 2500000; i++) {
            if (resArr[i] == 0) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void dfs(int cur, int sum) {
        if (cur == n) {
            // 구해진 값을 체크
            resArr[sum] = 1;
            return;
        }

        // 모든 경우의 수 구하기
        dfs(cur + 1, sum + arr[cur]);
        dfs(cur + 1, sum);
    }
}
