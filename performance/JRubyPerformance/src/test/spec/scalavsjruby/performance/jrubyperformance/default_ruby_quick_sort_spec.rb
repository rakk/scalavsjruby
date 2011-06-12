require File.join(File.dirname(__FILE__), '..', '..', '..', '..', '..', 'main', 'ruby', 'scalavsjruby', 'performance', 'jrubyperformance', 'DefaultRubyQuickSort.rb')

require 'java'

java_import 'scalavsjruby.tools.sorting.SortUtils'
java_import 'java.util.ArrayList'

describe DefaultRubyQuickSort do
  before(:each) do
    @defaultRubyQuickSort = DefaultRubyQuickSort.new
    @sortUtils = SortUtils.new
  end

  it "should not be nil" do
    @defaultRubyQuickSort.should_not be_nil
  end
  
  it "should save description" do
    @defaultRubyQuickSort.get_description().should == ""
    test_description = "some test description"
    @defaultRubyQuickSort.set_description(test_description)
    @defaultRubyQuickSort.get_description().should == test_description
  end
  
  it "should sort properly" do
    SortUtils.TESTING_MAP.keySet().each do |key|
      @defaultRubyQuickSort.sort(key.to_a).should == SortUtils.TESTING_MAP.get(key).to_a
    end
    
    (0..20000).step((13 * 11 * 17) + 1) do |i|
      unsortedList = @sortUtils.generateIntegers(i)
      sortedList = @defaultRubyQuickSort.sort(unsortedList.to_a)
      i.should == sortedList.size
      
      # cannot convert instance of class org.jruby.java.proxies.ArrayJavaProxy to interface java.util.List
      # :(
      # @sortUtils.areIntegersSorted(sortedList.to_java(Java::JavaLang::Integer)).should == true
    end
  end

end