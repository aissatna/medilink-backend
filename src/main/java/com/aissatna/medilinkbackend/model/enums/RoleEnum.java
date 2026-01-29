package com.aissatna.medilinkbackend.model.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    DIRECTOR("","",""),
    SECRETARY("Export_Secretaries", "Secretaries Export", "Export_Secretaries"),
    NURSE("Export_Nurses", "Nurses Export", "Export_Nurses");

    private final String excelTitle;
    private final String emailSubject;
    private final String filePrefix;

    RoleEnum(String excelTitle, String emailSubject, String filePrefix) {
        this.excelTitle = excelTitle;
        this.emailSubject = emailSubject;
        this.filePrefix = filePrefix;
    }

}