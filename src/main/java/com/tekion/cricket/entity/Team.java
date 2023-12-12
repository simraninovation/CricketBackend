package com.tekion.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long teamId;

    @Column(name = "teamName")
    private String teamName;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy="team")
    private Set<Players> player;

    @JsonIgnore
    @OneToMany(mappedBy="team")
    private List<Players> players;

}
