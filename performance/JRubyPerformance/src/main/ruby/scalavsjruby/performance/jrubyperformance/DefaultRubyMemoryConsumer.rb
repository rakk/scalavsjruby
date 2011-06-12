require 'java'

java_package 'scalavsjruby.performance.jrubyperformance'

java_import 'scalavsjruby.performance.performanceinterface.Performance'
java_import 'scalavsjruby.tools.GeneralUtils'
java_import 'scalavsjruby.tools.sorting.SortingHelper'
java_import 'scalavsjruby.tools.sorting.SortingTask'


class DefaultRubyMemoryConsumer
  java_implements Java::scalavsjruby.performance.performanceinterface.Performance
  
  java_signature 'void init()'
	def init()
    
	end  

  java_signature 'void performance()'
	def performance()
    sorting_task = SortingTask.new("java", "default", get_max_horizontal_size());
    list = SortingHelper.getIntegersFromFileOrCreateNewOnes(sorting_task);
    matrix = [];
       
    (0..get_max_vertical_size).each { |i|
      matrix << []
      (0..get_max_horizontal_size).each { |j|
        matrix[i][j] = i.to_s + " " + list[j].to_s
      }
    }
       
    GeneralUtils.waitAwhile

    #  just to be sure that JVM will not clean objects
    (0..get_max_vertical_size).each { |i|
      (0..get_max_horizontal_size).each { |j|
        temp = matrix[i][j]
      }
    }

	end

  java_signature 'java.lang.String getDescription()'
	def get_description()
    if(@description.nil?)
      java.lang.String.new("memory_consuption;\truby;")
    else
      java.lang.String.new(@description);
    end
	end

  java_signature 'void setDescription(java.lang.String) '
	def set_description(description)
    @description = description
	end
  
  java_signature 'int getMaxVerticalSize()'
  def get_max_vertical_size
    @max_vertical_size
  end

  java_signature 'void setMaxVerticalSize(int)'
  def set_max_vertical_size(maxVerticalSize)
    @max_vertical_size = maxVerticalSize
  end
  
  java_signature 'int getMaxHorizontalSize()'
  def get_max_horizontal_size
    @max_horizontal_size
  end

  java_signature 'void setMaxHorizontalSize(int)'
  def set_max_horizontal_size(maxHorizontalSize)
    @max_horizontal_size = maxHorizontalSize
  end

end

