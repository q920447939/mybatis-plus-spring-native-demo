

# SpringBoot3+MybatisPlus 在CentOS7打包native镜像

前提条件

- 操作需要网络连接
- 操作系统内存和CPU尽量大一点，不低于4核4G





```shell
[root@localhost ~]# uname -a
Linux localhost.localdomain 3.10.0-693.el7.x86_64 #1 SMP Tue Aug 22 21:09:27 UTC 2017 x86_64 x86_64 x86_64 GNU/Linux
```

1. 设置代理(有网络直接跳转到第2步)

   1. 增加系统代理 
      1. `vi /etc/profile`
      ```shell
      export http_proxy='http://192.168.230.1:808'
      export https_proxy='http://192.168.230.1:808'
      ```
      2. 更新一下系统缓存
         1. `source /etc/profile`
   2. `yum` 设置代理
      
      1. `vi etc/yum.conf `
      2. 在最后添加代理信息 `proxy=http://192.168.230.1:808`

2. 安装依赖 

   1. 安装`GCC`依赖

   ```shell
   sudo yum install gcc glibc-devel zlib-devel
   ```
   2. 更新`GCC`依赖
   ```shell
   yum install centos-release-scl -y
   yum install devtoolset-7 -y
   scl enable devtoolset-7 bash
   ```
3. 安装graalvm

   1. 下载graalvm
   2. 上传到服务器
   3. 解压 `tar -zxvf graalvm-jdk-17_linux-x64_bin.tar.gz`
   4. 移动  `mv ./graalvm-jdk-17.0.8+9.1 /usr/local/`
   5. 修改系统变量 `vi /etc/profile`
   	```shell
   	export JAVA_HOME=/usr/local/graalvm-jdk-17.0.8+9.1
   	export PATH=/usr/local/graalvm-jdk-17.0.8+9.1/bin:$PATH
   	```
   6. 测试安装结果（正确结果如下）
      ```shell
      [root@localhost apache-maven-3.9.4]# java -version
   java version "17.0.8" 2023-07-18 LTS
   Java(TM) SE Runtime Environment Oracle GraalVM 17.0.8+9.1 (build 17.0.8+9-LTS-jvmci-23.0-b14)
   Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 17.0.8+9.1 (build 17.0.8+9-LTS-jvmci-23.0-b14, mixed mode, sharing)
      ```

4. 安装`maven`

   1. 下载`maven`并上传到服务器（`maven`版本`apache-maven-3.9.4-bin.tar.gz `）

   2. 解压 `tar -zxvf  apache-maven-3.9.4-bin.tar.gz`

   3. 移动 `mv ./apache-maven-3.9.4 /usr/local/ `

   4. 配置环境变量

      1. `vi /etc/profile`

      ```shell
      MAVEN_HOME=/usr/local/apache-maven-3.9.4
      export PATH=${MAVEN_HOME}/bin:${PATH}
      ```
      2. 使生效 `source /etc/profile`
      
   5. 测试安装结果（正确结果如下）

      ```shell
      [root@localhost apache-maven-3.9.4]# mvn -version
      Apache Maven 3.9.4 (dfbb324ad4a7c8fb0bf182e6d91b0ae20e3d2dd9)
      Maven home: /usr/local/apache-maven-3.9.4
      Java version: 17.0.8, vendor: Oracle Corporation, runtime: /usr/local/graalvm-jdk-17.0.8+9.1
      Default locale: en_US, platform encoding: UTF-8
      OS name: "linux", version: "3.10.0-693.el7.x86_64", arch: "amd64", family: "unix"
      ```

   6. maven 设置代理（按需）

      1. 编辑`setting.xml`文件，在`proxy`节点加入代理信息 。`vi  /usr/local/apache-maven-3.9.4/conf/settings.xml `

         ```xml
           <proxies>
             <proxy>
               <id>example-proxy</id>
               <active>true</active>
               <protocol>http</protocol>
               <host>192.168.230.1</host>
               <port>808</port>
               <nonProxyHosts>*local*|*127*</nonProxyHosts>
             </proxy>
           </proxies>
         ```

         

   7. 下载测试项目，地址：https://github.com/q920447939/mybatis-plus-spring-native-demo`

   8. 使用

      1. 运行数据库脚本

         ```sql
         执行 [structure.sql](sql/structure.sql)
         
         执行 [init_data.sql](sql/init_data.sql)
         
         ```
         
      2. 修改`spring.datasource`中的配置为自己创建的数据库

         ```yaml
         spring:
          datasource:
             url: jdbc:mysql://192.168.230.1:3306/mybatis-demo?serverTimezone=Asia/Shanghai  #修改ip
             hikari:
               username: root #修改用户名
               password: iiTX0ByCkZkrvRok #修改密码
         ```

         

      3. 上传`mybatis-plus-spring-native-demo`到`CentOS7服务器`

      4. `maven`命令打包(`-x` 加了可以看到调试信息，第一次打包尝试建议打开，方便排查问题)

         ```shell
         mvn -Pnative native:compile -DskipTests -X
         ```

		打包正常结束如下：

          ```shell
          #省略前面的部分日志
          [2/8] Performing analysis...  [*******]                                                                 (80.2s @ 3.89GB)
            21,453 (91.56%) of 23,430 types reachable
            35,341 (66.10%) of 53,462 fields reachable
           118,313 (65.65%) of 180,207 methods reachable
             6,710 types,   551 fields, and 8,516 methods registered for reflection
                67 types,    74 fields, and    57 methods registered for JNI access
                 4 native libraries: dl, pthread, rt, z
          [3/8] Building universe...                                                                              (11.1s @ 2.80GB)
          [4/8] Parsing methods...      [*****]                                                                   (26.2s @ 3.58GB)
          [5/8] Inlining methods...     [***]                                                                      (3.2s @ 3.41GB)
          [6/8] Compiling methods...    [*************]                                                          (176.6s @ 2.49GB)
          [7/8] Layouting methods...    [***]                                                                      (8.5s @ 4.50GB)
          [8/8] Creating image...       [***]                                                                      (8.5s @ 2.86GB)
            68.11MB (62.20%) for code area:    68,191 compilation units
            40.73MB (37.20%) for image heap:  513,751 objects and 207 resources
           666.80kB ( 0.59%) for other data
           109.49MB in total
          ------------------------------------------------------------------------------------------------------------------------
          Top 10 origins of code area:                                Top 10 object types in image heap:
            17.93MB java.base                                           14.63MB byte[] for code metadata
             5.74MB svm.jar (Native Image)                               5.14MB byte[] for java.lang.String
             5.44MB tomcat-embed-core-10.1.8.jar                         3.98MB java.lang.Class
             5.12MB java.xml                                             3.84MB java.lang.String
             3.55MB mysql-connector-j-8.0.33.jar                         3.26MB byte[] for general heap data
             2.48MB jackson-databind-2.14.2.jar                          1.54MB byte[] for embedded resources
             2.32MB hibernate-validator-8.0.0.Final.jar                  1.48MB byte[] for reflection metadata
             2.31MB spring-core-6.0.8.jar                             1005.61kB com.oracle.svm.core.hub.DynamicHubCompanion
             2.14MB mybatis-3.5.13.jar                                 614.56kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
             1.85MB spring-boot-3.0.6.jar                              530.59kB java.lang.String[]
            18.74MB for 110 more packages                                4.48MB for 3995 more object types
          ------------------------------------------------------------------------------------------------------------------------
          Recommendations:
           G1GC: Use the G1 GC ('--gc=G1') for improved latency and throughput.
           PGO:  Use Profile-Guided Optimizations ('--pgo') for improved throughput.
           HEAP: Set max heap for improved and more predictable memory usage.
           CPU:  Enable more CPU features with '-march=native' for improved performance.
           QBM:  Use the quick build mode ('-Ob') to speed up builds during development.
          ------------------------------------------------------------------------------------------------------------------------
                                 27.1s (8.3% of total time) in 160 GCs | Peak RSS: 5.89GB | CPU load: 3.71
          ------------------------------------------------------------------------------------------------------------------------
          Produced artifacts:
           /home/springboot3/mybatis-plus-spring-native-demo/target/starter (executable)
          ========================================================================================================================
          Finished generating 'starter' in 5m 24s.
          [INFO] ------------------------------------------------------------------------
          [INFO] BUILD SUCCESS
          [INFO] ------------------------------------------------------------------------
          [INFO] Total time:  05:39 min
          [INFO] Finished at: 2023-09-18T13:27:30-04:00
          [INFO] ------------------------------------------------------------------------
          ```
        
          提示`[INFO] BUILD SUCCESS`并且在`target`目录下有生成`starter`二进制文件，则说明打包成功

      5.运行 
      
      ```shell
      ./target/starter
      ```
      
      如果能够正常启动（应该是毫秒级），并且通过`HTTP Get请求访问到`(`192.168.230.230:8500/mybatis-plus-spring-native-demo/api/user/findById?organizationId=100001&id=10000001`) 。则说明成功
      
      

