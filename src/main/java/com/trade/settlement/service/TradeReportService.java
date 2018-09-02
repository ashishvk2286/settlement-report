package com.trade.settlement.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.trade.settlement.cache.TradeCacheRepository;
import com.trade.settlement.cache.TradeCacheRepositoryImpl;
import com.trade.settlement.model.InstructionType;
import com.trade.settlement.model.Trade;

// @Service -> Can be instantiated using spring
public class TradeReportService {

    // @Autowired
    private TradeCacheRepository tradeRepo;

    public TradeReportService() {

        // Trade cache repository can be injected using Spring context
        tradeRepo = new TradeCacheRepositoryImpl();
    }

    public Map<LocalDate, Double> getTotalSettlementAmountsByDay(InstructionType instructionType) {

        List<Trade> trades = tradeRepo.getByInstrumentType(instructionType);

        // Group trades by settlement date and calculate sum of Amount in USD for each day
        Map<LocalDate, Double> totalSettlementAmountsByDay = trades.stream().collect(Collectors.groupingBy(trade -> trade.getSettlementDate(), Collectors.summingDouble(trade -> trade.getAmountInUSD().doubleValue())));

        return totalSettlementAmountsByDay;
    }

    public List<Trade> getRankingsByAmounts(InstructionType instructionType) {

        List<Trade> trades = tradeRepo.getByInstrumentType(instructionType);

        // 1st Step is to group trades by entity and find Trade with maximum amount in USD
        Map<String, Optional<Trade>> rankingByAmountsOpts = trades.stream()
                .collect(Collectors.groupingBy(Trade::getEntity, Collectors.mapping(Function.identity(), Collectors.maxBy(Comparator.comparing(Trade::getAmountInUSD)))));

        // 2nd Step is to collect trades determined in last step and sort final result by amount in USD
        List<Trade> rankingByAmounts = rankingByAmountsOpts.values().stream().map(tradeOpt -> tradeOpt.get()).sorted(Comparator.comparing(Trade::getAmountInUSD).reversed()).collect(Collectors.toList());

        return rankingByAmounts;
    }
}
