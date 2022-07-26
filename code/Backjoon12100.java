package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon12100 {

    static int n;
    static int[][] board;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        game(0);
        System.out.println(max);
    }

    public static void game(int cnt) {
        // 5번 움직인 경우 배열에서 가장 큰값이 있다면 max를 갱신합니다.
        if (cnt == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(max, board[i][j]);
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] copy = new int[n][n];
            switch (d) {
                // 위로 움직임
                case 0:
                    for (int i = 0; i < n; i++) {
                        int idx = 0;
                        boolean visited = false;
                        for (int j = 0; j < n; j++) {
                            // 현재 위치의 값이 0이 아닌경우 위치를 옮겨야함
                            if (board[i][j] != 0) {
                                if (idx == 0) {
                                    // 처음으로 움직이는 값인 경우
                                    copy[i][idx] = board[i][j];
                                    idx++;
                                } else {
                                    if (!visited && copy[i][idx - 1] == board[i][j]) {
                                        // 그 전 값이 합쳐지지 않았고 두 값이 같은 경우 합쳐야합니다.
                                        copy[i][idx - 1] *= 2;
                                        visited = true;
                                    } else {
                                        copy[i][idx] = board[i][j];
                                        visited = false;
                                        idx++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                // 아래로 움직임
                case 1:
                    for (int i = 0; i < n; i++) {
                        int idx = n - 1;
                        boolean visited = false;
                        for (int j = n - 1; j >= 0; j--) {
                            // 현재 위치의 값이 0이 아닌경우 위치를 옮겨야함
                            if (board[i][j] != 0) {
                                if (idx == n - 1) {
                                    // 처음으로 움직이는 값인 경우
                                    copy[i][idx] = board[i][j];
                                    idx--;
                                } else {
                                    if (!visited && copy[i][idx + 1] == board[i][j]) {
                                        // 그 전 값이 합쳐지지 않았고 두 값이 같은 경우 합쳐야합니다.
                                        copy[i][idx + 1] *= 2;
                                        visited = true;
                                    } else {
                                        copy[i][idx] = board[i][j];
                                        visited = false;
                                        idx--;
                                    }
                                }
                            }
                        }
                    }
                    break;
                // 왼쪽으로 움직임
                case 2:
                    for (int i = 0; i < n; i++) {
                        int idx = 0;
                        boolean visited = false;
                        for (int j = 0; j < n; j++) {
                            // 현재 위치의 값이 0이 아닌경우 위치를 옮겨야함
                            if (board[j][i] != 0) {
                                if (idx == 0) {
                                    // 처음으로 움직이는 값인 경우
                                    copy[idx][i] = board[j][i];
                                    idx++;
                                } else {
                                    if (!visited && copy[idx - 1][i] == board[j][i]) {
                                        // 그 전 값이 합쳐지지 않았고 두 값이 같은 경우 합쳐야합니다.
                                        copy[idx - 1][i] *= 2;
                                        visited = true;
                                    } else {
                                        copy[idx][i] = board[j][i];
                                        visited = false;
                                        idx++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                // 오른쪽으로 움직임
                case 3:
                    for (int i = 0; i < n; i++) {
                        int idx = n - 1;
                        boolean visited = false;
                        for (int j = n - 1; j >= 0; j--) {
                            // 현재 위치의 값이 0이 아닌경우 위치를 옮겨야함
                            if (board[j][i] != 0) {
                                if (idx == n - 1) {
                                    // 처음으로 움직이는 값인 경우
                                    copy[idx][i] = board[j][i];
                                    idx--;
                                } else {
                                    if (!visited && copy[idx + 1][i] == board[j][i]) {
                                        // 그 전 값이 합쳐지지 않았고 두 값이 같은 경우 합쳐야합니다.
                                        copy[idx + 1][i] *= 2;
                                        visited = true;
                                    } else {
                                        copy[idx][i] = board[j][i];
                                        visited = false;
                                        idx--;
                                    }
                                }
                            }
                        }
                    }
            }

            int[][] tmp = new int[n][n];
            for (int i = 0; i < n; i++) {
                tmp[i] = board[i].clone();
            }
            board = copy;
            game(cnt + 1);
            board = tmp;
        }
    }
}
