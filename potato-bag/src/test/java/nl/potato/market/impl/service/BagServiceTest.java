package nl.potato.market.impl.service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import nl.potato.market.api.domain.Bag;
import nl.potato.market.api.domain.Supplier;
import nl.potato.market.impl.entity.BagEntity;
import nl.potato.market.impl.repository.BagRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Test for bag service.
 * @author Olga_Zlobina
 */
public class BagServiceTest {

    @InjectMocks
    private BagServiceImpl bagService;

    @Mock
    private BagRepository bagRepository;

    @Mock
    private ModelMapper mapper;

    private final static String UUID = "1e0e403e-3194-11e8-b467-0ed5f89f718b";

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        mapper = new ModelMapper();

        Pageable pageForOne = new PageRequest(0, 1);

        Page<BagEntity> one = new PageImpl<>(Arrays.asList(new BagEntity()));
        when(bagRepository.findAll(pageForOne)).thenReturn(one);

        Pageable pageForThree = new PageRequest(0, 3);
        Page<BagEntity>  three = new PageImpl<>(Arrays.asList(new BagEntity(),
                new BagEntity(),
                new BagEntity()));

        when(bagRepository.findAll(pageForThree)).thenReturn(three);
        BagEntity newBag = new BagEntity()
                .setId(UUID)
                .setPackedDate(new Date())
                .setPotatoesNumber(10)
                .setPrice(50)
                .setSupplier(Supplier.DE_COSTER.name());
        when(bagRepository.save(any(BagEntity.class))).thenReturn(newBag);

        when(bagRepository.findOne(UUID)).thenReturn(newBag.setId(UUID));

    }

    @Test
    public void findBagsTest() {
        List<Bag> findedOne = bagService.findBags(1);
        assertEquals(findedOne.size(), 1);

        List<Bag> findedThree = bagService.findBags(3);
        assertEquals(findedThree.size(), 3);
    }

    @Test
    public  void createNewBagTest() {
        Bag newBag = new Bag()
                .setPackedDate(new Date())
                .setPotatoesNumber(10)
                .setPrice(50)
                .setSupplier(Supplier.DE_COSTER);
        String id = bagService.createNewBag(newBag);

        BagEntity finded = bagRepository.findOne(id);
        assertNotNull(finded);

    }


}
