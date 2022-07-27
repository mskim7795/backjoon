package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon12886 {

    static int ans = 0;
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // a, b, c의 합이 3으로 나누어 지지 않는 경우는 a, b, c의 값이 모두 같은 것이 불가능합니다.
        if ((a + b + c) % 3 == 0) {
            dfs(a, b, c);
        }
        System.out.println(ans);
    }

    public static void dfs(int a, int b, int c) {
        // 세 값이 모두 같은 경우
        if (a == b && b == c) {
            ans = 1;
            return;
        }

        // a, b 를 선택한 경우
        if (a != b && !visited[a][b]) {
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            // a, b 두 값을 방문한것을 체크합니다.
            visited[a][b] = visited[b][a] = true;
            dfs(min * 2, max - min, c);
        }
        // b, c를 선택한 경우
        if (b != c && !visited[b][c]) {
            int min = Math.min(b, c);
            int max = Math.max(b, c);
            //  b, c 두 값을 방문한것을 체크합니다.
            visited[b][c] = visited[c][b] = true;
            dfs(a, min * 2, max - min);
        }
        // c, a를 선택한 경우
        if (c != a && !visited[c][a]) {
            int min = Math.min(c, a);
            int max = Math.max(c, a);
            // c, a 두 값을 방문한것을 체크합니다.
            visited[c][a] = visited[a][c] = true;
            dfs(min * 2, b, max - min);
        }
    }
}
