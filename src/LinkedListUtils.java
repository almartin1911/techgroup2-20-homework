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
        if (one != null && two != null && one.size() > 0 & two.size() > 0) {
            if (two.size() <= one.size()) {

                for (int i = 0; i < one.size(); i++) {
                    boolean result = true;
                    int iTemp = i;

                    for (int j = 0; j < two.size(); j++) {
                        if (one.get(iTemp) != two.get(j)) {
                            result = false;
                            break;
                        } else {
                            if (iTemp < one.size() - 1) {
                                iTemp++;
                            }
                        }
                    }

                    if (result) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }

        return false;
    }
}
