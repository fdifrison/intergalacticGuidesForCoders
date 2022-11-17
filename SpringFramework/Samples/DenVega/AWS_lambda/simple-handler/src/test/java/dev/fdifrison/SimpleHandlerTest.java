package dev.fdifrison;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleHandlerTest {

    private SimpleHandler simpleHandler;

    @Mock
    Context context;

    @Mock
    LambdaLogger logger;

    @BeforeEach
    public  void setup() {
        // when the method .getLogger() is called, then return the mocked version of the logger
        when(context.getLogger()).thenReturn(logger);

        // Stubbing : substitute a routine with something else
        // in the specific case we are replacing logger.log with system.ou.println()
        Mockito.doAnswer(call ->  {
            System.out.println((String) call.getArgument(0));
            return null;
        }).when(logger).log(anyString());

        simpleHandler = new SimpleHandler();

    }


    @Test
    void ShouldReturnUpperCaseInput() {
        when(context.getFunctionName()).thenReturn("handleRequest");
        Assertions.assertEquals("HELLO WORLD!", simpleHandler.handleRequest("hello world!", context));

    }

}