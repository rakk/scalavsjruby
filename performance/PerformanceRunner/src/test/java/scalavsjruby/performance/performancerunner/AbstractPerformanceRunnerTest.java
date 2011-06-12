package scalavsjruby.performance.performancerunner;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Assert;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

import scalavsjruby.performance.performanceinterface.PerfomanceMock;
import scalavsjruby.performance.performanceinterface.Performance;

public abstract class AbstractPerformanceRunnerTest {
	Mockery context = new Mockery();

	private PerformanceRunner pr;

	private static final float MARGIN_OF_MISTAKE = 1.5F;

	abstract public PerformanceRunner getPerformanceRunner();

	@Before
	public void before() {
		pr = getPerformanceRunner();
	}

	@Test
	public void perfomanceWithBlankDescriptionRaiseException() {
		try {
			pr.performance(null);
			Assert.fail("empty descirption should raise RuntimeException");
		} catch (RuntimeException re) {
			Assert.assertTrue(re.getMessage(),
					re.getMessage().contains("description is blank!"));
		}
	}

	@Test
	public void perfomanceRunSecondTimeShouldRaiseRuntimeException() {
		pr.setDescription("perfomanceRunSecondTimeShouldRaiseRuntimeException");

		final Performance perfomance = context.mock(Performance.class);

		context.checking(new Expectations() {
			{
				oneOf(perfomance).performance();
			}
		});
		pr.performance(perfomance);
		try {
			pr.performance(perfomance);
			Assert.fail("second perfomance run should raise RuntimeException");
		} catch (RuntimeException re) {
			Assert.assertTrue(re.getMessage(),
					re.getMessage().contains("already performed!"));
		}
		context.assertIsSatisfied();
	}

	@Test
	public void getDeltaRaiseRuntimeExceptionIfPerfomanceWasNotStarted() {
		try {
			pr.getDelta();
			Assert.fail("getDelta should raise RuntimeException if perfomance was not started");
		} catch (RuntimeException re) {
			Assert.assertTrue(re.getMessage(),
					re.getMessage().contains("was not started yet!"));
		}
	}

	@Test
	public void deltaGreaterThanZero() {
		pr.setDescription("deltaGreaterThanZero");

		final Performance perfomance = context.mock(Performance.class);

		context.checking(new Expectations() {
			{
				oneOf(perfomance).performance();
			}
		});
		pr.performance(perfomance);
		Assert.assertTrue(pr.getDelta() >= 0);
		context.assertIsSatisfied();
	}

	@Test
	public void testTime() {
		List<Integer> times = Arrays.asList(new Integer[] { 3, 5, 8 });

		for (Integer i : times) {
			pr = getPerformanceRunner();
			pr.setDescription("description for time " + i);
			pr.performance(new PerfomanceMock(i));
			int expectedTime = i + 1;
			Assert.assertTrue("perfomance took: " + pr.getDelta()
					+ ", but expected: " + expectedTime,
					testMarginOfMistake(pr.getDelta(), expectedTime));
		}
	}
        
	@Test
	public void setDescriptionFromPeformance() {
		List<Integer> times = Arrays.asList(new Integer[] { 3, 5, 8 });

		for (Integer i : times) {
			pr = getPerformanceRunner();
                        Performance performanceMock = new PerfomanceMock(i);
                        performanceMock.setDescription("peformanceDescription");
			pr.performance(performanceMock);
			int expectedTime = i + 1;
			Assert.assertTrue("perfomance took: " + pr.getDelta()
					+ ", but expected: " + expectedTime,
					testMarginOfMistake(pr.getDelta(), expectedTime));
		}
	}

	private boolean testMarginOfMistake(long realTimeMiliseconds,
			int expectedTime) {
		int realTimeInSeconds = (int) (realTimeMiliseconds / 1000);

		if (realTimeInSeconds < 0) {
			throw new RuntimeException(
					"real time (in seconds) is less than zero");
		}

		if (expectedTime < 0) {
			throw new RuntimeException("expected time is less than zero");
		}

		int mistake = Math.abs(realTimeInSeconds - expectedTime);

		return mistake < MARGIN_OF_MISTAKE;
	}
}
