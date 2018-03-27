package nl.potato.market.impl.controller;

import lombok.extern.slf4j.Slf4j;
import nl.potato.market.api.controller.BagResource;
import nl.potato.market.api.domain.Bag;
import nl.potato.market.api.service.BagService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Impl for bags API.
 * @author Olga_Zlobina
 */
@Slf4j
@RestController
public class BagResourceImpl implements BagResource {

    @Value("${bags.page.size}")
    private int defBagsCount;

    private final BagService bagService;

    public BagResourceImpl(BagService bagService) {
        this.bagService = bagService;
    }

    @Override
    @RequestMapping(value = "ui/nl/potato/market/bags", method = RequestMethod.GET)
    public List<Bag> getBags(@RequestParam(required = false, name = "count")
                                         Integer bagCount) {
        log.debug("Getting request for find bags");
        return bagService.findBags(bagCount != null
                ? bagCount : defBagsCount);
    }

    @Override
    @RequestMapping(value = "ui/nl/potato/market/bag", method = RequestMethod.POST)
    public String createBag(@Valid @RequestBody Bag bag) {
        log.debug("Getting request for save new bag with {} PotatoesNumber"
                        + " and Price {} and Supplier {}",
                bag.getPotatoesNumber(),
                bag.getPrice(),
                bag.getSupplier().getName());
        return bagService.createNewBag(bag);
    }
}
