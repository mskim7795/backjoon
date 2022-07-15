package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon2580 {

    private static int[][] board = new int[9][9];
    private static List<Point> pointList = new ArrayList<>();
    private static boolean flag = false;

    // 0인 점들을 저장하기 위한 클래스
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    // 해당 점이 0일 경우 pointList에 저장
                    pointList.add(new Point(i, j));
                }
            }
        }

        dfs(0);
    }

    public static void dfs(int idx) {
        if (idx == pointList.size()) {
            // 한 가지 경우의 수만 출력하기 위해 flag 값으로 이미 출력을 했는지 체크합니다.
            if (flag) {
                return;
            }
            flag = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        Point p = pointList.get(idx);
        // 1 ~ 9 까지의 수 중에서 가능한 숫자를 찾습니다.
        for (int i = 1; i < 10; i++) {
            if (isPos(p.x, p.y, i)) {
                board[p.x][p.y] = i;
                dfs(idx + 1);
                board[p.x][p.y] = 0;
            }
        }
    }

    public static boolean isPos(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            // 해당 점의 세로에 num과 같은 값이 있는지 확인합니다.
            if (board[i][y] == num) {
                return false;
            }
            // 해당 점의 가로에 num과 같은 값이 있는지 확인합니다.
            if (board[x][i] == num) {
                return false;
            }
        }

        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        // 해당 점의 3 * 3 크기의 작은 네모안에 num과 같은 값이 있는지 확인합니다.
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
