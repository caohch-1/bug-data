# Bug Data
 Replication of train-ticket and collect logs, traces and metrics.
 Admin account is `adminroot` and pwd is same.

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
2. Change version of `selenium-server` in `pom.xml` to `3.9.0`.
3. Change the chromedriver location of `System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");` in the code.
4. `mvn clean test` is all you need.


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
5. F5 in `ts-ticketinfo-service/src/main/java/ticketinfo/async/ExecutorConfig.java`. L13-18 set the thread pool capacity. I cannot reproduce the bug by its test scripts, but I follow its readme and get the buggy result (i.e.,  visit `http://loaclhost:15345/click/occupy` and then visit `http://localhost:15345/click/auto` in another explorer tab quickly).
6. F6 in `ts-voucher-service/server.py`. L70 `voucherId` is a wrong SQL arg. Not sure how to reproduce normal version without change source code.
7. F7 in `ts-inside-payment-service/src/main/java/inside_payment/service/InsidePaymentServiceImpl.java`. L93-110 simulates the thrid party service timeout. However, even I set L97 to 6000 the bug still exists. I cannot reproduce the normal run. Here normal run result use L91-92.
8. F8 in `ts-cancel-service/src/main/java/cancel/controller/CancelController.java`. L59-65 process "VIP" incorrectly. Authors provide scripts for buggy and normal run. I still feel difference between "buggy" and "normal" run is unclear.
9. F9 `ts-ui-dashboard/static/css/style.css`. A frontend bug and I don't know how to get a normal run result without changing source code.
10. F10 failed to reproduce. Lack of test scripts. Authors upload wrong test scripts zip and the fault cannot be reproduced by following trigger steps...
11. F11 in `ts-cancel-service/src/main/java/cancel/service/CancelServiceImpl.java`. L129-138 implements the logic that lacks of sequence control. The trigger script somethimes can reproduce buggy and sometimes normal.
12. F12 in `ts-order-other-service/src/main/java/other/service/OrderOtherServiceImpl.java`. Two scripts, one for normal and the other for buggy.
13. F13 failed to reproduce. Test script has bugs.
14. F14 in `ts-basic-service/src/main/java/fdse/microservice/service/BasicServiceImpl.java`. L87-90 set the prices of diffeent classes. Cannot have normal run without changing codes.
15. F15 in `ts-ui-dashboard/nginx.conf`. L317,324 set the `client_max_body_size` which makes requests with food and consign information cannot be sent and cause the bug. Not sure the normal run should run without food and consign or increase `client_max_body_size`.
