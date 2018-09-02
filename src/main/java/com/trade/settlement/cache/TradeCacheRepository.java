package com.trade.settlement.cache;

import java.util.List;

import com.trade.settlement.model.InstructionType;
import com.trade.settlement.model.Trade;

// @Repository -> this interface may extend spring-data CacheRepository for common cache query functionality
// which includes insert, delete, getById, update
public interface TradeCacheRepository {

    List<Trade> getAll();

    List<Trade> getByInstrumentType(InstructionType instructionType);
}
