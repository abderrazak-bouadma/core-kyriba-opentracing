# Spring Boot Simple OpenTracing Starter

by including this starter you enable streaming openTracing Metrics to Wavefront

## How to use the starter

add the following dependency to your project

```xml
<dependencies>
    <dependency>
        <groupId>com.kyriba</groupId>
        <artifactId>core-kyriba-opentracing</artifactId>
        <version>{release}</version>
    </dependency>
</dependencies>
```

then configure the following within your `application.properties`

```properties
tracing.service=serviceName
tracing.application=yourApplicationName
tracing.proxyUrl={wavefront proxy URL}
tracing.portNumbers[0]=2878
tracing.portNumbers[1]=30000
tracing.sourceName=my service
```
