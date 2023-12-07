package com.tekion.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Players")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "Age")
    private int age;

    @Column(name = "isCaptain")
    private Boolean isCaptain;

    @Column(name ="PlayerType")
    private String playerType;

     @ManyToOne
    @JoinColumn(name="teamId", nullable = false)
      private Team team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players = (Players) o;
        return age == players.age && Objects.equals(Id, players.Id) && Objects.equals(firstName, players.firstName) && Objects.equals(lastName, players.lastName) && Objects.equals(isCaptain, players.isCaptain) && Objects.equals(playerType, players.playerType) && Objects.equals(team, players.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, age, isCaptain, playerType, team);
    }

    @Override
    public String toString() {
        return "Players{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isCaptain=" + isCaptain +
                ", playerType='" + playerType + '\'' +
                ", team=" + team +
                '}';
    }
}
