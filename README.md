#Warning: this service is in beta and it's still not customizable, but it will be improved in future. If you want to help me don't hesistate to ping me

#Overview

A Web Service that allows to create a newsletter service for free. You can customize the service and deploy the war file with Amazon Elasticbeanstalk and be ready to start. Newsletters should be pdf files uploaded by the admin that will be sent as email attachments to all the subscribers automatically in a period of time that is customizable. Data about users email and passwords are saved as xml files so the service doesn't require an external db. Users can sign up and sign in, passwords are stored in a secure way. There is also a paypal or credit card payment, if you want to monetize your newsletter.

#Urgent developments

passwords, emails, paypal ids shouldn't be hard coded but they should be put in a configuration file
