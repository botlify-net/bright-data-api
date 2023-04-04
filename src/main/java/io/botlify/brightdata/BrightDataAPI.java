package io.botlify.brightdata;

import com.google.common.util.concurrent.RateLimiter;
import io.botlify.brightdata.object.LumTestEcho;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.internal.http2.Header;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class is the main class of the API.
 * It is used to manage the API and the sub API.
 */
@Log4j2
public class BrightDataAPI {

    /**
     * The host of the API.
     */
    @Getter @NotNull
    private static final String brightDataHost = "https://brightdata.com";

    /**
     * The authorization header used to authenticate the user.
     */
    @NotNull @Getter(AccessLevel.PACKAGE)
    private final Header authorizationHeader;

    /**
     * The HTTP client used to send request to the API.
     */
    @NotNull @Getter(AccessLevel.PACKAGE)
    private final OkHttpClient client;

    @NotNull
    private final RateLimiter rateLimiter = RateLimiter.create(1.5);

    public BrightDataAPI(@NotNull final String apiKey) {
        if (apiKey.isEmpty())
            throw (new IllegalArgumentException("The API key cannot be empty."));
        this.authorizationHeader = new Header("Authorization", "Bearer " + apiKey);
        this.client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    rateLimiter.acquire();
                    return chain.proceed(chain.request());
                }).build();
    }

    /**
     * Get the object to manage the zone.
     * @return The {@link ZoneAPI} object.
     */
    public @NotNull ZoneAPI getZoneAPI() {
        return (new ZoneAPI(this));
    }

    /**
     * Get the object to manage the account.
     * @return The {@link AccountAPI} object.
     */
    public @NotNull AccountAPI getAccountAPI() {
        return (new AccountAPI(this));
    }

    /**
     * Get the information about your IP.<br />
     * From the <a href="https://lumtest.com/echo.json">link</a>.
     * @return A {@link LumTestEcho} object with the information about your IP.
     */
    public @NotNull LumTestEcho getMyIpInformation() throws IOException {
        log.trace("Get information about my IP...");
        final String url = "https://lumtest.com/echo.json";

        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();

        try (final okhttp3.Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            log.trace("Response body: {}", body);
            return (new LumTestEcho(new JSONObject(body)));
        }
    }

}
