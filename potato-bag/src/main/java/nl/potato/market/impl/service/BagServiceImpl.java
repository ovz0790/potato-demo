package nl.potato.market.impl.service;

import lombok.AllArgsConstructor;
import nl.potato.market.api.domain.Bag;
import nl.potato.market.api.service.BagService;
import nl.potato.market.impl.entity.BagEntity;
import nl.potato.market.impl.repository.BagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for potato bag.
 * @author Olga_Zlobina
 */
@Service
@AllArgsConstructor
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;

    private final ModelMapper mapper;

    @Override
    public List<Bag> findBags(int bagCount) {
        Pageable page = new PageRequest(0, bagCount);

        List<BagEntity> result = bagRepository.findAll(page).getContent();

        return Optional.ofNullable(result)
                .orElse(Collections.emptyList())
                .stream()
                .map(b -> mapper.map(b, Bag.class))
                .collect(Collectors.toList());
    }

    @Override
    public String createNewBag(Bag bag) {
        return bagRepository.save(mapper.map(bag, BagEntity.class)).getId();
    }
}
