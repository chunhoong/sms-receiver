# SMS Receiver Server
A server implementation using Jetty and Jersey.

## Dependencies
1. `org.eclipse.jetty:jetty-server:11.0.0`  
Provide the core Servlet integration support and is required in any Servlet 2.5 or higher container.

2. `org.eclipse.jetty:jetty-servlet:11.0.0`  
To support Servlet 3.x deployment modes and asynchronous JAX-RS resource programming model.

3. `org.glassfish.jersey.media:jersey-media-json-jackson:3.0.0`  
To support POJO/JSON serialization.

4. `org.glassfish.jersey.inject:jersey-cdi2-se:3.0.0`  
Jersey must work with one of the DI framework - HK2 / CDI, else it will hit the error as below:
    ```
    root cause java.lang.IllegalStateException: InjectionManagerFactory not found.
    ```

5. `org.glassfish.jaxb:jaxb-runtime:3.0.0`  
JAXB implementation was removed from Java SE since JDK 9, thus JAXB implementation must be added separately, else it will hit the error as below:
    ```
    Implementation of JAXB-API has not been found on module path or classpath.
    ```
   
## Note
Jersey 3.0 is "jakarta-fied", which the package is now `jakarta.` instead of `javax.`

