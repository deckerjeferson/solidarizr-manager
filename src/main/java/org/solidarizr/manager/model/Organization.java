package org.solidarizr.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Organization {

    public static final String SEQUENCE_NAME = "organization_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;
}
