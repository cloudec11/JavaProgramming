

import java.io.*;
import java.util.*;

public class BPizza {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
        }
        //start
        int[] c = new int[360];
        Arrays.fill(c, 0);
        int t = 0;
        int i = 0;
        while (i < n) {
            t += a[i];
            if (t >= 360) {
                t -= 360;
            }
            c[t] = 1;
            i++;
        }
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int j : c) {
            if (j == 0) count++;
            else {
                if (max < count) max = count;
                count = 0;
            }
        }
        if(max < count) max = count;
        System.out.println(max);


    }
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter() throws IOException {
        return next().charAt(0);
    }
    static String readLine() throws IOException {
        return br.readLine().trim();
    }
}
