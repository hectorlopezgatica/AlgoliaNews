# Algolia News
 
This Android app is a simple app which shows a list of posts from this query:

https://hn.algolia.com/api/v1/search_by_date?query=mobile

The main view of the app is a scrolling list view of recent posts in date order. If the user taps
on a post, show a web view (within the app) with the linked article. Also, you should be able
to swipe on a cell, and delete an individual post from this view (see mockups). Once a post is
deleted it should not reappear even if the data is refreshed.

If the app is used offline, it should show the items downloaded last time.

# Libraries
This use (mainly) this libreries:
- Jetpack Compose
- Material 3
- Room
- Dagger Hilt
- Navigation Compose / Hilt Navigation Compose
- Retrofit (and converter-gson)
- ViewModel
- Coroutines
- Mockito
- kotlinx-serialization-json

# Architecture
This app uses three layers: presentation, domain and data. Where domain layer is the main main (presentation and data layers depoens on domain layer). Also, this app uses MVI approach.

![Captura de pantalla 2024-05-30 133051](https://github.com/hectorlopezgatica/PostList/assets/116033767/145e64cb-d8fc-4eaf-b265-43ccbd7ce9b9)
![Captura de pantalla 2024-05-30 133136](https://github.com/hectorlopezgatica/PostList/assets/116033767/e661a0f0-44ec-4a6a-b2dc-42f4f1ec380c)
![Captura de pantalla 2024-05-30 154146](https://github.com/hectorlopezgatica/PostList/assets/116033767/f628f5ee-d9e3-476c-8983-734d8443176a)
![Captura de pantalla 2024-05-30 133101](https://github.com/hectorlopezgatica/PostList/assets/116033767/67d101d3-c505-4a78-ae59-41fa93b4e13a)
![Captura de pantalla 2024-05-30 155219](https://github.com/hectorlopezgatica/PostList/assets/116033767/444c2058-f546-48dd-8581-7d5d5dffd696)


# Pending taks
![Captura de pantalla 2024-05-30 150402](https://github.com/hectorlopezgatica/PostList/assets/116033767/8d3eaf5a-d9a2-479a-8063-d60ab2c6a456)

# Autor
Héctor López Gatica
