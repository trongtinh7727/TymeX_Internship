# Currency Converter App

## Overview
Currency Converter is an Android application built with Kotlin that allows users to convert between currencies using live exchange rates. The app uses the [FreeCurrencyAPI](https://freecurrencyapi.com) to fetch real-time exchange rates, and it is structured with a modular architecture for easy maintenance and scalability.

## Project Structure
- **data**: Contains all data-related components.
  - `model`: Data models representing currency exchange data.
  - `remote`: API service definitions.
  - `repository`: Repository to handle data operations and connect with the remote API.
- **di**: Dependency injection setup using Hilt.
- **ui**: User interface components.
  - `binding`: Data binding-related components.
  - `main`: Main screen components, including currency selection and conversion logic.
  - `splash`: Splash screen for the initial app launch.
- **utils**: Utility classes and helper functions.

### MVVM Architecture
The app follows the MVVM architecture:
- **Model**: Data classes and repository that handle data logic.
- **View**: Activities, Fragments, and Composables that handle the UI.
- **ViewModel**: Provides data to the UI and handles user interactions, keeping UI logic separate from data logic.


## Prerequisites
- Android Studio (recommended version Arctic Fox or higher).
- `local.properties` file with `EXCHANGE_API_KEY` set to your FreeCurrencyAPI key. Example:
```
EXCHANGE_API_KEY=your_api_key_here
```


## Build and Run the App
1. Clone the repository:
 ```bash
 git clone https://github.com/your-repo/currency-converter.git
```
2. Open the project in Android Studio.

3. Add your API key to the local.properties file.

4. Sync the project to install dependencies.

5. Select a device or emulator to run the app.

6. Click on Run to build and deploy the app.

# Notes and Challenges
API Key Management: The API key is securely loaded from the local.properties file to keep it out of source control.

Network Handling: Retrofit and Kotlin Serialization handle the API requests and responses, while error handling provides feedback to the user if the network is unavailable.

# Demonstration Video
A video demonstrating the app's key features is available [here](https://youtu.be/HOclBMsuH44)