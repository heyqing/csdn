 

# 1、认识git

------

​        Git是一个开源的分布式版本控制系统，用于高效地处理从小型到大型的项目版本管理。它最初由Linux操作系统内核的创造者Linus Torvalds在2005年开发。Git的设计注重性能、安全性和灵活性，允许开发者在本地提交更改，并通过克隆整个仓库来在本地机器上保持项目的完整历史记录。Git的核心优势在于其分布式架构，这意味着每个开发者的工作副本都是一个完整的存储库，包含了所有变更的完整历史记录.

[Git官网](https://git-scm.com/)

------

# 2、git功能

## 2.1、功能

------

1、从服务器上克隆完整的Git仓库（包括代码和版本信息）到单机上。

2、在自己的机器上根据不同的开发目的，创建分支，修改代码。

3、在单机上自己创建的分支上提交代码。

4、在单机上合并分支。

5、把服务器上最新版的代码fetch下来，然后跟自己的主分支合并。

6、生成补丁（patch），把补丁发送给主开发者。

7、看主开发者的反馈，如果主开发者发现两个一般开发者之间有冲突（他们之间可以合作解决的冲突），就会要求他们先解决冲突，然后再由其中一个人提交。如果主开发者可以自己解决，或者没有冲突，就通过。

8、一般开发者之间解决冲突的方法，开发者之间可以使用pull 命令解决冲突，解决完冲突之后再向主开发者提交补丁。

从主开发者的角度（假设主开发者不用开发代码）看，git有以下功能：

1、查看邮件或者通过其它方式查看一般开发者的提交状态。

2、打上补丁，解决冲突（可以自己解决，也可以要求开发者之间解决以后再重新提交，如果是[开源项目](https://baike.baidu.com/item/开源项目/3406069?fromModule=lemma_inlink)，还要决定哪些补丁有用，哪些不用）。

3、向公共服务器提交结果，然后通知所有开发人员。

## 2.2、优缺点

优点：

适合[分布式开发](https://baike.baidu.com/item/分布式开发/4143301?fromModule=lemma_inlink)，强调个体。

公共服务器压力和数据量都不会太大。

速度快、灵活。

任意两个开发者之间可以很容易的解决冲突。

离线工作。

缺点：

资料少（起码中文资料很少）。

学习周期相对而言比较长。

不符合[常规思维](https://baike.baidu.com/item/常规思维/9532113?fromModule=lemma_inlink)。

代码[保密性](https://baike.baidu.com/item/保密性/4928247?fromModule=lemma_inlink)差，一旦开发者把整个库克隆下来就可以完全公开所有代码和版本信息。

------

# 3、基础操作

## 3.1、配置git

------

​        在使用Git之前，通常需要进行一些基本配置。通常包括设置用户名和电子邮件地址：

```bash
git config --global user.name "Your Name"
git config --global user.email "your_email@example.com"
```

![img](https://i-blog.csdnimg.cn/direct/4fbaaa95911c4324af3e4c494916c908.jpeg)
 ![img](https://i-blog.csdnimg.cn/direct/421fb7571dd44501854e327ac04a6026.jpeg)

## 3.2、创建git仓库

> 下载并配置完成Git后会有相应的Bash
>
> [鼠标右击] => [显示更多选项] => [Git Bash Here]
>
> 打开后如图：
>
> ![img](https://i-blog.csdnimg.cn/direct/473421464c914b3e960ee2a66fa41931.jpeg)以后得git语句都会再次编写~~
>

### **3.2.1、初始化新仓库**

------

​        在项目目录下执行 `git init` 命令来初始化一个新的Git仓库。

```bash
git init
```

![img](https://i-blog.csdnimg.cn/direct/60966772dd594147bec7b497ae6c5b5c.jpeg)

​        使用init创建出仓库后文件夹会创建出.git文件夹【默认是隐藏的需打开文件夹的

[查看]=>[显示]=>[隐藏的项目]】，并且默认分支为master。

------

### **3.2.2、克隆现有仓库**

------

​         使用 `git clone [repository URL]` 命令来克隆一个远程仓库到本地。

```bash
git clone [repository URL]
```

 例如：

```bash
git clone https://github.com/heyqing/git.git

# 或者使用 SSH 地址：
git clone git@github.com:heyqing/git.git
```

## 3.3、文件操作

### **3.3.1、查看文件状态**

------

​        使用 `git status` 命令来查看当前文件的状态，包括新增、修改、删除等。

```bash
git status
```

### **3.3.2、跟踪新文件**

------

​        使用 `git add [filename]` 命令来跟踪新文件或已修改的文件。

```bash
git add [filename]
```

### **3.3.3、提交更新**

------

​        使用 `git commit -m "Commit message"` 命令来提交已跟踪的文件到本地仓库。

```bash
git commit -m "Commit message"
```

------

>  ![img](https://i-blog.csdnimg.cn/direct/a5e2037830b140a6bd18267a9914a571.jpeg)
>
> ​         文件夹中想创建一个文本，直接查看状态为提示需要 add 的有README.md ，使用 add 添加过后再次查看提示可以提交的有README.md，commit 提交过后提示无可commit文件。

## 3.4、查看提交历史

------

​        使用 `git log` 命令来查看提交历史记录。

```bash
git log
```

![img](https://i-blog.csdnimg.cn/direct/5fb7e572bcd5483aa208fd0424c34134.jpeg)

​        显示commit的id，提交的分支，提交的作者信息，时间，提交的信息

------

## 3.5、远程仓库操作

> ​        Git 远程仓库是指托管在因特网或其他网络中的你的项目的版本库。它允许你与其他开发者协作开发和共享代码，也可以作为你的代码的备份和发布的平台。你可以有多个远程仓库，有些是只读的，有些是可读写的。
>
> Git 远程仓库的主要作用包括：
>
> 1. **分享和协作**：你可以将你的代码推送到远程仓库，让其他人能够看到你的工作，或者邀请其他人参与你的项目。你也可以从远程仓库拉取或合并其他人的修改，实现代码的同步和协作。
> 2. **备份和恢复**：你可以将你的代码存储在远程仓库，作为你本地仓库的一个备份。这样，即使你的本地仓库丢失或损坏，你也可以从远程仓库恢复你的代码。
> 3. **分支和标签**：你可以在远程仓库创建和管理分支和标签，用来表示你的项目的不同阶段或功能。你可以在不同的分支上进行并行的开发，或者在不同的标签上进行版本的发布。
> 4. **审查和质量**：你可以在远程仓库进行代码的审查和质量检查，用来保证你的代码的质量和风格。你可以使用远程仓库的一些功能，如pull request，code review，issue，merge request等，来进行代码的评审和反馈。
>
> 常见的远程仓库有：
>
> [Github](https://github.com/):被称为世界最大的代码托管平台
>
> [Gitee](https://gitee.com/):提供中国本土化的代码托管服务

### **3.5.1、查看远程仓库**

------

​        使用 `git remote -v` 命令来查看已配置的远程仓库。

```bash
git remote -v
```

### **3.5.2、添加远程仓库**

------

​        使用 `git remote add [remote name] [repository URL]` 命令来添加一个新的远程仓库。

```bash
git remote add [remote name] [repository URL]
```

###  3.5.3、**从远程仓库获取数据**

------

​        使用 `git fetch [remote name]` 命令从远程仓库获取最新数据。

```bash
git fetch [remote name]
```

###  **3.5.4、推送数据到远程仓库**

------

​        使用 `git push [remote name] [branch name]` 命令推送本地分支到远程仓库。

```bash
git push [remote name] [branch name]
```

------

![img](https://i-blog.csdnimg.cn/direct/978823468c1a4d92bb236a654428fe06.jpeg)

## 3.6、分支管理

### **3.6.1、创建分支**

------

​        使用 `git branch [branch name]` 命令创建一个新分支。

```bash
git branch [branch name]
```

### 3.6.2、 **切换分支**

------

​        使用 `git checkout [branch name]` 命令切换到指定分支。

```bash
git checkout [branch name]
```

###   3.6.3、**合并分支**

------

​        使用 `git merge [branch name]` 命令合并指定分支到当前分支。

```bash
git merge [branch name]
```

------

> ![img](https://i-blog.csdnimg.cn/direct/7b030bbac2944a48becb8a3c1252f239.jpeg)
>
> ​        创建和切换可合并为:
>
> ```bash
> git checkout -b [branch name]
> ```
>
> ![img](https://i-blog.csdnimg.cn/direct/546bc5f5d68441299889ca9b4d1d68ca.jpeg)
>
>  ​        谁需要合并谁，在谁那使用，如这里 master 需要合并 br ，则需要在 master 下使用 merge

## 3.7、标签管理

> ​        Git标签（Tag）是Git版本库的一个标记，用来指向某个特定的提交（Commit）。它类似于书签，可以帮助你快速定位到项目的关键版本或里程碑。Git标签主要分为两种类型：轻量级标签（Lightweight Tag）和附注标签（Annotated Tag）。轻量级标签仅仅是一个引用，指向特定的提交，而附注标签则是一个完整的Git对象，包含作者信息、标签信息、日期等，并且可以被签名和验证。
>
> Git标签的用途主要包括：
>
> 1. **版本管理**：通过为每个发布的版本打上标签，可以清晰地追踪项目的历史版本。
> 2. **快速定位**：使用标签可以快速检查或恢复到特定的版本，而无需查找复杂的提交哈希值。
> 3. **发布管理**：在软件发布时，通常会创建一个新的标签，以便于识别和分发特定的版本。
> 4. **协作沟通**：在团队合作中，标签可以作为讨论和参照的基准，帮助团队成员理解项目的状态和进展。

### **3.7.1、创建标签**

​        使用 `git tag [tag name]` 命令创建一个新的标签。

```bash
git tag [tag name]
```

###  **3.7.2、查看标签**

​        使用 `git tag` 命令查看所有标签。

```bash
git tag
```

![img](https://i-blog.csdnimg.cn/direct/4ea91c09d1f54c3ab69ce327ea839b1a.jpeg)

## 3.8、撤销操作

### **3.8.1、撤销提交**

------

​        使用 `git reset HEAD~` 命令撤销最近一次提交。

```bash
git reset HEAD~
```

###  **3.8.2、撤销对文件的修改**

------

​        使用 `git checkout -- [filename]` 命令撤销对文件的修改。

```bash
git checkout -- [filename]
```

# 4、完！

