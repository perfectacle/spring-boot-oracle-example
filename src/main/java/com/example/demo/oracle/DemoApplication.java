package com.example.demo.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  @WebMvcTest나 @DataJpaTest와 같은 Slice Test를 할 때는 특정 빈들만 어플리케이션 컨텍스트에 올리면 된다.
  따라서 쓸 데 없는 Configuration들이 주입되는 것을 방지하고자 Main 클래스에는 @SpringBootApplication을 제외한 어노테이션을 붙이는 것을 지양해야한다.
  또한 로컬에서는 오라클 DB를 띄우지 않았으므로 DB에 커넥션을 맺는 순간 오류가 발생합니다
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
