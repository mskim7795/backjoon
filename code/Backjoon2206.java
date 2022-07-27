package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon2206 {

    static int n, m;
    static int[][] board;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 현재 위치, 벽을 부순적이 있는지, 지나간 타일의 수를 저장하는 클래스입니다.
    static class Point {
        int x;
        int y;
        boolean destroyed;
        int cnt;

        public Point(int x, int y, boolean destroyed, int cnt) {
            this.x = x;
            this.y = y;
            this.destroyed = destroyed;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = Character.getNumericValue(charArray[j]);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();

        // 시작점을 큐에 넣습니다.
        queue.offer(new Point(0, 0, false, 1));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            // 도착하면 지나간 타일 수를 반환합니다.
            if (point.x == n - 1 && point.y == m - 1) {
                return point.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int newX = point.x + dx[d];
                int newY = point.y + dy[d];

                // 배열을 벗어난 경우는 넘어갑니다.
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                    continue;
                }

                // 벽을 부순적이 있는지 확인합니다.
                if (point.destroyed) {
                    // 벽을 부순적이 있을 때 해당 지점이 벽이 아니고, 방문한적이 없다면 큐에 정보를 넣습니다.
                    if (board[newX][newY] == 0 && !visited[newX][newY][1]) {
                        visited[newX][newY][1] = true;
                        queue.offer(new Point(newX, newY, true, point.cnt + 1));
                    }
                } else {
                    // 해당 위치가 벽인지 확인합니다.
                    if (board[newX][newY] == 1) {
                        // 벽이라면 벽을 부수고 큐에 값을 넣습니다.
                        visited[newX][newY][1] = true;
                        queue.offer(new Point(newX, newY, true, point.cnt + 1));
                    } else if (!visited[newX][newY][0]){
                        // 벽이 아니고 방문한적이 없다면 큐에 값을 넣습니다.
                        visited[newX][newY][0] = true;
                        queue.offer(new Point(newX, newY, false, point.cnt + 1));
                    }
                }
            }
        }

        return -1;
    }
}
