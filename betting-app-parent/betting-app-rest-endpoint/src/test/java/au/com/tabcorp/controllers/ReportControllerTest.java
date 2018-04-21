package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.*;
import au.com.tabcorp.core.services.BetReportService;
import au.com.tabcorp.filters.CORSFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class to unit test the functionalities of ReportController class
 */
public class ReportControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BetReportService betReportService;

    @InjectMocks
    private ReportController reportController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).addFilters(new CORSFilter()).build();
    }

    @Test
    public void getInvestmentPerBetTypeReport_SavedBetsExist_NonEmptyReport() throws Exception {
        Report<List<InvestmentPerBetType>> report = new Report<>();
        List<InvestmentPerBetType> investmentPerBetTypesList = new ArrayList<>();
        investmentPerBetTypesList.add(new InvestmentPerBetType(BetType.WIN, 650.50));
        investmentPerBetTypesList.add(new InvestmentPerBetType(BetType.TRIFECTA, 100.50));
        report.setReports(investmentPerBetTypesList);

        when(betReportService.generateInvestmentPerBetTypeReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/investment-per-bet-type"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateInvestmentPerBetTypeReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getInvestmentPerBetTypeReport_NoSavedBets_NEmptyReport() throws Exception {
        Report<List<InvestmentPerBetType>> report = new Report<>();

        when(betReportService.generateInvestmentPerBetTypeReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/investment-per-bet-type"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateInvestmentPerBetTypeReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getInvestmentPerCustomerReport_SavedBetsExist_NonEmptyReport() throws Exception {
        Report<List<InvestmentPerCustomer>> report = new Report<>();
        List<InvestmentPerCustomer> investmentPerCustomerList = new ArrayList<>();
        investmentPerCustomerList.add(new InvestmentPerCustomer(1080, 650.50));
        investmentPerCustomerList.add(new InvestmentPerCustomer(1081, 100.50));
        report.setReports(investmentPerCustomerList);

        when(betReportService.generateInvestmentPerCustomerReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/investment-per-customer"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateInvestmentPerCustomerReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getInvestmentPerCustomerReport_NoSavedBets_NEmptyReport() throws Exception {
        Report<List<InvestmentPerCustomer>> report = new Report<>();

        when(betReportService.generateInvestmentPerCustomerReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/investment-per-customer"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateInvestmentPerCustomerReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getBetsSoldPerBetType_SavedBetsExist_NonEmptyReport() throws Exception {
        Report<List<BetsSoldPerBetType>> report = new Report<>();
        List<BetsSoldPerBetType> betsSoldPerBetTypeList = new ArrayList<>();
        betsSoldPerBetTypeList.add(new BetsSoldPerBetType(BetType.WIN, 2));
        betsSoldPerBetTypeList.add(new BetsSoldPerBetType(BetType.TRIFECTA, 1));
        report.setReports(betsSoldPerBetTypeList);

        when(betReportService.generateBetsSoldPerBetTypeReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/bets-sold-per-bet-type"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateBetsSoldPerBetTypeReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getBetsSoldPerBetType_NoSavedBets_NEmptyReport() throws Exception {
        Report<List<BetsSoldPerBetType>> report = new Report<>();

        when(betReportService.generateBetsSoldPerBetTypeReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/bets-sold-per-bet-type"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateBetsSoldPerBetTypeReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getBetsSoldPerHour_SavedBetsExist_NonEmptyReport() throws Exception {
        Report<List<BetsSoldPerHour>> report = new Report<>();
        List<BetsSoldPerHour> betsSoldPerHourList = new ArrayList<>();
        betsSoldPerHourList.add(new BetsSoldPerHour(12, 2));
        betsSoldPerHourList.add(new BetsSoldPerHour(14, 1));
        report.setReports(betsSoldPerHourList);

        when(betReportService.generateBetsSoldPerHourReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/bets-sold-per-hour"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateBetsSoldPerHourReport();
        verifyNoMoreInteractions(betReportService);
    }

    @Test
    public void getBetsSoldPerHour_NoSavedBets_NEmptyReport() throws Exception {
        Report<List<BetsSoldPerHour>> report = new Report<>();

        when(betReportService.generateBetsSoldPerHourReport()).thenReturn(report);
        MvcResult response = mockMvc.perform(get("/reports/bets-sold-per-hour"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(convertToJsonString(report), response.getResponse().getContentAsString());

        verify(betReportService, times(1)).generateBetsSoldPerHourReport();
        verifyNoMoreInteractions(betReportService);
    }

    private String convertToJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
