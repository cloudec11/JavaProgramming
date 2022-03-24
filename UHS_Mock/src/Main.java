import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        //here TODO
        int n = readInt(), t , x;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            t = readInt(); x = readInt();
            arr[i][0] = t;
            arr[i][1] = x;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i!=j){
                    if(arr[i][0]<arr[j][0]){
                        int temp = arr[i][0];
                        int temp2 = arr[i][1];
                        arr[i][0] = arr[j][0];
                        arr[i][1] = arr[j][1];
                        arr[j][0] = temp;
                        arr[j][1] = temp2;
                    }
                }
            }
        }
        double max = Double.MIN_VALUE;
        for (int i = 0; i < n-1; i++) {
            int dist = Math.abs(arr[i][1]-arr[i+1][1]);
            int tim = Math.abs(arr[i][0]-arr[i+1][0]);
            double speed = (double)dist/(double)tim;
            if(max<speed) max = speed;
        }
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
