package dev.fdifrison;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloLambdaTest {

    @Test
    public  void shouldReturnHello() {
        // sut = system under test
        var sut = new HelloLambda();
        Assertions.assertEquals("Hello, AWS Lambda!", sut.handleRequest());
    }

}