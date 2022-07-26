package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjoon9019 {

    static int n;
    static int num;
    static int result;
    static boolean[] visited;
    static String[] ans;

    // 현재 값과 사용한 계산기 명령어 정보를 저장하는 클래스
    static class Info {
        int num;
        String str;

        public Info(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = new String[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            result = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            ans[i] = bfs();
        }

        for (String s: ans) {
            System.out.println(s);
        }
    }

    public static String bfs() {
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(num, ""));

        while (!queue.isEmpty()) {
            Info info = queue.poll();
            // 해당 숫자를 방문한적이 있다면 다음 큐 값으로 넘어갑니다.
            if (visited[info.num]) {
                continue;
            }
            visited[info.num] = true;

            // 정답과 일치한다면 명령어 모음을 반환합니다.
            if (info.num == result) {
                return info.str;
            }

            // D 명령어
            queue.offer(new Info((info.num * 2) % 10000, info.str + "D"));

            // S 명령어
            int numS = (info.num == 0)? 9999: info.num - 1;
            queue.offer(new Info(numS, info.str + "S"));

            // L 명령어
            int numL = (info.num / 1000) + ((info.num / 100) % 10) * 1000 + ((info.num / 10) % 10) * 100 + (info.num % 10) * 10;
            queue.offer(new Info(numL, info.str + "L"));

            // R 명령어
            int numR = (info.num / 1000) * 100 + ((info.num / 100) % 10) * 10 + (info.num / 10) % 10 + (info.num % 10) * 1000;
            queue.offer(new Info(numR, info.str + "R"));
        }

        return "";
    }
}
