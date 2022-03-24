import java.io.*;
import java.util.*;
public class s1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        int n = readInt();
        int temp = n;
        int count = 0;
        if(n%4==0) count++;
        if(n%5==0) count++;
        int max = Math.floorDiv(n, 4);
        for (int i = 1; i <= max; i++) {
            int numfour = max-i;
            int fourval = 4*numfour;
            int fival = i*5;
            int sum = fourval + fival;
            if(sum>n){
                max--;
            }
            if(sum==n&&fourval>0&&fival>0) count++;
        }
        System.out.println(count);
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
