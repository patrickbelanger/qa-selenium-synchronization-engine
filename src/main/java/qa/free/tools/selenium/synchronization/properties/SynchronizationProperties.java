package qa.free.tools.selenium.synchronization.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:synchronization.properties" })
public interface SynchronizationProperties extends Config {


	@Key("synchronization.maximum.retry.attempts")
	@DefaultValue("3")
	int getMaximumRetryAttempts();	
	
	@Key("synchronization.webdriver.wait.timeout")
	@DefaultValue("10")
	int getTimeout();

}
