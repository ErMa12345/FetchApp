
# Fetch App Submission

For the Fetch Internship App Submission


## Running
To run the app, simply clone the project and run the emulated app. For recreatability, I've done my development on a Pixel Pro 8 API 35.
It should product something like:

<img width="646" alt="image" src="https://github.com/user-attachments/assets/9ba38f16-50ad-47b4-954c-88657176c42c">
<img width="229" alt="image" src="https://github.com/user-attachments/assets/adb56d52-26d1-49a2-a8b5-ba062b6d7da4">


*Note: If the phone is in portrait mode, scroll right to see all columns.*
## UI 

#### MainActivity.kt

The MainActivity class sets up the Jetpack Compose UI which is primarily based in the GenerateTable function

The GenerateTable composable function fetches data asynchronously through a helper class. Once loaded, it displays the parsed data. 


## Helper Functions

#### APIHelper.kt and APIRequest interface

This API helper class uses retrofit to make requests to the https://fetch-hiring.s3.amazonaws.com/ domain. It then uses the APIRequest method to pull the specific hiring.json endpoint. 

Explanation for separation: While it would have worked to simply call the https://fetch-hiring.s3.amazonaws.com/hiring.json endpoint, in my experience it is better practice to separate the responsibilities for better future scalability. 

#### PullData.kt

This class has two functions. getData() which makes the request to the endpoint and parseData() which sorts the data based on the project requirements. 

I've also created data classes here which help with the data parsing.

