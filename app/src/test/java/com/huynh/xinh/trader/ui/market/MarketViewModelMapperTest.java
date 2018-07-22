package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.trader.ui.CoinManager;
import com.huynh.xinh.trader.utils.AssetAndQuoteUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.huynh.xinh.trader.utils.AssertBigDecimalWrapperTestUtils.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CoinManager.class})
public class MarketViewModelMapperTest {
    @Mock
    private CoinManager coinManager;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(CoinManager.class);
        when(CoinManager.getInstance()).thenReturn(coinManager);
        when(CoinManager.getInstance().getCoins()).thenReturn(mockCoins());
    }

    private List<Coin> mockCoins() {
        return Arrays.asList(
            mockCoin(1, "btc", "Bitcoin", false),
            mockCoin(1, "usd", "USD", true),
            mockCoin(1, "usdt", "Tether", false),
            mockCoin(1, "btcd", "BitcoinDark", false),
            mockCoin(1, "eth", "Ethereum", false)
        );
    }

    private Coin mockCoin(long id, String symbol, String name, boolean fiat) {
        return new Coin(id, symbol, "", name, fiat);
    }

    @Test
    public void toMarketViewModel() {
        MarketSummary marketSummary = mockMarketSummary();

        MarketViewModel marketViewModel = MarketViewModelMapper.INSTANCE.toMarketViewModel(marketSummary);

        assertEquals("btcusdt", marketViewModel.getPair());
        assertEquals("btc", marketViewModel.getAsset());
        assertEquals("usdt", marketViewModel.getQuote());
        assertEquals(6571.4, marketViewModel.getPrice());
        assertEquals(0.0023489783, marketViewModel.getPercent());
        assertEquals(true, marketViewModel.isFavorite());

    }

    private MarketSummary mockMarketSummary() {
        MarketSummary marketSummary = new MarketSummary();
        marketSummary.setPair("btcusdt");
        marketSummary.setPrice(BigDecimalWrapper.from(6571.4));
        marketSummary.setPercent(BigDecimalWrapper.from(0.0023489783));
        marketSummary.setPeriods(Collections.emptyList());
        marketSummary.setFavorite(true);
        return marketSummary;
    }
}