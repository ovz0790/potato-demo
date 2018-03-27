package nl.potato.market.impl.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity for bag.
 * @author Olga_Zlobina
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bags")
public class BagEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "potatoes_number")
    private Integer potatoesNumber;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "packed_date")
    private Date packedDate;

    @Column(name = "price")
    private Integer price;


    @PrePersist
    void preInsert() {

        if (this.packedDate == null) {
            this.packedDate = new Date();
        }
    }
}
