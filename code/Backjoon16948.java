package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon16948 {

    static int n, r1, c1, r2, c2;
    static boolean[][] visited;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};

    // 현재 체스말의 위치와 움직인 횟수 정보를 가진 클래스입니다.
    static class Night {
        int r;
        int c;
        int cnt;

        public Night(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        visited = new boolean[n][n];

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Night> queue = new LinkedList<>();
        queue.offer(new Night(r1, c1, 0));
        visited[r1][c1] = true;

        while (!queue.isEmpty()) {
            Night night = queue.poll();
            // 현재 체스말의 위치가 찾고 있던 위치라면 cnt를 반환합니다.
            if (night.r == r2 && night.c == c2) {
                return night.cnt;
            }

            for (int i = 0; i < 6; i++) {
                int newR = night.r + dx[i];
                int newC = night.c + dy[i];
                // 체스판 범위를 벗어나거나 이미 방문한 지점이라면 다음 경우의 수로 넘어갑니다.
                if (newR < 0 || newR >= n || newC < 0 || newC >= n || visited[newR][newC]) {
                    continue;
                }

                queue.offer(new Night(newR, newC, night.cnt + 1));
                visited[newR][newC] = true;
            }
        }

        return -1;
    }
}
