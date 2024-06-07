package com.catering.manager.api.business.common.util;

import lombok.Getter;

@Getter
public enum BusinessError {
    NON_EXISTING_CATEGORY("Non existing Category");


    private String libelle;

    BusinessError(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return name();
    }
}
