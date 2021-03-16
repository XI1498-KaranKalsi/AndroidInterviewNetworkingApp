## About the project

All the tasks were made including the nice to have tasks. 

Businesslogic stayed in the domain layer with the use cases. 
Repository manages the datasource from network or from DB.
When there is an error fetching posts from network a toast will appear.



## Instructions

### This repository contains the outline and some pre-made components for a simple app, including:
* Clean architecture with 3 layers: data, domain, presentation
* Dagger 2 configurations for all 3 layers
* Configured Application class
* A simple MVP in presentation layer that has already defined base classes (BasePresenter and BaseActivity)
* A simple activity that contains pre-defined `showLoading` and `hideLoading` methods. Feel free to check base classes, you may find there something useful.

### Your task is to:
- Call the network service on click on the "Fetch" button. Use `https://jsonplaceholder.typicode.com/posts` endpoint to get data from there
- Show the loading indicator (ProgressBar in the center of screen) while the call is pending and hide it after successfull fetch or error
- Display title and body of each JSON object in the `RecyclerView` if the call is successful. 
- If the network call fails, show an error to the user. Feel free to show the AlertDialog or just setting TextView in the center of screen.

### Nice to have tasks:
- Before each element add an item, which contains current date with adding `id` value to hours with format. Use following format: `Thu Jan 01 22:00:00`. For example if we have 22:00 now, we should show `Thu Jan 01 23:00:00` for item with `id == 1`.
- Add caching for data. Would be great if you will be able to implement cachig to database with any ORM (for example Room). Otherwise, feel free to use anything you feel can be implemented for data caching.  

### Hints:
- At minimum, you'll need to fill in the `TODO`s included in the template, although you'll need to add additional code as well
- The app's UI should be a strict reflection of state
- Don't worry about visual aesthetics, as there won't be time to cover that during the interview
- Caching should be located within repository class. 
