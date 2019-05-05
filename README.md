# toris-account-service
POC on spring cloud config client

Once we start the application, use the below endpoint for accessing the configs from spring cloud config server which was running on (http://localhost:8081).

http://localhost:8082/testAccount

When we push any of the updated configs in git and that needs to get reflect in clioent, please run the below CURL command.

curl -X POST localhost:8082/actuator/refresh
