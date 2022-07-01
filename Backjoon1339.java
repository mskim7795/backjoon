import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Backjoon1339 {

    static int n;
    static List<String> alphaList;
    static Map<Character, Integer> charMap;
    static int[] isVisited;
    static int cnt;
    static int[] arr = new int[10];
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        charMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char[] readLine = br.readLine().toCharArray();
            int k = 1;
            for (int j = readLine.length - 1; j >= 0; j--) {
                char c = readLine[j];
                charMap.put(c, charMap.getOrDefault(c, 0) + k);
                k *= 10;
            }
        }
        List<Integer> list = new ArrayList<>(charMap.values());
        list.sort(Collections.reverseOrder());
        int k = 9;
        for (int i: list) {
            ans += k * i;
            k--;
        }

        System.out.println(ans);
    }
}
