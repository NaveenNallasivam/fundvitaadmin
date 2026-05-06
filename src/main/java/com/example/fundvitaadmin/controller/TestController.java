package com.example.fundvitaadmin.controller;

import com.example.fundvitaadmin.service.GoogleSheetsService ;
import com.example.fundvitaadmin.service.UpdateStockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping(value = "/api/v1/googlesheets")
public class TestController {

    @Autowired
    private GoogleSheetsService googleSheetsService;
    @Autowired
    private UpdateStockDataService updateStockDataService;

    @GetMapping(value="ping")
    public void getSpreadsheetValues() throws IOException, GeneralSecurityException {
         updateStockDataService.updateStockDataFromGoogleSheets(googleSheetsService.getSpreadsheetValues());
    }

    @GetMapping(value="getStockPrice")
    public String getStockData(@RequestParam("stockCode") String stockCode) throws IOException, GeneralSecurityException {
        updateStockDataService.updateStockDataFromGoogleSheets(googleSheetsService.getSpreadsheetValues());
        return "OK";
    }
}