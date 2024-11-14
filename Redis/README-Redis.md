# 1 Redis是什么

> 官网：https://redis.io 
>
> 开发者：Antirez

Redis诞生于2009年全称是**Re**mote  **D**ictionary **S**erver 远程词典服务器，是一个基于内存的键值型NoSQL数据库。

Redis是一个开源的、高性能的**键值对**存储系统，它支持多种数据结构，包括字符串、哈希、列表、集合、有序集合等。Redis通常被用作数据库、缓存和消息中间件。它以**内存存储**为主，因此读写速度极快，同时也支持数据的持久化，以防止数据丢失。Redis是**单线程**的，但得益于高效的I/O多路复用技术，它仍然能够处理大量的并发请求。并且**支持数据持久化**、**支持主从集群、分片集群**、**支持多语言客户端**。

而NoSql则是相对于传统关系型数据库而言，有很大差异的一种数据库

> NoSQl简介
>
> **NoSql：**Not Only Sql（不仅仅是SQL），或者是No Sql（非Sql的）数据库。是相对于传统关系型数据库而言，有很大差异的一种特殊的数据库，因此也称之为**非关系型数据库**。
>
> - 结构化与非结构化：传统关系型数据库是结构化数据，每一张表都有严格的约束信息：字段名、字段数据类型、字段约束等等信息，插入的数据必须遵守这些约束，而NoSql则对数据库格式没有严格约束，往往形式松散，自由。
> - 关联和非关联：传统数据库的表与表之间往往存在关联，例如外键，而非关系型数据库不存在关联关系，要维护关系要么靠代码中的业务逻辑，要么靠数据之间的耦合。
> - 查询方式：传统关系型数据库会基于Sql语句做查询，语法有统一标准，非关系数据库查询语法差异极大，五花八门各种各样。
>   - redis：get user:1
>   - mongodb：db.users.find({_id:1})
>   - elasticsearch:GET http://localhost:9200/users/1
> - 事务:传统关系型数据库能满足事务ACID的原则。而非关系型数据库往往不支持事务，或者不能严格保证ACID的特性，只能实现基本的一致性。
>
> ![](assets/redis-compare.png)

# 2 Redis的安装

通常情况下redis都是安装在Linux服务器上的，基于redis的特点在Linux上才会发挥其最佳效果，官网也推荐使用Linux。

## 2.1 安装纯净版

### 2.1.1 下载依赖库

redis是基于C语言编写的，因此需要gcc环境

```shell
yum install -y gcc tcl
```

### 2.1.2 上传安装包并解压

官网下载：http://download.redis.io/releases/

建议下载第二位为偶数的如7.0.x 或 7.2.x 等等

这里我下载的是`7.0.0`版本

![](E:\code_special\Github\independence\blog\Redis\assets\redis-tar.jpg)

上传至要存放的目录中如`\opt`下

进入压缩包所在目录

```shell
tar -xzf redis-7.0.0.tar.gz
```

进入redis目录，进行安装

```shell
 cd /opt/redis-7.0.0/
make && make install
```

该目录已经默认配置到环境变量`/usr/local/bin`

![](assets\redis-install-default-path.jpg)

因此可以在任意目录下运行这些命令。其中：

- `redis-cli`:是redis提供的命令行客户端
- `redis-server`:是redis的服务端启动脚本
- `redis-sentinel`:是redis的哨兵启动脚本
- `redis-benchmark`:性能测试工具
- `redis-check-aof`:修复有问题的AOF文件，
- `redis-check-aof`:修复有问题的dump.rdb文件

### 2.1.3 修改配置文件

进入到安装redis的目录，我这里是`/opt/redis-7.0.0/`

![](assets\redis-conf-path.jpg)

这里建议先将原conf配置文件拷贝一份

```shell
cp redis.conf /heyqings/myredis/
```

`/heyqings/myredis/`为自定义目录

![](assets\copy-conf-my-path.jpg)

*修改配置文件后需重启生效*

注意这里修改的是拷贝的文件

1. 修改`daemonize no` -> `daemonize yes` （309行左右）：是否为后台启动

   ![](assets\conf-daemonize.jpg)

2. 修改`protected-mode yes`->`protected-mode no`（111行左右）：是否开启保护模式

   ![](assets\conf-protected-mode.jpg)

3. 修改`bind 127.0.0.1`->直接注释（或改为本机IP）（87行左右）：允许访问的地址

   ![](assets\conf-bind.jpg)

4. 添加密码->requirepass设置你的密（1033行左右）

![](assets\conf-requirepass.jpg)

### 2.1.4 启动redis

因redis安装默认安装在`/usr/local/bin`所以我们可以在任何地方使用redis命令

启动redis服务并告诉其配置文件路径

```shell
redis-server /heyqings/myredis/redis.conf
```

没有消息就是好消息，若无错误报出则证明启动成功，可通过

```shell
ps -ef | grep redis | grep -v grep
```

查看，结果为

![](assets\start-success-flag.jpg)

则证明启动成功

**设置开机自启**

首先需要新建一个服务文件

```shell
vim /etc/systemd/system/redis.service
```

其中内容如下

```shell
[Unit]
Description=redis-server
After=network.target

[Service]
Type=forking
ExecStart=/usr/local/bin/redis-server /heyqings/myredis/redis.conf
PrivateTmp=true

[Install]
WantedBy=multi-user.target
```

然后重载系统服务

```shell
systemctl daemon-reload
```

开机自启

```shell
systemctl enable redis
```

现在我们可以用系统命令操作redis

```shell
# 启动
systemctl start redis
# 停止
systemctl stop redis
# 重启
systemctl restart redis
# 查看状态
systemctl status redis
```

### 2.1.5 停止redis

利用redis-cli来执行 `shutdown `命令，即可停止 Redis 服务

```shell
redis-cli -u 123456 shutdown
```

`-u`后为密码也可用`-a`

### 2.1.6 连接测试

Linux服务器上可通过

```shell
redis-cli -a 123456 -p 6379
```

进行连接`-a`后为密码，`-p`后为端口号，默认为6379

![](assets\cli-connect-success.jpg)

## 2.2 docker安装redis

### 2.2.1 拉取镜像

```shell
docker pull redis:7.0.0
```

![](assets\docker-pull.jpg)

拉取成功可通过`docker images`查看

### 2.2.2 创建配置文件

创建`redis.conf`我这里创建在`/heyqings/docker/redis/conf`目录下

```shell
cd /heyqings/docker/redis/conf
vim redis.conf
```

里面内容为：

```shell
# Redis服务器配置 
 
# 绑定IP地址
#解除本地限制 注释bind 127.0.0.1  
#bind 127.0.0.1  
 
# 服务器端口号  
port 6379 
 
#配置密码，不要可以删掉
requirepass 123456
  
 
 
#这个配置不要会和docker -d 命令 冲突
# 服务器运行模式，Redis以守护进程方式运行,默认为no，改为yes意为以守护进程方式启动，可后台运行，除非kill进程，改为yes会使配置文件方式启动redis失败，如果后面redis启动失败，就将这个注释掉
daemonize no
 
#当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定(自定义)
#pidfile /heyqings/docker/redis/run/redis6379.pid  
 
#默认为no，redis持久化，可以改为yes
appendonly yes
 
 
#当客户端闲置多长时间后关闭连接，如果指定为0，表示关闭该功能
timeout 60
# 服务器系统默认配置参数影响 Redis 的应用
maxclients 10000
tcp-keepalive 300
 
#指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合（分别表示900秒（15分钟）内有1个更改，300秒（5分钟）内有10个更改以及60秒内有10000个更改）
save 900 1
save 300 10
save 60 10000
 
# 按需求调整 Redis 线程数
tcp-backlog 511
 

# 设置数据库数量，这里设置为16个数据库  
databases 16
 
 
# 启用 AOF, AOF常规配置
appendonly yes
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
 
# 慢查询阈值
slowlog-log-slower-than 10000
slowlog-max-len 128
 
# 是否记录系统日志，默认为yes  
syslog-enabled yes  
 
#指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose
loglevel notice
  
# 日志输出文件，默认为stdout，也可以指定文件路径  
logfile stdout
 
# 日志文件
#logfile /var/log/redis/redis-server.log
 
 
# 系统内存调优参数   
# 按需求设置
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-entries 512
list-max-ziplist-value 64
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
```

### 2.2.3 运行redis

```shell
docker run \
-p 6378:6379 \
--name domeredis \
-v /heyqings/docker/redis/conf/redis.conf:/etc/redis/redis.conf \
-v /heyqings/docker/redis/data:/var/lib/redis \
-v /heyqings/docker/redis/logs:/logs \
-d redis:7.0.0 redis-server /etc/redis/redis.conf
```

命令解释

```bash
-p 端口号映射 主机端口：容器端口
-v 数据卷挂载
-d 后台运行
--name 容器名称
```

![](assets\docker-run-redis.jpg)

查看是否启动

`docker ps`若出现则成功

![](assets\docker-ps-redis.jpg)

开机自启

```shell
docker update --restart=always domeredis
```

### 2.2.4 测试连接

```shell
docker exec -it domeredis bash
```

![](assets\docker-test-connect.jpg)

## 2.3 下载客户端

**RedisDesktopManager**官网：https://redis.io/insight/

下载并安装后进行连接

点击【Connect to Redis Server】填写信息

![](assets\rdm-docker-connect.jpg)

这里我填写的是docker容器中的redis，纯净版同理

然后点击【Test Connection】出现

![](assets\rdm-docker-connect-success.jpg)

依次点击【OK】即可

# 3 Redis十大数据类型

操作命令指南：https://redis.io/docs/latest/commands/

## 3.0 前置-key的相关操作

- `keys *`:查看当前库的所有key
- `exists key`:判断某个key是否存在
- `type key`:查看key的类型
- `del key`:删除key
- `unlink key`:非阻塞删除，仅仅将keys从keyspace元数据中删除，真正的删除会在后续异步操作中
- `ttl key`:查看还有多少秒过期，-1：永不过期，-2：已过期
- `expire key`:秒钟，为key设置过期时间
- `move key dbindex`:将当前的key移动到给定数据库
- `select dbindex`:切换数据库
- `dbsize`:查看数据库key的数量
- `flushdb`:清空当前库
- `flushall`:通杀全部库

***命令不区分大小写，key区分***

通用操作

```shell
SET key value [NX | XX] [GET] [EX seconds | PX milliseconds |
  EXAT unix-time-seconds | PXAT unix-time-milliseconds | KEEPTTL]
```

NX:不存在时创建

XX:存在时创建

GET:返回旧值插入新值

EX:过期时间 秒

PX:过期时间 毫秒

EXAT:过期时间 unix时间 秒

PXAT:过期时间 unix时间 毫秒

KEEPTTL:保持过期时间

## 3.1 String 字符串

最基础数据类型，一般情况下key都为字符串类型

String的常见命令有：

- SET：添加或者修改已经存在的一个String类型的键值对
- GET：根据key获取String类型的value
- MSET：批量添加多个String类型的键值对
- MGET：根据多个key获取多个String类型的value
- INCR：让一个整型的key自增1
- INCRBY:让一个整型的key自增并指定步长，例如：incrby num 2 让num值自增2
- INCRBYFLOAT：让一个浮点类型的数字自增并指定步长
- SETNX：添加一个String类型的键值对，前提是这个key不存在，否则不执行
- SETEX：添加一个String类型的键值对，并且指定有效期

## 3.2 List 列表

底层为双向链表

- 有序
- 元素可以重复
- 插入和删除快
- 查询速度一般

List的常见命令有：

- LPUSH key element ... ：向列表左侧插入一个或多个元素
- LPOP key：移除并返回列表左侧的第一个元素，没有则返回nil
- RPUSH key element ... ：向列表右侧插入一个或多个元素
- RPOP key：移除并返回列表右侧的第一个元素
- LRANGE key star end：返回一段角标范围内的所有元素
- LREM key number element:删除number个element
- BLPOP和BRPOP：与LPOP和RPOP类似，只不过在没有元素时等待指定时间，而不是直接返回nil

## 3.3 Set 集合

无序，无重复集合

- 无序
- 元素不可重复
- 查找快
- 支持交集、并集、差集等功能

Set的常见命令有：

- SADD key member ... ：向set中添加一个或多个元素
- SREM key member ... : 移除set中的指定元素
- SCARD key： 返回set中元素的个数
- SISMEMBER key member：判断一个元素是否存在于set中
- SMEMBERS：获取set中的所有元素
- SINTER key1 key2 ... ：求key1与key2的交集
- SINTERCARD numkeys key [key …] [LIMIT limit]:redis7新命令，返回结果的基数（返回由所有给定集合的交际产生的集合的基数）

## 3.4 SortedSet 有序集合

有序，无重复集合，关联double类型的分数(score),分数可重复

- 可排序
- 元素不重复
- 查询速度快

SortedSet的常见命令有：

- ZADD key score member：添加一个或多个元素到sorted set ，如果已经存在则更新其score值
- ZREM key member：删除sorted set中的一个指定元素
- ZSCORE key member : 获取sorted set中的指定元素的score值
- ZRANK key member：获取sorted set 中的指定元素的排名
- ZCARD key：获取sorted set中的元素个数
- ZCOUNT key min max：统计score值在给定范围内的所有元素的个数
- ZINCRBY key increment member：让sorted set中的指定元素自增，步长为指定的increment值
- ZRANGE key min max：按照score排序后，获取指定排名范围内的元素
- ZRANGEBYSCORE key min max：按照score排序后，获取指定score范围内的元素
- ZDIFF、ZINTER、ZUNION：求差集、交集、并集

注意：所有的排名默认都是升序，如果要降序则在命令的Z后面添加REV即可，例如：

- **升序**获取sorted set 中的指定元素的排名：ZRANK key member
- **降序**获取sorted set 中的指定元素的排名：ZREVRANK key memeber

## 3.5 Hash 哈希表

field(String):value

用java解释就是`Map<String,Map<Object,Object>>`

Hash的常见命令有：

- HSET key field value：添加或者修改hash类型key的field的值
- HGET key field：获取一个hash类型key的field的值
- HMSET：批量添加多个hash类型key的field的值
- HMGET：批量获取多个hash类型key的field的值
- HGETALL：获取一个hash类型的key中的所有的field和value
- HKEYS：获取一个hash类型的key中的所有的field
- HINCRBY:让一个hash类型key的字段值自增并指定步长
- HSETNX：添加一个hash类型的key的field值，前提是这个field不存在，否则不执行

## 3.6 Bitfield 位域

通过bitfield命令可以一次性操作多个**多个比特位域**（连续的多个比特位），执行操作并返回一个相应数组，了解即可

位域修改，溢出控制

直接修改底层

## 3.7 Bitmap 位图

由0和1状态表现得二进制bit数组

主要用于状态记录等等

主要命令有：

- setbit key offset val:给指定key的值的第offset赋值val
- getbit key offset:获取指定key的offset位
- bitlen key：返回占用的字节数
- bitcount key start end:返回指定key中【start，end】中1的数量
- bitop operation destkey key:对不同的二进制存储数据进行位运算（and、or、not、xor）

## 3.8 Geospatial 地理空间

存储地理位置信息，并对信息进行操作

- 添加地理位置坐标
- 获取地理位置的坐标
- 计算两个位置之间的距离
- 根据用户给定的经纬度坐标来获取指定范围内的地理位置集合

常用命令：

- geoadd:多个经纬度、位置名称添加到指定的key中
- geopos:从键里面返回所有给定的位置元素
- geodist:返回两个位置之间的距离
- georadius:以给定经纬度为中心，返回与中心距离不超过给定最大距离的所有位置元素
- georadiusbymember:与georadius类似
- geohash:返回一个或多个位置元素的geohash表示

## 3.9 Hyperloglog 基数统计

输入元素数量或体积非常大时，计算基数所需要的空间总是固定且很小的。

***去重复基数统计***

基数：去重复的数据集合（只记录个数不存储数据）

适用场景一般为统计UV（Unique VIsitor）、统计网站关键词搜索…

常用命令：pfadd\pfcount\pfdebug\pfmerge\pfselftest

## 3.10 Stream 流

类似mq中间件，主要用于消息队列

特殊符号：

- +，- ：最大最小可能出现的id
- $：只消费新的消息
- \> ：用于xreadgroup命令，表示迄今还没有发送给组中使用者的信息，会更新新消费者组的最后id
- *：用于xadd命令，让系统自动生成id

常用命令：xadd\xrevrange\xdel…

更多命令参考：https://redis.io/docs/latest/commands/?group=stream

# 4 Redis持久化

## 4.1 RDB

RDB全称Redis Database Backup file（Redis数据备份文件），也被叫做Redis数据快照。简单来说就是把内存中的所有数据都记录到磁盘中。当Redis实例故障重启后，从磁盘读取快照文件，恢复数据。快照文件称为RDB文件，默认是保存在当前运行目录。

RDB持久化在四种情况下会执行：

- 执行save命令：由redis主线程来执行rdb，会阻塞所有命令，只有在数据迁移时可能用到
- 执行bgsave命令：开启独立进程完成RDB，主进程可以持续处理用户请求，不受影响
- Redis停机时：Redis停机时会执行一次save命令，实现RDB持久化。
- 触发RDB条件时：Redis内部有触发RDB的机制，可以在redis.conf文件中找到如：save 900 1

## 4.2 AOF

AOF全称为Append Only File（追加文件）。Redis处理的每一个写命令都会记录在AOF文件，可以看做是命令日志文件。

AOF默认是关闭的，需要修改redis.conf配置文件来开启AOF：

```conf
# 是否开启AOF功能，默认是no
appendonly yes
# AOF文件的名称
appendfilename "appendonly.aof"
```

AOF的命令记录的频率也可以通过redis.conf文件来配：

```conf
# 表示每执行一次写命令，立即记录到AOF文件
appendfsync always 
# 写命令执行完先放入AOF缓冲区，然后表示每隔1秒将缓冲区数据写到AOF文件，是默认方案
appendfsync everysec 
# 写命令执行完先放入AOF缓冲区，由操作系统决定何时将缓冲区内容写回磁盘
appendfsync no
```

appendfsync三种命令的对比：

|  配置项  |   刷盘时机   |           优点           |            缺点            |
| :------: | :----------: | :----------------------: | :------------------------: |
|  always  |   同步刷盘   | 可靠性高，数据几乎不丢失 |         性能影响大         |
| everysec |   每秒刷盘   |         性能适中         |      最多丢失1秒数据       |
|    no    | 操作系统控制 |         性能最好         | 可靠性差，可能丢失大量数据 |

**AOF重写机制**

因为是记录命令，AOF文件会比RDB文件大的多。而且AOF会记录对同一个key的多次写操作，但只有最后一次写操作才有意义。通过执行bgrewriteaof命令，可以让AOF文件执行重写功能，用最少的命令达到相同效果。

Redis也会在触发阈值时自动去重写AOF文件。阈值也可以在redis.conf中配置：

```conf
# AOF文件比上次文件 增长超过多少百分比则触发重写
auto-aof-rewrite-percentage 100
# AOF文件体积最小多大以上才触发重写 
auto-aof-rewrite-min-size 64mb 
```

## 4.3 RDB与AOF对比

RDB和AOF各有自己的优缺点，如果对数据安全性要求较高，在实际开发中往往会**结合**两者来使用。

|                |                     RDB                      |                          AOF                           |
| :------------: | :------------------------------------------: | :----------------------------------------------------: |
|   持久化方式   |             定时对整个内存做快照             |                    记录每一次写操作                    |
|   数据完整性   |           不完整，两次备份之间丢失           |                相对完整，取决与刷盘策略                |
|    文件大小    |              有压缩，文件体积小              |                  记录命令，文件体积大                  |
|  宕机恢复速度  |                     很快                     |                           慢                           |
| 数据恢复优先级 |          低，因为数据完整性不如AOF           |                  高，因为数据完整性高                  |
|  系统资源占用  |            高，大量CPU和内存消耗             | 低，主要是磁盘IO资源但AOF重写时会占用大量CPU和内存资源 |
|    使用场景    | 可以容忍数分钟的数据丢失，追求更快的启动速度 |                  对数据安全性要求较高                  |

# 5 Redis事务

Redis事务提供了一种将多个命令请求打包然后**一次性、顺序性**执行的机制。事务功能是通过MULTI、EXEC、DISCARD和WATCH这几个命令实现的。事务中的所有命令都会按照顺序执行，并且在事务执行期间，Redis服务器不会被其他客户端的请求打断。然而，与传统的关系型数据库事务不同，Redis事务并不能保证严格意义上的原子性，即使在事务中某个命令执行失败，也不会停止执行其他命令，也___不会回滚已经执行的命令___。

## 5.1 基础命令

- **开始事务**：使用`MULTI`命令开始一个事务，执行后Redis返回`OK`表示进入了事务模式。

- **命令入队**：在`MULTI`之后和`EXEC`之前的所有命令，都会被放入事务队列中，但不会立即执行。

- **执行事务**：使用`EXEC`命令提交事务，此时Redis会以FIFO（先进先出）的方式执行队列中的所有命令。

- **取消事务**：使用`DISCARD`命令可以取消事务，放弃执行已经在事务队列中的命令。

- **监控键值**：通过`WATCH`命令可以监控一个或多个键值，如果在事务执行前这些键值发生变化，则会自动回滚事务。

## 5.2 Redis事务的特性
  - **原子性**：事务作为一个整体被执行，要么全部执行，要么全部不执行。
  - **顺序性**：事务内部的命令会按照它们在队列中的顺序执行。
  -    **排他性**：在事务执行期间，Redis服务器不会被其他客户端的请求打断。
  - **非隔离性**：Redis事务不支持像传统数据库那样的隔离级别，事务中的命令有可能被其他命令插队执行。
  - **不支持回滚**：如果事务中存在错误，Redis不会回滚已经执行的命令。

# 6 Redis管道

Redis管道（Pipeline）是Redis客户端**发送多个命令**请求的一种机制。通过将一系列的单独命令请求打包成一组，可以一次性地从服务器返回结果，从而提高执行效率和性能。在客户端层面，管道操作就像是在一个事务中一样，所有命令都先积累起来，然后在某个时间点一起发送给服务器。服务器端会缓存这些命令，并在内部处理后一次性响应结果给客户端。这种方式减少了网络通信的开销，因为客户端只需要与服务器进行一次交互就可以获取多个命令的结果。

# 7 Redis发布与订阅

Redis发布与订阅模式（Pub/Sub）是一种消息通信模式，允许消息的发送者（publisher）发送消息到一个频道（channel），而消息的接收者（subscriber）可以订阅这个频道来接收消息。这种模式在实时通信、事件驱动的应用中非常有用。

> 个人不推荐使用，了解即可

发布订阅的操作命令

- `SUBSCRIBE`：订阅一个或多个频道。
- `UNSUBSCRIB`：取消订阅某个频道。
- `PSUBSCRIBE`：模式订阅，可以订阅多个匹配模式的频道。
- `PUBLISH`：向一个或多个频道发送消息。

# 8 Redis复制

Redis主从复制是指将Redis服务器的数据从一个节点（主节点）复制到其他节点（从节点）的过程。主节点负责处理写操作，而复制操作则是单向的，只能由主节点到从节点。一个主节点可以有多个从节点，但一个从节点只能有一个主节点。主从复制的主要作用包括数据冗余、读写分离和故障恢复。

## 8.1 主从复制

**配从不配主**：从机配置去找主机，主机不动

从机配置：

```conf
masterauth <master-password>
replicaof master-ip master-port
```

常用命令：

- `info replication`:可以查看复制节点的主从关系和配置信息
- `replicaof master-ip master-port`:配置在conf中
- `slaveof master-ip master-port`:切换主库
- `slaveof no one`:停止连接

操作步骤：

- 开启`daemonize yes`
- 注释掉`bing 127.0.0.1`
- 设置`protected-mode no`
- 指定端口
- 指定当前工作目录
- `pid`文件名字
- `log`文件名字
- `requirepass`
- `dump.rdb`名字
- `aof`文件，`appendfilename`
- 从机访问主机的通行密码`masterauth`

注意：

从机只读，主机可读可写（一般只做写）

从机开机迟让可复制主机所有信息

主机挂掉，从机原地待命，不会争夺主机

主机重新启动，主从关系依然在

配置文件中的永久有效，手动命令的单次有效

# Redis Springboot整合

pom文件导入

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

properties配置

```properties
spring.redis.host=192.168.200.128
spring.redis.port=6379
spring.redis.password=123456
spring.redis.database=0
```

