# 1、Docker是什么？

![logo](https://i-blog.csdnimg.cn/direct/08577a4e27ac4ff9b20f0aed5ad353fc.png)

Docker是一个开源的应用容器引擎，它允许开发者将他们的应用及其依赖包打包到一个可移植的容器中。这些容器可以在任何支持Docker的Linux或Windows机器上运行，无需担心环境差异。Docker容器是完全使用沙箱机制，相互之间不会有任何接口，从而保证了应用的独立性和安全性。

Docker的主要组成部分包括：

```
Docker Client：用于与Docker守护进程通信的客户端。

Docker Daemon：作为服务端接受客户端的命令，管理镜像、容器、容器网络、数据卷等。

Docker Image：用于创建Docker容器的模板，通常通过Dockerfile文件定义。

Docker Container：镜像运行的实例，即容器本身。

Docker Registry：用于存储和管理镜像的仓库，可以是公共的也可以是私有的。
```

Docker的优势在于它提供了一种标准化的方式来打包、分发和运行应用，简化了软件的集成和交付过程，使得开发、测试和生产环境更加一致。此外，Docker容器相比传统虚拟机具有更轻量级、更快启动和更高效资源利用率的特点



# 2、安装Docker

清除原有的docker，如果你的操作系统没有安装过Docker , 就不需要执行卸载命令。

```bash
yum remove docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-engine
```

安装依赖包

```bash
yum install -y yum-utils
```

建立Docker仓库 (映射仓库地址)

```bash
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

安装Docker引擎

```bash
yum install docker-ce docker-ce-cli containerd.io
```

启动docker

```bash
systemctl start docker
systemctl enable docker
```

查看版本-验证是否安装成功

```bash
docker version
```

# 3、配置阿里云镜像加速

阿里云官网地址: [www.aliyun.com](https://www.aliyun.com/)

登录后点击：[控制台] => 搜索[容器镜像服务]
![aliyun-op-1](https://i-blog.csdnimg.cn/direct/044c04fd5ae047949690af5bec8a5b28.png#pic_center)
![aliyun-op-2](https://i-blog.csdnimg.cn/direct/b606c81f80fe490b9dbb528325e1b56f.png#pic_center)





```bash
#创建docker目录
sudo mkdir -p /etc/docker
#配置阿里云镜像加速
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://XXXX.mirror.aliyuncs.com"]
}
EOF
#重启docker
sudo systemctl daemon-reload
sudo systemctl restart docker
```

检查

```bash
#1、
cat  /etc/docker/daemon.json
```

结果出现：

```bash
[root@localhost ~]# cat  /etc/docker/daemon.json
{
  "registry-mirrors": ["https://XXXX.mirror.aliyuncs.com"]
}
```

```bash
#2、
docker info
```

结果出现：

![Result](https://i-blog.csdnimg.cn/direct/9ff1b8bb0beb44cf9b836c0842005828.png)


# 4、主要组件

- **Docker镜像（Image）**：镜像是一个只读模板，包含了应用程序及其运行环境的所有必要信息。它类似于虚拟机的磁盘映像，但更小且更高效。

![Image](https://i-blog.csdnimg.cn/direct/44caab740b5f4b65998f40ab3ec6fd54.png)


- **Docker容器（Container）**：容器是镜像的运行实例。它们包含了应用程序及其运行环境，并且可以被启动、停止、重启、删除等。容器之间是相互隔离的，每个容器都有自己的文件系统、网络配置和资源分配。

- **Docker宿主机（Host）**：安装了Docker引擎的机器，容器在宿主机上运行。

- **Docker客户端（Client）**：用于与Docker守护进程通信的工具，可以通过命令行界面或API与Docker引擎交互。

![Client](https://i-blog.csdnimg.cn/direct/38a7ab43cd504aceb32721c24d5d4d64.png)


- **Docker仓库（Registry）**：用于存储和分发Docker镜像的服务。Docker Hub是最常用的公共仓库，用户可以从中拉取（pull）或推送（push）镜像。

# 5、常用命令

```bash
systemctl start docker	#启动docker
systemctl stop docker	#关闭docker
systemctl restart docker	#重启docker
systemctl enable docker		#docker设置随服务启动而自启动
systemctl status docker		#查看docker 运行状态 active为运行
#查看docker 版本号信息
docker version				
docker info	

docker --help				#docker 帮助命令
#拉取镜像 不加tag(版本号) 即拉取docker仓库中 该镜像的最新版本latest 加:tag 则是拉取指定版本
docker pull 镜像名 
docker pull 镜像名:tag

docker images				#查看自己服务器中docker 镜像列表
#搜索镜像
docker search 镜像名
docker search --filter=STARS=9000 mysql 搜索 STARS >9000的 mysql 镜像

#运行镜像
docker run 镜像名
docker run 镜像名:Tag

#删除一个
docker rmi -f 镜像名/镜像ID

#删除多个 其镜像ID或镜像用用空格隔开即可 
docker rmi -f 镜像名/镜像ID 镜像名/镜像ID 镜像名/镜像ID

#删除全部镜像  -a 意思为显示全部, -q 意思为只显示ID
docker rmi -f $(docker images -aq)

#强制删除镜像
docker image rm 镜像名称/镜像ID

#保存镜像
docker save 镜像名/镜像ID -o 镜像保存在哪个位置与名字

#加载镜像
docker load -i 镜像保存文件位置

#镜像标签
app:1.0.0 基础镜像
# 分离为开发环境
app:develop-1.0.0   
# 分离为alpha环境
app:alpha-1.0.0   

docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]

docker tag 源镜像名:TAG 想要生成新的镜像名:新的TAG

# 如果省略TAG 则会为镜像默认打上latest TAG
docker tag aaa bbb
# 上方操作等于 docker tag aaa:latest bbb:test

docker ps				#查看正在运行容器列表
docker ps -a			#查看所有容器
# -it 表示 与容器进行交互式启动  /bin/bash  交互路径
docker run -it -d --name 要取的别名 镜像名:Tag /bin/bash #运行一个容器

# netstat是控制台命令,是一个监控TCP/IP网络的非常有用的工具，它可以显示路由表、实际的网络连接以及每一个网络接口设备的状态信息
netstat -untlp

#进入容器
docker exec -it 容器名/容器ID /bin/bash
docker attach 容器名/容器ID

#退出容器
#-----直接退出  未添加 -d(持久化运行容器) 时 执行此参数 容器会被关闭  
exit
# 优雅退出 --- 无论是否添加-d 参数 执行此命令容器都不会被关闭
Ctrl + p + q

docker stop 容器ID/容器名		#停止容器
docker restart 容器ID/容器名		#重启容器
docker start 容器ID/容器名		#启动容器
docker kill 容器ID/容器名		#删除容器

#容器文件拷贝 —无论容器是否开启 都可以进行拷贝
#docker cp 容器ID/名称:文件路径 要拷贝到外部的路径| 要拷贝到外部的路径 容器ID/名称:文件路径
#从容器内 拷出
docker cp 容器ID/名称: 容器内路径  容器外路径
#从外部 拷贝文件到容器内
docker  cp 容器外路径 容器ID/名称: 容器内路径

docker logs -f --tail=要查看末尾多少行 默认all 容器ID	#查看容器日志

```

`-d`:后台运行

`--name`: 容器名

`-p`: 端口映射

`-e`: 环境变量 Key:Value

`-v`: 数据卷参数 Volume:Contents
# 6、Docker数据卷

Docker 数据卷是一种轻量级的、持久化的存储方式，用于存储容器的数据。数据卷的生命周期独立于容器，即使容器被删除，数据卷中的数据也不会丢失。数据卷可以被多个容器挂载，实现数据的共享，也方便进行数据的备份、恢复和迁移。

## 6.1、数据卷的类型

1. **卷 (Volume)**：这是 Docker 管理的宿主机文件系统的一部分，通常位于 `/var/lib/docker/volumes` 目录下。卷是最常用的数据卷类型，它们是 Docker 管理的，并且可以跨容器共享。
2. **绑定挂载 (Bind Mounts)**：这种方式允许将宿主机的任意目录或文件挂载到容器中。绑定挂载是非持久化的，因为它们依赖于宿主机的文件系统。
3. **tmpfs 挂载 (Tmpfs Mounts)**：这种方式将数据挂载到宿主机的内存中，不会写入宿主机的文件系统。容器关闭后，数据会丢失。

## 6.2、数据卷的使用

### 6.2.1、 创建数据卷

可以使用 `docker volume create` 命令来创建一个新的数据卷。例如：

```bash
docker volume create my-volume
```

### 6.2.2、挂载数据卷到容器

可以在运行容器时使用 `-v` 或 `--mount` 标志来挂载数据卷。例如：

```bash
docker run -d -v my-volume:/path/to/container/dir my-image
```

或者

```bash
docker run --mount type=bind,source=/host/path,target=/container/path,other-options my-image
```

### 6.2.3、查看数据卷

可以使用 `docker volume ls` 命令来列出所有的数据卷，以及 `docker volume inspect` 命令来查看特定数据卷的详细信息。

### 6.2.4、删除数据卷

可以使用 `docker volume rm` 命令来删除不再需要的数据卷。

# 7、Docker 网络设置

Docker 网络是指 Docker 容器之间以及容器与宿主机之间的通信机制。Docker 提供了多种网络模式，以适应不同的应用场景和需求。

## 7.1、Docker 网络模式

Docker 的网络模式主要包括以下几种类型：

- **Bridge (桥接)**：这是 Docker 的默认网络模式。在这种模式下，Docker 会创建一个虚拟的网桥 `docker0`，并将新创建的容器连接到这个网桥上。容器之间可以通过 IP 地址直接通信。

- **Host (主机)**：在这种模式下，容器不会获得独立的 IP 地址，而是与宿主机共享网络堆栈。容器使用宿主机的 IP 地址和端口，适合需要高性能网络通信的场景。

- **None (无)**：在这种模式下，容器没有网络接口，完全隔离，适合不需要网络的容器。

- **Overlay (覆盖)**：这种网络模式用于 Docker Swarm 集群，可以跨多个主机创建虚拟网络，适合分布式应用和服务。

- **Macvlan (MAC 虚拟化)**：为每个容器分配一个 MAC 地址，使其像宿主机上的物理设备一样工作，适合需要与物理网络直接交互的场景。

## 7.2、Docker 网络操作

Docker 提供了一系列命令来管理网络，包括：

![network-1](https://i-blog.csdnimg.cn/direct/3b22842afd804a9cad569b985ba31e1d.jpeg)


`docker network ls`:列出所有可用的网络。

`docker network create`：创建新的网络。

`docker network connect`：将容器连接到网络。

`docker network disconnect`：从网络断开会话。

`docker network inspect`：查看网络的详细信息。

`docker network rm`：删除网络。

![network-2](https://i-blog.csdnimg.cn/direct/36ca320708364f109c596f207c994df1.png)


## 7.3、Docker 网络配置

在配置 Docker 网络时，可以使用 `--network` 参数来指定容器应该连接到哪个网络。此外，还可以通过 `-p` 或 `--publish` 选项来设置端口映射，允许外部网络访问容器内部的服务。

# 完！！！