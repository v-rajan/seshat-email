The solution was was build on Spring Boot with Apache camel as thepintegration Platform. 

[Spring Boot](https://spring.io/projects/spring-boot)

[EclipseLink MOXy](https://www.mailgun.com)

Although Camel has a rich set of components to manage Rest API, JDK 11 HTTP client was used here.

```console
com.chnoumis.seshat.email.routes.client.HttpClientService
```
[Java 11 HTTP Client](https://openjdk.java.net/groups/net/httpclient/intro.html)

A common API was has been created in seshat-email-jaxb using JAXB that will be used by EclipseLink MOXy to support JSON and XML incoming payload to be shared my internal services.

[EclipseLink MOXy](https://www.mailgun.com)


# Prerequites
* JDK 11
* Maven
* API key from [Mailgun](https://www.mailgun.com)
* API key from [SendGrid](https://sendgrid.com/)

# Setup
### Build
```console
cd seshat-email
mvn clean install
```
### Start Server

```console
Edit seshat-email/seshat-email/src/main/resources/config/com.chnoumis.seshat.email.properties

sendgrid.url=https://api.sendgrid.com/v3/mail/send
sendgrid.apikey=[API]

mailgun.url=https://api.mailgun.net/v3/[$DOMAIN]/messages
mailgun.apikey=[API]

service.url=http://0.0.0.0:9191/sews001/json
```
```console
cd seshat-email/seshat-email
mvn spring-boot:run

http://127.0.0.1:9191/sews001/json
```

# Testing
## API test
Karate is used to test the deployed REST APIs.

[Karate](https://github.com/intuit/karate)

### Run test
```console
cd ..
cd seshat-email-karate
mvn clean install

cd target/cucumber-html-reports
```

### View test report
```console
cd target/cucumber-html-reports

open overview-features.html
```

# Docker
[seshat-email-springboot](https://cloud.docker.com/u/chnoumis/repository/docker/chnoumis/seshat-email-springboot)
## Start Server
```console
cd ..
docker pull chnoumis/seshat-email-springboot:latest

docker run  -t \
	--name seshat-email-springboot \
	-p 9191:9191 \
	-e SERVICE_URL=http://0.0.0.0:9191/sews001/json \
   	-e SENDGRID_URL=https://api.sendgrid.com/v3/mail/send \
	-e SENDGRID_API_KEY=[API-KEY] \
   	-e MAILGUN_URL=https://api.mailgun.net/v3/[DOMAIN]/messages \
	-e MAILGUN_API_KEY=[API-KEY]\
	-e JAVA_APP_DIR=/maven \
	chnoumis/seshat-email-springboot:latest
```
## API test
```console
cd ..
cd seshat-email-karate

Edit seshat-email-karate/src/test/java/com/chnoumis/seshat/karate/email/email.feature

Replace 
Given url 'http://127.0.0.1:9191/'
With
Given url 'http://192.168.0.5:9191/'
to enable API tester to access the docker containers

mvn clean install

cd target/cucumber-html-reports

open overview-features.html
```