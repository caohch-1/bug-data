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
1. Excute `docker-compose up` in `monitor` dir.
2. Create python environment meets the requirements in `monitor/dependency`. We provide both `conda` and `pip` format.
3. Run `python main.py`.


*Note*: To use `docker-py` with `Docker Desktop`, set env variable`export DOCKER_HOST="unix:///home/caohch1/.docker/desktop/docker.sock"` first. Follow my answer [here](https://stackoverflow.com/a/76927390/12871978).


### How to run test scripts
1. Download Chromedriver accroding to your Chrome version.
2. Change the chromedriver location of `System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");` in the code.
3. `mvn clean test` is all you need.


*Note*: If your chrome driver is in the dir that need sudo premission, execute something like `sudo chmod +x /usr/local/share/chromedriver`.


## Todo
1. Replicate all bugs.
2. Analyze root causes........long and painful process.....aaaaaaaaaaa......aaaaaaaa!!!
3. Metrics extractor. Easy to implement but hard to choose proper paramaters.


## Bug Replication Records
1. F1 in `ts-cancel-service/src/main/java/cancel/async/AsyncTask.java`. L32-39 simulates unstable network condition. Just `sleep` is buggy version. The root cause can be [Reset Order Stauts] completes before [Drawback Money], which means the missing logic of controlling events sequence.
2. F2 in `ts-preserve-service/src/main/java/preserve/service/PreserveServiceImpl.java`. L24-29 simulates delay which makes requests may returns at a different order with the origin. Just increase `sleep` time is buggy version. The root cause can be the missing logic of processing async responses in order. 
3. F3 in `ts-order-service/src/main/java/order/service/OrderServiceImpl.java`. L28-33 simulates requests occupy too much memory. Meanwhile, in `ts-order-service/Dockerfile` L4 and `docker-compose.yml` L131-132, 192-193, and 255-256, JVM memory limit is bigger than the Docker memory limit. Just add `mem_limit` and `memswap_limit` is buggy version. The root cause is misconfiguration.
4. F4 in `ts-xxx-service/src/main/java/xxx/service/xxxServiceImpl.java`. All `http` are repaced by `https`. However, I failed to reproduce the buggy result. Authors mentioned that "if the response time is very long, then it fails and the fault occurs", but it seems that the response time of the buggy version is acceptable. Thus, for F4, the buggy logs and traces are just the results extracted under buggy version instaed of "the fault occurs".
