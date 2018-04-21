package au.com.tabcorp.core.services;

import au.com.tabcorp.core.dao.inmemory.InMemoryBetsDao;
import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.models.BetType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

/**
 * This is the test class to unit test the functionalities of BetStoreService class
 */
public class BesStoreServiceTest {
    @Mock
    private InMemoryBetsDao inMemoryBetsDao;

    @InjectMocks
    private BetStoreService betStoreService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveBets_EmptyList_False() {
        List<Bet> betList = new ArrayList<>();

        when(inMemoryBetsDao.saveBets(anyListOf(Bet.class))).thenReturn(false);
        assertFalse(betStoreService.saveBets(betList));

        verify(inMemoryBetsDao, times(1)).saveBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(inMemoryBetsDao);
    }

    @Test
    public void saveBets_NonEmptyList_True() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));
        when(inMemoryBetsDao.saveBets(anyListOf(Bet.class))).thenReturn(true);
        assertTrue(betStoreService.saveBets(betList));

        verify(inMemoryBetsDao, times(1)).saveBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(inMemoryBetsDao);
    }

    @Test
    public void saveBets_NullList_False() {
        when(inMemoryBetsDao.saveBets(anyListOf(Bet.class))).thenReturn(false);
        assertFalse(betStoreService.saveBets(null));

        verify(inMemoryBetsDao, times(1)).saveBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(inMemoryBetsDao);
    }
}
