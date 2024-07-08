# 前言

​		本篇主要讲述的是关系型数据库Sql Server，原因也很简单，因为大部分学校还在以sql server 为教学材料，不过没关系无论是sql server 、mysql 还是oracle,只要是关系型数据库，概念都是相通的，语句也大差不差。

​		关系型数据库是一种采用关系模型来组织数据的数据库系统。它将数据存储在表格形式的结构中，通常称为表。这些表由行和列组成，每一行代表一条记录，每一列代表一个字段。关系型数据库的核心是表之间的关系，这些关系可以是一对一、一对多或多对多的形式。关系型数据库的设计允许用户通过查询语言（如SQL）来检索、更新和管理数据。

​		关系型数据库主要用于存储、检索和管理结构化数据。它们在企业级应用中非常流行，因为它们提供了数据一致性、完整性和并发控制的保障。关系型数据库可以处理复杂的事务处理，支持多种数据类型，并且可以高效地查询数据。此外，它们还提供了安全可靠的数据访问机制。关系型数据库广泛应用于银行、零售、电信、互联网等领域，例如亚马逊的AWS和谷歌的GCP都是基于关系型数据库构建的。

# 1、数据库系统概述

## 1.1、数据库基础知识

1. **信息与数据**

- *数据（Data*）：数据是原始的、未经处理的数字、文字、图像或其他形式的输入，它们本身没有意义。例如，一个人的姓名、年龄、性别等信息单独存在时，只是数据。
- *信息（Information）*：信息是经过处理、组织或分析后的数据，它具有一定的意义和目的。例如，将一个人的姓名、年龄、性别组合起来，形成了一个人的基本信息。

2. **数据库（Database）**

- *数据库*：数据库是一个有组织的、可共享的数据集合，这些数据按照特定的模式存储，以便于管理和检索。数据库可以是关系型的，也可以是非关系型的，如文档型、键值型、图形型等。

3. **数据库管理系统（Database Management System, DBMS）**

- *数据库管理系统*：DBMS 是一种软件系统，用于创建、维护和使用数据库。它允许用户定义、查询、更新和管理数据库中的数据。常见的 DBMS 包括 MySQL、Oracle、SQL Server、PostgreSQL 和 MongoDB 等。

4. **数据库系统（Database System）**

- *数据库系统*：数据库系统是指由数据库、数据库管理系统以及相关的软件、硬件和人员组成的整体。它不仅包括数据的存储和管理，还包括数据的处理、维护、安全和备份等功能。

**联系**

- **数据与信息**：数据是信息的原材料，信息是数据处理后的产物。在数据库系统中，原始数据被收集、存储，并通过各种操作转化为有用的信息。
- **DB与DBMS**：数据库是数据的物理存储，而DBMS 是管理和操作这些数据的软件工具。DBMS 提供了用户与数据库之间的接口，使得用户可以方便地进行数据的增删改查等操作。
- **DBMS与DBS**：DBMS 是数据库系统的核心组成部分，负责数据库的逻辑和物理层面的管理。数据库系统则包含了DBMS，同时还包括了支持DBMS运行的硬件、软件环境，以及管理和使用数据库的人员。DBS(DB + DBMS)。
- **数据库系统与信息**：数据库系统通过对数据的组织和管理，使得数据能够有效地转化为信息，从而支持决策制定、业务流程和其他各种应用。

## 1.2、数据库系统的结构

### 1.2.1、相关概念

1. **型**
   - 数据的“型”指的是*数据的结构*，数据的结构指的是*数据的内部构成和对外联系*。
   - 例如：图书的数据 = 图书编号 + 书名 + 作者 等属性组成
2. **值**
   - 数据的“值”指的是*数据的具体取值*
   - 例如： “CSDN0001,数据库期末法典，heyqings” 就是图书的一个具体值
3. **模式和实例**
   - 模式（schema）指的是对一个事物的图解、框架之意，目的是进一步认识这个事物。
   - 数据库的描述称之为数据库模式，模式是数据库中全体数据逻辑结构和特征的描述，不涉及具体的值
   - 模式是静态的，反映数据的结构及其联系，数据库设计阶段确定，一般不会频繁修改
   - 实例，模式的一个具体值，反映数据库在某一刻的状态，因此是动态的
   - 同一模式可以有多个实例

### 1.2.2、数据库系统的内部体系结构

1. **数据抽象**

   - 物理层抽象
   - 逻辑层抽象
   - 视图层抽象

2. **数据库系统的三级模式结构**

   ***应用程序 => 外模式(用户级) => 模式(概念级) => 内模式(物理级) => 数据库***

   - *模式（Schema）*：也称为概念模式或逻辑模式，它是数据库中全体数据的逻辑结构和特征描述，是所有用户的公共数据视图。

     ![Schema](https://i-blog.csdnimg.cn/direct/fca240304f094b5da41aa2bd35bdbe9a.png)

   - *外模式（External Schema）*：也称为子模式（Subschema）或用户模式，它是数据库用户能够看见和使用的局部数据的逻辑结构和特征描述，是与某一应用有关的数据的逻辑表示。

     ![ExternalSchema](https://i-blog.csdnimg.cn/direct/58c7c2f9cfca4105b664212096bd8dd3.png)

   - *内模式（Internal Schema）*：也称为存储模式（Storage Schema），它是对数据库中数据物理结构和存储方式的描述。

     ![InternalSchema](https://i-blog.csdnimg.cn/direct/f423b76ef11a4d2eabc9298fa61c89d5.png)

3. **两层映象与数据独立性**

   - *外模式/模式映像*：定义了各个外模式与概念模式之间的映像关系，这些映像定义通常在各自的外模式中加以描述。
     - 对于同一个模式可以有多个外模式
     - 对于一个外模式，数据库都有一个外模式/模式映像
   - *模式/内模式映像*：定义了数据库全局逻辑结构与物理存储之间的关系，这种模式定义通常在模式中加以描述。
     - 模式/内模式映像是唯一的

4. **三级模式结构与两层映像的优点**

   - 保证数据的独立性
   - 方便用户使用，简化用户接口
   - 保证数据库安全性

### 1.2.3、数据库系统的外部体系结构

1. **三层架构**

   界面表示层 => 业务处理层 => 数据访问层

   - 界面表示层（用户界面层）
   - 业务处理层（应用层）
   - 数据访问层

2. **结构类型**

   - 单用户数据库系统：整个数据库系统（应用程序、DBMS、数据）装在一台计算机上，为一个用户独占，不同机器之间不能共享数据。
   - 主从式数据库系统：也称为集中式结构，是一个主机带多个终端用户结构的数据库系统。所有处理任务都由主机来完成，用户通过主机的终端可同时或并发地存取数据库。
   - 分布式数据库系统：数据库中的数据在逻辑上是一个整体，但物理地分布在计算机网络的不同结点上。网络中的每个结点都可以独立处理本地数据库中的数据，执行局部应用，同时也可以存取和处理多个异地数据库中的数据，执行全局应用。
   - 客户机/服务器（C/S）结构的数据库系统：将数据库系统看作由两个非常简单的部分组成，一个服务器（后端）和一组客户（前端）。服务器指DBMS本身，客户指在DBMS上运行的各种应用程序。
   - 浏览器/服务器（B/S）结构的数据库系统：用户通过浏览器来访问数据库，服务器负责处理数据库的请求并返回结果给用户。



# 2、关系数据库基础理论

## 2.1、关系的概念

1. **域**：关系中每个属性的取值范围称为域。
2. **笛卡尔积**：关系中的所有可能元组的集合称为笛卡尔积。
3. **元组**：笛卡尔积中的每一个元素称为一个元组。
4. **关系**：在某个域上的笛卡尔积的子集称为关系。

相关的术语

- 元组与属性
  - 表中每一行对应关系的一个元组
  - 表中每一列对应一个域，多列的域可以相同，为了加以区分将每一列称属性，即n元关系必须有n个属性
- 候选码与主码
  - 候选码：关系中某*属性或属性组* 可以唯一标识一个元组，而其子集不能
  - 主码：候选码中最小属性组
- 主属性与非主属性
  - 主属性：候选码中属性
  - 非主属性：不包含任何候选码中的属性

## 2.2、关系数据模型

1. **关系模型及其要素**
   - 关系的描述称之为关系模式，关系模式即关系的框架或结构：***R(U,D,dom,F)***
   - R:关系名，U:关系的属性集，D:属性组中属性所来的域，dom：属性向域的映射集合，F:属性间的依赖关系
   - 完整性约束：域完整性，实体完整性，参照完整性（引用完整性），用户定义完整性
2. **关系的性质和类型**
   - 性质
     - 同一列的数据具有同质性
     - 关系中所有属性都是原子的
     - 同一关系中每一列对应一个属性
     - 关系中不允许有完全相同的元组
     - 在一个关系中元组的次序是无关紧要的
     - 在一个关系中属性的次序是无关紧要的
   - 类型
     - 基本表
     - 查询表
     - 视图表

## 2.3、关系代数

1. **关系操作集合**
   - 关系操作集合包括一系列用于处理关系数据的操作，这些操作可以分为查询操作和更新操作。
   - 查询操作包括选择、投影、连接、除、并、交、差、笛卡尔积等，其中***选择、投影、并、差、笛卡尔积***是五种基本操作。
   - 更新操作包括插入、删除、修改等。
2. **专门的关系运算**
   - 选择（限制），符号：σ
     - 单价大于45
     - σ单价 > 45(图书)
   - 投影，符号：Π
     - 所有图书的书名和作者
     - Π书名，作者（图书）
   - 连接，符号：⋈
   - 除法，符号：÷

## 2.4、关系演算

- R ⋃ S = { t | t∈R ∧ t ∈S}

- R − S = { t | t ∈R ∧ ┐ t ∈ S}

- R(A) × S(B) = { t | ∃ (u ∈R) ∃(s ∈S)(t[A] = u[A] ∧ t[B] = s[B])}

- σcon(R）={ t | t ∈ R ∧ F(con) }

- ∏A( R ) = { t[A] | t ∈ R }

## 2.5、关系数据库的查询优化

代数优化、物理优化

# 3、数据库设计

**基本步骤**

需求分析 => 概念结构设计 => 逻辑结构设计 => 物理结构设计 => 数据库实施 => 数据库运行和维护

## 3.1、需求分析

### 3.1.1、需求分析任务

1. 获取需求
   - 功能性需求
   - 非功能性需求
2. 确定对象以及对象之间的关系

### 3.1.2、用例建模

- 确定系统参与者
- 确定需求用例
- 构造用例图
- 用例规约

### 3.1.3、对象模型

- 识别对象和类
- 确定属性
- 确定对象之间的关系（1-1，1-n，m-n）
- 确定服务

## 3.2、概念结构设计

### 3.2.1、概念数据模型

- **E-R图**

  ```mermaid
  graph LR
      A[实体] --- C
      B((属性)) 
      C{关系} --- B
  
  ```

  

## 3.3、逻辑结构设计

1. **概念模型设计**：首先，需要将用户需求抽象成信息结构，通常使用实体-关系（E-R）模型来描述。
2. **逻辑模型转换**：将概念模型转换为适合特定数据库管理系统（DBMS）的逻辑模型，如关系模型、网状模型或层次模型。
3. **规范化**：通过分解或合并关系模式，按照规范化理论的指导，至少达到第三范式（3NF），以消除部分函数依赖和传递依赖，减少数据冗余。
4. **设计用户子模式**：根据局部应用需求，结合具体DBMS，设计用户的外模式，以便用户能够方便地访问和更新数据

## 3.4、物理结构设计

1. **确定数据的存储结构**：这包括选择合适的数据文件组织方式，如堆文件、排序文件或哈希文件，以及决定数据的存储布局，如行存储或列存储。
2. **设计数据的存取路径**：主要是通过建立索引来实现，索引可以显著加快数据检索速度。需要决定哪些属性列应该被索引，以及是否创建单一索引或组合索引。
3. **确定数据的存放位置**：为了提高系统性能，数据应该根据其访问频率和变化频率进行分区，以便于管理和优化。
4. **确定系统配置**：包括选择合适的硬件资源，如磁盘、内存和处理器，以及配置数据库管理系统（DBMS）的存储分配参数。

# 4、规范化理论

## 4.1、函数依赖及关系范式

### 4.1.1、函数依赖

函数依赖是指在关系模式中，一个或一组属性（称为决定因素）能够决定另一个或一组属性（称为依赖因素）的关系。函数依赖通常用 X→Y来表示，其中X 是决定因素，Y 是依赖因素。

- **平凡函数依赖**：如果 X包含了所有可能的值，那么X→Y 总是成立的，这种依赖被称为平凡函数依赖。
- **非平凡函数依赖**：如果 X不包含所有可能的值，但仍然能够决定Y，这种依赖被称为非平凡函数依赖。
- **完全函数依赖**：如果 X的任何真子集都不能决定Y，那么 X→ (f)Y 被称为完全函数依赖。
- **部分函数依赖**：如果 X的某个真子集可以决定Y，那么 X→(p)Y 被称为部分函数依赖。
- **传递函数依赖**：如果存在 X→Y和Y→Z，但 Y不能决定X，那么 X→(t )Z 被称为传递函数依赖。

### 4.1.2、关系范式

关系范式是用来衡量关系模式是否规范化的标准。常见的关系范式包括：

- **第一范式（1NF）**：要求关系模式中的每个属性都是原子的，即不可再分。
- **第二范式（2NF）**：要求关系模式中的每个非主属性完全函数依赖于主键。
- **第三范式（3NF）**：要求关系模式中的每个非主属性既不部分依赖于主键，也不传递依赖于主键。
- **博伊斯-科得范式（BCNF）**：要求关系模式中的每个决定因素都是超键。
- **第四范式（4NF）**：要求关系模式中的每个非平凡多值依赖都包含码。

## 4.2、函数依赖公理系统

### 4.2.1、Armstrong公理系统

- 逻辑蕴含:如果从给定的F能推导出X→Y，则称F逻辑蕴含X→Y
- 函数依赖集的闭包：F逻辑蕴含的所有函数依赖集合，记作F+

Armstrong公理系统的基本规则包括：

1. **自反律**：如果属性集Y是属性集X的子集，并且X是U的子集，那么X→Y是R上成立的。
2. **增广律**：如果X→Y在R上成立，并且Z是U的子集，那么XZ→YZ在R上成立。
3. **传递律**：如果X→Y和Y→Z在R上成立，那么X→Z在R上成立。

Armstrong公理系统还包括一些推论规则，例如：

1. **合并规则**：如果X→Y和X→Z在R上成立，那么X→YZ在R上也成立。
2. **伪传递律**：如果X→Y和WY→Z在R上成立，那么XW→Z在R上也成立。
3. **分解规则**：如果X→Y在R上成立，并且Z是Y的子集，那么X→Z在R上也成立。

### 4.2.2、函数依赖集的等价和最小化

1. 函数依赖集的等价

   - F+ = G+的充分必要条件是G+包含F,F+包含G

   - 两个函数依赖集等价的定义是：如果它们的闭包相等，即一个函数依赖集能够推导出另一个函数依赖集中的所有函数依赖，反之亦然。这种等价关系允许我们在不同的函数依赖集中进行转换，而不会改变关系模式的本质特征。

2. 函数依赖集的最小化

   - 函数依赖集的最小化是指找到一个包含最少函数依赖的集合，同时保持与原集合等价。最小化的函数依赖集有助于简化关系模式，减少存储空间和提高查询效率。最小化通常通过逐步移除不必要的函数依赖来实现，这些依赖要么是冗余的，要么可以通过其他依赖间接推导出来。

# 5、sql语句（T-SQL为例）

## 5.1、基础知识

### 5.1.1、语法格式约定

T-SQL（Transact-SQL）是Microsoft SQL  Server中使用的扩展版本的SQL语言。在T-SQL中，命令和语句的书写通常不区分大小写，但是为了提高代码的可读性，建议使用统一的命名规范，例如使用大写字母表示关键字，小写字母表示变量和列名。

### 5.1.2、运算符

T-SQL中的运算符分为几类：

1. **算术运算符**：包括加（+）、减（-）、乘（*）、除（/）和取模（%）。
2. **比较运算符**：用于比较两个值是否相等或不等，包括等于（=）、不等（<> 或 !=）、大于（>）、小于（<）、大于等于（>=）和小于等于（<=）。
3. **逻辑运算符**：用于组合多个条件判断，包括AND、OR、NOT、IN、BETWEEN、EXISTS、ANY、ALL、SOME、LIKE等。
4. **字符串连接运算符**：用于连接两个字符串，通常使用加号（+）。
5. **位运算符**：包括按位与（&）、按位或（|）、按位异或（^）和按位取反（~）。

### 5.1.3、批处理和脚本

- 批处理
  - 批处理是T-SQL语句集合的逻辑单元。在批处理中，所有的语句要么被放在一起通过解析，要么没有一句能够执行。批处理内部的语句被整合成一个执行计划，这意味着批处理中的语句是一起提交给服务器的，可以节省系统开销。如果批处理中的任何一条语句存在语法错误，整个批处理都无法通过编译，也不会执行。
- 脚本
  - 脚本则是由一系列的SQL命令组成的文本文件，它可以包含多个批处理。脚本中的每个批处理都是独立执行的，一个批处理的错误不会阻止其他批处理的运行。脚本中常用的控制语句包括变量声明、分支、循环等，这些控制语句使得脚本能够实现一些复杂的任务。

## 5.2、数据操作语言（DDL）

### 5.2.1、定义语言

- 数据库定义

  ```sql
  -- 通常情况下只需要以下语句
  CREATE DATABASE MyDataBase;
  ```

- 基本表定义

  ```sql
  -- 定义语法
  CREATE TABLE table_name (
      column1 datatype constraint1,
      column2 datatype constraint2,
      column3 datatype constraint3,
      ...
      columnN datatype constraintN
  );
  -- 例如
  CREATE TABLE Students (
      StudentID INT PRIMARY KEY,
      Name VARCHAR(50) NOT NULL,
      Age INT,
      Gender CHAR(1),
      Major VARCHAR(100)
  );
  ```

  1. 数据属性约束

  - **主键约束（PRIMARY KEY）**：确保主键列唯一且不允许为空。
  - **唯一约束(UNIQUE)**：确保列或列组合中的数据唯一，允许为空，但每个空值只能出现一次。
  - **检查约束(CHECK(?))**：限制列可接受的值范围或格式，通常通过逻辑表达式来定义。
  - **默认约束(DEFAULT)**：为列设置默认值，当插入新记录时，如果未指定该列的值，则会使用默认值。
  - **外键约束(FOREIGN KEY)**：在两个表之间建立关系，确保参照完整性，即一个表中的外键值必须在另一个表的主键中存在。

  2. 数据的基本类型有：

  **数值数据类型**

  - `INT`：可以存储4个字节的整数值，范围从-2,147,483,648到2,147,483,647。
  - `SMALLINT`：可以存储2个字节的整数值，范围从-32,768到32,767。
  - `TINYINT`：可以存储1个字节的整数值，范围从0到255。
  - `BIGINT`：可以存储8个字节的整数值，范围从-263到263-1。
  - `DECIMAL` 和 `NUMERIC`：用于存储精确的小数值，可以指定精度和小数位数。
  - `FLOAT`：用于存储浮点数值，可以指定精度。
  - `MONEY` 和 `SMALLMONEY`：用于存储货币值，通常有四位小数。

  **字符数据类型**

  - `CHAR`：存储固定长度的字符串，不足部分会用空格填充。
  - `VARCHAR`：存储可变长度的字符串，不需要额外的空格填充。
  - `NCHAR` 和 `NVARCHAR`：类似于 `CHAR` 和 `VARCHAR`，但用于存储Unicode字符数据，`NCHAR` 是固定长度，`NVARCHAR` 是可变长度。

  **日期和时间数据类型**

  - `DATETIME`：用于存储日期和时间值，范围从1753年1月1日到9999年12月31日。

  **其他数据类型**

  - `TEXT`、`NTEXT`、`IMAGE`、`VARBINARY(MAX)` 和 `XML`：用于存储大量文本或二进制数据。

- 修改基本表

  ```sql
  -- 新增新列
  ALTER TABLE <表名>
  ADD <列名> <数据类型> [<列级完整性约束>]
  -- 修改原列
  ALTER TABLE <表名>
  ALTER COLUMN <列名> <新数据类型> [<列级别完整性约束>]
  -- 删除列
  ALTER TABLE <表名>
  DROP COLUMN <列名>
  -- 添加或删除完整性约束
  ALTER TABLE <表名>
  ADD CONSTRAINT <约束名> <约束定义>
  
  ALTER TABLE <表名>
  DROP CONSTRAINT <约束名>
  ```

  

- 索引定义

  ```sql
  -- 创建
  CREATE [UNIQUE][CLUSTERED | NONCLUSTERED] INDEX index_name
  ON {table_name | view_name} [WITH [index_property [,....n]]]
  ```

  - `UNIQUE`：表示创建的是唯一索引，不允许有重复的键值。
  - `CLUSTERED`：表示创建的是聚集索引。
  - `NONCLUSTERED`：表示创建的是非聚集索引。
  - `index_name`：索引的名称。
  - `table_name | view_name`：要在其上创建索引的表或视图的名称。
  - `index_property`：索引属性，用于定义索引的额外特性，如填充因子等。

  ```sql
  -- 删除
  DROP INDEX table_name.index_name[,table_name.index_name]
  ```

  ```sql
  -- 显示
  EXEC sp_helpindex table_name
  ```

- 索引修改

  ```sql
  -- 添加或删除索引列
  -- 假设已经存在一个名为IX_WorkOrder_ProductID的非聚集索引
  -- 现在想要添加一个新列Column3到索引中
  
  CREATE NONCLUSTERED INDEX IX_WorkOrder_ProductID ON Production.WorkOrder(ProductID)
  WITH (DROP_EXISTING = ON, FILLFACTOR = 80, PAD_INDEX = ON)
  INCLUDE (Column3);
  ```

  ```sql
  -- 修改索引属性
  -- 修改名为AK_SalesOrderHeader_SalesOrderNumber的唯一索引的属性
  
  ALTER INDEX AK_SalesOrderHeader_SalesOrderNumber ON Sales.SalesOrderHeader
  SET (STATISTICS_NORECOMPUTE = ON, IGNORE_DUP_KEY = ON, ALLOW_PAGE_LOCKS = ON);
  ```

  ```sql
  -- 重建或重组索引
  -- 重建名为IX_WorkOrder_ProductID的非聚集索引
  
  ALTER INDEX IX_WorkOrder_ProductID ON Production.WorkOrder REBUILD;
  
  -- 重组名为IX_WorkOrder_ProductID的非聚集索引
  
  ALTER INDEX IX_WorkOrder_ProductID ON Production.WorkOrder REORGANIZE;
  ```

  

- 视图定义

  ```sql
  -- 创建
  CREATE [OR ALTER] VIEW [schema_name.]view_name [(column [,...n])]
  AS
  SELECT_statement
  [WITH <view_attribute> [,...n]]
  ```

  其中 `<view_attribute>` 可以是以下选项之一：

  - `ENCRYPTION`：加密视图定义，防止未授权的用户查看。
  - `SCHEMABINDING`：绑定视图，阻止对基表进行某些更改，除非先删除视图。
  - `VIEW_METADATA`：返回视图的元数据，而不是基表的元数据。

- 修改视图

  ```sql
  -- 假设我们有一个名为MyView的视图，我们想要修改它
  ALTER VIEW MyView
  AS
  SELECT Column1, Column2
  FROM MyTable
  WHERE SomeCondition = True;
  ```

### 5.2.2、操作语言

- 增

  ```sql
  INSERT INTO 表名 (列名 [, 列名...])
  VALUES (值 [, 值...])
  ```

- 删

  ```sql
  DELETE FROM 表名
  WHERE 条件
  ```

- 改

  ```sql
  UPDATE 表名
  SET 列名 = 新值 [, 列名 = 新值...]
  WHERE 条件
  ```

- 查

  ```sql
  SELECT [ALL | DISTINCT] 列名 [AS 别名]
  FROM 表名
  [WHERE 条件]
  [GROUP BY 列名]
  [HAVING 条件]
  [ORDER BY 列 [ASC | DESC]]
  ```

  - 简单查询

    ```sql
    -- 查询所有列
    SELECT * FROM TableName;
    -- 查询特定列
    SELECT Column1, Column2 FROM TableName;
    -- 条件查询
    SELECT * FROM TableName WHERE Condition;
    -- 模糊查询
    SELECT * FROM TableName WHERE Column LIKE Pattern;
    	-- 使用 % 作为通配符来匹配任意数量的字符
    	SELECT * FROM TableName WHERE Column LIKE 'Pattern%';
    -- 排序查询
    SELECT * FROM TableName ORDER BY Column ASC/DESC;
    -- 统计查询
    SELECT COUNT(*), SUM(Column), AVG(Column), MIN(Column), MAX(Column) FROM TableName;
    -- 分组查询
    SELECT Column, COUNT(*) FROM TableName GROUP BY Column HAVING Condition;
    ```

  - 连接查询

    ```sql
    -- 内连接
    SELECT column_list
    FROM table1
    INNER JOIN table2
    ON table1.column_name = table2.column_name;
    -- 外连接(OUTER JOIN)
    -- 左外(LEFT OUTER JOIN)，右外(RIGHT OUTER JOIN)
    SELECT column_list
    FROM table1
    LEFT OUTER JOIN table2
    ON table1.column_name = table2.column_name;
    -- 全连接
    SELECT column_list
    FROM table1
    FULL OUTER JOIN table2
    ON table1.column_name = table2.column_name;
    -- 交叉连接
    SELECT column_list
    FROM table1
    CROSS JOIN table2;
    -- 自连接
    SELECT column_list
    FROM table1
    SELF JOIN table2;
    ```

  - 嵌套查询

    ```sql
    SELECT column_list
    FROM outer_table
    WHERE condition
        AND (SELECT column_list
             FROM inner_table
             WHERE another_condition);
    ```

    *常见的嵌套查询类型*

    1. **IN子查询**：用于检查某个值是否存在于子查询返回的结果集中。
    2. **EXISTS子查询**：用于检查子查询是否返回至少一行数据。
    3. **NOT IN和NOT EXISTS**：用于排除某个值或数据集。
    4. **比较运算符**：如 `>`, `<`, `=` 等，用于比较子查询返回的值。

  - 组合查询

    ```sql
    -- 获取所有员工及其所在部门的信息
    SELECT Employees.EmployeeID, Employees.Name, Departments.DepartmentName
    FROM Employees
    INNER JOIN Departments ON Employees.DepartmentID = Departments.DepartmentID;
    ```

### 5.2.3、权限控制语言

权限控制通常涉及以下几个关键概念：

- **用户**：指在数据库中拥有账户的个体或实体。
- **角色**：一组权限的集合，可以被分配给一个或多个用户。
- **权限**：允许用户执行特定操作的许可，如SELECT（选择）、INSERT（插入）、UPDATE（更新）、DELETE（删除）等。
- **对象**：数据库中的实体，如表、视图、存储过程等。

1. 授予权限

   ```sql
   GRANT SELECT, INSERT ON ObjectName TO UserOrRoleName;
   ```

2. 撤销权限

   ```sql
   REVOKE SELECT ON ObjectName FROM UserOrRoleName;
   ```

3. 查看权限

   使用 `EXEC sp_helprotect` 或 `EXEC sp_helprolemember` 等系统存储过程来查看对象的权限信息或用户或角色的权限信息。

# 6、数据库编程

## 6.1、常用语言元素

### 6.1.1、变量

- 声明

  ```sql
  DECLARE @局部变量名|@@全局变量 数据类型[ = 初值];
  DECLARE @id INT;
  ```

- 赋值和查询

  ```sql
  -- 赋值
  -- 单值
  SET @id = 1;
  -- 多值
  SELECT @name = name FROM students WHERE id = @id;
  ```

- 输出

  ```sql
  PRINT @id; -- 打印输出
  ```

### 6.1.2、流程控制语句

- 语句块

  ```sql
  BEGIN
  	语句
  	...
  END;
  ```

- IF…ELSE条件语句

  ```sql
  IF 条件表达式
  	语句
  ELSE
  	语句
  ```

- WHILE循环语句

  ```sql
  WHILE 条件表达式
  	语句 | BREAK | CONTINUE
  ```

- CASE多条件函数

  ```sql
  CASE 输入表达式
  	WHEN 比较表达式 THEN 结果	
  	WHEN 比较表达式 THEN 结果
  	WHEN 比较表达式 THEN 结果	
  	ElSE
  		语句
  	END
  ```

- WAITFOR语句

  ```sql
  WAITFOR {DELAY | TIME}
  ```

- GOTO语句

  ```sql
  标签名称
  	语句组
  GOTO 标签名称
  ```

- RETURN语句

  ```sql
  RETURN [<整数表达式>]
  ```

- 注释语句

  ```sql
  /*多行注释*/
  -- 单行注释
  ```

## 6.2、创建和执行存储过程

### 6.2.1、创建

```sql
CREATE PROCEDURE MyProcedure
AS
BEGIN
    -- 这里放置您的SQL语句
    SELECT * FROM MyTable WHERE MyColumn = 'SomeValue';
END
GO
```

```sql
-- 创建带有参数的存储过程
CREATE PROCEDURE MyProcedureWithParam @MyParam INT
AS
BEGIN
    -- 这里放置您的SQL语句，使用@MyParam作为参数
    SELECT * FROM MyTable WHERE MyColumn = @MyParam;
END
GO
```



### 6.2.2、执行

```sql
EXEC MyProcedure;
-- 或者
EXECUTE MyProcedure;
```

```sql
-- 执行带有参数的存储过程
EXEC MyProcedureWithParam @MyParam = 10;
-- 或者
EXECUTE MyProcedureWithParam 10;
```

## 6.3、触发器

### 6.3.1、DML触发器

DML 触发器是在执行 DML 操作（如 INSERT、UPDATE 或 DELETE）时自动触发的存储过程。它们用于在数据更改之前或之后执行某些操作，例如记录更改、强制业务规则或同步多个表之间的数据。

```sql
-- 创建 DML 触发器的示例
CREATE TRIGGER trg_AfterInsert ON YourTable
AFTER INSERT
AS
BEGIN
    -- 在这里编写触发器逻辑
    -- 例如，记录插入的数据到另一个表
    INSERT INTO AuditLogTable (ColumnName, OperationType)
    SELECT ColumnName, 'INSERT' FROM inserted
END;
```

### 6.3.2、DDL触发器

DDL 触发器与 DML 触发器类似，但它们响应的是 DDL 事件，如 CREATE、ALTER 或 DROP 语句。DDL 触发器通常用于审计数据库架构的变化，或者防止对某些对象的修改或删除。

```sql
-- 创建 DDL 触发器的示例
CREATE TRIGGER trg_PreventTableDrop ON DATABASE
FOR DROP_TABLE
AS
BEGIN
    -- 阻止 DROP TABLE 操作
    RAISERROR ('不允许删除表。', 16, 1)
    ROLLBACK
END;
```

## 6.4、函数

```sql
-- 创建函数
CREATE FUNCTION [schema_name].function_name ([{ @parameter_name [ AS ] [ type_schema_name. ] parameter_data_type [ NULL ] [ = default ] [ READONLY ] } [ , ...n ] ])
RETURNS return_data_type [ WITH <function_option> [ , ...n ] ]
[ AS ]
BEGIN
    function_body
    RETURN scalar_expression
END;
```



### 6.4.1、标量函数

标量函数返回单个确定类型的值。它们可以接受零个或多个参数，并执行计算后返回结果。标量函数通常用于执行简单的数学运算、字符串处理或日期时间处理等操作。

```sql
-- 标量函数示例，它计算两个数的平均值
CREATE FUNCTION dbo.AverageNumbers (@num1 INT, @num2 INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @result FLOAT
    SET @result = (@num1 + @num2) / 2.0
    RETURN @result
END
```

```sql
-- 使用这个函数的SQL语句如下
SELECT dbo.AverageNumbers(10, 20) AS AverageResult
```



### 6.4.2、表值函数

表值函数则返回一个表格数据结构，即一组行和列。它们可以接受参数，并根据这些参数执行查询或计算，最终返回一个结果集。表值函数通常用于处理复杂的数据检索或聚合操作。

```sql
-- 表值函数，以下是一个简单的例子，它返回一个部门的所有员工信息
CREATE FUNCTION dbo.GetEmployeesByDepartment (@deptID INT)
RETURNS TABLE
AS
RETURN (
    SELECT EmployeeID, Name, BirthDate
    FROM Employees
    WHERE DepartmentID = @deptID
)
```

```sql
-- 使用这个函数的SQL语句如下
SELECT * FROM dbo.GetEmployeesByDepartment(1)
```

## 6.5、游标

游标是一种数据访问机制，它允许您在SQL Server数据库中逐行访问结果集。游标的实质是一种能从包括多条数据记录的结果集中每次取出一条记录的缓冲区。游标通常用于处理那些不适合用单一SQL语句一次性处理完毕的复杂逻辑。

在T-SQL中，使用游标通常涉及以下几个步骤：

1. **声明游标**：使用`DECLARE CURSOR`语句定义游标，并指定其属性，如方向（正向、反向、静态等）、类型（只读、可更新等）以及作用域（局部或全局）。
2. **打开游标**：使用`OPEN`语句激活游标，此时游标会准备好开始遍历结果集。
3. **获取数据**：使用`FETCH`语句从游标中检索数据。可以选择`FETCH NEXT`来获取下一条记录，或者使用其他选项如`FIRST`、`LAST`、`PRIOR`等来获取特定位置的记录。
4. **处理数据**：在`FETCH`之后，可以执行各种SQL语句来处理检索到的数据，例如更新、插入或删除操作。
5. **关闭游标**：使用`CLOSE`语句关闭游标，释放与之相关的资源。
6. **释放游标**：使用`DEALLOCATE`语句彻底移除游标，释放内存空间。

```sql
/*
我们首先声明了一个名为myCursor的游标，它用于检索employees表中属于Sales部门的员工记录。然后我们打开游标，并使用FETCH NEXT来获取第一条记录。接着，我们进入一个WHILE循环，直到@@FETCH_STATUS指示没有更多的记录可以获取为止。在循环体内，我们打印出每条记录的ID和姓名，并可以根据需要添加其他业务逻辑。最后，我们关闭并释放游标。
*/
DECLARE myCursor CURSOR FOR
    SELECT id, name FROM employees
    WHERE department = 'Sales';

OPEN myCursor;

FETCH NEXT FROM myCursor INTO @id, @name;

WHILE @@FETCH_STATUS = 0
BEGIN
    -- 处理每条记录
    PRINT 'ID: ' + CAST(@id AS VARCHAR) + ', Name: ' + @name;
    
    -- 可以在这里添加更多的业务逻辑

    FETCH NEXT FROM myCursor INTO @id, @name;
END;

CLOSE myCursor;
DEALLOCATE myCursor;
```

# 7、安全性和完整性控制

## 7.1、安全性控制

- **数据库安全性控制的方法**

1. 用户标识和鉴别
2. 存取控制
3. 视图
4. 跟踪审计
5. 数据加密

```sql
-- 创建新的服务器角色
CREATE SERVER ROLE server_role_name;
-- 向服务器角色添加成员
EXEC sp_addrolerolemember 'server_role_name', 'member_name';
-- 授予权限
GRANT permission_name TO server_role_name;
-- 撤销权限
REVOKE permission_name FROM server_role_name;
```

## 7.2、完整性控制

完整性控制类型，主要包括以下几种：

1. **实体完整性**：确保每个表中的记录都是唯一的，通常通过主键约束来实现。
2. **域完整性**：限制列中可以接受的值的类型和范围，例如通过NOT NULL约束、CHECK约束、数据类型约束等。
3. **参照完整性**：维护表之间的关系，确保一个表中的外键值在另一个表中有对应的主键值。
4. **用户定义完整性**：允许用户根据特定业务规则定义额外的约束条件。

实现完整性控制的方法包括：

1. **约束**：在表定义中直接指定约束条件，如PRIMARY KEY、FOREIGN KEY、UNIQUE、CHECK等。
2. **规则**：定义一组规则，然后将这些规则应用到表或列上。
3. **默认值**：为列指定默认值，当插入新记录时，如果未指定该列的值，则会使用默认值。
4. **存储过程**：编写存储过程来执行复杂的数据校验逻辑。
5. **触发器**：创建触发器来自动执行某些操作，如在插入、更新或删除数据时检查完整性约束。

# 8、事务管理与并发控制

## 8.1、事务（transaction）控制

数据库事务管理是数据库管理系统中的一个重要组成部分，它确保了数据库操作的一致性和可靠性。事务是一系列对数据库的操作，这些操作要么全部执行，要么全部不执行，是一个不可分割的工作单位。事务管理必须满足以下四个基本特性，通常称为ACID属性：

- **原子性（Atomicity）**：事务中的所有操作要么全部完成，要么全部不执行，不允许部分执行。
- **一致性（Consistency）**：事务必须使数据库从一个一致的状态转变到另一个一致的状态。
- **隔离性（Isolation）**：并发执行的事务之间互不干扰，每个事务都认为自己独占了数据库资源。
- **持久性（Durability）**：一旦事务提交，其对数据库的更改就永久保存在数据库中，即使系统发生故障也不会丢失。

```sql
-- 开始一个新的事务。在事务开始后，所有后续的数据库操作都会包含在这个事务中，直到事务被提交或回滚。
BEGIN TRANSACTION;
-- 这里放置一系列的数据库操作
COMMIT TRANSACTION;

-- 提交当前事务，使所有自事务开始以来所做的更改永久保存到数据库中。
BEGIN TRANSACTION;
-- 这里放置一系列的数据库操作
COMMIT TRANSACTION;

-- 撤销当前事务中所做的所有更改，将数据库恢复到事务开始前的状态。
BEGIN TRANSACTION;
-- 这里放置一系列的数据库操作
ROLLBACK TRANSACTION;

-- 创建一个保存点，允许在事务中回滚到该特定点，而不是整个事务。
BEGIN TRANSACTION;
-- 这里放置一系列的数据库操作
SAVE TRANSACTION savepoint_name;
-- 如果发生错误，可以回滚到savepoint_name
ROLLBACK TRANSACTION savepoint_name;

-- 回滚到指定的保存点，撤销该保存点之后的所有更改。
BEGIN TRANSACTION;
-- 这里放置一系列的数据库操作
SAVE TRANSACTION savepoint_name;
-- 如果发生错误，可以回滚到savepoint_name
ROLLBACK TRANSACTION TO savepoint_name;
```

## 8.2、并发控制

并发控制是指在多个用户或进程同时访问和修改数据库时，确保数据的一致性和完整性。并发操作可能导致以下几种问题：

1. **丢失修改**：当两个事务尝试修改同一个数据项时，如果第一个事务尚未提交，第二个事务的修改可能会覆盖第一个事务的修改，导致第一个事务的修改丢失。
2. **不可重复读**：如果一个事务在读取数据后，另一个事务修改了这些数据，然后第一个事务再次读取这些数据，它可能会得到不同的结果，因为数据已经被改变。
3. **读脏数据**：如果一个事务读取了正在被另一个事务修改的数据，然后第一个事务提交或回滚，导致读取的数据变得无效，这种现象称为读脏数据。

### 8.2.1、锁

***锁类型***

1. **共享锁 (S)**：允许多个事务同时读取同一资源，但不允许修改。一旦所有读取操作完成，共享锁会被释放。
2. **排他锁 (X)**：只允许一个事务对数据进行读写操作，阻止其他事务同时访问。
3. **更新锁 (U)**：在修改操作的初始阶段用来锁定可能要被修改的资源，避免死锁。更新锁在数据修改时升级为排他锁。
4. **意向锁**：包括意向共享锁 (IS)、意向排他锁 (IX)、意向更新锁 (IU) 等，它们表示事务对资源的意图，以便其他事务了解当前事务的锁定状态。
5. **键范围锁**：在使用可序列化事务隔离级别时，锁定整个键范围，防止其他事务插入或更新该范围内的数据。

# 完！

有异议、意见等等欢迎评论区留言反馈！