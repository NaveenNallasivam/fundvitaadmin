package com.example.fundvitaadmin.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.example.fundvitaadmin.util.GoogleAuthorizationConfig;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSheetsServiceImpl.class);

    @Value("${spreadsheet.id}")
    private String spreadsheetId;

    @Autowired
    private GoogleAuthorizationConfig googleAuthorizationConfig;

    @Override
    public List<List<Object>> getSpreadsheetValues() throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleAuthorizationConfig.getSheetsService();
        Sheets.Spreadsheets.Values.BatchGet request =
                sheetsService.spreadsheets().values().batchGet(spreadsheetId);
        request.setRanges(getSpreadSheetRange());
        request.setMajorDimension("ROWS");
        BatchGetValuesResponse response = request.execute();
        List<List<Object>> spreadSheetValues = response.getValueRanges().stream().map(ValueRange::getValues).filter(values -> values != null && !values.isEmpty())
                .flatMap(values -> {
                    // skip header row safely
                    return values.size() > 1
                            ? values.subList(1, values.size()).stream()
                            : Stream.empty();
                })
                .collect(Collectors.toList());
        return spreadSheetValues;
    }

    private List<String> getSpreadSheetRange() throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleAuthorizationConfig.getSheetsService();
        Spreadsheet spreadsheet = sheetsService.spreadsheets()
                .get(spreadsheetId)
                .execute();
        List<String> ranges = new ArrayList<>();
        for (Sheet sheet : spreadsheet.getSheets()) {
            String sheetName = sheet.getProperties().getTitle();
            ranges.add(sheetName + "!A1:Z1000");
        }
        return ranges;
    }
}