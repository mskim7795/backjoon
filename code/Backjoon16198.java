package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon16198 {

    private static int n;
    private static List<Integer> numList;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            numList.add(Integer.parseInt(st.nextToken()));
        }

        dfs(n, 0);
        System.out.println(max);
    }

    // dfs로 완전 탐색하여 최대값을 구합니다.
    public static void dfs(int length, int sum) {
        // 구슬이 두 개가 남았을 때 sum의 값으로 max 값을 갱신합니다.
        if (length == 2) {
            max = Math.max(max, sum);
            return;
        }

        // 첫 번째 구슬과 마지막 구슬을 제외한 구슬을 선택하고 선택된 구슬의 앞과 뒤의 숫자를 곱합니다.
        for (int i = 1; i < length - 1; i++) {
            int energy = numList.get(i - 1) * numList.get(i + 1);
            int tmp = numList.remove(i);
            dfs(length - 1, sum + energy);
            numList.add(i, tmp);
        }
    }
}
