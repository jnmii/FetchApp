
# Fetch Application

## Description
This application retrieves and displays a list of items from a remote API, grouping them by `listId` and sorting them by `name`. It uses Jetpack Compose for building the UI and Kotlin Coroutines for asynchronous operations. The app also handles cases where the `name` property of items is blank or null.

## Technologies Used
- **Kotlin**: The primary programming language.
- **Jetpack Compose**: For building the UI.
- **ViewModel & StateFlow**: For managing and displaying UI state.
- **Retrofit**: For API communication.
- **OkHttp**: For HTTP requests with logging.
- **Material3**: For theming and UI components.
- **Coroutines**: For asynchronous operations.

## Setup and Installation
1. Clone the repository:
   ```bash
   git clone <https://github.com/jnmii/Fetchapp.git>
   ```
2. Open the project in Android Studio.
3. Build and run the project.

## API
The app uses the Fetch API to retrieve a list of items. Each item has the following properties:
- `id`: The unique identifier for the item.
- `listId`: The group identifier for the item.
- `name`: The name of the item (can be null or blank).

Example API response:
```json
[
  { "id": 1, "listId": 1, "name": "Item 1" },
  { "id": 2, "listId": 1, "name": "" },
  { "id": 3, "listId": 2, "name": "Item 3" }
]
```

## Key Classes and Files
- **Data Models**:
  - `Item`: Represents an individual item with `id`, `listId`, and `name`.
- **API Interface**:
  - `DataApi`: Defines the Retrofit service to fetch items from the API.
- **Repository**:
  - `ItemsRepository`: Interface for fetching items.
  - `ItemsRepositoryImpl`: Implements `ItemsRepository`, handles API calls, and returns results.
- **Result Handling**:
  - `Result`: A sealed class to represent success and error states.
- **ViewModel**:
  - `ItemsViewModel`: Manages data fetching, sorting, and filtering.
- **Networking**:
  - `FetchInstance`: Provides a Retrofit instance with logging capabilities.
- **UI**:
  - `GroupedItemList`: Displays items grouped by `listId` with sticky headers.
  - `MainActivity`: Initializes the ViewModel and sets up the UI.

## Screenshots
![Screenshot 2024-09-08 123958](https://github.com/user-attachments/assets/ce1f8227-63a3-4ec5-bfa9-3f2c4c76903f)


## Future Enhancements
- Add a search bar to filter items by name.
- Implement pagination for handling large data sets.
- Buttons to show or hide null or no named values.
  



## Notes
- Ensure you have internet access for the app to fetch data from the API.
- This project uses experimental APIs from Jetpack Compose; ensure compatibility with your environment.

```

