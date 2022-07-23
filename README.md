# qa-selenium-synchronization-engine
A library to ease the development of Selenium's powered automation framework by helping element synchronization and reducing boilerplate code.

## What is this project?

The synchronization engine is a library helping you dealing with elements synchronization by providing a mechanism that reduce code boilerplate and return the wanted element(s). 

Under the hood, the synchronization will perform 3 attempts to get the wanted element (the number of attempts is configuration through the .properties file). If the element cannot be synchronized, an ElementSynchronizationException will be triggered, allowing you to handle the exception if required.

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

As you can see, the synchronization engine reduce the amount of code you need to write to handle element synchronization. If an exception occurs, it will return an ElementSynchronizationException (which is a RuntimeException). 

With the example using plain Java and Selenium WebDriver, as a test automation developer, it's your responsability to handle the synchronization if the element is not synchronized. With the Synchronization engine, it reduce the burden. If implemented at the core of your framework, this will reduce considerably the amount of code required to perform this same exact action.

```Java 
public void setFlightFrom(String iataCode) {
	select().customInputList(By.name("from"), By.xpath("//div[@class='autocomplete-result']//b"), iataCode); // The framework method calls the synchronization engine for you, and perform the required action against this element
}
```
## What are the benefits?

1. Make element synchronization much simpler. Let the engine handling the burden to deal with element synchronization.
2. Easy to configuration: you can adjust the number of attempt(s) and timeout duration using the .properties file to fit the reality of your project.
3. Reduce boilerplate code (see examples above)
4. Production-ready. The code base is scanned using SonarQube/Lint and integration testing have a test coverage up to 80%.

## What are the common issues this library can solve?

Element synchronization is one of the biggest issues a test automation developer has to experience on a daily basis. In Page Object Model classes, we tend to use the WebElementWait class across methods to deal with that issue. Sometimes, singleton patterns (synchronization helper classes) are created to handle this issue, but in reality, this leads to more flaky tests. 

The synchronization engine helps resolve this issue by providing a reusable mechanism, helping to make test cases more resilient.


### The code base is scanned using SonarQube/Lint
The goal is creating a library you can trust and rely on. I'm currently working to increase the test coverage.