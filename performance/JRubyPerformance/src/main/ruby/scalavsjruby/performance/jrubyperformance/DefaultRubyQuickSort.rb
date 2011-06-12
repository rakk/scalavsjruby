require 'java'

java_package 'scalavsjruby.performance.jrubyperformance'

java_import 'scalavsjruby.performance.performanceinterface.Performance'

class DefaultRubyQuickSort
  java_implements Java::scalavsjruby.performance.performanceinterface.Performance
  
  java_signature 'void init()'
	def init()
	end
  
  java_signature 'void setIntegers(java.util.List<java.lang.Integer>)'
  def set_integers(integers)
    @integers = integers;
  end
  
  java_signature 'java.util.List<java.lang.Integer> getIntegers()'
  def get_integers()
    @integers
  end  

  java_signature 'void performance()'
	def performance()
    sort(@integers)
	end

  java_signature 'java.lang.String getDescription()'
	def get_description()
    if(@description.nil?)
      java.lang.String.new("")
    else
      java.lang.String.new(@description);
    end
	end

  java_signature 'void setDescription(java.lang.String) '
	def set_description(description)
    @description = description
	end

  def sort(unsortedList)
    quicksort(unsortedList, 0, unsortedList.size-1)
  end
 
  private
    def quicksort(list, p, r)
      if p < r then
          q = partition(list, p, r)
          quicksort(list, p, q-1)
          quicksort(list, q+1, r)
      end
      list
    end

    def partition(list, p, r)
      pivot = list[r]
      i = p - 1
      p.upto(r-1) do |j|
        if list[j] <= pivot
          i = i+1
          list[i], list[j] = list[j], list[i]
        end
      end
      list[i+1],list[r] = list[r],list[i+1]
      i + 1
    end
    
end