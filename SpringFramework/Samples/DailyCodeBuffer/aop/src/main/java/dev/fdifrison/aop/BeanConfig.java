package dev.fdifrison.aop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "dev.fdifrison.aop")
@EnableAspectJAutoProxy
public class BeanConfig {
}
