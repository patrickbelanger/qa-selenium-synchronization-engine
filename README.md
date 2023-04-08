# qa-selenium-synchronization-engine

A library that facilitates element synchronization and minimizes boilerplate code to make the building of Selenium-powered automation frameworks easier.

## What is this project?

The synchronization engine is a library that facilitates in element synchronization by offering a technique to reduce code boilerplate and return the desired element (s).

To get the desired element, the synchronization will make 3 attempts (the number of attempts is configuration through the .properties file). An ElementSynchronizationException will be raised in the event that the element cannot be synchronized, allowing you to intervene if necessary.

## How to use this project in my Page Object Model classes?

For demonstrate the capability of the synchronization engine, here's an example:

https://www.phptravels.net/

* From the Flight tab, you need to provide the value "YUL" in the "Flying From" input text element, then you need to select the "Pierre Elliott Trudeau YUL" airport from the auto complete suggestion list.

Example using plain Java and Selenium WebDriver

```Java

public void setFlightFrom(String iataCode) {
	WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
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

As you can see, using a synchronization engine makes it easier to handle element synchronization. It will return an 
ElementSynchronizationException if an error occurs (which is a RuntimeException).

If the element in the example using simple Java and Selenium WebDriver is not synced, it is your responsibility as 
a test automation developer to handle the synchronization. By using the synchronization engine, the workload is reduced. 
The amount of code needed to carry out this particular activity will be greatly decreased if integrated 
at the framework's core.

```Java 
public void setFlightFrom(String iataCode) {
	select().customInputList(By.name("from"), By.xpath("//div[@class='autocomplete-result']//b"), iataCode); // The framework method calls the synchronization engine for you, and perform the required action against this element
}
```
## What are the benefits?

1. Simplify the process of synchronizing elements. Give the engine the responsibility of taking care of element synchronization.
2. Simple configuration: Using the.properties file, you may modify the number of attempts and timeout period to suit the specifics of your project.
3. Reducing boilerplate code (see examples above)
4. Able can be produced. SonarQube/Lint is used to scan the code base, and integration testing has test coverage of up to 80%.

## What are the common issues this library can solve?

One of the major challenges a developer of test automation faces every day is element synchronization. To address that 
problem, we frequently employ the WebElementWait class across methods in Page Object Model classes. Singleton patterns 
(synchronization helper classes) are occasionally developed to address this problem, however in practice, this results 
in more brittle testing.

The synchronization engine offers a reusable method that helps to tackle this problem and strengthens test cases.

### The code base is scanned using SonarQube/Lint

The goal is creating a library you can trust and rely on.