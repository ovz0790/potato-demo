package nl.potato.market.impl.repository;

import nl.potato.market.impl.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for bag.
 * @author Olga_Zlobina
 */
public interface BagRepository extends PagingAndSortingRepository<BagEntity, String>,
        JpaSpecificationExecutor<BagEntity> {
}
