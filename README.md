## access-control extension.


## How to use
### Option 1: Java class
export SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.jdbc.Driver
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=root
export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/accesscontrol_pandp_service?useSSL=false"

java \
-cp "target/access-control-service-extension-1.0.0-SNAPSHOT.jar:target/lib/*" \
com.backbase.accesscontrol.Application

### Option 2: Base jar

To use your service extension, you include the JAR build from this artifact to the CLASSPATH used when the service is 
started.


When you run a service as an [executable JAR](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#executable-jar-property-launcher-features), 
use the `loader.path` command line argument to add JARs or directories of JARs to the classpath. For example:

```
./target/lib/access-control-1.14.7.jar -Dloader.path=target/access-control-service-extension-1.0.0-SNAPSHOT.jar
```

If you are not running the Service as a bootable jar, use the mechanism available in your application server.

If you are using Thin WAR files instead of executable JAR files, add the runtime dependencies to the classpath in one of the following ways:
1. Inject the behavior extension JAR into the service WAR:
   *  ``` mkdir -p WEB-INF/lib ```
   *  ``` cp /path/to/service-extension.jar WEB-INF/lib/ ```
   *  ``` jar -uf /path/to/target-service.war WEB-INF/lib/service-extension.jar```
2. Use a Maven WAR overlay to package the extension [Maven WAR overlay](https://maven.apache.org/plugins/maven-war-plugin/overlays.html)
3. Add the behavior extension JAR to the web-app classpath in a container-specific way. 
For example, if you are using JBoss, you could use a deployment overlay to add the extension JAR to the /WEB-INF/lib folder of the deployed WAR. Consult the documentation for your specific application container for details.
   

### Docker

There is an example [Dockerfile](Dockerfile) demonstrating how to extend the Backbase Docker images
with the Behaviour Extension jar built from this project.

    mvn package -Pdocker

The build creates a Docker image with the extension added and ready to use.

## Community Documentation

* [Extend the behavior of a service](https://community.backbase.com/documentation/ServiceSDK/latest/extend_service_behavior)
* [Extend the behavior of access control]()

