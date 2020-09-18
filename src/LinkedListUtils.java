import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

    public static void insertSorted(LinkedList<Integer> list, int value) {
        if (list != null) {
            if (list.size() == 0) {
                list.add(value);
            } else {
                Iterator<Integer> iterator = list.iterator();
                boolean isInserted = false;
                int index = 0;

                while (iterator.hasNext() && !isInserted) {
                    if (iterator.next() >= value) {
                        list.add(index, value);
                        isInserted = true;
                    }
                    index++;
                }
                if (index == list.size()) {
                    list.add(value);
                }
            }
        }
    }

    public static void removeMaximumValues(LinkedList<String> list, int N) {
        if (list != null) {
            if (N > 0 && list.size() > 0) {
                int valuesCount = 0;

                while (valuesCount < N && !list.isEmpty()) {
                    String maximum = list.getFirst();
                    Iterator<String> iterator = list.iterator();

                    // Find maximum
                    while (iterator.hasNext()) {
                        String current = iterator.next();
                        if (current.compareTo(maximum) > 0) {
                            maximum = current;
                        }
                    }

                    // Delete maximum occurrences
                    if(list.removeAll(new LinkedList<String>(Arrays.asList(maximum)))) {
                        valuesCount++;
                    }
                }
            }
        }
    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
        // STILL BUGGY. HONESTLY A RIP-OFF FROM HERE: https://www.geeksforgeeks.org/sublist-search-search-a-linked-list-in-another-list/
        boolean result = false;
        if (one != null && two != null) {
            if (two.size() <= one.size()) {
                int twoCursor = 0;

                while (twoCursor < two.size()) {
                    int twoCurrent = two.get(twoCursor);
                    int oneCursor = 0;

                    while (oneCursor < one.size()) {
                        if (twoCursor == two.size()) {
                            return false;
                        } else if (one.get(oneCursor) == twoCurrent) {
                            oneCursor ++;
                            twoCursor++;
                        } else {
                            break;
                        }
                    }

                    if (oneCursor == one.size()) {
                        return true;
                    }

                    oneCursor = 0;
                    twoCursor++;
                }
            }
        }


        return result; // this line is here only so this code will compile if you don't modify it
    }
}
