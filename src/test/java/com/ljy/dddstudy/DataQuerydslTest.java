package com.ljy.dddstudy;

import com.ljy.dddstudy.core.querydsl.QuerydslRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.annotation.Rollback;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Rollback(value = false)
@DataJpaTest(
        properties = {
                "spring.jpa.properties.hibernate.format_sql=true"
        }
)
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = { QuerydslRepository.class }
                )
        },
        basePackageClasses = DddStudyApplication.class
)
public @interface DataQuerydslTest {
}
