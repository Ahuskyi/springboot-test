package com.waipin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
//@SpringBootApplication(scanBasePackages = "com.waipin")
@EnableAutoConfiguration
@MapperScan(basePackages ="com.waipin.mapper")
@ComponentScan(basePackages = {"com.waipin.web","com.waipin.service"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }
}
