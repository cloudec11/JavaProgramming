import java.io.*;
import java.util.*;
public class j2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        int n = readInt();
        int p, f;
        int total = 0;
        int star = 0;
        for (int i = 0; i < n; i++) {
            p = readInt();
            f = readInt();
            int points = (p*5) - (f*3);
            total += points;
            if(points>= 40)star++;
        }
        System.out.print(star);
        if(total>=(n*40)) System.out.print("+");
        System.out.println();
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

