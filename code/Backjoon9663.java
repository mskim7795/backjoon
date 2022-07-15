package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon9663 {

    private static int n;
    private static int[][] board;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        dfs(0);

        System.out.println(cnt);
    }

    public static void dfs(int idx) {
        if (idx == n) {
            cnt++;
            return;
        }

        // 해당 지점에 퀸을 놓을 수 있다면 놓은 지점을 1로 체크하고 dfs 순회합니다.
        for (int i = 0; i < n; i++) {
            if (isPos(idx, i)) {
                board[idx][i] = 1;
                dfs(idx + 1);
                board[idx][i] = 0;
            }
        }
    }

    // 해당 지점에 퀸을 놓을 수 있는지 확인합니다.
    public static boolean isPos(int x, int y) {
        // 해당 지점의 세로 위로 퀸이 위치하는지 확인합니다.
        for (int i = 0; i < x; i++) {
            if (board[i][y] == 1) {
                return false;
            }
        }

        for (int i = 1; i <= x; i++) {
            if (x - i >= 0 && y - i >= 0 && board[x - i][y - i] == 1) {
                // 해당 지점의 왼쪽 위 대각선으로 퀸이 위치하는지 확인합니다.
                return false;
            } else if (x - i >= 0 && y + i < n && board[x - i][y + i] == 1) {
                // 해당 지점의 오른쪽 위 대각선으로 퀸이 위치하는지 확인 합니다.
                return false;
            }
        }

        return true;
    }
}
