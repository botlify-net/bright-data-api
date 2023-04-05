package net.botlify.brightdata;

import net.botlify.brightdata.response.account.BalanceResponse;
import lombok.extern.log4j.Log4j2;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This sub api is used to manage the account on BrightData.
 */
@Log4j2
public class AccountAPI extends SubAPI {

    /**
     * Constructor of the ZoneAPI.
     * @param brightDataAPI The BrightDataAPI instance.
     */
    AccountAPI(@NotNull final BrightDataAPI brightDataAPI) {
        super(brightDataAPI);
    }

    /**
     * Get the balance of the account.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419694483473-Get-total-balance-through-API">
     *     Link to the documentation
     * </a>.
     * @return The {@link BalanceResponse} object response.
     * @throws IOException If an error occurred during the request.
     */
    public @NotNull BalanceResponse getBalance() throws IOException {
        final String url = BrightDataAPI.getBrightDataHost() + "/api/customer/balance";
        log.trace("URL: {}", url);

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            final JSONObject jsonResponse = new JSONObject(body);
            return (new BalanceResponse(jsonResponse));
        }
    }

}
