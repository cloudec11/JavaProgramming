import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EscapeRoom {

    private static class Breadcrumb {
        public Breadcrumb prev;
        public int target;
    }

    private static class IntList {
        public int[] arr = new int[2];
        public int start = 0;
        public int end = 0;

        public void add(int num) {
            if(end + 1 > arr.length) {
                resize(arr.length << 1);
            }
            arr[end++] = num;
        }

        private void resize(int capacity) {
            int[] newArr = new int[capacity];

            System.arraycopy(arr, start, newArr, 0, end - start);
            arr = newArr;

            end -= start;
            start = 0;
        }

        public int get(int index) {
            return arr[start + index];
        }

        public int removeFirst() {
            return arr[start++];
        }

        public int size() {
            return end - start;
        }
    }

    public static void main(String[] args) throws IOException {

        //So what I'm gonna do is go backwards, from the bottom right (which is where we need to get)
        //and look at the coordinates, multiply them together to get TARGET then look at where TARGET is
        //found on the grid, and take those coordinates and repeat in a depth-first-search.

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Get the size of the grid
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        //Define the grid
        int[][] grid = new int[rows][cols];

        //Make a map of certain numbers to the possible next numbers
        //It's just a map of number to a list of the products of the coordinates
        //of everywhere that number can be found on the grid
        Map<Integer, IntList> numMap = new HashMap<>(rows * cols);

        //Build the map's data
        //Loop through the rows
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];

            String[] nums = reader.readLine().split(" ");

            //Loop through the columns
            for (int j = 0; j < row.length; j++) {

                //For every position on the grid

                int num = grid[i][j] = Integer.parseInt(nums[j]);

                //I am using an IntList for better memory usage (int[] instead of Integer[], 4 bytes vs 16 bytes)
                //However you can use any list and it will be just fine
                IntList list = numMap.computeIfAbsent(num, k -> new IntList()); //If you're new to java, this line just puts a value (new IntList()) if one doesn't exist

                //Add the target of the position on the grid
                list.add((i + 1) * (j + 1));
            }
        }

        //Create the root (first) breadcrumb
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.target = rows * cols;
        breadcrumb.prev = null;

        //While we have not made it to the starting line (0, 0) and we haven't run out of options
        while(breadcrumb != null && breadcrumb.target != grid[0][0] && !numMap.isEmpty()) {

            //Get the possible next positions on the grid
            IntList possible = numMap.get(breadcrumb.target);

            //If we have no possible next positions, move back to the previous breadcrumb and try again
            if(possible == null || possible.size() == 0) {
                numMap.remove(breadcrumb.target); //The list is empty (or null), might as well remove it
                breadcrumb = breadcrumb.prev;
                continue;
            }

            //Get the first choice and remove it (so we don't backtrack, or waste time on options we've already explored)
            int choice = possible.removeFirst();

            //Create a new breadcrumb and add it to the end of the chain
            Breadcrumb breadcrumb1 = new Breadcrumb();

            //Our next target
            breadcrumb1.target = choice;

            //Add to chain
            breadcrumb1.prev = breadcrumb;

            breadcrumb = breadcrumb1;
        }

        reader.close();

        //If we've found a route, then breadcrumb will not be null
        if(breadcrumb != null || rows == 1 && cols == 1) {
            System.out.println("yes");
        } else { //Else, we did not make it, it will have backtracked all the way until breadcrumb = null
            System.out.println("no");
        }
    }
}