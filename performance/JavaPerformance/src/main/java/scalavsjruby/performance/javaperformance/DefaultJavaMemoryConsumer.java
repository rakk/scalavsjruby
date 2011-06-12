package scalavsjruby.performance.javaperformance;

import java.util.List;
import scalavsjruby.performance.performanceinterface.Performance;
import scalavsjruby.tools.GeneralUtils;
import scalavsjruby.tools.sorting.SortingHelper;
import scalavsjruby.tools.sorting.SortingTask;

public class DefaultJavaMemoryConsumer implements Performance{
    
    private String description = "memory_consuption;\tjava;";
    
    private int maxVerticalSize;
    
    private int maxHorizontalSize;

    public void init() {
    }

    public void performance() {
        SortingTask sortingTask = new SortingTask("java", "default", this.getMaxHorizontalSize());
        List<Integer> list = SortingHelper.getIntegersFromFileOrCreateNewOnes(sortingTask);
        String [][] matrix = new String[getMaxVerticalSize()][this.getMaxHorizontalSize()];
        for(int i = 0; i < getMaxVerticalSize(); i++){
            for(int j = 0; j < getMaxHorizontalSize(); j++){
                matrix[i][j] = i + " " + list.get(j);
            }            
        }
        
        GeneralUtils.waitAwhile();
        
        /* just to be sure that JVM will not clean objects*/
        for(int i = 0; i < getMaxVerticalSize(); i++){
            for(int j = 0; j < getMaxHorizontalSize(); j++){
                String temp = matrix[i][j];
                String temp2 = temp;
                temp = temp2;
            }            
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxVerticalSize() {
        return this.maxVerticalSize;
    }

    public void setMaxVerticalSize(int maxVerticalSize) {
        this.maxVerticalSize = maxVerticalSize;
    }

    public int getMaxHorizontalSize() {
        return this.maxHorizontalSize;
    }

    public void setMaxHorizontalSize(int maxHorizontalSize) {
        this.maxHorizontalSize = maxHorizontalSize;
    }
    
}
