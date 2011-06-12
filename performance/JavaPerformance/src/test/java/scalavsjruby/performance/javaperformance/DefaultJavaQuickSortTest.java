package scalavsjruby.performance.javaperformance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import scalavsjruby.tools.sorting.SortUtils;

public class DefaultJavaQuickSortTest {
	protected SortUtils sortUtils = new SortUtils();
	
	protected DefaultJavaQuickSort defaultJavaQuickSort = new DefaultJavaQuickSort();

	@Test
	public void testSort() {
		for(List<Integer> key: SortUtils.TESTING_MAP.keySet()){
			assertEquals(SortUtils.TESTING_MAP.get(key), this.defaultJavaQuickSort.sort(key));
		}
		
		int step = (13 * 11 * 17) + 1;
		for (int i = 0; i < 20000; i += step) {
			List<Integer> unsortedList = this.sortUtils.generateIntegers(i);
			List<Integer> sortedList = this.defaultJavaQuickSort
					.sort(unsortedList);
			assertEquals(i, sortedList.size());
			assertTrue("list should be sorted: " + sortedList,
					sortUtils.areIntegersSorted(sortedList));
		}
	}
}
