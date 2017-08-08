RUNNING
====================================
# running the application
- you need to buid and run the application using:
```
gradle bootRun
```
- and from browser open (by default):
```
http://localhost:8080
```

- this application provides a service which can be accessed at:

```
http://localhost:8080/api/<TODO>
  ```

PACKAGING
===========================================================

# as a jar
```
gradle build -x test # skip tests temporary because context-camel injection problem
java -jar build/libs/atm-0.0.1-SNAPSHOT.jar
```
# as a war
```
gradle war
# resulting war at: build/libs/atm-0.0.1-SNAPSHOT.war
```






PRERREQUISES
=====================================

# install git

# install jdk

# install gradle
* download from: https://gradle.org/install/
* version 4.0.2
* instructions
```
mkdir -p ~/.tools
cd ~/.tools
wget https://services.gradle.org/distributions/gradle-4.0.2-bin.zip
unzip gradle-4.0.2-bin.zip -d ~/.work

# modify env vars (sudo vi /etc/profile.d/myenvvars.sh)
export GRADLE_HOME=/home/vagrant/.work/gradle-4.0.2
export PATH=$GRADLE_HOME/bin:$PATH:
```

# install intellij-idea
```
* download from: https://www.jetbrains.com/idea/
* version: 2107.2.1 ~ 419Mb

mkdir -p ~/.tools
cd ~/.tools
wget <file_url>
sudo tar -xvf ideaIC-2017.2.1.tar.gz -C /opt/
sudo rm /usr/bin/idea
sudo ln -s /opt/idea-IC-172.3544.35/bin/idea.sh /usr/bin/idea


```

SCAFFOLD / BACKEND
=========================

# use spring initializr

# run idea
nohup /usr/bin/idea &

# open project

# configure project sdk
 * jdk: /usr/lib/jvm/java-1.8.0-openjdk


SCAFFOLD / FRONTEND
=========================


# use angular-cli
sudo npm install -g @angular/cli
ng new atm-ui
cd atm-ui





REFERENCES
=========================================
* [rest with camel & spring boot](https://medium.com/@mzimecki/spring-boot-apache-camel-web-service-a90696d8ac36)
* [consuming rest](https://spring.io/guides/gs/consuming-rest/)
* [via cxfrs](https://cwiki.apache.org/confluence/display/CAMEL/CXFRS)
* [stardust old](https://wiki.eclipse.org/Stardust/Knowledge_Base/Integration/Camel/Calling_RESTful_Service_Through_Camel_Route)
* [camel cxf client](http://www.rubix.nl/blogs/implementing-cxfrs-client-jboss-fuse)
* [raw with http client](https://github.com/apache/camel/blob/master/components/camel-weather/src/main/java/org/apache/camel/component/weather/WeatherProducer.java)
