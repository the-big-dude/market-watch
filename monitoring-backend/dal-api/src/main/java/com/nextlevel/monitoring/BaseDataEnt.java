package com.nextlevel.monitoring;

import javax.persistence.*;

/**
 * Base class for entities of JPA
 */
@MappedSuperclass

public abstract class BaseDataEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "om_entity_seq_gen")
    @SequenceGenerator(name = "om_entity_seq_gen", sequenceName = "order_management_sequence", allocationSize = 1)
    private Long id;

    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


}
