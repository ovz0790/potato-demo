package nl.potato.market.impl.controller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import nl.potato.market.api.domain.Bag;
import nl.potato.market.api.domain.Supplier;
import nl.potato.market.impl.TestConfig;
import nl.potato.market.impl.TestUtil;
import nl.potato.market.impl.entity.BagEntity;
import nl.potato.market.impl.repository.BagRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Test for bags API.
 * @author Olga_Zlobina
 */
@TestConfig
@RunWith(SpringRunner.class)
public class BagResourceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private BagRepository bagRepository;

    @Value("${bags.page.size}")
    private int defBagsCount;

    private final static String GET_BAGS_URL = "/ui/nl/potato/market/bags";
    private final static String CREATE_BAG_URL = "/ui/nl/potato/market/bag";

    @Before
    public void initTestData() throws IOException {
        testUtil.getBagEntities()
                .stream()
                .forEach(be -> bagRepository.save(be));
    }

    @After
    public void clearTestData() {
        bagRepository.deleteAll();
    }

    @Test
    public void getBagsWithDefaultPageSizeTest() {

        ResponseEntity<List<Bag>> response = restTemplate.exchange(
                GET_BAGS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bag>>() {});

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), defBagsCount);

    }

    @Test
    public void getBagsWithDefinedPageSizeTest() {

        int bagsCount = 5;

        ResponseEntity<List<Bag>> response = restTemplate.exchange(
                String.format("%s?count=%d", GET_BAGS_URL, bagsCount),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bag>>() {});

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), bagsCount);

    }

    @Test
    public void createBagTest() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        Bag newBag = new Bag().setPackedDate(new Date())
                .setPotatoesNumber(10)
                .setPrice(50)
                .setSupplier(Supplier.DE_COSTER);

        ResponseEntity<String> response = restTemplate.exchange(
                CREATE_BAG_URL,
                HttpMethod.POST,
                new HttpEntity<>(newBag, headers),
                String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        BagEntity findedBag = bagRepository.findOne(response.getBody());
        assertNotNull(findedBag);
        assertEquals(findedBag.getSupplier(), newBag.getSupplier().name());
        assertEquals(findedBag.getPotatoesNumber(), newBag.getPotatoesNumber());
        assertEquals(findedBag.getPrice(), newBag.getPrice());

    }

    @Test
    public void createBagWitValidationErrorTest() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        Bag newBag = new Bag().setPackedDate(new Date())
                .setPotatoesNumber(1000)
                .setPrice(50000)
                .setSupplier(Supplier.DE_COSTER);

        ResponseEntity<String> response = restTemplate.exchange(
                CREATE_BAG_URL,
                HttpMethod.POST,
                new HttpEntity<>(newBag, headers),
                String.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertTrue(response.getBody().toString().contains(MethodArgumentNotValidException.class.getSimpleName()));
    }

}
