# automation-test-v2
This project has two main set of tests that are currently working.
- Web UI tests, found in src/test/java/automation/test/Tests.java. The tests here checkt that from the TradeMe website, `https://api.tmsandbox.co.nz`, you can search for an existing used cars listing, select a listing from the search result and verify some of the data found on the selected listings. 
- API tests, found in src/test/java/apiTests/test0.java:
    - Using the TradeMe Sandbox environment retrieve a list of charities, and confirm that `St John` is included in the list.
    - Send a post request, and verify that it was successful using httpbin.org


To run the tests 
- Download the appropriate version of the ChromeDriver from [here](https://chromedriver.chromium.org/downloads), and Then update the value for the path in `Tests.java` file with the correct absoulte path. This will be found in the `@BeforeClass`.

The aim here was to write these tests using Java, and Selenium. Preferably in BDD style using [Cucumber] (https://cucumber.io/docs/tools/general/). 
The (https://testng.org/doc/) test framework was used.
For the API [HTTPClient v5] (https://hc.apache.org/httpcomponents-client-5.2.x/index.html) was used. 

Initially for API tests tried to use the [Rest-assured] (https://rest-assured.io/) library. However, was unable to use it as kept on getting the error `The import io.restassured.RestAssured cannot be resolved`, and was unable to fix it. After which decided to use the HTTPClient v5 library instead.  

Also was unable to integrate Cucumber, and write the tests in BBD Style.

The code was tested and written in the Following Dev Environment:
- OS: macOS Ventura 13.1
- IDE: [Visual Studio Code Version: 1.74.2 (Universal)] (https://code.visualstudio.com/).

Theorectially you should be able to run these tests on Windows/Linux/Mac using VSCode or another IDE. However, using VSCode might be the easiest way to run these tests. 


## Requirements:
- Java JDK: 17 
    JAVA JDK used:
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment Temurin-17.0.5+8 (build 17.0.5+8)
    OpenJDK 64-Bit Server VM Temurin-17.0.5+8 (build 17.0.5+8, mixed mode, sharing)
- Maven: 3.8.7
    https://maven.apache.org/install.html
    https://maven.apache.org/download.cgi
- TestNG: 7.7.0
- Selenium: 4.7.2
- Cucumber: https://cucumber.io/docs/tools/general/
- Visual Studio Extensions 
  - [Extension Pack for Java] (https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)


## References: 
- https://stackoverflow.com/questions/62163474/how-do-i-setup-vscode-to-create-maven-projects
- https://www.youtube.com/watch?v=BBy8vLYZbqM
- [Configuringing env variables on Mac] (https://stackoverflow.com/questions/22842743/how-to-set-java-home-environment-variable-on-mac-os-x-10-9)


## TODO:
- [ ] Figure out why cucumber tests are not running. 
- [ ] Create Utils, e.g. SeleniumUtils, RESTUtils.
- [ ] Move test data to a different file.
- [ ] Figure out javadocs and added them for better documentation.