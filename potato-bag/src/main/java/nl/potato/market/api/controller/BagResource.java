package nl.potato.market.api.controller;

import nl.potato.market.api.domain.Bag;

import java.util.List;

/**
 * REST API for potato bags.
 * @author Olga_Zlobina
 */
public interface BagResource {

    List<Bag> getBags(Integer bagCount);

    String createBag(Bag bag);
}
