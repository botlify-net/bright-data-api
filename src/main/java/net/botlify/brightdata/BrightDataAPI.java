package net.botlify.brightdata;

import com.google.common.util.concurrent.RateLimiter;
import lombok.SneakyThrows;
import net.botlify.brightdata.exception.BrightDataException;
import net.botlify.brightdata.object.LumTestEcho;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

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
     * The host of the super proxy of BrightData.
     */
    @Getter @NotNull
    private static final String brightDataSuperProxyHost = "zproxy.lum-superproxy.io";

    /**
     * The port of the super proxy of BrightData.
     */
    @Getter
    private static final int brightDataSuperProxyPort = 22225;

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

    /**
     * The rate limiter used to limit the number of request per second.
     * The limit is 1.5 request per second, because the API is limited to 1 request
     * per second, but we want to be sure that we don't exceed the limit.
     */
    @NotNull
    private final RateLimiter rateLimiter = RateLimiter.create(1.5);

    /**
     * Construct a new API with the given API key.
     * @param apiKey The BrightData API key.
     */
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
     * This method test if the proxy is valid.
     * It will call this <a href="https://lumtest.com/echo.json">link</a>
     * and check if the response code is 200. If an error occurs like network or anything else,
     * it will return {@code false}. This method will not set username and password
     * for proxy authentication.
     * @param host The host of the proxy.
     * @param port The port of the proxy.
     * @return {@code true} if the proxy is valid, {@code false} otherwise.
     */
    public boolean testProxyValidity(@NotNull final String host,
                                     final int port) {
        return (testProxyValidity(host, port, null, null));
    }

    /**
     * This method test if the proxy is valid.
     * It will call this <a href="https://lumtest.com/echo.json">link</a>
     * and check if the response code is 200. If an error occurs like network or anything else,
     * it will return {@code false}. The proxy will be the super proxy of BrightData host
     * and port.
     * @param username The username of the proxy.
     * @param password The password of the proxy.
     * @return {@code true} if the proxy is valid, {@code false} otherwise.
     */
    public boolean testProxyValidity(@NotNull final String username,
                                     @NotNull final String password) {
        return (testProxyValidity(brightDataSuperProxyHost, brightDataSuperProxyPort,
                username, password));
    }

    /**
     * This method test if the proxy is valid.
     * It will call this <a href="https://lumtest.com/echo.json">link</a>
     * and check if the response code is 200. If an error occurs like network or anything else,
     * it will return {@code false}.
     * @param host The host of the proxy.
     * @param port The port of the proxy.
     * @param username The username of the proxy.
     * @param password The password of the proxy.
     * @return {@code true} if the proxy is valid, {@code false} otherwise.
     */
    public boolean testProxyValidity(@NotNull final String host,
                                     final int port,
                                     @Nullable final String username,
                                     @Nullable final String password) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
        if (username != null && password != null)
            builder.proxyAuthenticator((route, response) -> {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();
            });
        final OkHttpClient client = builder.build();

        final Request request = new Request.Builder()
                .get()
                .url("https://lumtest.com/echo.json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            final int code = response.code();
            if (code == 407) {
                log.trace("Fail to test the proxy validity because of a proxy authentication error.");
                return (false);
            }
            final String body = response.body().string();
            assertResponse(code, body);
            return (response.code() == 200);
        } catch (Exception e) {
            log.trace("Fail to test the proxy validity because of an error: {}",
                    e.getMessage());
            return (false);
        }
    }

    // Sub API

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
     * @throws IOException If an error occurs while sending the request.
     */
    public @NotNull LumTestEcho getMyIpInformation() throws IOException {
        log.trace("Get information about my IP...");
        final String url = "https://lumtest.com/echo.json";

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            final int code = response.code();
            assertResponse(code, body);
            return (new LumTestEcho(new JSONObject(body)));
        }
    }

    /**
     * This method will check if the response code is 200
     * and if the body is not empty.
     * @param code The response code.
     * @param body The response body.
     */
    @SneakyThrows
    void assertResponse(final int code, @NotNull final String body) {
        log.trace("Response body: {}", body);
        log.trace("Response code: {}", code);
        // Check if the code is between 200 and 299.
        if (code < 200 || code > 299)
            throw (new BrightDataException(code, body));
    }

}
