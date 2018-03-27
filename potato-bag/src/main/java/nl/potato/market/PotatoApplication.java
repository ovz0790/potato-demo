package nl.potato.market;

import lombok.extern.slf4j.Slf4j;
import nl.potato.market.impl.entity.BagEntity;
import nl.potato.market.impl.repository.BagRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application start.
 * @author Olga_Zlobina
 */
@Slf4j
@EnableJpaRepositories(basePackageClasses = BagRepository.class)
@EntityScan(basePackageClasses = {BagEntity.class})
@SpringBootApplication
public class PotatoApplication {
    /**
     * Start.
     */
    public static void main(String[] args) {
        log.info("Version: {}", PotatoApplication.class.getPackage().getImplementationVersion());
        SpringApplication.run(PotatoApplication.class, args);
    }
}
