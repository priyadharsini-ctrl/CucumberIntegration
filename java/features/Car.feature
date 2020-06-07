Feature: Search Car

Scenario: work in CarWale

Given Go to https://www.carwale.com/
And  Click on Used
And  Select the City as Chennai
And  Select budget min and max and Click Search
And  Select Cars with Photos under Only Show Cars With
And  Select Manufacturer as Hyundai --> Creta
And  Select Fuel Type as Petrol
And  Select Best Match as KM: Low to High
And  Add the least KM ran car to Wishlist
And  Go to Wishlist and Click on More Details
When Print all the details under Overview in the Same way as displayed in applications
Then Close the browser