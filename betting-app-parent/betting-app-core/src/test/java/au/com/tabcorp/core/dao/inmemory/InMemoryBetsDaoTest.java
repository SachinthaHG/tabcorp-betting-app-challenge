package au.com.tabcorp.core.dao.inmemory;

import au.com.tabcorp.core.memory.BetStore;
import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.models.BetType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

/**
 * This is the test class to unit test the functionalities of InMemoryBetDao class
 */
public class InMemoryBetsDaoTest {
    @Mock
    private BetStore betStore;

    @InjectMocks
    private InMemoryBetsDao inMemoryBetsDao;

    @Before
    public void setUp() throws Exception {
        this.inMemoryBetsDao = InMemoryBetsDao.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveBets_EmptyList_False() {
        List<Bet> betList = new ArrayList<>();

        when(betStore.addBets(anyListOf(Bet.class))).thenReturn(false);
        assertFalse(inMemoryBetsDao.saveBets(betList));

        verify(betStore, times(1)).addBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(betStore);
    }

    @Test
    public void saveBets_NonEmptyList_True() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        when(betStore.addBets(anyListOf(Bet.class))).thenReturn(true);
        assertTrue(inMemoryBetsDao.saveBets(betList));

        verify(betStore, times(1)).addBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(betStore);
    }

    @Test
    public void saveBets_NullList_False() {
        when(betStore.addBets(anyListOf(Bet.class))).thenReturn(false);
        assertFalse(inMemoryBetsDao.saveBets(null));

        verify(betStore, times(1)).addBets(anyListOf(Bet.class));
        verifyNoMoreInteractions(betStore);
    }

    @Test
    public void getAllBets_EmptyList_EmptyList() {
        List<Bet> betList = new ArrayList<>();

        when(betStore.loadAllBets()).thenReturn(betList);
        assertEquals(inMemoryBetsDao.getAllBets().size(), 0);

        verify(betStore, times(1)).loadAllBets();
        verifyNoMoreInteractions(betStore);
    }

    @Test
    public void getAllBets_NonEmptyList_ListOfBets() {
        List<Bet> betList = new ArrayList<>();
        betList.add(new Bet("2018-01-01 12:56", BetType.WIN, 103333, 1081, 500.50));
        betList.add(new Bet("2018-01-01 14:56", BetType.TRIFECTA, 104567, 1080, 100.00));

        when(betStore.loadAllBets()).thenReturn(betList);
        assertThat(inMemoryBetsDao.getAllBets(), is(betList));

        verify(betStore, times(1)).loadAllBets();
        verifyNoMoreInteractions(betStore);
    }
}
