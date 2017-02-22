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

## MVP in action
- Each layer has its' own defined interface, so they won't interact with the concrete implementation directly. 
- The presenter is injected through the dependency injection, the presenter also need to know about the view instance so that it can execute the logic for the view.
- The underlying implementation can change based on the requirements or design, but the presenter will continue to interact with the view via the interface contract.
- The model object in turn forwards the call to a repository implementation to load and return the entity object.

## Dagger 2
- The Module class is where the dagger will keep track of the dependecies, it must be annotated with @module. In such modules dagger will look for variable methods and possible instance providers. The methods that will expose available return type should also be annotated with @provides decorator.
- The component is where the dagger konws where to inject the dependencies to, the injector class is called component. to annotate this class with @component declaration and set the modules that defined early. The activities, services or fragments that can be added should be declared in this class with individual inject() methods.
- Dagger will generate the initialization Class to return the instance we defined in related modules. We use the custom application class to handle the initial initialization.
- in onCreate() method the injection will be defined, so we can inject the dependecies at runtime by providing an @inject annotation.

##JUnit and Mockito
- We can remove all the third party dependencies out of the equation and using an interface and mocking it helps us deal with these kind of problems.
- We can create mocks in `setup()` function with `@before` annotation and can define the behaviour which we see fit for each test and for each interaction using these mocks, the focus is always on testing the core logic only.
- using `when().thenReturn()` function to simulate the specific situation.
- If we want to test the interactions inside the View, we can use `verifyZeroInteractions(mockView)` function.
- using `verify(mockView,times(1)).function()` to test the interaction has been called only once.

##Retrofit 2
- Retrofit is a type-safe REST client for Android, it provides a powerful framework for authenticating and interacting with API, it sends network requests with OkHttp. Retrofit handles entire network call as well as json/xml parsing.
- We can define the endpoints and use annotations on the interface methods and its parameters to indicate how a request will be handled.
- using the interceptor for debugging purposes.
