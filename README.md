# OpenWeatherAPI-TestingFramework
A testing framework designed to test the functionality of the OpenWeatherAPI





Firstly it is necessary to set up the connection to the api this is done as shown below.

![img.png](Images%20For%20ReadMe/img.png)

The connection and parameters need to be instantiated so that they are ready to use.

![img_3.png](Images%20For%20ReadMe/img_3.png)

It is recommended that after each test the Connection manager has its parameters Reset so that there is no data leaked between tests.

![img_4.png](Images%20For%20ReadMe/img_4.png)

Once the set-up has been completed the next step is to select the parameters you wish to use, for a basic search the q parameter can be used via the process shown below

![img_6.png](Images%20For%20ReadMe/img_6.png)

### The available endpoints are shown within the ENDPOINTS enum located in the connection manager
- FIND allows searching by coordinates, it also has the optional search paramters of 

![FindParams.png](Images%20For%20ReadMe/FindParams.png) 

*these need better explained*

- WEATHER_Q allows searching without specifying a specific search query
- WEATHER_CITY_ID allows searching using the city id as the search parameter
- WEATHER_ZIP allows searching using the sipcode or partial zipcode as the parameter
- BOX is a WIP

![endpoints.png](Images%20For%20ReadMe/endpoints.png)

