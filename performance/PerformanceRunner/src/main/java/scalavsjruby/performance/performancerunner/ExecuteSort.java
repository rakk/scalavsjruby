package scalavsjruby.performance.performancerunner;

import scalavsjruby.tools.sorting.SortingHelper;
import java.util.StringTokenizer;
import scalavsjruby.performance.javaperformance.DefaultJavaQuickSort;
import scalavsjruby.performance.performanceinterface.Performance;
import scalavsjruby.tools.sorting.SortingTask;
import scalavsjruby.performance.scalaperformance.DefaultScalaQuickSort;
import scalavsjruby.performance.jrubyperformance.DefaultRubyQuickSort;

public class ExecuteSort {
    private SortingTask sortingTask = null;
    
    public void execute(StringTokenizer st, String procedure) {
        sortingTask = new SortingTask(st);
        
        Performance peformance = getPerformanceRunner(sortingTask);

        PerformanceRunner performanceRunner = new DefaultPerformanceRunner();
        performanceRunner.performance(peformance);
        System.out.println(performanceRunner.getFullInfo());
    }

    public static Performance getPerformanceRunner(SortingTask sortingTask) {
        if ("java".equals(sortingTask.getLanguage())) {
            return getJavaPerformanceRunner(sortingTask);
        } else if ("scala".equals(sortingTask.getLanguage())) {
            return getScalaPerformanceRunner(sortingTask);
        } else if ("ruby".equals(sortingTask.getLanguage()) || "jruby".equals(sortingTask.getLanguage())) {
            return getJRubyPerformanceRunner(sortingTask);
        } else {
            throw new RuntimeException("Language unsupported! [" + sortingTask.getLanguage() + "]");
        }
    }

    public static Performance getJavaPerformanceRunner(SortingTask sortingTask) {
        if ("quicksort".equals(sortingTask.getSortingMethod())) {
            DefaultJavaQuickSort defaultJavaQuickSort = new DefaultJavaQuickSort();
            defaultJavaQuickSort.setDescription(sortingTask.getDescription("DefaultJavaQuickSort"));
            defaultJavaQuickSort.setIntegers(SortingHelper.getIntegersFromFileOrCreateNewOnes(sortingTask));
            return defaultJavaQuickSort;
        } else {
            throw new RuntimeException("Sorting sorting method unsupported! [" + sortingTask.getSortingMethod() + "]");
        }
    }

    public static Performance getScalaPerformanceRunner(SortingTask sortingTask) {
        if ("quicksort".equals(sortingTask.getSortingMethod())) {
            DefaultScalaQuickSort defaultScalaQuickSort = new DefaultScalaQuickSort();
            defaultScalaQuickSort.setDescription(sortingTask.getDescription("DefaultScalaQuickSort"));
            defaultScalaQuickSort.setIntegers(SortingHelper.getIntegersFromFileOrCreateNewOnes(sortingTask));
            return defaultScalaQuickSort;
        } else {
            throw new RuntimeException("Sorting sorting method unsupported! [" + sortingTask.getSortingMethod() + "]");
        }
    }

    public static Performance getJRubyPerformanceRunner(SortingTask sortingTask) {
        if ("quicksort".equals(sortingTask.getSortingMethod())) {
            DefaultRubyQuickSort defaultRubyQuickSort = new DefaultRubyQuickSort();
            defaultRubyQuickSort.setDescription(sortingTask.getDescription("DefaultRubyQuickSort"));
            defaultRubyQuickSort.setIntegers(SortingHelper.getIntegersFromFileOrCreateNewOnes(sortingTask));
            return defaultRubyQuickSort;
        } else {
            throw new RuntimeException("Sorting sorting method unsupported! [" + sortingTask.getSortingMethod() + "]");
        }

    }
}
