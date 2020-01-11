**基于Springboot1.5.9+SpringCloud+Zipkin+ELK链路跟踪实现**

1. 思想实现：<br>
   ``基于zipkin和feign结合，生成span数据，写到本地文件，然后通过logstash发送到es，通过zipkin-ui或Kibana来展示``
  
2. 工程包目录介绍：<br>
   > zipkin-common 公共组件包，主要实现logback过滤对中文的转义以及本demo重点核心：扩展Zipkin的span发送器<br>
   > zipkin-eureka 注册中心<br>
   > zipkin-provider-a/b 服务提供<br>
   > zipkin-consumer 服务消费<br>
   > zipkin-server zipkin链路跟踪UI展示<br>
3. 扩展Zipkin的span发送器说明：<br>
   >3.1 翻Spring-cloud-sleuth-zipkin源码，知道其工作主要线路：<br>
   > resources/META-INF/spring.factoires/[ZipkinAutoConfiguration] -->
   > [ZipkinSpanReporter] -> [HttpZipkinSpanReporter] -> 
   > [RestTemplateSender]--> [AsyncReporter] -> [Sender.sendSpans]<br>
   >3.2 至于如何封装成Span的不在本文描述范围<br>
   >3.3 基于以上我们扩展一个Sender，实现sendSpans方法的实现，改为slf4j日志打印即可将feign产生的Span信息保存到本地文件<br>
   >3.4 详情请参考[com.xiao.zipkin.common.zipkin](https://github.com/Xlinlin/spingcloud-zipkin-elk-demo/tree/master/zipkin-common/src/main/java/com/xiao/zipkin/common)包的内容实现<br>
   >3.5 [logback-spring.xml](https://github.com/Xlinlin/spingcloud-zipkin-elk-demo/blob/master/zipkin-provider-b/src/main/resources/logback-spring.xml)参考
4. logstash 配置(待补充)：<br>
   ELK环境搭建请自行查阅资料安装<br>
   ```$xslt
    
   ```                    
   
5. 更多参考资料：<br>
  [构建sleuth+zipkin全链路监控系统](https://blog.csdn.net/u012394095/category_9279645.html)<br>
  [sleuth+zipkin+kafka+logstash链路追踪二次开发方案](https://blog.csdn.net/u012394095/article/details/94389644)<br>
  [分布式链路追踪技术对比](https://blog.csdn.net/u012394095/article/details/79700200)<br>
  
6. [Github地址](https://github.com/Xlinlin/spingcloud-zipkin-elk-demo)