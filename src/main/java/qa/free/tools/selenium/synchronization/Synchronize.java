package qa.free.tools.selenium.synchronization;

import org.openqa.selenium.support.ui.ExpectedConditions;

import qa.free.tools.selenium.synchronization.exceptions.ElementSynchronizationException;

public class Synchronize {

	private Synchronize() { }
	
	public static ElementSynchronized synchronize(ExpectedConditions expectedConditions) {
		try {
  		return ((ElementSynchronized) 
  				Class.forName(getConditionPackageName(expectedConditions)).getDeclaredConstructor().newInstance());
		} catch(Exception  e) {
			throw new ElementSynchronizationException(e);
		}
	}
	
	private static String getConditionPackageName(ExpectedConditions expectedConditions) {
		return String.format("%s%s", Synchronize.class.getPackageName(), expectedConditions.toString());
	}
	
	
}
