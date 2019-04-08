# Webcrawler Application

You can sign up for flight offers from origin to destination and as soon as there is a new better offer, you will receive email.

# Crawler API

Offer model: 
{
	"origin": "sof",             //flight origin (string)
	"destination": "lon",        //flight destination (string)
	"flightNumber": "sfln2",     //flightNumber (string(unique))
	"timestamp": 1561892400000,  //milliseconds of the flight time (long - 64bit number)
	"price": 40.50               //price of the offer (double)
}

The crawler API can save new offers at "http://...../api/flight/new" and is expecting a POST Http request.
Whenever you add a new offer, the Crawler checks if the offer can fir one of the users that are signed up and if it is the best offer for the user and if the user has not received an email in the time period selected by the user, the user will receive the offer on email.

Also you can get all saved offers at "http://.../api/flight/all" with a GET Http request,
you can get all offers for a specific origin and destination at "http://.../api/flight/specific?origin=yourOrigin&destination=yourDestination" with a GET Http request and "yourOrigin" and "yourDestination" changed with the origin and destination required,
you can get the offer with lowest pricefor a specific origin and destination at "http://.../api/flight/lowest?origin=yourOrigin&destination=yourDestination" with a GET Http request and "yourOrigin" and "yourDestination" changed with the origin and destination required.
