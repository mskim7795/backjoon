package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon16197 {

    private static int n;
    private static int m;
    private static char[][] board;
    private static Coin c1;
    private static Coin c2;
    private static Pair p;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int ans = -1;
    private static boolean[][][][] visited = new boolean[20][20][20][20];

    static class Pair {
        Coin c1;
        Coin c2;
        int cnt;

        public Pair(Coin c1, Coin c2, int cnt) {
            this.c1 = c1;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }
    static class Coin {
        private int x;
        private int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        boolean check = false;

        // 코인 각각의 위치를 찾고 저장합니다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'o') {
                    if (check) {
                        c1 = new Coin(i, j);
                    } else {
                        c2 = new Coin(i, j);
                        check = true;
                    }
                    board[i][j] = '.';
                }
            }
        }

        p = new Pair(c1, c2, 0);
        bfs();
        System.out.println(ans);

    }

    // 두 코인의 위치를 방문 체크를 하면서 bfs를 통해 한 코인이 벗어나는 경우에 바로 ans에 값을 저장한 후 종료합니다.
    public static void bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(p);
        boolean found = false;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.cnt == 10) {
                break;
            }
            Coin c1 = p.c1;
            Coin c2 = p.c2;

            for (int d = 0; d < 4; d++) {
                Coin nc1 = new Coin(c1.x + dx[d], c1.y + dy[d]);
                Coin nc2 = new Coin(c2.x + dx[d], c2.y + dy[d]);
                if ((nc1.x < 0 || nc1.x >= n || nc1.y < 0 || nc1.y >= m)
                        && (nc2.x < 0 || nc2.x >= n || nc2.y < 0 || nc2.y >= m)) {
                    // 코인 두 개 모두 벗어난 경우
                    continue;
                } else if ((nc1.x < 0 || nc1.x >= n || nc1.y < 0 || nc1.y >= m)
                        || (nc2.x < 0 || nc2.x >= n || nc2.y < 0 || nc2.y >= m)) {
                    // 코인중 하나만 벗어난 경우
                    found = true;
                    ans = p.cnt + 1;
                    break;
                }

                if (board[nc1.x][nc1.y] == '#' && board[nc2.x][nc2.y] == '#') {
                    // 새로 움직일 곳이 둘다 벽으로 막혀있는 경우
                    continue;
                } else if (board[nc1.x][nc1.y] == '#') {
                    if (!visited[c1.x][c1.y][nc2.x][nc2.y]) {
                        // 코인 1이 벽에 막혀있고 두 코인의 위치가 방문하지 않은 경우
                        visited[c1.x][c1.y][nc2.x][nc2.y] = true;
                        queue.offer(new Pair(c1, nc2, p.cnt + 1));
                    }
                } else if (board[nc2.x][nc2.y] == '#') {
                    if (!visited[nc1.x][nc1.y][c2.x][c2.y]) {
                        // 코인 2가 벽에 막혀있고 두 코인의 위치가 방문하지 않은 경우
                        visited[nc1.x][nc1.y][c2.x][c2.y] = true;
                        queue.offer(new Pair(nc1, c2, p.cnt + 1));
                    }
                } else {
                    if (!visited[nc1.x][nc1.y][nc2.x][nc2.y]) {
                        // 두 코인 모두 움직일 수 있고 위치가 방문하지 않은 경우
                        visited[nc1.x][nc1.y][nc2.x][nc2.y] = true;
                        queue.offer(new Pair(nc1, nc2, p.cnt + 1));
                    }
                }
            }
            if (found) {
                break;
            }
        }
    }
}
