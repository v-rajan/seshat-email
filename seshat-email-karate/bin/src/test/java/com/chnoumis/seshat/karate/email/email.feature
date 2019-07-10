Feature: email test

        Background:

        Scenario: sendgrid email
                Given url 'http://127.0.0.1:9191/sews001/json'
                And header Content-Type = 'application/json'
                And request read('email-1.json')
                When method post
                Then status 200
                And match response.Response contains { respCode: 'SEWS-000-000' }

        Scenario: mailgun email
                Given url 'http://127.0.0.1:9191/sews001/json'
                And header Content-Type = 'application/json'
                And request read('email-2.json')
                When method post
                Then status 200
                And match response.Response contains { respCode: 'SEWS-000-000' }

        # Mailgun allows duplicate email while SendGrid does not. Use a request with duplicated email to test failover from sendgrid to mailgun.
        Scenario: sendgrid mailgun email
                Given url 'http://127.0.0.1:9191/sews001/json'
                And header Content-Type = 'application/json'
                And request read('email-3.json')
                When method post
                Then status 200
                And match response.Response contains { respCode: 'SEWS-000-000' }
