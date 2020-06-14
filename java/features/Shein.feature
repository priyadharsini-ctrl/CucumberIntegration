Feature: Shein Practice
Scenario: Choose Jeans

Given open https://www.shein.in/
And Mouseover on Clothing and click Jeans
And Choose Black under Jeans product count
And check size as medium
And check whether the color is black
And Click first item to Add to Bag
And Click the size as M abd click Submit
When Click view Bag
Then Check the size is Medium or not and close browser