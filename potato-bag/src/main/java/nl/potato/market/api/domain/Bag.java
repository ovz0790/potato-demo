package nl.potato.market.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * Domain model for potato bag.
 * @author Olga_Zlobina
 */
@Getter
@Setter
@Accessors(chain = true)
@Validated
public class Bag {

    private String id;

    @Valid
    @NotNull
    @Min(value=1, message = "Potato number could be more than 1" )
    @Max(value=100, message = "Potato number could be less than 100" )
    private Integer potatoesNumber;

    private Supplier supplier;

    private Date packedDate;

    @Valid
    @NotNull
    @Min(value=1, message = "Price could be more than 1" )
    @Max(value=50, message = "Price could be less than 50" )
    private Integer price;

}
