# RetrofitSampleAPI: Fetch and Display JSON Data from JSONPlaceholder

This project demonstrates how to fetch JSON data from the JSONPlaceholder API using Retrofit and display it in an Android application. The data is presented in a RecyclerView, managed with ViewModel and LiveData for efficient and responsive data handling. The code is written in Kotlin, showcasing modern Android development practices.

## Features

- **Retrofit for Network Requests**: Fetch data from the JSONPlaceholder API.
- **MVVM Architecture**: Implement Model-View-ViewModel for clean and maintainable code.
- **LiveData**: Observe data changes and update UI reactively.
- **RecyclerView**: Efficiently display a large dataset with a scrollable list.
- **Kotlin**: Modern, concise, and safe language features.

## Getting Started

### Prerequisites

- Android Studio 4.0 or higher
- Kotlin 1.4 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone git@github.com:vigneshwar52/RetrofitSampleAPI.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or a physical device.

## Usage

1. The app fetches a list of posts from JSONPlaceholder API.
2. The data is displayed in a RecyclerView.
3. ViewModel and LiveData are used to handle data operations and lifecycle management.

## Code Overview

- **MainActivity**: Hosts the RecyclerView and observes data changes.
- **PostViewModel**: Manages the data for the UI.
- **PostRepository**: Handles data operations, fetching from the API.
- **RetrofitInstance**: Configures Retrofit for network requests.
- **PostAdapter**: Binds the data to the RecyclerView.

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests.

### Reference Repo
    https://github.com/LinkedInLearning/android-development-retrofit-with-kotlin-2882228.git

    proj gradle:
        ext.kotlin_version = "1.8.0"
        ext.agp_version = '8.5.0'

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This structure makes it easy for users to understand the project, its features, and how to get started. You can place this in your README.md file in your GitHub repository.