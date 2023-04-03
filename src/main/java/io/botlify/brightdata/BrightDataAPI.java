package io.botlify.brightdata;

import io.botlify.brightdata.response.AddIpInZoneResponse;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final Header authorizationHeader;

    /**
     * The HTTP client used to send request to the API.
     */
    private final OkHttpClient client = new OkHttpClient();

    public BrightDataAPI(@NotNull final String apiKey) {
        this.authorizationHeader = new Header("Authorization", "Bearer " + apiKey);
    }

    /**
     * Get the information about the specified zone specified in parameter of the method.
     * @param zoneName The name of the zone to get information about it.
     * @return A {@link ZoneInformation} object.
     * @throws IOException A network error occurred.
     */
    public @NotNull ZoneInformation getZoneInformation(@NotNull final String zoneName) throws IOException {
        log.trace("Get information about zone: {}", zoneName);
        final String url = brightDataHost + "/api/zone?zone=" + zoneName;

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            log.trace("Response body: {}", body);
            return (new ZoneInformation(new JSONObject(body)));
        }
    }

    /**
     * Get the list of IP of the specified zone.
     * @param zoneName The name of the zone to get the list of IP.
     * @return A list of IP.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> getIpOfZone(@NotNull final String zoneName) throws IOException {
        log.trace("Get IP of zone: {}", zoneName);
        final String url = brightDataHost + "/api/zone/ips?zone=" + zoneName;

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            log.trace("Response body: {}", body);
            final JSONArray ips = new JSONObject(body).getJSONArray("ips");

            final List<String> result = new ArrayList<>(ips.length());
            for (int i = 0; i < ips.length(); i++) {
                final JSONObject tmp = ips.getJSONObject(i);
                result.add(tmp.getString("ip"));
            }
            return (result);
        }
    }

    /**
     * Add to the specified zone the list of Ip specified in parameter of the method
     * to the list of IP in whitelist.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4420252781457-Add-IP-to-Zone-whitelist">
     *     More information.
     * </a>
     * @param ip The list of IP to add to the whitelist.
     * @return {@code true} if the IP has been added to the whitelist.
     */
    @Deprecated
    public boolean whitelistIp(@NotNull final String... ip) {
        return (true);
    }

    /**
     * This method will request to add the specified number of IP to the specified zone.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419834466833-Add-Static-Datacenter-ISP-IPs">
     *     More information.
     * </a>
     * @param zoneName The name of the zone to add IP.
     * @param customerId The customer ID, also known as the account ID.
     * @param nbIp The number of IP to add.
     * @return A {@link AddIpInZoneResponse} response object.
     * @throws IOException A network error occurred.
     */
    public @NotNull AddIpInZoneResponse addIpInZone(@NotNull final String zoneName,
                                                    @NotNull final String customerId,
                                                    @Range(from = 0, to = Integer.MAX_VALUE) final int nbIp)
            throws IOException {
        log.trace("Adding {} IP to zone {} for customer {}", nbIp, zoneName, customerId);
        final String url = brightDataHost + "/api/zone/ips";
        final JSONObject body = new JSONObject();
        body.put("zone", zoneName);
        body.put("count", nbIp);
        body.put("customer", customerId);

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .post(okhttp3.RequestBody.create(body.toString(), okhttp3.MediaType.parse("application/json")))
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            log.trace("Response body: {}", responseBody);
            return (new AddIpInZoneResponse(new JSONObject(responseBody)));
        }
    }

    public @NotNull List<String> removeIpInZone(@NotNull final String zoneName,
                                                @NotNull final String... ips)
            throws IOException {
        return (this.removeIpInZone(zoneName, List.of(ips)));
    }

    /**
     * This method will request to remove the specified IP to the specified zone.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419834577041-Remove-Static-Datacenter-ISP-IPs">
     *     More information.
     * </a>
     * @param zoneName The name of the zone to remove IP.
     * @param ips The list of IP to remove.
     * @return A new list of IP removed from the specified zone.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> removeIpInZone(@NotNull final String zoneName,
                                                @NotNull final List<String> ips)
            throws IOException {
        log.trace("Trying to remove IP {} from zone {}...", ips, zoneName);
        final String url = brightDataHost + "/api/zone/ips";
        final JSONObject body = new JSONObject();
        body.put("zone", zoneName);
        body.put("ips", new JSONArray(ips));

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .delete(okhttp3.RequestBody.create(body.toString(), okhttp3.MediaType.parse("application/json")))
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            log.trace("Response body: {}", responseBody);
            final JSONArray ipsJson = new JSONObject(responseBody).getJSONArray("ips");
            return (ipsJson.toList()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
    }

}
