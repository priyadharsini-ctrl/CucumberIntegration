Feature: BigBasket Practice

Scenario: Book Rice Product online

Given go to given url
And Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
And Click on Boiled & Steam Rice and get URL of the page
And Choose the Brand as bb Royal
And Go to Ponni Boiled Rice - Super Premium and select 10kg bag from Dropdown
And Click Add button and search Dal
And Go to Toor Dal and select 2kg & set Qty 2
And Select CHennai as City, Alandur-600016,Chennai as Area and click Continue
And Mouse over on My Basket take a screen shot
When Click View Basket and Checkout
Then Click the close button and close the browser