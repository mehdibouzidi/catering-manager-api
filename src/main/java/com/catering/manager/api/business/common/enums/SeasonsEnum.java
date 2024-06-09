package com.catering.manager.api.business.common.enums;

import lombok.Getter;

@Getter
public enum SeasonsEnum {
    ETE("Été"), PRINTEMPS("Printemps"), HIVER("Hiver"), AUTOMNE("Automne");

    private String season;

    SeasonsEnum(String season) {
        this.season = season;
    }

}
