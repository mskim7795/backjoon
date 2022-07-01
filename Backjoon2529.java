import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon2529 {

    private static int num;
    private static List<String> charList = new ArrayList<>();
    private static Long max = Long.MIN_VALUE;
    private static Long min = Long.MAX_VALUE;
    private static int[] arr;
    private static int[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[num + 1];
        check = new int[10];

        for (int i = 0; i < num; i++) {
            charList.add(st.nextToken());
        }

        dfs(0);
        String maxAns = Long.toString(max);
        String minAns = Long.toString(min);
        if (maxAns.length() < num + 1) {
            maxAns = "0" + maxAns;
        }
        if (minAns.length() < num + 1) {
            minAns = "0" + minAns;
        }

        System.out.println(maxAns);
        System.out.println(minAns);
    }

    static void dfs(int depth) {
        if (depth == num + 1) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < num + 1; i++) {
                result.append(arr[i]);
            }
            long ans = Long.parseLong(result.toString());
            max = Math.max(max, ans);
            min = Math.min(min, ans);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (check[i] == 1) {
                continue;
            }

            if (depth == 0) {
                check[i] = 1;
                arr[depth] = i;
                dfs(depth + 1);
                check[i] = 0;
            } else {
                arr[depth] = i;
                if (charList.get(depth - 1).equals("<") && (arr[depth - 1] < arr[depth])) {
                    check[i] = 1;
                    arr[depth] = i;
                    dfs(depth + 1);
                    check[i] = 0;
                } else if (charList.get(depth - 1).equals(">") && (arr[depth - 1] > arr[depth])) {
                    check[i] = 1;
                    arr[depth] = i;
                    dfs(depth + 1);
                    check[i] = 0;
                }
            }
        }
    }
}
