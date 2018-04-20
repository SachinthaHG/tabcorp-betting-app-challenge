package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class BetReportService {
    private InMemoryBetsDao inMemoryBetsDao;

    public BetReportService() {
        this.inMemoryBetsDao = InMemoryBetsDao.getInstance();
    }

    public Report<List<InvestmentPerBetType>> generateInvestmentPerBetTypeReport() {
        List<InvestmentPerBetType> investmentPerBetTypeList = new ArrayList<>();

        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        Map<BetType, Double> investmentPerBetType =
                savedBetList.stream().collect(
                        Collectors.groupingBy(Bet::getBetType,
                                Collectors.summingDouble(Bet::getInvestment)));

        investmentPerBetType.forEach((betType, totalInvestment) ->
                investmentPerBetTypeList.add(new InvestmentPerBetType(betType, totalInvestment)));

        Report<List<InvestmentPerBetType>> report = new Report<>();
        report.setReports(investmentPerBetTypeList);

        if (savedBetList.size() > 0) {
            Logger.getLogger(BetReportService.class).info("INVESTMENT PER BET TYPE report generated");
        } else {
            Logger.getLogger(BetReportService.class).info("No saved bets to generate INVESTMENT PER BET TYPE report");
        }

        return report;
    }

    public Report<List<InvestmentPerCustomer>> generateInvestmentPerCustomerReport() {
        List<InvestmentPerCustomer> investmentPerCustomerList = new ArrayList<>();

        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        Map<Integer, Double> investmentPerCustomer =
                savedBetList.stream().collect(
                        Collectors.groupingBy(Bet::getCustomerId,
                                Collectors.summingDouble(Bet::getInvestment)));

        investmentPerCustomer.forEach((customerId, totalInvestment) ->
                investmentPerCustomerList.add(new InvestmentPerCustomer(customerId, totalInvestment)));

        Report<List<InvestmentPerCustomer>> report = new Report<>();
        report.setReports(investmentPerCustomerList);

        if (savedBetList.size() > 0) {
            Logger.getLogger(BetReportService.class).info("INVESTMENT PER CUSTOMER report generated");
        } else {
            Logger.getLogger(BetReportService.class).info("No saved bets to generate INVESTMENT PER CUSTOMER report");
        }

        return report;
    }

    public Report<List<BetsSoldPerBetType>> generateBetsSoldPerBetTypeReport() {
        List<BetsSoldPerBetType> betsSoldPerBetTypeList = new ArrayList<>();

        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        Map<BetType, List<Bet>> betsSoldByBetType =
                savedBetList.stream().collect(Collectors.groupingBy(Bet::getBetType));

        betsSoldByBetType.forEach((betType, betList) ->
                betsSoldPerBetTypeList.add(new BetsSoldPerBetType(betType, betList.size())));

        Report<List<BetsSoldPerBetType>> report = new Report<>();
        report.setReports(betsSoldPerBetTypeList);

        if (savedBetList.size() > 0) {
            Logger.getLogger(BetReportService.class).info("BETS SOLD PER BET TYPE report generated");
        } else {
            Logger.getLogger(BetReportService.class).info("No saved bets to generate BETS SOLD PER BET TYPE report");
        }

        return report;
    }

    public Report<List<BetsSoldPerHour>> generateBetsSoldPerHourReport() {
        List<BetsSoldPerHour> betsSoldPerHourList = new ArrayList<>();

        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        Map<Integer, List<Bet>> betsSoldByHour = savedBetList.stream().
                collect(Collectors.groupingBy(Bet::getHourOfTheDay));

        betsSoldByHour.forEach((hour, betList) -> betsSoldPerHourList.add(new BetsSoldPerHour(hour, betList.size())));

        Report<List<BetsSoldPerHour>> report = new Report<>();
        report.setReports(betsSoldPerHourList);

        if (savedBetList.size() > 0) {
            Logger.getLogger(BetReportService.class).info("BETS SOLD PER HOUR report generated");
        } else {
            Logger.getLogger(BetReportService.class).info("No saved bets to generate BETS SOLD PER HOUR report");
        }

        return report;
    }
}
