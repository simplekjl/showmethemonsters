# Show me some Monsters
Let's read some news from  https://pokeapi.co/

# Libraries used 
- Koin for Dependency Injection 
- Retrofit && Adapter for RxJava
- Picasso 
- RxJava 2
- RxKotlin 

# Notes to run the project: 

There are no special notes to run the project, just open it on Android Studio and run it!

# Login Validation 
Login is fake given that there was no endpoint for a login, the action has a delay of 2 seconds and then if will pass to the MainScreen

Constrains : 
- Email has to contain '@' in the value to pass the validation 
- Password has to be at least 4 random symbols

# Time spend 
9 hrs

# Reminding tasks
- Gather more information to present a better UI, there was no images available for example
- UI test
- Unit test 
- Store in Local

# Architecture 

- It follows very close the clean architecture using interactors to communicate the presentation layer with the domain and repositories to comunicate with the data layer.

- The project can be still improved if necessary to improve scalability

- MVVM and MVI using sealed classes for view states 




