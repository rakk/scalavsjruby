import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Assertions
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import scalavsjruby.performance.scalaperformance.DefaultScalaQuickSort
import scalavsjruby.tools.sorting.SortUtils
import scalaj.collection.Imports._

@RunWith(classOf[JUnitRunner])
class DefaultScalaQuickSortTest extends FlatSpec with ShouldMatchers {
  
  it should "sort given list of Integers" in {
    val defaultScalaQuickSort = new DefaultScalaQuickSort()
    val sortUtils = new SortUtils()
	  
    for (key <- SortUtils.TESTING_MAP.keySet())
      SortUtils.TESTING_MAP.get(key).asScala should equal (defaultScalaQuickSort.sort(key.asScala.toList))	

    val step = (13 * 11 * 17) + 1
    var i = 0
    while (i < 20000) {
      val unsortedList = sortUtils.generateIntegers(i)
      val sortedList = defaultScalaQuickSort.sort(unsortedList.asScala.toList)
      i should equal (sortedList.size) 
      assert(sortUtils.areIntegersSorted(sortedList.asJava))
      i += step
    }
  }
}
