# automation-test-v2

## Overview
This project has two main set of tests that are currently working.
- ***Web UI tests:*** found in src/test/java/automation/test/searchUsedCars.java. 
  - The tests here checkt that from the TradeMe website, `https://api.tmsandbox.co.nz`, you can search for an existing used cars listing, select the listing from the search result and verify `Number plate`, `Kilometers`, `Body` and `Seats` for the listing. 
  - Currently only supports the Chrome Browser
- ***API tests:*** found in src/test/java/apiTests/sendRequests.java:
  - Using the TradeMe Sandbox environment retrieve a list of charities, and confirm that `St John` is included in the list.
  - Send a post request, and verify that it was successful using httpbin.org

### Notes
The initial aim here was to write these tests using Java, and Selenium. Preferably in BDD style using [Cucumber](https://cucumber.io/docs/tools/general/). However, was unable to integrate Cucumber and was not able to write the tests in the BBD style. However, a feature file is there to give an example of what the BBD scenarios might have looked like. The issue was that for some reason I could not bind the scenario with the step definitions, and when trying to run the BBD style tests, I would get the error;
```
Exception occured while running tests.
java.lang.RuntimeException: No test found to run.
	at com.microsoft.java.test.runner.testng.TestNGLauncher.execute(TestNGLauncher.java:29)
	at com.microsoft.java.test.runner.Launcher.main(Launcher.java:57)
```

Along with Selenium the [TestNG](https://testng.org/doc/) test framework was used. Selected TestNG over JUnit, as TestNG seemd a better option as it could run tests in parallel, and from the quick google search, it seemed to be a better long term alternative to JUnit. 

For the API [HTTPClient v5](https://hc.apache.org/httpcomponents-client-5.2.x/index.html) was used. 

Initially for API tests tried to use the [Rest-assured](https://rest-assured.io/) library. However, was unable to use it as kept on getting the error `The import io.restassured.RestAssured cannot be resolved`, and was unable to fix it. After which decided to use the Apache HTTPClient v5 library instead.  


## To run the tests 
- Download the appropriate version of the ChromeDriver, (based on your OS and Chrome ver), from [here](https://chromedriver.chromium.org/downloads), and place it the `../src/test/resources/drivers/` folder. Please ensure the name of the dirver is `chromedriver`, and not `chromedriver.exe` etc. 
- Check the listing `https://www.trademe.co.nz/a/motors/cars/toyota/c-hr/listing/3855382911` still exists. If not then choose another listing and update the test data as appropriate in `../src/test/java/automation/test/searchUsedCars.java` file.


## Dev Environment used for development and testing
- OS: macOS Ventura 13.1
- Code Editor: [Visual Studio Code Version: 1.74.2 (Universal)] (https://code.visualstudio.com/).
- Browser: Chrome Version 108.0.5359.124 (Official Build) (x86_64)

Theorectially you should be able to run these tests on Windows/Linux/Mac using VSCode editor or other editors/IDEs.  However, using VSCode might be the easiest way to run these tests. 


## Requirements:
- Java JDK: 17 
    JAVA JDK used:
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
    OpenJDK 64-Bit Server VM Temurin-17.0.5+8 (build 17.0.5+8, mixed mode, sharing)
- Maven: 3.8.7
    https://maven.apache.org/install.html
    https://maven.apache.org/download.cgi
- TestNG: 7.7.1
- Selenium: 4.7.2
- Cucumber: https://cucumber.io/docs/tools/general/
- Aapache HttpClient 5.2.1 API
  - https://hc.apache.org/httpcomponents-client-5.2.x/index.html
  - https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5/apidocs/overview-summary.html
- [Jackson v2.14.1](https://github.com/FasterXML/jackson)

### If using the VS Code editor (optional)
- Visual Studio Extensions 
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)


## References: 
- [Selenium WebDriver Basic - Full Course, LetCode with Koushik](https://www.youtube.com/watch?v=rDd16R96w4s&t=5360s)
- [How do I setup VSCODE to create Maven projects?](https://stackoverflow.com/questions/62163474/how-do-i-setup-vscode-to-create-maven-projects)
- [Create maven java project in VSCode and run testNG tests](https://www.youtube.com/watch?v=BBy8vLYZbqM)
- [Configuringing env variables on Mac](https://stackoverflow.com/questions/22842743/how-to-set-java-home-environment-variable-on-mac-os-x-10-9)
- [Apache HttpClient 5 tutorial](https://www.springcloud.io/post/2022-08/httpclient5/#gsc.tab=0)
- [Cucumber tutorial, LetCode with Koushik]( https://www.youtube.com/watch?v=YzJBiqfISU8&list=PL699Xf-_ilW6oK3_otMtu7BPqiy0VlkE-&index=2)
- [Automation Testing With Selenium, Cucumber & TestNG](https://www.lambdatest.com/blog/automation-testing-with-selenium-cucumber-testng/?utm_source=github&utm_medium=repo&utm_campaign=cucumber-testng-sample)
- https://httpbin.org/


## TODO:
- [ ] Figure out why cucumber tests are not running. 
- [ ] Create Utils, e.g. SeleniumUtils, RESTUtils.
- [ ] Move test data to a different file.
- [ ] Figure out javadocs and added them for better documentation.
- [ ] See if I can get it working with RESTAssured.
