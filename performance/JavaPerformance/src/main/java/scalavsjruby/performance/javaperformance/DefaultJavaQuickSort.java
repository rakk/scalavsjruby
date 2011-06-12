package scalavsjruby.performance.javaperformance;

import java.util.List;

import scalavsjruby.performance.performanceinterface.Performance;

public class DefaultJavaQuickSort implements Performance {

    private List<Integer> integers;
    private String descritption;
    
    @Override
    public String getDescription() {
        return this.descritption;
    }

    @Override
    public void setDescription(String description) {
        this.descritption = description;
    }


    @Override
    public void init() {
        if (this.integers == null) {
            throw new RuntimeException("integers field is not set!");
        }
    }

    @Override
    public void performance() {
        this.sort(this.integers);
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> sort(List<Integer> values) {
        if (values == null || values.size() == 0) {
            return values;
        }
        return quicksort(values, 0, values.size() - 1);
    }

    private List<Integer> quicksort(List<Integer> list, int low, int high) {
        int i = low, j = high;
        int pivot = list.get(low + (high - low) / 2);

        while (i <= j) {
            while (list.get(i) < pivot) {
                i++;
            }
            while (list.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(list, i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            list = quicksort(list, low, j);
        }
        if (i < high) {
            list = quicksort(list, i, high);
        }

        return list;
    }

    private void exchange(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
