package io.botlify.brightdata;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.internal.http2.Header;
import org.jetbrains.annotations.NotNull;

public abstract class SubAPI {

    /**
     * The BrightDataAPI instance.
     */
    @NotNull @Getter
    protected final BrightDataAPI brightDataAPI;

    /**
     * The authorization header used to authenticate the user.
     */
    @NotNull
    protected final Header authorizationHeader;

    /**
     * The HTTP client used to send request to the API.
     */
    @NotNull
    protected final OkHttpClient client;

    /**
     * Constructor of the ZoneAPI.
     * @param brightDataAPI The BrightDataAPI instance.
     */
    SubAPI(@NotNull final BrightDataAPI brightDataAPI) {
        this.brightDataAPI = brightDataAPI;
        this.authorizationHeader = brightDataAPI.getAuthorizationHeader();
        this.client = brightDataAPI.getClient();
    }

}
