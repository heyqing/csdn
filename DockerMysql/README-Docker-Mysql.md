# Docker部署Mysql

# 1、拉取Mysql镜像

- 拉取最新版本的MySQL镜像

```bash
docker pull mysql:latest
```

- 特定版本的MySQL，可以替换`latest`为相应的标签

```bash
docker pull mysql:5.7
```

![pull](https://i-blog.csdnimg.cn/direct/7b93fdfc9917444f847d3defbbdf0236.jpeg)

# 2、查看是否拉取成功

```bash
docker images
```

![images](https://i-blog.csdnimg.cn/direct/481cbcce9bcb4e16827e3fbb34a1355c.jpeg)

# 3、运行Mysql

```bash
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e TZ=Asia/Shanghai \
  -e MYSQL_ROOT_PASSWORD=123 \
  mysql
```

`-d`:后台运行

`--name`:镜像名称

`-p`:端口映射

`-e`:环境变量（这里配置了时区：亚洲上海，用户名root密码123）

`-v`:数据卷挂载，因需求而选择

![run](https://i-blog.csdnimg.cn/direct/e1d5b262e83b4fb9a2d515e5dfcd7503.jpeg)

# 4、查看MySQL状态

```bash
docker ps
```

![ps](https://i-blog.csdnimg.cn/direct/255d66f3574946da8f7b92122971f89a.jpeg)

# 5、远程连接Mysql

![ssh](https://i-blog.csdnimg.cn/direct/0cfb931c7229470fab36ceadfbcc18b1.png)

## 远程连接若出现问题

- 默认情况下，MySQL只允许本地访问。要允许远程访问，需要进入MySQL容器并执行以下SQL命令：

  ```sql
  grant all privileges on *.* to 'root'@'%' with grant option;
  FLUSH PRIVILEGES;
  ```

- 开启防火墙端口,确保你的服务器防火墙允许MySQL的3306端口访问