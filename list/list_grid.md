# LazyLoading in Jetpack Compose

## What is LazyLoading?
Lazy loading is a technique used to load content only when it is needed, typically to improve performance and resource efficiency. In Jetpack Compose, lazy loading is implemented using components like `LazyColumn`, `LazyRow`, and `LazyVerticalGrid`.

## LazyColumn
`LazyColumn` is a vertically scrolling list that only composes and lays out the visible items on the screen. It efficiently handles large data sets by loading items as they come into view and recycling items that move out of view.
