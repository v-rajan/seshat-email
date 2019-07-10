The solution was 
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
