package dev.fdifrison.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);

		ShoppingCart cart = ctx.getBean(ShoppingCart.class);
		cart.checkout("CANCELLED");
		cart.quantity();

	}

}
