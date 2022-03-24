import java.io.*;
import java.util.*;
public class s2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        HashMap <String, ArrayList<String>> tog = new HashMap<String, ArrayList<String>>();
        HashMap <String, ArrayList<String>> sep = new HashMap<String, ArrayList<String>>();
        int x = readInt();
        for (int i = 0; i < x; i++) {
            String temp1 = next();
            String temp2 = next();
            if(tog.containsKey(temp1)){
                tog.get(temp1).add(temp2);
            }else{
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(temp2);
                tog.put(temp1, tempArrayList);
            }
            if(tog.containsKey(temp2)){
                tog.get(temp2).add(temp1);
            }else{
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(temp1);
                tog.put(temp2, tempArrayList);
            }

        }
        int y = readInt();
        for (int i = 0; i < y; i++) {
            String temp1 = next();
            String temp2 = next();
            if(sep.containsKey(temp1)){
                sep.get(temp1).add(temp2);
            }else{
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(temp2);
                sep.put(temp1, tempArrayList);
            }
            if(sep.containsKey(temp2)){
                sep.get(temp2).add(temp1);
            }else{
                ArrayList<String> tempArrayList = new ArrayList<>();
                tempArrayList.add(temp1);
                sep.put(temp2, tempArrayList);
            }
        }
        int g = readInt();
        int counter = 0;
        for (int a = 0; a < g; a++) {
            ArrayList<String> current = new ArrayList<String>();

            for (int b = 0; b < 3; b++)
                current.add(next());

            for (int b = 0; b < current.size(); b++) {
                //Friends

                if (tog.containsKey(current.get(b))) {
                    ArrayList<String> currentArray = tog.get(current.get(b));

                    for (int c = 0; c < currentArray.size(); c++) {
                        if (!current.contains(currentArray.get(c))) {
                            counter++;
//							friends.get(currentArray.get(c)).remove(current.get(b));
                        }
                    }
                }


                //Enemies

                if (sep.containsKey(current.get(b))) {
                    ArrayList<String> currentArray = sep.get(current.get(b));

                    for (int c = 0; c < currentArray.size(); c++) {
                        if (current.contains(currentArray.get(c))) {
                            counter++;
//							enemies.get(currentArray.get(c)).remove(current.get(b));
                        }
                    }
                }
            }
        }
        System.out.println(counter/2);



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

