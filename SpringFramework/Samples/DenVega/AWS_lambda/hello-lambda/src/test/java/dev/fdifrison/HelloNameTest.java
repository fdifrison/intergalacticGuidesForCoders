package dev.fdifrison;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloNameTest {

    public final String NAME = "fdifrison";

    @Test
    public void returnMyName() {
        var stu = new HelloName();
        Assertions.assertEquals("Hello FDIFRISON!", stu.handleRequest(NAME));
    }

}