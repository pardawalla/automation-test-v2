Feature: Search Trademe Motors

    Search Trademe Motors for an existing used car listing and 
    verify the results

    Scenario: Go to the Trademe home page
        Given The browser is open
        When I enter the url "https://www.trademe.co.nz/"
        Then the page is successfully loaded


