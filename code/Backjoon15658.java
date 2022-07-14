package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon15658 {

    static int n;
    static int[] numArr;
    static int[] operArr = new int[4];
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numArr = new int[n];

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

    public static void dfs(int cnt, long res) {
        //연산이 끝난 경우에 최대값과 최소값 갱신
        if (cnt == n) {
            max = Math.max(res, max);
            min = Math.min(res, min);
            return;
        }

        // 연산자 사용 개수를 갱신해주고 연산자 별로 res에 저장하여 dfs 적용
        for (int i = 0; i < 4; i++) {
            if (operArr[i] > 0) {
                operArr[i] -= 1;
                switch (i) {
                    case 0:
                        dfs(cnt + 1, res + numArr[cnt]);
                        break;
                    case 1:
                        dfs(cnt + 1, res - numArr[cnt]);
                        break;
                    case 2:
                        dfs(cnt + 1, res * numArr[cnt]);
                        break;
                    case 3:
                        dfs(cnt + 1, res / numArr[cnt]);
                        break;
                }
                operArr[i] += 1;
            }
        }
    }
}
