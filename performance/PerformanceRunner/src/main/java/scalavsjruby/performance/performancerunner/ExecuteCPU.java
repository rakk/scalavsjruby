package scalavsjruby.performance.performancerunner;

import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import scalavsjruby.performance.performanceinterface.Performance;
import scalavsjruby.tools.sorting.SortingTask;

class ExecuteCPU {

    public ExecuteCPU() {
    }

    public void execute(StringTokenizer st, String procedureName) {
        SortingTask sortingTask = new SortingTask(st);

        System.out.println("Enter when you are ready...");
        readFromConsole();
        System.out.println(new Date());
        for (int i = 0; i < 10; i++) {
            Performance peformance = ExecuteSort.getPerformanceRunner(sortingTask);
            PerformanceRunner performanceRunner = new DefaultPerformanceRunner();
            performanceRunner.performance(peformance);
        }
        System.out.println(new Date());

    }

    private void readFromConsole() {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        in.close();
    }
}
