# Hello World
This example is basically the same as the minimal HelloWorld but provides some basic configurations
- http port and listenaddress
- custom launcher
(see `helloWorld.properties`)

To start the service, you have to invoke `io.inkstand.examples.helloworld.HelloWorld.main()`

The services starts on port 22448 (so the port must not be bound already) and is reachable via [http://localhost:22448/hello/world](http://localhost:22448/hello/world)

The example only defines a REST Service on Path / in `HelloWorldService` and activates the `PublicService`
stereotype in the `beans.xml`.
