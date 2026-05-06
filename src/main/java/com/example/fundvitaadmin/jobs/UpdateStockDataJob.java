// language: java
package com.example.fundvitaadmin.jobs;

import com.example.fundvitaadmin.service.GoogleSheetsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Component
public class UpdateStockDataJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateStockDataJob.class);

    @Autowired
    private GoogleSheetsServiceImpl googleSheetsService;

    // Runs at minute 0 of every hour (every hour)
    @Scheduled(cron = "0 0 * * * *")
    public void runHourly() {
        try {
            LOGGER.info("Starting hourly spreadsheet update");
            List<List<Object>>stockData = googleSheetsService.getSpreadsheetValues();
            LOGGER.info("Completed hourly spreadsheet update");
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.error("Failed to update spreadsheet values", e);
        } catch (Exception e) {
            LOGGER.error("Unexpected error in UpdateStockDataJob", e);
        }
    }
}