# tabcorp-betting-app-challenge
Repository for the simulation of a betting app

# How to run
Generate a .war file and deploy it in a tomcat server.

# Save bets
Send a HTTP POST request to ${baseurl}/bets/save with a request body contains a bet list. Following is an exampe request body.

[
	{
		"DateTime" : "2018-01-01 13:36",
		"BetType": "TRIFECTA",
		"PropNumber": 10456,
		"CustomerID" : 1080,
		"Investment": 100.00
	},
	{
		"DateTime" : "2018-01-01 3:56",
		"BetType": "WIN",
		"PropNumber": 10333,
		"CustomerID" : 1081,
		"Investment": 150.50
	}
]

# Generate report: Total investment per bet type
Send a HTTP GET request to ${baseurl}/reports/investment-per-bet-type

# Generate report: Total investment per CustomerID
Send a HTTP GET request to ${baseurl}/reports/investment-per-customer

# Generate report: Total bets sold per bet type
Send a HTTP GET request to ${baseurl}/reports/bets-sold-per-bet-type

# Generate report: Total number of bets sold per hour
Send a HTTP GET request to ${baseurl}/reports/bets-sold-per-hour
