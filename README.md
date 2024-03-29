# qa-selenium-synchronization-engine

A library that facilitates element synchronization and minimizes boilerplate code to make the building of Selenium-powered automation frameworks easier.

## What is this project?

The synchronization engine is a library that facilitates in element synchronization by offering a technique to reduce code boilerplate and return the desired element(s).

To get the desired element, the synchronization will make 3 attempts (or the number of attempts is configuration through the .properties file). An ElementSynchronizationException will be raised in the event that the element cannot be synchronized, allowing you to intervene if necessary.

## Requirement

* Java 11 SDK
* Lombok (Eclipse: Make sure to install the Lombok plugin)
* Selenium 4.9.0

## How to use this project in my Page Object Model classes?

For demonstrate the capability of the synchronization engine, here's an example:

https://www.phptravels.net/

* From the Flight tab, you need to provide the value "YUL" in the "Flying From" input text element, then you need to select the "Pierre Elliott Trudeau YUL" airport from the auto complete suggestion list.

Example using plain Java and Selenium WebDriver

```Java

public void setFlightFrom(String iataCode) {
	WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
	try {
		webDriver().findElement(By.name("from").sendKeys(iataCode);
		List<WebElements> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='autocomplete-result']//b"));
		Optional<WebElement> webElement = webElements.streams().filter(e -> e.equals(iataCode.toUpperCase()).findAny();
		if (webElement.isPresent()) {
			webElement.get().click();
		}
	} catch(Exception e) {
		// Handle synchronization time out and potential issues
	}
}

```

Example using plain Java and the Synchronization Engine


```Java 

public void setFlightFrom(String iataCode) {
	new Synchronize(webDriver).synchronizeWebElement(SynchronizationMethods.ELEMENT_TO_BE_CLICKABLE, By.name("from")).sendKeys(iataCode);
	List<WebElements> webElements = new Synchronize(webDriver).(SynchronizationMethods.VISIBILITY_OF_ALL_ELEMENTS_LOCATED_BY, By.xpath("//div[@class='autocomplete-result']//b"))
		.streams().filter(e -> e.equals(iataCode.toUpperCase()).findAny();
	if (webElement.isPresent()) {
		webElement.get().click();
	}
}

```

Your automation framework can be made more efficient by using the synchronization engine to handle element synchronization. An ElementSynchronizationException, a RuntimeException, will be consequently thrown by the engine if a synchronization problem takes place.

Without the synchronization engine, test automation developers must manage element synchronization manually, which can be a time-consuming and error-prone operation. Your framework will be more effective and simpler to maintain if you use a synchronization engine to drastically minimize the amount of code required to complete this task.

```Java 
public void setFlightFrom(String iataCode) {
	select().customInputList(By.name("from"), By.xpath("//div[@class='autocomplete-result']//b"), iataCode); // The framework method calls the synchronization engine for you, and perform the required action against this element
}
```

## What are the benefits?

1. Simplify the process of synchronizing elements. Give the engine the responsibility of taking care of element synchronization.
2. Reduce drastically flaky test executions; elements are synchronized before performing the wanted action, and the retry mechanism will attempt to recover the execution.
3. Simple configuration: Using the.properties file, you may modify the number of attempts and timeout period to suit the specifics of your project.
4. Reducing boilerplate code (see examples above)
5. Production-ready: SonarQube/Lint is used to scan the code base, and integration testing has test coverage of up to 80%.
6. You can now fully rely on the synchronization engine! This means that you no longer need to manually search for web elements using the `webDriver.findElement(...)` or `webDriver.findElements()` methods. The synchronization engine takes care of this for you automatically.
7. Thread-safe: The synchronization engine is designed for simultaneous multi-threaded execution.
8. Ease the development of Page Object Model classes; easier to read and maintain.

```Java
private final Synchronize synchronize;
private final WebDriver webDriver;

public LoginPage(WebDriver webDriver) {
	this.webDriver = webDriver;
	this.synchronize = new Synchronize(webDriver);
}

private By inputUsername = By.name("username");
private By inputPassword = By.name("password");
private By loginButton = By.name("login-button");

public void setUsername(String username) {
	synchronize
		.synchronizeWebElement(SynchronizationMethods.PRESENCE_OF_ELEMENT_LOCATED, inputUsername)
		.sendKeys(username);
}

public void setPassword(String password) {
	synchronize
		.synchronizeWebElement(SynchronizationMethods.PRESENCE_OF_ELEMENT_LOCATED, inputPassword)
		.sendKeys(password);
}

public void clickButtonLogin() {
	synchronize
		.synchronizeWebElement(SynchronizationMethods.ELEMENT_TO_BE_CLICKABLE, loginButton)
		.click();
}

public void fillLoginScreen(Account account) {
	setUsername(account.getUsername());
	setPassword(account.getPassword());
	clickButtonLogin();
}
```

## What are the common issues this library can solve?

One of the major challenges a developer of test automation faces every day is element synchronization. To address that 
problem, we frequently employ the WebElementWait class across methods in Page Object Model classes. Singleton patterns 
(synchronization helper classes) are occasionally developed to address this problem, however in practice, this results 
in more brittle testing.

The synchronization engine offers a reusable method that helps to tackle this problem and strengthens test cases.

### The code base is scanned using SonarQube/Lint

The goal is creating a library you can trust and rely on.