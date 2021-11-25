Feature: Canavas Calculator
	Description: This feature will test functions of Canvas Calculator

@test
Scenario Outline: As a user, I want to perform mathematic Addition Calculation calculations
	Given I am on the calculater screen
	When I provide calculation <problems> as an input
	Then I get <answer> as calculated for given input
	Examples:
	|problems|answer|
	|"1+1="|2|
	|"-1+1="|0|
	|"0.1+0.1="|0.2|
	
@test
Scenario Outline: As a user, I want to perform mathematic Subtraction Calculation calculations
	Given I am on the calculater screen
	When I provide calculation <problems> as an input
	Then I get <answer> as calculated for given input
	Examples:
	|problems|answer|
	|"1-1="|0|
	|"-0.1-0.1="|-0.2|
	
@test
Scenario Outline: As a user, I want to perform mathematic Division Calculation calculations
	Given I am on the calculater screen
	When I provide calculation <problems> as an input
	Then I get <answer> as calculated for given input
	Examples:
	|problems|answer|
	|"0/1="|0|
	|"1/1="|1|