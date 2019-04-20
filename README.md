# pitaya-finch

This project explores the idea to create web services which runs faster and elegant using Finch. Finch allows you to create composable http services definitions in a functional programming way making them elegant.

Finch runs over Finagle which already provides a super performance. In short: We want to build tiny, elegant and efficient web services.

## How to run
This project uses Twitter-Server to wrap the entire service and provide usefull tools for monitoring. When run the project you can _admin_ your service through: `http://<domain>:9990`
```shell
$ sbt compile
$ sbt run
```

### Stats console
![Requests metric](docs/img/stats.png)

## How to test
```shell
$ sbt test
```