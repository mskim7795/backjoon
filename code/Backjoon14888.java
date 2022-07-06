package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon14888 {

    static int n;
    static int[] numArr;
    static int[] operArr = new int[4];
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            operArr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, numArr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int cur, long res) {
        if (cur == n) {
            max = Math.max(res, max);
            min = Math.min(res, min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: if (operArr[0] > 0) {
                    operArr[0] -= 1;
                    dfs(cur + 1, res + numArr[cur]);
                    operArr[0] += 1;
                }
                    break;
                case 1: if (operArr[1] > 0) {
                    operArr[1] -= 1;
                    dfs(cur + 1, res - numArr[cur]);
                    operArr[1] += 1;
                }
                    break;
                case 2: if (operArr[2] > 0) {
                    operArr[2] -= 1;
                    dfs(cur + 1, res * numArr[cur]);
                    operArr[2] += 1;
                }
                    break;
                case 3: if (operArr[3] > 0) {
                    operArr[3] -= 1;
                    dfs(cur + 1, res / numArr[cur]);
                    operArr[3] += 1;
                }
            }
        }
    }
}
