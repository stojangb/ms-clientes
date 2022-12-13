package com.quiz.msclient.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="phone")
public class Phone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String idPhone;
    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id")
    private Client client;
}
