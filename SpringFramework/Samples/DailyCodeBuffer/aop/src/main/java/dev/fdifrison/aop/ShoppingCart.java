package dev.fdifrison.aop;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    /*
     This class should require a lot of cross-cutting concerns like:
     Logging
     Authentication
     Data validation
     and each of this task can be traduced into an aspect in our aop structure
    */

    public void checkout(String status) {

        System.out.println("checkout method called... " + status);
    }

    public Integer quantity() {
        return 2;
    }
}
