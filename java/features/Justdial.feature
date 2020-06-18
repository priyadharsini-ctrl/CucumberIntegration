Feature: JustDial practice

Scenario Outline: Book Air Ticket

Given open url
And Click on Air Tickets
And Type <fromArea> and choose Chennai, IN - Chennai Airport as Leaving From
And Type <toArea> and select Toronto, CA - Toronto City Centre Airport as Going To
And Set Departure as 2020, July 22
And Add Adult 2, Children 1 click and Search
And Select Air Canada from multi-airline itineraries
And Click on Price to sort the result
And Click on Details of first result under Price
And Capture the Flight Arrival times
And Capture the total price in a list and Click on Book
When Capture the Airport name base on the list of time
Then Capture the total fare and print the difference amount from previous total price

Examples:
|fromArea||toArea|
|Chennai||Toronto|