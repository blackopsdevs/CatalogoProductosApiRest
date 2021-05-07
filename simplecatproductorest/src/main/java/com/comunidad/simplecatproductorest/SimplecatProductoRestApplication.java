package com.comunidad.simplecatproductorest;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml", "classpath*:com/comunidad/simplecatproductorest/**/*Dao.xml"})
public class SimplecatProductoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplecatProductoRestApplication.class, args);
    }

}
