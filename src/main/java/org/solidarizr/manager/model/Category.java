package org.solidarizr.manager.model;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Entity
@Value
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
}
