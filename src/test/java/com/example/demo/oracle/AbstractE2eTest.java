package com.example.demo.oracle;

import com.example.demo.oracle.config.TestDataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*
  기존의 것과 최대한 동일하게 Junit 4를 사용했습니다.
  DataSourceConfiguration에 있는 DataSource 빈이 사용되는 것을 방지하고자 e2e-test profile이 사용되게 끔 설정했습니다.
  그리고 application-{profile}.yml이 적용되기 때문에 application-e2e-test.yml에 있는 설정에 의해
  init-schema.sql에서는 테이블 생성문이 실행되고, init-data.sql에서는 초기 데이터 삽입이 진행됩니다.
  또한 DataSource 빈이 있어야 DB 커넥션을 맺을 수 있기 때문에 @TestConfiguration으로 선언했던 TestDataSourceConfiguration이 적용되도록 설정했습니다.

  https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications
  SpringBootTest는 기본적으로 HTTP 서버를 띄우지 않고 Mock 환경으로 웹 환경을 구성합니다.
  근데 E2E 테스트를 하기 위해서는 실제로 API를 호출하려면 포트 바인딩까지 시켜서 웹을 구성해야하죠.
  하지만 그러면 어플리케이션 컨텍스트가 뜰 때 시간이 좀 더 걸리다보니 Mock 환경에서도 API 호출을 가능하게 해주는 MockMvc를 사용하기 위해 @AutoConfigureMockMvc를 사용했습니다.

  https://jehuipark.github.io/spring/boot-2-2-x-mock-mvc-encoding-issue
  참고로 스프링 부트 2.2부터는 mock mvc에서 한글 깨짐 이슈가 있으니 위 블로그를 확인하여 해결하면 됨.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("e2e-test")
@Import(TestDataSourceConfiguration.class)
@AutoConfigureMockMvc
public class AbstractE2eTest {
    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void contextLoads() {}
}
