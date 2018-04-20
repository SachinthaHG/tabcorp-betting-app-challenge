package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


import static org.mockito.Mockito.when;

public class BetReportServiceTest {
    @Mock
    private InMemoryBetsDao inMemoryBetsDao;

    @InjectMocks
    private BetReportService betReportService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateInvestmentPerBetTypeReport_SavedBetsExist_NonEmptyReport() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103233, 1082, 150.00));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        List<InvestmentPerBetType> expectedList = new ArrayList<>();
        expectedList.add(new InvestmentPerBetType(BetType.WIN, 650.50));
        expectedList.add(new InvestmentPerBetType(BetType.TRIFECTA, 100.00));

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);
        List<InvestmentPerBetType> actualList = betReportService.generateInvestmentPerBetTypeReport().getReports();

        expectedList.sort(Comparator.comparing(InvestmentPerBetType::getBetType));
        actualList.sort(Comparator.comparing(InvestmentPerBetType::getBetType));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }

    @Test
    public void generateInvestmentPerBetTypeReport_NoSavedBets_EmptyReport() {
        List<Bet> betList = new ArrayList<>();
        List<InvestmentPerBetType> expectedList = new ArrayList<>();

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<InvestmentPerBetType> actualList = betReportService.generateInvestmentPerBetTypeReport().getReports();

        expectedList.sort(Comparator.comparing(InvestmentPerBetType::getBetType));
        actualList.sort(Comparator.comparing(InvestmentPerBetType::getBetType));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }

    @Test
    public void generateInvestmentPerCustomerReport_SavedBetsExist_NonEmptyReport() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103233, 1081, 150.00));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        List<InvestmentPerCustomer> expectedList = new ArrayList<>();
        expectedList.add(new InvestmentPerCustomer(1081, 650.50));
        expectedList.add(new InvestmentPerCustomer(1080, 100.00));

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<InvestmentPerCustomer> actualList = betReportService.generateInvestmentPerCustomerReport().getReports();

        expectedList.sort(Comparator.comparing(InvestmentPerCustomer::getCustomerId));
        actualList.sort(Comparator.comparing(InvestmentPerCustomer::getCustomerId));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }

    }

    @Test
    public void generateInvestmentPerCustomerReport_NoSavedBets_EmptyReport() {
        List<Bet> betList = new ArrayList<>();
        List<InvestmentPerCustomer> expectedList = new ArrayList<>();

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<InvestmentPerCustomer> actualList = betReportService.generateInvestmentPerCustomerReport().getReports();

        expectedList.sort(Comparator.comparing(InvestmentPerCustomer::getCustomerId));
        actualList.sort(Comparator.comparing(InvestmentPerCustomer::getCustomerId));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }

    }

    @Test
    public void generateBetsSoldPerBetTypeReport_SavedBetsExist_NonEmptyReport() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103233, 1081, 150.00));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        List<BetsSoldPerBetType> expectedList = new ArrayList<>();
        expectedList.add(new BetsSoldPerBetType(BetType.WIN, 2));
        expectedList.add(new BetsSoldPerBetType(BetType.TRIFECTA, 1));

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<BetsSoldPerBetType> actualList = betReportService.generateBetsSoldPerBetTypeReport().getReports();

        expectedList.sort(Comparator.comparing(BetsSoldPerBetType::getBetType));
        actualList.sort(Comparator.comparing(BetsSoldPerBetType::getBetType));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }

    @Test
    public void generateBetsSoldPerBetTypeReport_NoSavedBets_EmptyReport() {
        List<Bet> betList = new ArrayList<>();
        List<BetsSoldPerBetType> expectedList = new ArrayList<>();

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<BetsSoldPerBetType> actualList = betReportService.generateBetsSoldPerBetTypeReport().getReports();

        expectedList.sort(Comparator.comparing(BetsSoldPerBetType::getBetType));
        actualList.sort(Comparator.comparing(BetsSoldPerBetType::getBetType));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }

    @Test
    public void generateBetsSoldPerHourReport_SavedBetsExist_NonEmptyReport() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103233, 1081, 150.00));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        List<BetsSoldPerHour> expectedList = new ArrayList<>();
        expectedList.add(new BetsSoldPerHour(12, 2));
        expectedList.add(new BetsSoldPerHour(14, 1));

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<BetsSoldPerHour> actualList = betReportService.generateBetsSoldPerHourReport().getReports();

        expectedList.sort(Comparator.comparing(BetsSoldPerHour::getHourOfTheDay));
        actualList.sort(Comparator.comparing(BetsSoldPerHour::getHourOfTheDay));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }

    @Test
    public void generateBetsSoldPerHourReport_NoSavedBets_EmptyReport() {
        List<Bet> betList = new ArrayList<>();
        List<BetsSoldPerHour> expectedList = new ArrayList<>();

        when(inMemoryBetsDao.getAllBets()).thenReturn(betList);

        List<BetsSoldPerHour> actualList = betReportService.generateBetsSoldPerHourReport().getReports();

        expectedList.sort(Comparator.comparing(BetsSoldPerHour::getHourOfTheDay));
        actualList.sort(Comparator.comparing(BetsSoldPerHour::getHourOfTheDay));

        assertEquals(expectedList.size(), actualList.size());

        for (int i = 0; i < actualList.size(); i++) {
            assertThat(expectedList.get(i), new ReflectionEquals(actualList.get(i)));
        }
    }
}
