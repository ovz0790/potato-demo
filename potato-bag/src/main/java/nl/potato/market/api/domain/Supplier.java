package nl.potato.market.api.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for Supplier.
 * @author Olga_Zlobina
 */
@AllArgsConstructor
@Getter
public enum Supplier {
    DE_COSTER("De Coster"),
    OWEL("Owel"),
    PATATAS_RUBEN("Patatas Ruben"),
    YUNNAN_SPICES("Yunnan Spices");

    @JsonValue
    public String getName() {
        return name;
    }

    private final String name;
}
