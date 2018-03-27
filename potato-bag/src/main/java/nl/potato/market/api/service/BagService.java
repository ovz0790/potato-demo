package nl.potato.market.api.service;

import nl.potato.market.api.domain.Bag;

import java.util.List;

/**
 * Service for potato bag.
 * @author Olga_Zlobina
 */
public interface BagService {

    List<Bag> findBags(int bagCount);

    String createNewBag(Bag bag);
}
