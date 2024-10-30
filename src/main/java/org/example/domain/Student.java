package org.player.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Player {
    private String playerID;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private String birthCountry;
    private String deathYear;
    private String deathMonth;
    private String deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private String weight;
    private String height;
    private String bats;
    private String thr;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}
