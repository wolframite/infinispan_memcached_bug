# Steps to reproduce the bug

## Start standalone infinispan

`JAVA_OPTS="-server -Xms4g -Xmx4g -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.modules.metrics=true" bin/standalone.sh -c standalone.xml`

Output:

```
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /opt/infinispan-server-8.2.4.Final

  JAVA: /usr/lib/jvm/java-8-openjdk-amd64/bin/java

  JAVA_OPTS:   -server -Xms4g -Xmx4g -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true -Djboss.modules.metrics=true -Djboss.modules.metrics=true

=========================================================================

17:04:27,585 INFO  [org.jboss.modules] (main) JBoss Modules version 1.5.1.Final
17:04:27,711 INFO  [org.jboss.msc] (main) JBoss MSC version 1.2.6.Final
17:04:27,764 INFO  [org.jboss.as] (MSC service thread 1-7) WFLYSRV0049: Infinispan Server 8.2.4.Final (WildFly Core 2.0.10.Final) starting
17:04:28,388 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0039: Creating http management service using socket-binding (management-http)
17:04:28,403 INFO  [org.xnio] (MSC service thread 1-5) XNIO version 3.3.4.Final
17:04:28,412 INFO  [org.xnio.nio] (MSC service thread 1-5) XNIO NIO Implementation Version 3.3.4.Final
17:04:28,438 INFO  [org.wildfly.extension.io] (ServerService Thread Pool -- 19) WFLYIO001: Worker 'default' has auto-configured to 16 core threads with 128 task threads based on your 8 available processors
17:04:28,442 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 20) Activating Infinispan subsystem.
17:04:28,451 WARN  [org.jboss.as.txn] (ServerService Thread Pool -- 29) WFLYTX0013: Node identifier property is set to the default value. Please make sure it is unique.
17:04:28,455 INFO  [org.jboss.as.security] (ServerService Thread Pool -- 27) WFLYSEC0002: Activating Security Subsystem
17:04:28,458 INFO  [org.jboss.remoting] (MSC service thread 1-6) JBoss Remoting version 4.0.18.Final
17:04:28,459 INFO  [org.jboss.as.naming] (ServerService Thread Pool -- 25) WFLYNAM0001: Activating Naming Subsystem
17:04:28,460 INFO  [org.jboss.as.security] (MSC service thread 1-1) WFLYSEC0001: Current PicketBox version=4.9.4.Final
17:04:28,461 INFO  [org.jboss.as.connector] (MSC service thread 1-2) WFLYJCA0009: Starting JCA Subsystem (WildFly/IronJacamar 1.3.2.Final)
17:04:28,484 INFO  [org.jboss.as.connector.subsystems.datasources] (ServerService Thread Pool -- 18) WFLYJCA0004: Deploying JDBC-compliant driver class org.h2.Driver (version 1.3)
17:04:28,492 INFO  [org.jboss.as.naming] (MSC service thread 1-6) WFLYNAM0003: Starting Naming Service
17:04:28,492 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-3) WFLYJCA0018: Started Driver service with driver-name = h2
17:04:28,772 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-5) WFLYJCA0001: Bound data source [java:jboss/datasources/ExampleDS]
17:04:28,785 INFO  [org.jboss.as.server.deployment.scanner] (MSC service thread 1-8) WFLYDS0013: Started FileSystemDeploymentService for directory /opt/infinispan-server-8.2.4.Final/standalone/deployments
17:04:29,014 INFO  [org.infinispan.factories.GlobalComponentRegistry] (MSC service thread 1-7) ISPN000128: Infinispan version: Infinispan 'Chakra' 8.2.4.Final
17:04:29,304 INFO  [org.infinispan.expiration.impl.ExpirationManagerImpl] (MSC service thread 1-1) ISPN000025: wakeUpInterval is <= 0, not starting expired purge thread
17:04:29,304 INFO  [org.infinispan.expiration.impl.ExpirationManagerImpl] (MSC service thread 1-8) ISPN000025: wakeUpInterval is <= 0, not starting expired purge thread
17:04:29,305 INFO  [org.infinispan.expiration.impl.ExpirationManagerImpl] (MSC service thread 1-2) ISPN000025: wakeUpInterval is <= 0, not starting expired purge thread
17:04:29,305 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-1) DGISPN0001: Started default cache from local container
17:04:29,305 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-2) DGISPN0001: Started namedCache cache from local container
17:04:29,305 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-8) DGISPN0001: Started memcachedCache cache from local container
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-6) DGENDPT10000: MemcachedServer starting
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-8) DGENDPT10000: WebSocketServer starting
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-6) DGENDPT10001: MemcachedServer listening on 0.0.0.0:11211
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-8) DGENDPT10001: WebSocketServer listening on 0.0.0.0:8181
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-4) DGENDPT10000: HotRodServer starting
17:04:29,306 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-3) DGENDPT10000: REST starting
17:04:29,307 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-4) DGENDPT10001: HotRodServer listening on 0.0.0.0:11222
17:04:29,349 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-3) DGISPN0001: Started ___protobuf_metadata cache from local container
17:04:29,356 INFO  [org.jboss.as.clustering.infinispan] (MSC service thread 1-3) DGISPN0001: Started ___script_cache cache from local container
17:04:29,687 INFO  [org.infinispan.rest.NettyRestServer] (MSC service thread 1-3) ISPN012003: REST server starting, listening on 0.0.0.0:7070
17:04:29,687 INFO  [org.infinispan.server.endpoint] (MSC service thread 1-3) DGENDPT10002: REST mapped to /rest
17:04:29,749 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9990/management
17:04:29,750 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9990
17:04:29,750 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: Infinispan Server 8.2.4.Final (WildFly Core 2.0.10.Final) started in 2376ms - Started 155 of 161 services (44 services are lazy, passive or on-demand)
```

## Run program

`mvn clean install exec:java`

## Exception

```
17:14:42,531 ERROR [org.infinispan.server.memcached.RequestResolver$] (MemcachedServerWorker-8-1) ISPN005003: Exception reported: org.infinispan.server.memcached.UnknownOperationException: Unknown operation: 
	at org.infinispan.server.memcached.RequestResolver$.toRequest(MemcachedDecoder.scala:952)
	at org.infinispan.server.memcached.MemcachedDecoder.readHeader(MemcachedDecoder.scala:243)
	at org.infinispan.server.memcached.MemcachedDecoder.decodeHeader(MemcachedDecoder.scala:144)
	at org.infinispan.server.memcached.MemcachedDecoder.decodeDispatch(MemcachedDecoder.scala:122)
	at org.infinispan.server.memcached.MemcachedDecoder.decode(MemcachedDecoder.scala:77)
	at io.netty.handler.codec.ReplayingDecoder.callDecode(ReplayingDecoder.java:376)
	at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:245)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:292)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:278)
	at io.netty.channel.ChannelInboundHandlerAdapter.channelRead(ChannelInboundHandlerAdapter.java:86)
	at org.infinispan.server.core.transport.StatsChannelHandler.channelRead(StatsChannelHandler.java:39)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:292)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:278)
	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:962)
	at io.netty.channel.epoll.AbstractEpollStreamChannel$EpollStreamUnsafe.epollInReady(AbstractEpollStreamChannel.java:879)
	at io.netty.channel.epoll.EpollEventLoop.processReady(EpollEventLoop.java:360)
	at io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:276)
	at io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:112)
	at io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:137)
	at java.lang.Thread.run(Thread.java:745)
```
