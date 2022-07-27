package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon14502 {

    static int n, m;
    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max = 0;

    // 바이러스의 위치를 저장하기 위한 클래스
    static class Virus {
        int x;
        int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        // 입력값을 board에 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        createWall(0, 0);

        System.out.println(max);
    }

    // 벽 3개를 설치하는 경우의 수를 dfs를 사용하여 조합으로 구했습니다.
    public static void createWall(int cnt, int idx) {
        // 벽을 3개 설치했으면 안전 영역을 구해 최대값을 갱신합니다.
        if (cnt == 3) {
            max = Math.max(max, findSafeArea());
            return;
        }

        int row = idx / m;
        int col = idx % m;

        for (int i = row; i < n; i++) {
            for (int j = col; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    createWall(cnt + 1, i * m + j + 1);
                    board[i][j] = 0;
                }
            }
            col = 0;
        }
    }

    // 벽을 3개 설치 했을 때 안전 영역의 개수를 구합니다.
    public static int findSafeArea() {
        int[][] copy = new int[n][m];

        // 벽을 3개 설치한 2차원 배열을 따로 깊은 복사를 해줍니다.
        for (int i = 0; i < n; i++) {
            copy[i] = board[i].clone();
        }
        Queue<Virus> queue = new LinkedList<>();

        // 먼저 바이러스가 있는 위치를 큐에 넣어줍니다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 2) {
                    queue.offer(new Virus(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Virus virus = queue.poll();

            //상하좌우 방향으로 탐색합니다.
            for (int d = 0; d < 4; d++) {
                int newX = virus.x + dx[d];
                int newY = virus.y + dy[d];

                // 배열을 벗어나거나 0이 아닌 경우 넘어갑니다.
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || copy[newX][newY] != 0) {
                    continue;
                }

                // 바이러스와 근접한 지점은 감염시킵니다.
                copy[newX][newY] = 2;
                queue.offer(new Virus(newX, newY));
            }
        }

        int cnt = 0;
        // 안전 영역의 개수를 구합니다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
