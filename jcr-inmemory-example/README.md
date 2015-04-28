# JCR In-Memory Service
This example uses an in-memory content repository and provides an endpoint to create a node and
one to read the nodes underneath the root node.

To start the service, you have to invoke `io.inkstand.example.jcr.JcrServiceLauncher.main()`

The services starts on port 22448 (so the port must not be bound already) and is reachable via [http://localhost:22448/nodes](http://localhost:22448/nodes)

The example only defines a REST Service on Path / in `io.inkstand.example.jcr.inmemory.JcrService` and activates the `PublicService`
stereotype in the `beans.xml`.

The endpoint [http://localhost:22448/nodes](http://localhost:22448/nodes) support two HTTP methods:

- POST with the name parameter in the body (i.e. `name=Test`)
- GET lists the nodes (as plain text)
