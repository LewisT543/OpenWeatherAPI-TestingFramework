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

- isFeelsLike{units}GreaterThanMin() 

- Example for isFeelsLike field:
>responseDTO.isFeelsLikeMetricGreaterThanMin()
>
>Will return a boolean value that checks if the getFeelsLike in units field is greater than or equal to its min

>For units: Standard,  Metric, Imperial 
