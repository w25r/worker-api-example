# worker-api-example
A simple example that demonstrates the basic functionality and power of Gradle's Worker API

## Intra-project parallel tasks
This is having multiple, independent tasks in one Gradle project running in parallel.

    `./gradlew sleep`
    
## Intra-task parallel task actions
This is having multiple, independent task actions running in parallel within a single task

    `./gradlew multiSleep`


### Things to follow up on
* Why does the isolation mode not show up in IDEA?
    * Perhaps because it's not in the public API yet?
* Real use case
    * A useful (or more nearly useful) use case would help clarify the Worker API.