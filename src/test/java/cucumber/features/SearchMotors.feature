Feature: Search Trademe Motors

    Search Trademe Motors for an existing used car listing and
    verify the results

    
    Scenario: Find and verify details of an existing user car listing on Trademe
        Given I am on the TradeMe Motors search page
    #     When I search for an existing listing with the search criteria
    #         | Type | Keywords | Make  | Model | Body Style       |
    #         | Used | MNE951   | Honda | Civic | Coupe, Hatchback |
    #     Then the listing is found and displays
    #         | Number plate | Kilometers | Body      | Seats |
    #         | MNE951       | 36,689km   | Hatchback | 5     |


    # Scenario: St John is in the list of charities
    #     Given the API "https://api.tmsandbox.co.nz/v1/Charities.json"
    #     When I make a GET request
    #     Then the response contains "St John"

    # Scenario: I can make a POST Request
    #     Given the API "https://httpbin.org/anything"
    #     When I make a POST request using the JSON data '{{"Category":"0002-0356-0032-2273-"}, {"Title":"Test Listing"}}'
    #     Then the POST request returns "200"

    # Scenario: Create a TradeMe Listing in the TradeMe on the tmsandbox site
    #     Given the API "https://api.trademe.co.nz/v1/Selling.json"
    #     When I make a POST request using the JSON data '{{"Category":"0002-0356-0032-2273-"}, {"Title":"Test Listing"}}'
    #     Then the listing is successfully created