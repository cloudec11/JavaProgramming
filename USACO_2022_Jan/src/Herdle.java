import java.io.*;
import java.util.*;

public class Herdle {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        String a, b, c, d, e, f;
        int i  = 1, green = 0, yellow = 0, dc = 0, ec = 0, fc = 0;
        a = next(); b = next(); c = next(); d = next(); e = next(); f = next();
        HashMap<Character, Integer> count = new HashMap<>();
        //green
        for (int j = 0; j < a.length(); j++) {
            int temp = green;
            for (int k = 0; k < d.length(); k++) {
                if(k==j){
                    if(a.charAt(j)==d.charAt(k)){
                        green++;
                    }
                }
            }
            if(green!=temp && !count.containsKey(a.charAt(j))){
                i = 1;
                count.put(a.charAt(j), i);
            }
            else if(green!=temp){
                count.replace(a.charAt(j), i, i++);
            }
        }
        for (int j = 0; j < b.length(); j++) {
            int temp = green;
            for (int k = 0; k < e.length(); k++) {
                if(k==j){
                    if(b.charAt(j)==e.charAt(k)){
                        green++;
                    }
                }
            }
            if(green!=temp && !count.containsKey(b.charAt(j))){
                count.put(b.charAt(j), 1);
            }
            else if(green!=temp){
                count.replace(b.charAt(j), i, i++);
            }
        }
        for (int j = 0; j < c.length(); j++) {
            int temp = green;
            for (int k = 0; k < f.length(); k++) {
                if(k==j){
                    if(c.charAt(j)==f.charAt(k)){
                        green++;
                    }
                }
            }
            if(green!=temp && !count.containsKey(c.charAt(j))){
                count.put(c.charAt(j), 1);
            }
            else if(green!=temp){
                count.replace(c.charAt(j), i, i++);
            }
        }
        //yellow
        for(char C : d.toCharArray()){
            if(count.containsKey(C)){
                dc++;
                if(dc<=count.get(C))
                    yellow++;
            }
        }
        for(char C : e.toCharArray()){
            if(count.containsKey(C)){
                ec++;
                if(ec<=count.get(C))
                    yellow++;
            }
        }
        for(char C : f.toCharArray()){
            if(count.containsKey(C)){
                fc++;
                if(fc<=count.get(C))
                    yellow++;
            }
        }
        System.out.println(green);
        System.out.println(yellow);
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
