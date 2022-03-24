import java.io.*;
import java.util.*;
public class j3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        String n =  next();
        String out="";
        int num = 1;
        int test = 0;
        int state;
        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i < n.length(); i++) {
            test =0;
            out = "";
            state = 0;
            try{
                test = Integer.parseInt(String.valueOf(n.charAt(i)));
            }catch(Exception e){}
            if(n.charAt(i) != '+' && n.charAt(i)!='-' && test == 0) {
                chars.add(n.charAt(i));
            }
            if(n.charAt(i)=='+') state = 1;
            if(n.charAt(i)=='-') state = 2;
            if(state==1){
                for (char l: chars
                ) {
                    out+=l;
                }
                num = Integer.parseInt(String.valueOf(n.charAt(i+1)));
                System.out.println(out+" tighten " + num);
                chars.clear();
            }else if(state == 2){
                for (char l: chars
                ) {
                    out+=l;
                }
                num = Integer.parseInt(String.valueOf(n.charAt(i+1)));
                System.out.println(out+" loosen " + num);
                chars.clear();
            }
        }

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

