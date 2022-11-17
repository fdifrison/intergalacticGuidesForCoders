package dev.fdifrison;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleHandler implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        // extract the logger from the context
        LambdaLogger logger = context.getLogger();
        logger.log("Function " + context.getFunctionName() + " was called");
        return input.toUpperCase();
    }
}
