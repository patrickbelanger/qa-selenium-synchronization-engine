package qa.free.tools.selenium.synchronization.converter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Converter<T> {

	 private T t;
	
	 public Converter(final T t) {
		 this.t = t;
	 }
	 
	 public By convertAsBy() {
		 return (By) this.t;
	 }

	 public int convertAsInteger() {
		 return (int) this.t;
	 }
	 
	 public String convertAsString() {
		 return (String) this.t;
	 }
	 
	 public WebElement convertAsWebElement() {
		 return (WebElement) this.t;
	 }
	 
}
