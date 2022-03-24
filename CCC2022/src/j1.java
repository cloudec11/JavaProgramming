import java.io.*;
import java.util.*;
public class j1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        int r = readInt();
        int  s = readInt();
        int left = ((r*8) + s*3)-28;
        System.out.println(left);
    }
    static String next() throws IOException {
        while(st==null || !st.hasMoreElements()){
            st = new StringTokenizer(br.readLine().trim());
        }
        return st.nextToken();
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
}

