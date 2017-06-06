# Steps to reproduce the bug

## Start standalone infinispan

Copy the standalone memcached configuration (`docs/examples/configs/standalone-memcached.xml`) to standalone/configuration first, then run...

`JAVA_OPTS="-server -Xms4g -Xmx4g -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.modules.metrics=true" bin/standalone.sh -c standalone-memcached.xml`

Output:

```
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /opt/infinispan-server-9.0.1.Final

  JAVA: java

  JAVA_OPTS:   -server -Xms4g -Xmx4g -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.modules.metrics=true

=========================================================================

14:37:03,215 INFO  [org.jboss.modules] (main) JBoss Modules version 1.5.2.Final
14:37:03,339 INFO  [org.jboss.msc] (main) JBoss MSC version 1.2.6.Final
14:37:03,395 INFO  [org.jboss.as] (MSC service thread 1-7) WFLYSRV0049: Infinispan Server 9.0.1.Final (WildFly Core 2.2.0.Final) starting
14:37:04,044 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0039: Creating http management service using socket-binding (management-http)
14:37:04,064 INFO  [org.xnio] (MSC service thread 1-8) XNIO version 3.4.0.Final
14:37:04,073 INFO  [org.xnio.nio] (MSC service thread 1-8) XNIO NIO Implementation Version 3.4.0.Final
14:37:04,085 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 19) Activating Infinispan subsystem.
14:37:04,094 INFO  [org.jboss.as.naming] (ServerService Thread Pool -- 25) WFLYNAM0001: Activating Naming Subsystem
14:37:04,098 INFO  [org.wildfly.extension.io] (ServerService Thread Pool -- 21) WFLYIO001: Worker 'default' has auto-configured to 16 core threads with 128 task threads based on your 8 available processors
14:37:04,102 WARN  [org.jboss.as.txn] (ServerService Thread Pool -- 29) WFLYTX0013: Node identifier property is set to the default value. Please make sure it is unique.
14:37:04,104 INFO  [org.jboss.as.security] (ServerService Thread Pool -- 27) WFLYSEC0002: Activating Security Subsystem
14:37:04,107 INFO  [org.jboss.as.security] (MSC service thread 1-1) WFLYSEC0001: Current PicketBox version=4.9.6.Final
14:37:04,114 INFO  [org.jboss.remoting] (MSC service thread 1-8) JBoss Remoting version 4.0.21.Final
14:37:04,119 INFO  [org.jboss.as.connector.subsystems.datasources] (ServerService Thread Pool -- 18) WFLYJCA0004: Deploying JDBC-compliant driver class org.h2.Driver (version 1.3)
14:37:04,126 INFO  [org.jboss.as.naming] (MSC service thread 1-5) WFLYNAM0003: Starting Naming Service
14:37:04,127 INFO  [org.jboss.as.connector] (MSC service thread 1-6) WFLYJCA0009: Starting JCA Subsystem (WildFly/IronJacamar 1.3.4.Final)
14:37:04,133 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-3) WFLYJCA0018: Started Driver service with driver-name = h2
14:37:04,360 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-4) WFLYJCA0001: Bound data source [java:jboss/datasources/ExampleDS]
14:37:04,365 INFO  [org.jboss.as.server.deployment.scanner] (MSC service thread 1-3) WFLYDS0013: Started FileSystemDeploymentService for directory /opt/infinispan-server-9.0.1.Final/standalone/deployments
14:37:04,657 INFO  [org.infinispan.factories.GlobalComponentRegistry] (MSC service thread 1-2) ISPN000128: Infinispan version: Infinispan 'Ruppaner' 9.0.1.Final
14:37:05,003 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-2) DGISPN0001: Started namedCache cache from local container
14:37:05,003 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-5) DGISPN0001: Started default cache from local container
14:37:05,030 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-1) DGENDPT10000: MemcachedServer starting
14:37:05,030 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-1) DGENDPT10001: MemcachedServer listening on 127.0.0.1:11211
14:37:05,163 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
14:37:05,164 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
14:37:05,164 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: Infinispan Server 9.0.1.Final (WildFly Core 2.2.0.Final) started in 2167ms - Started 148 of 160 services (47 services are lazy, passive or on-demand)
```

## Run program

`mvn clean install exec:java`

## Exception

```
14:38:09,096 ERROR [org.infinispan.server.memcached.MemcachedDecoder] (Memcached-ServerWorker-3-1) ISPN005003: Exception reported: org.infinispan.server.memcached.UnknownOperationException: Unknown operation:
        at org.infinispan.server.memcached.MemcachedDecoder.toRequest(MemcachedDecoder.java:1053)
        at org.infinispan.server.memcached.MemcachedDecoder.readHeader(MemcachedDecoder.java:316)
        at org.infinispan.server.memcached.MemcachedDecoder.decodeHeader(MemcachedDecoder.java:196)
        at org.infinispan.server.memcached.MemcachedDecoder.decodeDispatch(MemcachedDecoder.java:174)
        at org.infinispan.server.memcached.MemcachedDecoder.decode(MemcachedDecoder.java:127)
        at io.netty.handler.codec.ReplayingDecoder.callDecode(ReplayingDecoder.java:367)
        at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:248)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:340)
        at io.netty.channel.ChannelInboundHandlerAdapter.channelRead(ChannelInboundHandlerAdapter.java:86)
        at org.infinispan.server.core.transport.StatsChannelHandler.channelRead(StatsChannelHandler.java:26)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:340)
        at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1334)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
        at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:926)
        at io.netty.channel.epoll.AbstractEpollStreamChannel$EpollStreamUnsafe.epollInReady(AbstractEpollStreamChannel.java:1017)
        at io.netty.channel.epoll.EpollEventLoop.processReady(EpollEventLoop.java:394)
        at io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:299)
        at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:858)
        at io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:144)
        at java.lang.Thread.run(Thread.java:745)
```

## PHP

The PHP version causes the same effect:

```
stats
STAT pid 0
STAT uptime 13
STAT uptime 13
STAT time 1475832256
STAT version 8.2.4.Final
STAT pointer_size 0
STAT rusage_user 0
STAT rusage_system 0
STAT curr_items 4960
STAT total_items 4987
STAT bytes 0
STAT curr_connections 0
STAT total_connections 0
STAT connection_structures 0
STAT cmd_get 0
STAT cmd_set 4987
STAT get_hits 0
STAT get_misses 0
STAT delete_misses 0
STAT delete_hits 0
STAT incr_misses 0
STAT incr_hits 0
STAT decr_misses 0
STAT decr_hits 0
STAT cas_misses 0
STAT cas_hits 0
STAT cas_badval 0
STAT auth_cmds 0
STAT auth_errors 0
STAT evictions 0
STAT bytes_read 442481
STAT bytes_written 40085
STAT limit_maxbytes 0
STAT threads 0
STAT conn_yields 0
STAT reclaimed 0
END
```

Of the 4987 keys, only 4960 found their way in the cache

## sysdig

Running sysdig (`sudo sysdig -s 2000 -A -c echo_fds fd.port=11211`) shows that java tries to read 2B at a time it's not supposed to:

```
[[34m------ Write 130B to  [[34m 127.0.0.1:59062->127.0.0.1:11211 (php)

set MY_product_maybelline-maybelline-color-show-lips-[#203-cherry-on-top]-pink-623871.html_en 0 0 26
test value 1475833492.6105

[[31m------ Read 128B from  [[31m 127.0.0.1:59062->127.0.0.1:11211 (java)

set MY_product_maybelline-maybelline-color-show-lips-[#203-cherry-on-top]-pink-623871.html_en 0 0 26
test value 1475833492.6105
[[34m------ Write 8B to  [[34m 127.0.0.1:59062->127.0.0.1:11211 (java)

STORED

[[31m------ Read 8B from  [[31m 127.0.0.1:59062->127.0.0.1:11211 (php)

STORED

[[31m------ Read 2B from  [[31m 127.0.0.1:59062->127.0.0.1:11211 (java)



[[34m------ Write 128B to  [[34m 127.0.0.1:59062->127.0.0.1:11211 (php)

set MY_product_cotton-silk-luffy-luffy-selendang-damia-wide-shawl-red-orange-765145.html_en 0 0 26
test value 1475833492.6105

[[34m------ Write 7B to  [[34m 127.0.0.1:59062->127.0.0.1:11211 (java)

ERROR

[[31m------ Read 7B from  [[31m 127.0.0.1:59062->127.0.0.1:11211 (php)

ERROR
```
