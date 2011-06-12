import scalaj.collection.Imports._
import scalavsjruby.performance.performanceinterface.Performance
import scalavsjruby.tools.GeneralUtils
import scalavsjruby.tools.sorting.SortingHelper
import scalavsjruby.tools.sorting.SortingTask


package scalavsjruby.performance.scalaperformance {
  class DefaultScalaMemoryConsumer extends Performance {
    
    var _name: java.lang.String = _
    
    var _maxVerticalSize:Int = _
    
    var _maxHorizontalSize:Int = _
	
    def getDescription(): java.lang.String = { _name }
	
    def setDescription(name: java.lang.String) { _name = name }
    
    def getMaxVerticalSize(): Int = { _maxVerticalSize }
	
    def setMaxVerticalSize(maxVerticalSize: Int) { _maxVerticalSize = maxVerticalSize }
    
    def getMaxHorizontalSize(): Int = { _maxHorizontalSize }
	
    def setMaxHorizontalSize(maxHorizontalSize: Int) { _maxHorizontalSize = maxHorizontalSize }
    
    def init() { }
    
    
    def performance() = {
      val sortingTask = new SortingTask("scala", "default", getMaxHorizontalSize);
      val list = SortingHelper.getIntegersFromFileOrCreateNewOnes(sortingTask);
      var matrix = new Array[Array[String]](getMaxVerticalSize);
      for(i <- 0 to getMaxVerticalSize() -1) {
        matrix(i) = new Array[String](getMaxHorizontalSize);
        for(j <- 0 to getMaxHorizontalSize() - 1){
          matrix(i)(j) = i + " " + list.get(j)
        }
      }
        
      GeneralUtils.waitAwhile();

      /* just to be sure that JVM will not clean objects*/
      for(i <- 0 to getMaxVerticalSize() -1) {
        for(j <-0 to getMaxHorizontalSize() -1){
          var temp = matrix(i)(j)
        }
      }
    }
  }
}