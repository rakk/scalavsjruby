package scalavsjruby.performance.performanceinterface;

import org.apache.commons.lang.StringUtils;

public class PerfomanceMock implements Performance{
	private int sleepTime = 0;
        
        private String description = "";
	
	public PerfomanceMock(int sleepTime){
		this.sleepTime = sleepTime;
	}

	@Override
	public void init() {		
	}

	@Override
	public void performance() {
		sleep(1000 * this.sleepTime);
	}
	
	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if(StringUtils.isNotBlank(description)){
            this.description = description + " MOCK";
        }
    }

}