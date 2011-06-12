package scalavsjruby.performance.performancerunner;

public class DefaultPerformanceRunnerTest extends AbstractPerformanceRunnerTest{

	@Override
	public PerformanceRunner getPerformanceRunner() {
		return new DefaultPerformanceRunner();
	}	    
}
