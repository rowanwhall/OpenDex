# OpenDex

A rudimentary application used to showcase new design patterns and technologies as I learn about them. It makes requests to the open Pokemon API found here: https://pokeapi.co/. This is a work in progress and is intended as practice of the technologies mentioned above as opposed to a user facing application.

## MVP Design Pattern

This application makes use of the MVP design pattern, splitting its UI classes into packages representing different sections of the app. Its base classes make use of a synchronous loader that delivers the relevant presenter, instantiated via a factory class made specifically for that presenter. This pattern allows the presenter to be persisted even when its attached view dies, but still have the ability to construct a new presenter in the event that the process is killed. Views are defined in interfaces and implemented through standard Android Activity classes, although the pattern could also be applied to Fragments by creating a base class copying the functionality of BasePresenterActivity. Ultimately, this results in a very testable code base that also often manages to limit class and method visibility to package private. 

## Dagger 2

This application makes considerable use of Dagger 2 and its features to create a testable code base whose entities exist only within the scope they need. The application itself as well as each package that separately implements the MVP pattern has its own component, and each of those packages also has a custom scope. At the moment, modules only exist at the application level, so these scopes only serve to limit those classes that rely on constructor injection to be provided. However, should it be necessary, any of these packages' components could have their own module that use their custom scope and only be supplied to their component. 

## RxJava with Retrofit and Realm

RxJava is employed fairly heavily within the presenter classes of the application for its ability to perform asynchronous work simply and often in-line with the help of RetroLambda. Retrofit and Realm also have convenient compatibility with RxJava, allowing presenters to load data from cache and deliver it to the view while querying the API for fresh data all within the same observable chain (see MainPresenter for example) while using shared logic for handling that data that is completely agnostic of its origins. Rx is also very useful when using Jake Wharton's RxBinding libraries for easily accessible Observable wrappers for Android View classes - for example, the main package of the application makes use of RxRecyclerView for its pagination/infinite scrolling implementation.

## Android Databinding

While the application is built to follow the MVP pattern, it makes use of Android databinding and therefore takes some small cues from MVVM. Specifically, in order to avoid complicating model classes tied to the web API, it uses view model classes that only hold data that will be directly referenced/displayed by the xml via Android databinding. This in combination with BindingAdapters (at this point only one is being used for image loading via Picasso) allows for very simple view classes. Android databinding also adds some efficiency by making fewer traversals of the view hierarchy when obtaining references to those views in Java, and eliminates a considerable amount of boilerplate. Additionally, it simplifies the question of what data to persist, as the view model class contains all relevant data for a view to be displayed.

## CoordinatorLayout Custom Behavior

In addition to the standard shared element transition API, the detail package of the application implements its own custom Behavior class in order to create a unique scrolling effect.
