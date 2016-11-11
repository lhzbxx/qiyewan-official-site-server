FROM maven:alpine

ADD settings.xml /root/.m2/settings.xml

ADD pom.xml /tmp/build/
RUN cd /tmp/build

ADD src /tmp/build/src
        #构建应用
RUN cd /tmp/build && mvn -q -DskipTests=true package \
        #拷贝编译结果到指定目录
        && mv target/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["mvn","--settings c:\user\settings.xml"]