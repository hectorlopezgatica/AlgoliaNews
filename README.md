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

![Captura de pantalla 2024-07-01 171113](https://github.com/hectorlopezgatica/AlgoliaNews/assets/116033767/614525d1-4ba4-4033-92ee-d953bad37a3f)
![Captura de pantalla 2024-07-01 171057](https://github.com/hectorlopezgatica/AlgoliaNews/assets/116033767/5f19d2fe-cf8b-4f85-a7d7-f0e5fba45236)
![Captura de pantalla 2024-07-01 171045](https://github.com/hectorlopezgatica/AlgoliaNews/assets/116033767/8667c256-8478-4552-b993-63f0149f254f)
![Captura de pantalla 2024-07-01 171135](https://github.com/hectorlopezgatica/AlgoliaNews/assets/116033767/4ee3292c-7ed5-438c-81ec-7b88ad9f42e8)
![Captura de pantalla 2024-07-01 171123](https://github.com/hectorlopezgatica/AlgoliaNews/assets/116033767/13dc30a7-0a72-4866-b10f-71a1fdbc8eba)

# Autor
Héctor López Gatica
