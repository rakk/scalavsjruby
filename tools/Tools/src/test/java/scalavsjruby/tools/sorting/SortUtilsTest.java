package scalavsjruby.tools.sorting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class SortUtilsTest {

    private SortUtils generator;
    private static int MAX_SIZE = 20000;
    private float MAX_TIMES = 0.02F; // %

    @Before
    public void before() {
        generator = new SortUtils();
    }

    @Test
    public void generateInteger() {
        List<Integer> generetedIntegers = new ArrayList<Integer>(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            generetedIntegers.add(generator.generateInteger());
        }
        assertEquals(MAX_SIZE, generetedIntegers.size());
        checkIntegersStats(getStats(generetedIntegers), MAX_SIZE, MAX_TIMES);
    }

    @Test
    public void generateIntegers() {
        for (int i = 1500; i < 20000; i += 73) {
            List<Integer> list = generator.generateIntegers(i);
            assertEquals(i, list.size());
            checkIntegersStats(getStats(list), i, MAX_TIMES);
        }
    }

    @Test
    public void areIntegersSorted() {
        Map<List<Integer>, Boolean> map = new HashMap<List<Integer>, Boolean>();
        map.put(Arrays.asList(new Integer[]{-5}), true);
        map.put(Arrays.asList(new Integer[]{17}), true);
        map.put(Arrays.asList(new Integer[]{-5, -3, -2, 0, 1, 1, 6, 7}), true);
        map.put(Arrays.asList(new Integer[]{1, 2, 3}), true);
        map.put(Arrays.asList(new Integer[]{88, 89, 89, 88}), false);
        map.put(Arrays.asList(new Integer[]{88, 89, 90}), true);
        map.put(Arrays.asList(new Integer[]{-70, -60, -30}), true);
        map.put(Arrays.asList(new Integer[]{-10, -12, -5, 1, 3}), false);
        map.put(Arrays.asList(new Integer[]{1, 1, 1, 1, 4, 4, 4, 4, 6, 6}), true);

        for (List<Integer> key : map.keySet()) {
            assertEquals("check list: " + key, map.get(key), generator.areIntegersSorted(key));
        }
    }
    
    @Test
    public void generateSortedIntegers() {
        for (int i = 1500; i < MAX_SIZE; i += 73) {
            List<Integer> list = generator.generateSortedIntegers(i);
            assertEquals(i, list.size());
            assertTrue(generator.areIntegersSorted(list));
            checkIntegersStats(getStats(list), i, MAX_TIMES);
        }
    }
    
    @Test
    public void generateReversedIntegers() {
        for (int i = 1500; i < MAX_SIZE; i += 73) {
            List<Integer> list = generator.generateReversedIntegers(i);
            assertEquals(i, list.size());
            checkIntegersStats(getStats(list), i, MAX_TIMES);
            assertFalse(generator.areIntegersSorted(list));            
            Collections.reverse(list);
            assertTrue(generator.areIntegersSorted(list));
        }
    }

    private Map<Integer, Integer> getStats(List<Integer> list) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (Integer value : list) {
            Integer howManyTimes = result.get(value);
            if (howManyTimes == null) {
                howManyTimes = 1;
            } else {
                howManyTimes++;
            }
            result.put(value, howManyTimes);
        }
        return result;
    }

    private void checkIntegersStats(Map<Integer, Integer> integerStats, int max, float maxTimes) {
        int sum = 0;
        Set<Integer> keys = integerStats.keySet();
        for (Integer key : keys) {
            int times = integerStats.get(key);
            sum += times;
            if (times > (int) (max * maxTimes)) {
                fail("too many times! key: " + key + " times: " + times + " max: " + max + " numbersStats: " + integerStats);
            }
        }
        assertEquals("sum of times should be equal max", sum, max);
    }
}
