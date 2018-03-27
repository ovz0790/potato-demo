package nl.potato.market.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.potato.market.api.domain.Bag;
import nl.potato.market.impl.entity.BagEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util for reading input data files
 * @author Olga_Zlobina
 */
@Component
public class TestUtil {
    @Value("${data.file.path}")
    private Resource inputFile;

    @Autowired
    ResourcePatternResolver resourceResolver;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;

    public List<BagEntity> getBagEntities() throws IOException {
        return getBags()
                .stream()
                .map(b -> mapper.map(b, BagEntity.class))
                .collect(Collectors.toList());
    }

    public List<Bag> getBags() throws IOException {
        Resource r = (resourceResolver.getResources("classpath:".concat(((ServletContextResource) inputFile)
                .getPath())))[0];
        return objectMapper.readValue(r.getInputStream(), new TypeReference<List<Bag>>(){});
    }
}
