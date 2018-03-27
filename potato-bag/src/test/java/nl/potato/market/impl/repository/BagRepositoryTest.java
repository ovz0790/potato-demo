package nl.potato.market.impl.repository;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import nl.potato.market.api.domain.Supplier;
import nl.potato.market.impl.TestConfig;
import nl.potato.market.impl.TestUtil;
import nl.potato.market.impl.entity.BagEntity;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

/**
 * Test for bag repository
 * @author Olga_Zlobina
 */
@TestConfig
@RunWith(SpringRunner.class)
public class BagRepositoryTest {

    @Autowired
    private BagRepository bagRepository;

    @Autowired
    private TestUtil testUtil;

    @After
    public void initTestData() {
        bagRepository.deleteAll();
    }

    @Test
    public void findAllTest() throws IOException {
        int count = 3;
        Pageable page = new PageRequest(1, count);

        Iterable<BagEntity> empty = bagRepository.findAll(page);
        assertFalse(empty.iterator().hasNext());

        testUtil.getBagEntities()
                .stream()
                .forEach(be -> bagRepository.save(be));

        Iterable<BagEntity> finded = bagRepository.findAll(page);
        assertEquals(((PageImpl) finded).getContent().size(),  count);

    }

    @Test
    public void saveTest() {
        BagEntity bag = new BagEntity()
                .setPackedDate(new Date())
                .setPotatoesNumber(10)
                .setPrice(50)
                .setSupplier(Supplier.DE_COSTER.name());

        BagEntity saved = bagRepository.save(bag);
        assertNotNull(saved.getId());

        BagEntity finded = bagRepository.findOne(saved.getId());
        assertNotNull(finded);
    }
}
