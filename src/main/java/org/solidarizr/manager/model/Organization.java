package org.solidarizr.manager.model;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Entity
@Value
@Builder
public class Organization {

    public static final String SEQUENCE_NAME = "organization_id_sec";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Integer id;

    private String name;
}
