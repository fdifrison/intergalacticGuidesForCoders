# AWS Lambda Java - by Dan Vega

<https://www.youtube.com/watch?v=MaHxZEBRcT4> part 1, pure Java
<https://www.youtube.com/watch?v=kyWllXOGMWQ> part 2, using aws core library
<https://www.youtube.com/watch?v=K1OI-S0ET70> part 3, connect to a db
<https://www.youtube.com/watch?v=gj1DDymw5iY> part 4, spring cloud

<https://tanzu.vmware.com/developer/guides/serverless-spring/> -> blog Serverless Spring
<https://docs.aws.amazon.com/lambda/latest/dg/lambda-java.html>

## Function handler

The following is the function name for the aws handler to be place in the edit-runtime-settings section during lambda
creation.

`org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest`
