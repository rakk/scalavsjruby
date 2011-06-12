package scalavsjruby.performance.performancerunner;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import scalavsjruby.performance.performanceinterface.Performance;
import scalavsjruby.performance.javaperformance.DefaultJavaMemoryConsumer;
import scalavsjruby.performance.scalaperformance.DefaultScalaMemoryConsumer;
import scalavsjruby.performance.jrubyperformance.DefaultRubyMemoryConsumer;

class ExecuteMemoryConsumption {

    private int MAX_VERTICAL_SIZE = 2000;
    private int VERTICAL_STEP = 400;
    private int MAX_HORIZONTAL_SIZE = 2000;
    private int HORIZONTAL_STEP = 400;
    private String language = null;

    public void execute(StringTokenizer st, String procedureName) {
        try {
            while (st.hasMoreTokens()) {
                StringTokenizer parameterST = new StringTokenizer(st.nextToken(), "=");
                String parameterName = parameterST.nextToken().toLowerCase();
                String parameterValue = parameterST.nextToken().toLowerCase();
                if ("language".equals(parameterName)) {
                    this.language = parameterValue;
                }
                for (int i = 0; i <= MAX_VERTICAL_SIZE; i += VERTICAL_STEP) {
                    for (int j = 0; j <= MAX_HORIZONTAL_SIZE; j += HORIZONTAL_STEP) {
                        System.out.println("memory [" + i + "] [" + j + "] language=" + this.language);
                        Performance performance = chooseImplementation(i, j);
                        performance.performance();
                    }
                }

            }
        } catch (NoSuchElementException nee) {
            throw new RuntimeException("Missing parameters");
        }
    }

    private Performance chooseImplementation(int verticalSize, int horizontalSize) {
        if ("java".equals(this.language)) {
            DefaultJavaMemoryConsumer djmc = new DefaultJavaMemoryConsumer();
            djmc.setMaxVerticalSize(verticalSize);
            djmc.setMaxHorizontalSize(horizontalSize);
            return djmc;
        } else if ("scala".equals(this.language)) {
            DefaultScalaMemoryConsumer dsmc = new DefaultScalaMemoryConsumer();
            dsmc.setMaxVerticalSize(verticalSize);
            dsmc.setMaxHorizontalSize(horizontalSize);
            return dsmc;
        } else if ("jruby".equals(this.language) || "ruby".equals(this.language)) {
            DefaultRubyMemoryConsumer drmc = new DefaultRubyMemoryConsumer();
            drmc.setMaxVerticalSize(verticalSize);
            drmc.setMaxHorizontalSize(horizontalSize);
            return drmc;
        } else {
            throw new RuntimeException("Language [" + this.language + "] unsupported!");
        }

    }
}
