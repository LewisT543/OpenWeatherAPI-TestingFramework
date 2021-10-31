# OpenWeatherAPI-TestingFramework
A testing framework designed to test the functionality of the OpenWeatherAPI





Firstly it is necessary to declare the below variables, the hashmap that contains the parameters, as well as the response DTO that contains the returned information.

![VariableDeclaration.png](Images%20For%20ReadMe/VariableDeclaration.png)

The connection and parameters need to be instantiated so that they are ready to use.

![BeforeAll.png](Images%20For%20ReadMe/BeforeAll.png)

It is recommended that after each test the Connection manager has its parameters Reset so that there is no data leaked between tests.

![BeforeEach.png](Images%20For%20ReadMe/BeforeEach.png)

Once the set-up has been completed the next step is to select the parameters you wish to use, for a basic search the q parameter can be used via the process shown below

![ExampleTest.png](Images%20For%20ReadMe/ExampleTest.png)

### The available endpoints are shown within the ENDPOINTS enum located in the connection manager

- WEATHER_Q allows searching without specifying a specific search query
- WEATHER_CITY_ID allows searching using the city id as the search parameter
- WEATHER_ZIP allows searching using the zipcode or partial zipcode as the parameter
- BOX is a WIP

![endpoints.png](Images%20For%20ReadMe/endpoints.png)

