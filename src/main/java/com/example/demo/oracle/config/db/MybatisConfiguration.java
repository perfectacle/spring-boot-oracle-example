package com.example.demo.oracle.config.db;

import com.example.demo.oracle.DemoApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/*
  @WebMvcTest나 @DataJpaTest와 같은 Slice Test를 할 때는 특정 빈들만 어플리케이션 컨텍스트에 올리면 된다.
  따라서 쓸 데 없는 Configuration들이 주입되는 것을 방지하고자 Main 클래스에는 @SpringBootApplication을 제외한 어노테이션을 붙이는 것을 지양해야한다.
  원래대로라면 아무 생각없이 Main 클래스에 붙였을 어노테이션들을 전부 별도의 Configuration 클래스로 빼면 다음과 같은 이점이 생긴다.
  예를 들면 @WebMvcTest는 Validation이나 Controller Advice, Filter 등등의 테스트를 하기 위해 사용하는 어노테이션이다.
  이 때 @MapperScan이 적용된다면 불필요한 빈이나 Configuration이 주입되는 바람에 어플리케이션 컨텍스트가 제대로 뜨지 않는 단점이 존재한다.
  이는 테스트 속도 저하 및 테스트 환경 구성에 많은 삽질을 요구하여 Testablity를 극명하게 떨어뜨리는 지름길이 된다.
 */
@Configuration
@MapperScan(basePackageClasses = DemoApplication.class)
public class MybatisConfiguration {}
