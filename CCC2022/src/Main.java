import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        Integer[] arr = {1,2,3};
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
        int five = 5;
        System.out.println(list.get(0)+five);
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

