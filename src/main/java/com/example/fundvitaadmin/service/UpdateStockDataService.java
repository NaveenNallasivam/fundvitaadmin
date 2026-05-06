package com.example.fundvitaadmin.service;

import java.util.List;

public interface UpdateStockDataService {
    void updateStockDataFromGoogleSheets(List<List<Object>> stockDataFromSheet);
}
