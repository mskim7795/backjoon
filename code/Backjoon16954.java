package code;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Backjoon16954 {

    static char[][][] board = new char[9][8][8]; // 움직일 때 마다 벽이 움직이므로 모든 벽이 사라지는 9번째 턴까지 체스판을 저장합니다.
    static boolean[][][] visited = new boolean[9][8][8]; // 방문체크도 벽의 모양에 따라 9가지로 체크해줍니다.
    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1, 0};

    static class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 8; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                board[0][i][j] = chars[j];
            }
        }

        // 체스판의 모양은 모든 벽이 사라지는 경우까지 총 9가지가 존재합니다. 9가지 체스판 모양을 생성합니다.
        for (int i = 1; i <= 8; i++) {
            for (int j = i - 1; j >= 0; j--) {
                board[i][j] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            }

            for (int j = i; j < 8; j++) {
                board[i][j] = board[i - 1][j - 1].clone();
            }
        }

        bw.write(bfs());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(7, 0, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            // 현재 벽 모양을 체크하기 위한 변수입니다.
            int count = Math.min(point.count, 8);

            if (point.x == 0 && point.y == 7) {
                return 1;
            }

            if (board[count][point.x][point.y] == '#') {
                continue;
            }

            for (int d = 0; d < 9; d++) {
                int newX = point.x + dx[d];
                int newY = point.y + dy[d];

                if (newX < 0 || newX >= 8 || newY < 0 || newY >= 8 || board[count][newX][newY] == '#' || visited[count][newX][newY]) {
                    continue;
                }

                visited[count][newX][newY] = true;
                queue.offer(new Point(newX, newY, count + 1));
            }
        }

        return 0;
    }
}
