package ProgrammierSerie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TravelingSalesPerson {

    /**
     * Computes the minimal traveling cost of a journey visiting all towns in acircle
     *
     * @param distances matrix containing all traveling costs
     * @return minimal costs necessary to visit all towns and then return tohometown
     * @throw IllegalArgumentException if input matrix is {@code null} or is nota square matrix.
     */
    public static int solve(int[][] distances) {
        //test if matrix is square
        int n = distances.length;
        for (int i = 0; i < n; i++) {
            if (distances[i].length != n) {
                throw new IllegalArgumentException("Matrix is not square)");
            }
        }
        Map<ArrayList<Integer>, Integer> table = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        list.add(0);
        fillTable(distances, list, table);
        int result = table.get(list);


        return result;


    }


    /**
     * Fill the recursive formula for the TSP problem. This method receives a list of citys to visit and a
     * dedicated final destination, where the journey is supposed to end (encoded into one list). It then is saved
     * into a hashmap. Table is filled with memoization so if the same subproblem is called more than once, nothing happens.
     * Starting city has to be 0.
     *
     * @param distances matrix storing all the costs from one city to another.
     * @param list      List containing all citys to be visited in ascending order and last city to visit as a separate entry
     * @param table     table to fill the solution of the current problem.
     */
    private static void fillTable(int[][] distances, ArrayList<Integer> list, Map<ArrayList<Integer>, Integer> table) {
        //memoization -if we've computed the table entry we don't do anything
        if (table.get(list) != null || list.size() < 2) {
            return;
        }
        //base case if only one city remains and it is supposed to be the last stop
        else if (list.size() == 2 && list.get(0) == list.get(1)) {
            //solution is distance to starting point.
            table.put(list, distances[0][list.get(0)]);

        } else {
            int value = Integer.MAX_VALUE;
            //remove city that is supposed to be the last visited.
            int lastStop = list.get(list.size() - 1);
            list.removeAll(Arrays.asList(lastStop));

            //recursively check all required subproblems
            for (int i = 0; i < list.size(); i++) {
                int candidate = list.get(i);
                //consider the problem with each city as candidate for the new last stop
                list.add(list.size(), candidate);
                fillTable(distances, list, table);
                //find the best solution so far, also considering distance to the final destination.
                value = Math.min(value, table.get(list) + distances[candidate][lastStop]);
                //remove last candidate for next iteration
                list.remove(list.size() - 1);
            }
            //insert the city that is supposed to be the last stop of current iteration
            sortedInsert(list, lastStop);
            list.add(list.size(), lastStop);
            //insert value in table for memoization
            table.put(list, value);

        }

    }

    /**
     * Helpmethod to insert an integer into a sorted list in the right spot. In case the input integer is already in the list, this does nothing.
     *
     * @param list List of integers, should be sorted for expected outcome.
     * @param x    value to put into the list.
     */
    public static void sortedInsert(ArrayList<Integer> list, int x) {
        //iterate through list
        for (int i = 0; i < list.size(); i++) {
            int y = list.get(i);
            //when current value is bigger we insert value here
            if (y > x) {
                list.add(i, x);
                return;
            } else if (y == x) {
                //if we find input value x we don't do anything
                return;
            }

        }
        //in case we find no larger entry or x itself, then x becomes the last entry
        list.add(list.size(), x);
    }

}