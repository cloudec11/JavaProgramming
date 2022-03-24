import java.io.*;
import java.util.StringTokenizer;

public class ASuffix {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        String s = next();
        char[] c = s.toCharArray();
        if(c[s.length()-1]=='r'&&c[s.length()-2]=='e') System.out.println("er");
        if(c[s.length()-1]=='t'&&c[s.length()-2]=='s'&&c[s.length()-3]=='i') System.out.println("ist");
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
