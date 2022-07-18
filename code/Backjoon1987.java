package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1987 {

    private static int r;
    private static int c;
    private static char[][] board;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static boolean[] visited = new boolean[100];
    private static int max = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // (0, 0)을 처음에 방문하므로 미리 체크해줍니다.
        // 알파벳을 아스키코드를 활용하여 인덱스로 사용합니다.
        visited[board[0][0]] = true;
        dfs(0, 0, 1);
        System.out.println(max);
    }

    public static void dfs(int x, int y, int cnt) {
        // 탐색할때마다 최대값을 갱신해줍니다.
        max = Math.max(max, cnt);
        // cnt가 가로 세로의 곱과 같다면 모든 점을 탐색한 것이므로 리턴해줍니다.
        if (cnt == r * c) {
            return;
        }

        // 상하좌우로 새로운 점을 탐색합니다.
        for (int d = 0; d < 4; d++) {
            int newX = x + dx[d];
            int newY = y + dy[d];
            // 배열에서 벗어나는 인덱스인지, 해당 좌표의 알파벳이 이미 방문한 적이 있는지 확인합니다.
            if (newX < 0 || newX >= r || newY < 0 || newY >= c || visited[board[newX][newY]]) {
                continue;
            }
            visited[board[newX][newY]] = true;
            dfs(newX, newY, cnt + 1);
            visited[board[newX][newY]] = false;
        }
    }
}
