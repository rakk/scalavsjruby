package scalavsjruby.performance.performanceinterface;

public interface Performance {
	public void init();
	public void performance();
        public String getDescription();
        public void setDescription(String description);
}
