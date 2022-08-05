package code;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon14442 {

    static int n, m, k;
    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][][] visited;

    static class Point {
        int x;
        int y;

        // 움직인 거리
        int movingCount;

        // 벽을 부순 횟수
        int breakingCount;

        public Point(int x, int y, int movingCount, int breakingCount) {
            this.x = x;
            this.y = y;
            this.movingCount = movingCount;
            this.breakingCount = breakingCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 벽을 몇 번 부쉈는지에 따라 방문 체크를 따로 해줘야 반례를 해결할 수 있습니다.
        visited = new boolean[n][m][k + 1];

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = Character.getNumericValue(charArray[j]);
            }
        }

        bw.append(Integer.toString(bfs()));
        bw.flush();
        bw.close();
    }

    // 벽을 부수고 이동할 수 있는 최단거리를 구합니다.
    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == n - 1 && point.y == m - 1) {
                return point.movingCount;
            }

            for (int d = 0; d < 4; d++) {
                int newX = point.x + dx[d];
                int newY = point.y + dy[d];

                // 배열을 벗어나는 경우는 제외
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                    continue;
                }

                // 해당 칸이 빈칸이고 방문하지 않은 경우
                if (board[newX][newY] == 0 && !visited[newX][newY][point.breakingCount]) {
                    queue.offer(new Point(newX, newY, point.movingCount + 1, point.breakingCount));
                    visited[newX][newY][point.breakingCount] = true;
                }

                // 해당 칸이 벽이고, k번 미만으로 벽을 부쉈고, 방문하지 않은 경우
                if (board[newX][newY] == 1 && point.breakingCount < k && !visited[newX][newY][point.breakingCount + 1]) {
                    queue.offer(new Point(newX, newY, point.movingCount + 1, point.breakingCount + 1));
                    visited[newX][newY][point.breakingCount + 1] = true;
                }
            }
        }

        return -1;
    }
}
