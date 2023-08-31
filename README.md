# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.

## Done
### How deploy trainticket
1. Download [myProject](https://github.com/FudanSELab/train-ticket/blob/master/old-docs/Lib/myproject.zip).
2. Move it to your `.m2` directory, where the lib of maven saved. The path just like `.m2\repository\myproject`
3. Modify `<version>1.2.2.BUILD.SNAPSHOT</version>` to `<version>1.2.2.RELEASE</version>` in `micro-service-monitoring-zipkin-1.2.2.BUILD-SNAPSHOT.pom` and `micro-service-monitoring-core-1.2.2.BUILD-SNAPSHOT.pom`
4. Modify image version for `ts-ticket-office-service` (node:13.3.0), `ts-voucher-mysql` (mysql:5.6.35), and all `ts-xxx-service` (openjdk:8-jre).
5. Excute following commands
```bash
mvn clean package
docker-compose build
docker-compose up
# Waiting until all the containers are running
# If you meet that some ts-xxx-mongo containers crash (e.g., exited with code 14 or 100)
# Try following 
mvn clean package
docker-compose build
docker system prune
docker volume prune
docker-compose up
```

Now visit `localhost:8080` and you should see the UI of trainticket.

### How to deploy monitors
1. Set env variable`export DOCKER_HOST="unix:///home/caohch1/.docker/desktop/docker.sock"`
2. Excute `docker-compose up` in `monitor` dir.
*Note*: To use `docker-py` with `Docker Desktop`, following my answer [here](https://stackoverflow.com/a/76927390/12871978).


### How to run test scripts
1. Download Chromedriver accroding to your Chrome version.
2. Change the chromedriver location of `System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");` in the code.
3. `mvn clean test` is all you need.


*Note*: If your chrome driver is in the dir that need sudo premission, execute something like `sudo chmod +x /usr/local/share/chromedriver`.


## Todo
1. Write extractors.
