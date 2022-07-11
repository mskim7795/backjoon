package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon14889 {
    static int n;
    static int[][] arr;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] visited = new boolean[n];
        divideTeam(0, 0, visited);

        System.out.println(ans);
    }

    public static void divideTeam(int cnt, int cur, boolean[] visited) {
        if (cnt == n / 2) {
            int team1 = 0;
            int team2 = 0;

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (visited[i] == true && visited[j] == true) {
                        team1 += arr[i][j] + arr[j][i];
                    }
                    else if (visited[i] == false && visited[j] == false) {
                        team2 += arr[i][j] + arr[j][i];
                    }
                }
            }

            ans = Math.min(Math.abs(team1 - team2), ans);
            return;
        }

        for (int i = cur; i < n; i++) {
            if (!visited[i]){
                visited[i] = true;
                divideTeam(cnt + 1, i + 1, visited);
                visited[i] = false;
            }
        }
    }
}
