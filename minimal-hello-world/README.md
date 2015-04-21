# Minimal Hello World
This minimal example shows you what is essential for starting a Inkstand-based REST service. It's using the default
configuration.

To start the service, you have to invoke `io.inkstand.Inkstand.main()`

The services starts on port 80 (so the port must not be bound already) and is reachable via [http://localhost/hello/world](http://localhost/hello/world)

The minimal example only defines a REST Service on Path / in `io.inkstand.examples.minimalhelloworld.HelloWorldService` and activates the `PublicService`
stereotype in the `beans.xml`.
