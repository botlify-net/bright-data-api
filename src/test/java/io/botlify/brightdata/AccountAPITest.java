package io.botlify.brightdata;

import io.botlify.brightdata.response.account.BalanceResponse;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccountAPITest {

    @NotNull
    public final String brightDataApiKey = System.getenv("BRIGHT_DATA_API_KEY");

    @Test
    void getBalance() throws IOException {
        final AccountAPI accountAPI = new BrightDataAPI(brightDataApiKey).getAccountAPI();
        final BalanceResponse balanceResponse = accountAPI.getBalance();
        assertNotNull(balanceResponse);
    }
}