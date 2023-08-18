# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### How deploy trainticket
1. Download [myProject](https://github.com/FudanSELab/train-ticket/blob/master/old-docs/Lib/myproject.zip).
2. Move it to your `.m2` directory, where the lib of maven saved. The path just like `.m2\repository\myproject`
3. Modify `<version>1.2.2.BUILD.SNAPSHOT</version>` to `<version>1.2.2.RELEASE</version>` in `micro-service-monitoring-zipkin-1.2.2.BUILD-SNAPSHOT.pom` and `micro-service-monitoring-core-1.2.2.BUILD-SNAPSHOT.pom`
4. Excute following commands
```bash
mvn clean package
docker-compose build
docker-compose up
# Waiting until all the containers are running
# If you meet that some ts-xxx-mongo containers crash (e.g., exited with code 14)
# Try following 
mvn clean package
docker-compose build
docker system prune
docker-compose up
```

Now visit `localhost:8080` and you should see the UI of trainticket.

### How deploy monitors
Excute `docker-compose up` in `monitor` dir.
To use `docker-py` with `Docker Desktop`, following my answer [here](https://stackoverflow.com/a/76927390/12871978).


### How to run test scripts
`mvn clean package` is all you need.

 ## Todo
1. Test if scripts can pass all tests.
2. Replicate F1.
3. Write extractors.