package com.example.demo.oracle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.OracleContainer;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/*
  @TestConfiguration 어노테이션은 컴포넌트 스캔 범위에 속하지 않으므로 자동으로 빈이 생성되지 않습니다.
  @Configuration 대신 @TestConfiguration을 사용한 이유는 이 컴포넌트는 DB Config를 다루는 핵심 Configuration이다보니
  자동으로 주입되기 보다는 해당 설정을 사용하고자 하는 니즈가 강한 테스트에 직접 주입하는 게 본인이 무엇을 설정하였는지 인지하기 쉽다고 판단하여 사용하였습니다.
 */
@TestConfiguration
public class TestDataSourceConfiguration {
    private final OracleContainer oracleContainer;

    public TestDataSourceConfiguration(
        // 만약 다른 오라클 이미지를 사용하고 싶다면 바꾸시면 됩니다.
        @Value("${oracle.container.image}")
        final String oracleImage
    ) {
        oracleContainer = new OracleContainer(oracleImage);
        oracleContainer.start();
    }

    @PreDestroy
    public void clean() {
        oracleContainer.stop();
    }

    @Bean
    public DataSource dataSource() {
        // 오라클 컨테이너가 시작돼야 아래 정보들을 읽어올 수 있습니다. 특히 포트는 컨테이너가 뜰 때마다 매번 바뀌어서 jdbcUrl은 동적으로 바뀝니다.
        return DataSourceBuilder.create()
                                .url(oracleContainer.getJdbcUrl())
                                .username(oracleContainer.getUsername())
                                .password(oracleContainer.getPassword())
                                .build();
    }
}