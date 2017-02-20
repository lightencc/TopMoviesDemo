# TopMoviesDemo
This demo shows the implementation about the MVP architecture by using Dagger2+Retrofit&amp;Rxjava

## MVP

- The Model-View-Presenter MVP pattern provides a better solution to build more complex app in a clean way. 
	- 	We try to keep things simple
	- 	we try to implement our code in layers rather than large monolithic blocks of code. 
	- 	We try to avoid depending directly on Android framework which is slow and cumbersome to test. 
	- 	Conform to the dependency rule.
- SOLID principle stands for single responsibility,open-closed,Liskov substitution,interface segregation and dependency inversion. When these five principles in object-oriented programming applied together, facilitate creation of a system that is easy to maintain and extend over time.
- Within clean architecture - each layer has its own models and models contain data relevant only to that layer. When data moves between layers - the models are transformed from one representation to another.
	- Presenters are middle men between the view and the model, it also contains the logic pertaining to the presentation of the data, retrieves the data from the model, formats the data before passing it to the view, presenter also updates the UI via the view.
	- View has a reference to the presenter, propagates events from the UI to the presenter, view exposes methods which control the presentation of data(or model).
	- The repository pattern separates the business logic from the underlying data source. It provides a substituion point for the unit tests, in turn enabling a flexible architecture.
	- When any of the external parts of the system become obsolete, like the database, or the web framework, we can replace those obsolete elements with a minimum of fuss.
