package nl.potato.market.impl;

import nl.potato.market.impl.entity.BagEntity;
import nl.potato.market.impl.repository.BagRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Config for spring tests
 * @author Olga_Zlobina
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ContextConfiguration
@EnableJpaRepositories(basePackageClasses = BagRepository.class)
@EntityScan(basePackageClasses = {BagEntity.class})
public @interface TestConfig {
}
