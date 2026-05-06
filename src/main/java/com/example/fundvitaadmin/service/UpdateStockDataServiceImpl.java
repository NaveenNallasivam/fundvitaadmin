package com.example.fundvitaadmin.service;

import com.example.fundvitaadmin.dto.StockDto;
import com.example.fundvitaadmin.entity.impl.StockImpl;
import com.example.fundvitaadmin.entity.impl.StockPeImpl;
import com.example.fundvitaadmin.entity.impl.StockPbImpl;
import com.example.fundvitaadmin.entity.impl.StockMarkcapImpl;
import com.example.fundvitaadmin.entity.impl.StockDeImpl;
import com.example.fundvitaadmin.repository.StockRepository;
import com.example.fundvitaadmin.repository.StockPeRepository;
import com.example.fundvitaadmin.repository.StockPbRepository;
import com.example.fundvitaadmin.repository.StockMarkcapRepository;
import com.example.fundvitaadmin.repository.StockDeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UpdateStockDataServiceImpl implements UpdateStockDataService{

    private final StockRepository stockRepository;
    private final StockPeRepository stockPeRepository;
    private final StockPbRepository stockPbRepository;
    private final StockMarkcapRepository stockMarkcapRepository;
    private final StockDeRepository stockDeRepository;

    public UpdateStockDataServiceImpl(StockRepository stockRepository,
                                      StockPeRepository stockPeRepository,
                                      StockPbRepository stockPbRepository,
                                      StockMarkcapRepository stockMarkcapRepository,
                                      StockDeRepository stockDeRepository) {
        this.stockRepository = stockRepository;
        this.stockPeRepository = stockPeRepository;
        this.stockPbRepository = stockPbRepository;
        this.stockMarkcapRepository = stockMarkcapRepository;
        this.stockDeRepository = stockDeRepository;
    }

    @Override
    public void updateStockDataFromGoogleSheets(List<List<Object>> stockDataFromSheet) {
        for(List<Object> stockData : stockDataFromSheet) {
            if (stockData.size() < 2) {
                continue; // Skip rows that don't have enough data (code + price at least)
            }
            StockDto stockDto = new StockDto();
            populateStockDto(stockData, stockDto);
            persistStockDto(stockDto);
        }

    }

    private void persistStockDto(StockDto dto) {
        if (dto.getCode() == null || dto.getCode().trim().isEmpty()) {
            return;
        }

        Optional<StockImpl> existing = stockRepository.findByCode(dto.getCode());
        StockImpl stock;
        if (existing.isPresent()) {
            stock = existing.get();
            stock.setDescription(dto.getDescription());
            stock.setCurrentPrice(dto.getCurrentPrice());
            stock = stockRepository.save(stock);
        } else {
            stock = new StockImpl();
            stock.setCode(dto.getCode());
            stock.setDescription(dto.getDescription());
            stock.setCurrentPrice(dto.getCurrentPrice());
            stock = stockRepository.save(stock);
        }

        // delete existing metric rows
        stockPeRepository.deleteByStock(stock);
        stockPbRepository.deleteByStock(stock);
        stockMarkcapRepository.deleteByStock(stock);
        stockDeRepository.deleteByStock(stock);

        // Insert new metric rows if values present
        if (dto.getStockPeValue() != null) {
            StockPeImpl pe = new StockPeImpl();
            pe.setStock(stock);
            pe.setValue(dto.getStockPeValue());
            stockPeRepository.save(pe);
        }
        if (dto.getStockPbValue() != null) {
            StockPbImpl pb = new StockPbImpl();
            pb.setStock(stock);
            pb.setValue(dto.getStockPbValue());
            stockPbRepository.save(pb);
        }
        if (dto.getStockMarkcapValue() != null) {
            StockMarkcapImpl markcap = new StockMarkcapImpl();
            markcap.setStock(stock);
            markcap.setValue(dto.getStockMarkcapValue());
            stockMarkcapRepository.save(markcap);
        }
        if (dto.getStockDeValue() != null) {
            StockDeImpl de = new StockDeImpl();
            de.setStock(stock);
            de.setValue(dto.getStockDeValue());
            stockDeRepository.save(de);
        }
    }

    private void populateStockDto(List<Object> stockDataFromSheet, StockDto stockDto) {
            stockDto.setCode(stockDataFromSheet.get(0).toString());
            try {
                stockDto.setCurrentPrice(new BigDecimal(stockDataFromSheet.get(1).toString()));
            } catch (Exception ex) {
                stockDto.setCurrentPrice(null);
            }
            try {
                stockDto.setStockPeValue(new BigDecimal(stockDataFromSheet.get(2).toString()));
            } catch (Exception ex) {
                stockDto.setStockPeValue(null);
            }
            try {
                stockDto.setStockPbValue(new BigDecimal(stockDataFromSheet.get(3).toString()));
            } catch (Exception ex) {
                stockDto.setStockPbValue(null);
            }
            try {
                stockDto.setStockMarkcapValue(new BigDecimal(stockDataFromSheet.get(4).toString()));
            } catch (Exception ex) {
                stockDto.setStockMarkcapValue(null);
            }
            try {
                stockDto.setStockDeValue(new BigDecimal(stockDataFromSheet.get(5).toString()));
            } catch (Exception ex) {
                stockDto.setStockDeValue(null);
            }
    }
}
