package code;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon3055 {

    static int r, c;
    static char[][] board;
    static boolean[][] visited;
    static boolean[] isTurn = new boolean[2501];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 고슴도치의 위치와 움직인 횟수입니다.
    static class Hedgehog {
        int x;
        int y;
        int count;

        public Hedgehog(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        bw.write(bfs());
        bw.flush();
        bw.close();
    }

    static String bfs() {
        Queue<Hedgehog> queue = new LinkedList<>();

        // 고슴도치의 처음 위치가 어디인지 찾습니다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'S') {
                    board[i][j] = '.';
                    visited[i][j] = true;
                    queue.offer(new Hedgehog(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Hedgehog hedgehog = queue.poll();

            // 고슴도치가 움직인 뒤에 물을 인접한 위치로 확장시킵니다.
            if (hedgehog.count > 0 && !isTurn[hedgehog.count - 1]) {
                boolean[][] checked = new boolean[r][c];
                isTurn[hedgehog.count - 1] = true;

                // 인접한 구역에 물이 있는지 확인합니다..
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        for (int d = 0; d < 4; d++) {
                            int newX = i + dx[d];
                            int newY = j + dy[d];

                            if (newX < 0 || newX >= r || newY < 0 || newY >= c) {
                                continue;
                            }
                            if (board[newX][newY] == '*') {
                                checked[i][j] = true;
                                break;
                            }
                        }
                    }
                }

                // 빈칸이고 물이 인접해 있다면 해당구역을 물로 바꿉니다.
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        if (checked[i][j] && board[i][j] == '.') {
                            board[i][j] = '*';
                        }
                    }
                }
            }

            // 비버의 굴을 찾았다면 움직인 횟수를 리턴합니다.
            if (board[hedgehog.x][hedgehog.y] == 'D') {
                return String.valueOf(hedgehog.count);
            }

            // 해당 위치가 물이 있다면 다음으로 넘어갑니다.
            if (board[hedgehog.x][hedgehog.y] == '*') {
                continue;
            }

            // 4가지 방향으로 고슴도치가 움직일 수 있다면 큐에 추가합니다.
            for (int d = 0; d < 4; d++) {
                int newX = hedgehog.x + dx[d];
                int newY = hedgehog.y + dy[d];

                if (newX < 0 || newX >= r || newY < 0 || newY >= c || board[newX][newY] == 'X' ||
                        board[newX][newY] == '*' || visited[newX][newY]) {
                    continue;
                }

                visited[newX][newY] = true;
                queue.offer(new Hedgehog(newX, newY, hedgehog.count + 1));
            }
        }

        return "KAKTUS";
    }
}
