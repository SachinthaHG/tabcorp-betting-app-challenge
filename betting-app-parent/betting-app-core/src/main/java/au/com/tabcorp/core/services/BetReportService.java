package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.*;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is a service class for bet report generation
 */
public class BetReportService {
    private InMemoryBetsDao inMemoryBetsDao;

    public BetReportService() {
        this.inMemoryBetsDao = InMemoryBetsDao.getInstance();
    }

    /**
     * generates the investment per bet type report
     *
     * @return investment per bet type report
     */
    public Report<List<InvestmentPerBetType>> generateInvestmentPerBetTypeReport() {
        List<InvestmentPerBetType> investmentPerBetTypeList = new ArrayList<>();

        /* load all the saved bets from in-memory data store */
        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        /* group the bets by betType and aggregate with investment */
        Map<BetType, Double> investmentPerBetType =
                savedBetList.stream().collect(
                        Collectors.groupingBy(Bet::getBetType,
                                Collectors.summingDouble(Bet::getInvestment)));

        /* generate report */
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

    /**
     * generates the investment per customer report
     *
     * @return investment per customer report
     */
    public Report<List<InvestmentPerCustomer>> generateInvestmentPerCustomerReport() {
        List<InvestmentPerCustomer> investmentPerCustomerList = new ArrayList<>();

        /* load all the saved bets from in-memory data store */
        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        /* group the bets by customerId and aggregate with investment */
        Map<Integer, Double> investmentPerCustomer =
                savedBetList.stream().collect(
                        Collectors.groupingBy(Bet::getCustomerId,
                                Collectors.summingDouble(Bet::getInvestment)));

        /* generate report */
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

    /**
     * generates bets sold per bet type report
     *
     * @return bets sold per bet type report
     */
    public Report<List<BetsSoldPerBetType>> generateBetsSoldPerBetTypeReport() {
        List<BetsSoldPerBetType> betsSoldPerBetTypeList = new ArrayList<>();

        /* load all the saved bets from in-memory data store */
        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        /* group the bets by betType */
        Map<BetType, List<Bet>> betsSoldByBetType =
                savedBetList.stream().collect(Collectors.groupingBy(Bet::getBetType));

        /* generate report */
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

    /**
     * generates the bets sold per hour report
     *
     * @return bets sold per hour report
     */
    public Report<List<BetsSoldPerHour>> generateBetsSoldPerHourReport() {
        List<BetsSoldPerHour> betsSoldPerHourList = new ArrayList<>();

        /* load all the saved bets from in-memory data store */
        List<Bet> savedBetList = inMemoryBetsDao.getAllBets();

        /* group the bets by hourOfTheDay */
        Map<Integer, List<Bet>> betsSoldByHour = savedBetList.stream().
                collect(Collectors.groupingBy(Bet::getHourOfTheDay));

        /* generate report */
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
