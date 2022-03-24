import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        int n = readInt(), q = readInt(); long a[] = new long [n+5];
        long psa [] = new long [n+5];
        for (int i = 1; i <= n; i++) {
            a[i] = readLong();
        }
        psa[1] = a[1];
        for (int i = 2; i <= n; i++) {
            psa[i] = psa[i-1] + a[i];
        }
        for (int i = 0; i < q; i++) {
            int x = readInt(), y = readInt();
            System.out.println(psa[y] - psa[x-1]);
        }
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
