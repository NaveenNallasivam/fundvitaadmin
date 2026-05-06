package com.example.fundvitaadmin.entity;

public interface Stock {
    Long getUidpk();
    void setUidpk(Long uidpk);

    String getCode();
    void setCode(String code);

    String getDescription();
    void setDescription(String description);
}
