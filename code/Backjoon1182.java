package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1182 {

    static int n;
    static int s;
    static int[] arr;
    static int ans = 0;
    static int[] foundNumArr = new int[2];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        if (s == 0) {
            System.out.println(ans - 1);
        } else {
            System.out.println(ans);
        }
    }

    public static void dfs(int cur, int sum) {
        if (cur == n) {
            if (sum == s) {
                ans++;
            }
            return;
        }

        dfs(cur + 1, sum + arr[cur]);
        dfs(cur + 1, sum);
    }
}
