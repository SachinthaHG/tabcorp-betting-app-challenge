package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.models.BetType;
import au.com.tabcorp.core.services.BetStoreService;
import au.com.tabcorp.filters.CORSFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is the test class to unit test the functionalities of BetController class
 */
public class BetControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BetStoreService betStoreService;

    @InjectMocks
    private BetController betController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(betController).addFilters(new CORSFilter()).build();
    }

    @Test
    public void saveBets_NonEmptyRequestBody_Created_BetsSaved() throws Exception {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        when(betStoreService.saveBets(anyListOf(Bet.class))).thenReturn(true);
        MvcResult response = mockMvc.perform(
                post("/bets/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(betList)))
                .andExpect(status().isCreated()).andReturn();

        verify(betStoreService, times(1)).saveBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(betStoreService);

        assertEquals(response.getResponse().getContentAsString(), "[\"Bets saved\"]");
    }

    @Test
    public void saveBets_EmptyRequestBody_Conflict_BetsNotSaved() throws Exception {
        List<Bet> betList = new ArrayList<>();

        when(betStoreService.saveBets(anyListOf(Bet.class))).thenReturn(false);
        MvcResult response = mockMvc.perform(
                post("/bets/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(betList)))
                .andExpect(status().isConflict()).andReturn();

        verify(betStoreService, times(1)).saveBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(betStoreService);

        assertEquals(response.getResponse().getContentAsString(), "[\"Best list is null, empty or has duplicate PropNumbers\"]");
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
