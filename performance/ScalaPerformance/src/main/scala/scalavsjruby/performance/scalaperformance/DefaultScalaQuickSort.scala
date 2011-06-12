import scalaj.collection.Imports._
import scalavsjruby.performance.performanceinterface.Performance

package scalavsjruby.performance.scalaperformance {
  class DefaultScalaQuickSort extends Performance {
    var _list: List[Int] = _
    
    var _name: java.lang.String = _
	
    def getDescription(): java.lang.String = { _name }
	
    def setDescription(name: java.lang.String) { _name = name }
    
    def init() { }
    
    def setIntegers(integerList: java.util.List[java.lang.Integer]){
      _list = integerList.asScala.toList
    }
    
    def performance() = {
      sort(_list)
    }
    
    def sort[A <% Ordered[A]](xs: List[A]):List[A] = {

      if (xs.isEmpty || xs.tail.isEmpty) 
        xs
      else {
        val pivot = xs( xs.length / 2)

        var lows: List[A] = Nil
        var mids: List[A] = Nil
        var highs: List[A] = Nil
        
        for( val item <- xs) {
          if ( item == pivot) 
            mids = item :: mids
          else if (item < pivot)
            lows = item :: lows
          else 
            highs = item :: highs
        }

        sort( lows) ::: mids ::: sort( highs) 
      }
    }
  }
}