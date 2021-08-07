

[docs/docs/serving/samples/hello\-world/helloworld\-kotlin at mkdocs · knative/docs](https://github.com/knative/docs/tree/mkdocs/docs/serving/samples/hello-world/helloworld-kotlin)

[Docker \| Ktor](https://ktor.io/docs/docker.html#getting-the-application-ready)



## up

```
docker-compose up
 
 or 
 
docker-compose up --build
```


## クリーンアップ

ボリュームも消す時は以下

```
docker-compose down --volumes
```

## dockerの中に入るコマンド

```
docker exec -i -t container--ktor-test bash
```
