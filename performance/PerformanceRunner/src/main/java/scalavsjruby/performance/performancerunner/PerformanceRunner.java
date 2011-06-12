package scalavsjruby.performance.performancerunner;

import scalavsjruby.performance.performanceinterface.Performance;

public interface PerformanceRunner {
	public void setDescription(String description);
	public void performance(Performance performance);
	public long getDelta();
	public String getFullInfo();
}
