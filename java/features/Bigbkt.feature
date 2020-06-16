Feature: BigBasket Shopping

Scenario Outline: Adding Juices to cart in BigBasket website

Given Click url
And mouse over on Shop by Category
And Go to Beverages and Fruit juices & Drinks
And Click on JUICES
And click Tropicana and Real under Brand
And Check whether the products is availabe with Add button
And Add the First listed available product
And Select Chennai as City, Alandur<Pincode>,Chennai as Area and click Continue
And Mouse over on My Basket print the product name. count and price
When Click View Basker and Checkout
Then Click the close button and close browser

Examples:
|Pincode|
|600016|
