# OpenWeatherAPI-TestingFramework
A testing framework designed to test the functionality of the OpenWeatherAPI

# Service Object Model Structure
>The SOM structure was the structure the testing framework followed. The SOM structure is a design pattern which breaks down the service of objects for a REST API. Since the API has a variety of different responses this pattern was chosen.

# Framework Components
#### **Connection Manager**
> The connection manager class is accessed by the testing framework in order to establish a connection to the desired URL. The hashmap takes an enum for the desired endpoint and returns a hashmap with the combined url along with the original query parameters.   
#### **DTO**
> The DTO classes are used in order to identify the design pattern conceived from the API and produce a class which identifies the data and data types of the field within the api and produces getters which allow us to access the fields of the API.

#### **Injector**
> The injector class is the 'tool' used to inject the data from the api into the suitable DTO allowing the framework to get access to the data in each field so that the framework is able to test them.

# How to use
- The first step is to pull the testing framework from the master branch in github
- Once the testing framework has been pulled the next step is to load and build the testing framework via desired application
- The testing framework

# Helper methods

### IsFeelsLike Helper method

- isFeelsLike`{units}`GreaterThanMin() 

- Example for isFeelsLike field:
>responseDTO.isFeelsLikeMetricGreaterThanMin()
>
>Will return a boolean value that checks if the getFeelsLike in units field is greater than or equal to its min

>For units: Standard,  Metric, Imperial 

### is`Bound`TempGreaterThanZero`Unit`
`Bound:`
- Min
- Max

`Unit:`
- Kelvin
- Celcius
- Fahrenheit

#### E.g. isMaxTempGreaterThanZeroCelcius:
- Checks whether the max temperature is greater than
absolute zero.

### Is Min Temp Less Than Max Temp Helper

isMinTempLessThanMaxTempKelvin() 
>will return a boolean that checks the minimum tmperature is less than or equal to maximum temperature.

### is Temp Greater Than Or Equal To Helper
- isTempGreaterThan0Kelvin()
- isTempGreaterThanMinus273Celcius()
- isTempGreaterThanMinus459Fahrenheit()

Example for isTempGreaterThanOrEqualTo usage:
> responseDTO.isTempGreaterThan0Kelvin()
> 
> Will return true if temp called is greater or equal to 0. This should be called when the standard measuring unit is being used.


For units: Standard, Metric, Imperial 

### Is Sunset/Sunrise Helper

is{fieldname}TimeLong()

Example for ID field:
> responseDTO.isSunsetTimeLong()
>
> Will return true if sunset is of Long type given by a OpenWeather call response.

is{fieldname}Time()

Example for ID field:
> responseDTO.isSunsetTime()
>
> Will return true if sunset is not null given by a OpenWeather call response.

### Is Correct City Helper

isCorrectyCity{fieldname}

Example for ID field:
> responseDTO.isCorrectCityID()
>
> Will return true if the city and ID are correct according to the citylist given by OpenWeather

For fields: ID, Longitude, Latitude

### Is Field Greater Than And Less Than Helper

is{Field}GreaterThan{MinValue}AndLessThan{MaxValue}

> responseDTO.isMainHumidityGreaterThan0AndLessThan100()  
> Takes no parameters and returns a boolean, indicating whether the specified field is within the range given.

Fields that use this:
* MainHumidity (min: 0, max: 100)
* CoordLat (min: Minus90, max: 90)
* CoordLon (min: Minus180, max: 180)


### Is Weather Item DTO Valid Helper

> response.isWeatherItemDTOValid(WeatherItemDTO weatherItemDTO)  
> Takes in a weather item DTO object as a parameter and returns a boolean, indicating whether the key value pairs match the weather codes given [here](https://openweathermap.org/weather-conditions)
