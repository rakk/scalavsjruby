package scalavsjruby.tools.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SortUtils {

    public static Map<List<Integer>, List<Integer>> TESTING_MAP = new HashMap<List<Integer>, List<Integer>>() {

        private static final long serialVersionUID = 4683745979423970687L;

        {
            put(Arrays.asList(new Integer[]{-2, -3, -10, 5, 1, 4}),
                    Arrays.asList(new Integer[]{-10, -3, -2, 1, 4, 5}));
            put(Arrays.asList(new Integer[]{2, 2, 1, 1, 3, 3, 3, 4, 5}),
                    Arrays.asList(new Integer[]{1, 1, 2, 2, 3, 3, 3, 4, 5}));
            put(Arrays.asList(new Integer[]{-2, 2, 1, 1, 3, 3, 3, 4, 5, 6, 6, 6, 6, 6}),
                    Arrays.asList(new Integer[]{-2, 1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 6, 6}));
            put(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
                    Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
            put(Arrays.asList(new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}),
                    Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));

        }
    };
    private Random generator = new Random();

    public int generateInteger() {
        return generator.nextInt();
    }

    public List<Integer> generateIntegers(int size) {
        List<Integer> result = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            result.add(generateInteger());
        }
        return result;
    }

    public boolean areIntegersSorted(List<Integer> list) {
        boolean result = true;
        if (list.size() > 0) {
            for (int i = 1; i < list.size() && result; i++) {
                if (list.get(i - 1) > list.get(i)) {
                    result = false;
                }
            }
        }
        return result;
    }

    public List<Integer> generateSortedIntegers(int size) {
        List<Integer> result = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++){
            result.add(i - (size/2));
        }
        return result;
    }

    public List<Integer> generateReversedIntegers(int size) {
        List<Integer> result = generateSortedIntegers(size);
        Collections.reverse(result);
        return result;
    }
}
