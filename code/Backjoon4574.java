package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon4574 {

    private static int n;
    private static boolean[][] visitedDomino;
    private static int[][] board;
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};
    private static int cnt = 1;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            visitedDomino = new boolean[10][10];
            board = new int[9][9];
            n = Integer.parseInt(br.readLine());

            // n 입력이 0이면 루프문 탈출
            if (n == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                char[] lu = st.nextToken().toCharArray();
                int v = Integer.parseInt(st.nextToken());
                char[] lv = st.nextToken().toCharArray();

                // 해당 도미노가 사용되었는지 u와 v를 통해 체크합니다.(역순 포함)
                visitedDomino[u][v] = true;
                visitedDomino[v][u] = true;
                // 아스키 코드를 사용하여 좌표를 2차원 배열의 인덱스로 변환합니다.
                board[lu[0] - 65][Character.getNumericValue(lu[1]) - 1] = u;
                board[lv[0] - 65][Character.getNumericValue(lv[1]) - 1] = v;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 10; i++) {
                char[] point = st.nextToken().toCharArray();
                board[point[0] - 65][Character.getNumericValue(point[1]) - 1] = i;
            }
            flag = false;
            dfs(0);
            cnt++;
        }
    }

    public static void dfs(int idx)  {
        // 스도미노쿠를 완성했다면 출력합니다.
        if (idx == 81) {
            // flag는 해당 퍼즐이 출력 된적이 있는지 확인합니다.
            if (flag) {
                return;
            }
            flag = true;
            System.out.printf("Puzzle %d%n", cnt);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            return;
        }

        // idx를 9 * 9 좌표로 변환합니다.
        int x = idx / 9;
        int y = idx % 9;

        // 해당 좌표에 값이 있는 경우 다음 좌표로 넘어갑니다.
        if (board[x][y] != 0) {
            dfs(idx + 1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isPosNum(x, y, i)) {
                // idx에 따라 순서대로 탐색하므로 해당 좌표에서 오른쪽, 아랫쪽에 숫자가 포함되는 도미노만 사용합니다.
                for (int d = 0; d < 2; d++) {
                    int newX = x + dx[d];
                    int newY = y + dy[d];
                    // 두 번째 좌표가 스도쿠를 벗어나는지 혹은 숫자가 이미 있는지 확인
                    if (newX >= 9 || newY >= 9 || board[newX][newY] != 0) {
                        continue;
                    }
                    for (int j = 1; j <= 9; j++) {
                        // 도미노 안의 숫자가 다르고, 사용하지 않은 도미노이고, 해당 좌표에 숫자 j를 쓸 수 있다면 다음점을 탐색
                        if (i != j && !visitedDomino[i][j] && isPosNum(newX, newY, j)) {
                            board[x][y] = i;
                            board[newX][newY] = j;
                            visitedDomino[i][j] = true;
                            visitedDomino[j][i] = true;
                            dfs(idx + 1);
                            board[x][y] = 0;
                            board[newX][newY] = 0;
                            visitedDomino[i][j] = false;
                            visitedDomino[j][i] = false;
                        }
                    }
                }
            }
        }
    }

    // 스도쿠 규칙에 따라 해당 숫자를 사용할 수 있는지 확인합니다.
    public static boolean isPosNum(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            // 세로에 같은 숫자가 있는지 확인
            if (board[i][y] == num) {
                return false;
            }
            // 가로에 같은 숫자가 있는지 확인
            if (board[x][i] == num) {
                return false;
            }
        }

        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        // 해당 좌표가 포함되는 3 * 3 네모 안에 같은 숫자가 있는지 확인
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
