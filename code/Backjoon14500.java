package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon14500 {

    private static int n;
    private static int m;
    private static int[][] board;
    private static int max = Integer.MIN_VALUE;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tmp = board[i][j];
                board[i][j] = -1;
                dfs(i, j, 0, 0);
                board[i][j] = tmp;
            }
        }

        //분홍색 도형을 구하기 위해 중심점이 2차원 배열 벽에 붙어있는지 아닌지 조건에 따라 구합니다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 2차원 배열 모서리에 중심점이 있는 경우
                if ((i == 0 && j == 0) || (i == 0 && j == m - 1) || (i == n - 1 && j == 0) || (i == n - 1 && j == m - 1)) {
                    continue;
                }
                int sum = 0;

                if (i == 0) {
                    // 윗쪽 벽에 중심점이 붙어있는 경우
                    sum = board[i][j] + board[i + dx[0]][j + dy[0]]
                            + board[i + dx[2]][j + dy[2]] + board[i + dx[3]][j + dy[3]];
                    max = Math.max(max, sum);
                } else if (i == n - 1) {
                    // 아랫쪽 벽에 중심점이 붙어있는 경우
                    sum = board[i][j] + board[i + dx[1]][j + dy[1]] + board[i + dx[2]][j + dy[2]]
                            + board[i + dx[3]][j + dy[3]];
                    max = Math.max(max, sum);
                } else if (j == 0) {
                    // 왼쪽 벽에 중심점이 붙어있는 경우
                    sum = board[i][j] + board[i + dx[0]][j + dy[0]] + board[i + dx[1]][j + dy[1]]
                            + board[i + dx[2]][j + dy[2]];
                    max = Math.max(max, sum);
                } else if (j == m - 1) {
                    // 오른쪽 벽에 중심점이 붙어있는 경우
                    sum = board[i][j] + board[i + dx[0]][j + dy[0]] + board[i + dx[1]][j + dy[1]]
                            + board[i + dx[3]][j + dy[3]];
                    max = Math.max(max, sum);
                } else {
                    // 중심점이 어떤 벽에도 붙어있지 않은 경우
                    sum = board[i][j] + board[i + dx[0]][j + dy[0]] + board[i + dx[1]][j + dy[1]]
                            + board[i + dx[2]][j + dy[2]] + board[i + dx[3]][j + dy[3]];
                    for (int d = 0; d < 4; d++) {
                        sum -= board[i + dx[d]][j + dy[d]];
                        max = Math.max(max, sum);
                        sum += board[i + dx[d]][j + dy[d]];
                    }
                }
            }
        }

        System.out.println(max);
    }

    public static void dfs(int a, int b, int cnt, int sum) {
        if (cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        // 분홍색 도형을 제외한 나머지 도형들을 dfs를 사용하여 구합니다.
        for (int d = 0; d < 4; d++) {
            int newA = a + dx[d];
            int newB = b + dy[d];
            if (newA < 0 || newA >= n || newB < 0 || newB >= m || board[newA][newB] == -1) {
                continue;
            }
            int tmp = board[newA][newB];
            board[newA][newB] = -1;
            dfs(newA, newB, cnt + 1, sum + tmp);
            board[newA][newB] = tmp;
        }
    }
}
