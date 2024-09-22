package com.winner.component;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.winner.mapper")
public class MybatisConfig {



}
