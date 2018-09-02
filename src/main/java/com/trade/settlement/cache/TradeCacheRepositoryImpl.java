package com.trade.settlement.cache;

import java.util.List;
import java.util.stream.Collectors;

import com.trade.settlement.db.TradeDataSource;
import com.trade.settlement.model.InstructionType;
import com.trade.settlement.model.Trade;

// @Component -> This class may not require if we are using spring-data library
public class TradeCacheRepositoryImpl implements TradeCacheRepository {

    // @Autowired
    private TradeDataSource tradeDataSource;
    private List<Trade> trades;

    public TradeCacheRepositoryImpl() {

        // trade data source can be injected using Spring context
        tradeDataSource = new TradeDataSource();
    }

    @Override
    public List<Trade> getAll() {

        if (trades == null) {
            trades = tradeDataSource.queryAll();
        }

        return trades;
    }

    @Override
    public List<Trade> getByInstrumentType(InstructionType instructionType) {

        return getAll().parallelStream().filter(trade -> trade.getType() == instructionType).collect(Collectors.toList());
    }
}
