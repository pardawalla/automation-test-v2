package cucumber.stepDefinitions;


import io.cucumber.java.en.Given;

public class SearchMotorsTest {
    @Given("I am on the TradeMe Motors search page")
    public void browserIsOpen() {
        System.out.println("mapping successful");
    }

}
