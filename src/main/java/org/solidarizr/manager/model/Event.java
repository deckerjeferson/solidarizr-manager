package org.solidarizr.manager.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {

    public static final String SEQUENCE_NAME = "event_id_seq";
<<<<<<< HEAD

=======
>>>>>>> cb1990ccc2c1194a680f270eebac232d9a228d26
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "creator")
=======
    @JoinColumn(name = "organization")
>>>>>>> cb1990ccc2c1194a680f270eebac232d9a228d26
    private Organization organization;

}
