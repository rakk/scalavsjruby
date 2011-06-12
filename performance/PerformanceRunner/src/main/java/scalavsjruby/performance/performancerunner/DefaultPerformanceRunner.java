package scalavsjruby.performance.performancerunner;

import scalavsjruby.performance.performanceinterface.Performance;
import org.apache.commons.lang.StringUtils;

public class DefaultPerformanceRunner implements PerformanceRunner {

    private String description;
    private long start = 0;
    private long end = 0;
    private boolean started = false;

    @Override
    public void performance(Performance performance) {
        setAndCheckDescription(performance);

        if (started) {
            stopWorkPerfomanceWasAlreadyStarted();
        }

        started = true;
        startTime();
        performance.performance();
        endTime();
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public long getDelta() {
        if (!started) {
            stopWorkPerfomanceWasNotStarted();
        }
        return (long) (this.end - this.start);
    }

    @Override
    public String getFullInfo() {
        return this.description + "\t" + getDelta();
    }
    
    protected void setAndCheckDescription(Performance performance) {
        if (StringUtils.isBlank(this.description)) {
            if(performance != null && StringUtils.isNotBlank(performance.getDescription())){
                setDescription(performance.getDescription());
            }else {
                stopWorkDescriptionIsNotSetUp();
            }
        }
    }


    protected void endTime() {
        this.end = System.currentTimeMillis();
    }

    protected void startTime() {
        this.start = System.currentTimeMillis();
    }

    protected void stopWorkPerfomanceWasAlreadyStarted() {
        throw new RuntimeException(this.description + " already performed!");
    }

    protected void stopWorkPerfomanceWasNotStarted() {
        throw new RuntimeException(this.description + " was not started yet!");
    }

    protected void stopWorkDescriptionIsNotSetUp() {
        throw new RuntimeException("description is blank!");
    }
}
