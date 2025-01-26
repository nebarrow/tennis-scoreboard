package com.nebarrow.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Matches")
@NoArgsConstructor
@AllArgsConstructor
public class Match extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JoinColumn(name = "Player1", referencedColumnName = "ID")
    @ManyToOne
    Player playerOne;

    @JoinColumn(name = "Player2", referencedColumnName = "ID")
    @ManyToOne
    Player playerTwo;

    @JoinColumn(name = "Winner", referencedColumnName = "ID")
    @ManyToOne
    Player winner;
}
