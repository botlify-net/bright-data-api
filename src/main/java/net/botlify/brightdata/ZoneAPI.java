package net.botlify.brightdata;

import net.botlify.brightdata.object.LumTestEcho;
import net.botlify.brightdata.object.ZoneInformation;
import net.botlify.brightdata.request.CreateZoneRequest;
import net.botlify.brightdata.response.AddIpInZoneResponse;
import net.botlify.brightdata.response.ZoneCreatedResponse;
import lombok.extern.log4j.Log4j2;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This api help you to manage your zones on BrightData.
 */
@Log4j2
public class ZoneAPI extends SubAPI {

    /**
     * Constructor of the ZoneAPI.
     * @param brightDataAPI The BrightDataAPI instance.
     */
    ZoneAPI(@NotNull final BrightDataAPI brightDataAPI) {
        super(brightDataAPI);
    }

    // Information about zone.

    /**
     * Get the information about the specified zone specified in parameter of the method.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419703061137-Get-Zone-info">
     *     Link to the documentation
     * </a>.
     * @param zoneName The name of the zone to get information about it.
     * @return A {@link ZoneInformation} object.
     * @throws IOException A network error occurred.
     */
    public @NotNull ZoneInformation getZoneInformation(@NotNull final String zoneName) throws IOException {
        log.trace("Get information about zone: {}", zoneName);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone?zone=" + zoneName;

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            return (new ZoneInformation(new JSONObject(body)));
        }
    }

    /**
     * This method will return {@code true} if the zone specified in parameter exist.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419703061137-Get-Zone-info">
     *     Link to the documentation
     * </a>.
     * @param zoneName The name of the zone to check if it exists.
     * @return {@code true} if the zone exist, {@code false} otherwise.
     * @throws IOException A network error occurred.
     */
    public boolean isZoneExist(@NotNull final String zoneName) throws IOException {
        log.trace("Get information about zone to known if zone exist: {}", zoneName);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone?zone=" + zoneName;

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (Response response = client.newCall(request).execute()) {
            return (response.code() == 200);
        }
    }

    /**
     * Create a new zone.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419707464209-Add-a-Zone">
     *     Link to the documentation
     * </a>.
     * @param createZoneRequest The request to create a new zone.
     * @return The information about the zone created.
     * @throws IOException A network error occurred.
     */
    public @NotNull ZoneCreatedResponse createZone(@NotNull final CreateZoneRequest createZoneRequest) throws IOException {
        log.trace("Create zone: {}", createZoneRequest);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone";
        final JSONObject bodyRequest = createZoneRequest.toJSONObject();
        log.trace("Body request: {}", bodyRequest);

        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(bodyRequest.toString(),
                        okhttp3.MediaType.parse("application/json")))
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();
        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            return (new ZoneCreatedResponse(new JSONObject(body)));
        }
    }

    /**
     * Delete the specified zone.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419833220497-Remove-Zone">
     *     Link to the documentation
     * </a>
     * @param zone The name of the zone to delete.
     * @return {@code true} if the zone has been deleted, {@code false} otherwise.
     * @throws IOException A network error occurred.
     */
    public boolean deleteZone(@NotNull final String zone) throws IOException {
        log.trace("Delete zone: {}", zone);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone";
        final JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("zone", zone);

        final Request request = new Request.Builder()
                .url(url)
                .delete(RequestBody.create(bodyRequest.toString(),
                        okhttp3.MediaType.parse("application/json")))
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();
        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            return (response.code() == 200);
        }
    }

    // Enable / Disable zone

    /**
     * Check if a zone is enabled.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419841373585-Get-Zone-status">
     *     Link to the documentation
     * </a>.
     * @param zone The name of the zone to check if it is enabled.
     * @return {@code true} if the zone is enabled, {@code false} otherwise.
     * @throws IOException A network error occurred.
     */
    public boolean isZoneEnabled(@NotNull final String zone) throws IOException {
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/status?zone=" + zone;
        log.trace("URL: {}", url);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();
        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            final String responseString = new JSONObject(body).getString("status");
            return (responseString.equals("active"));
        }
    }

    /**
     * Enable or disable a zone.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419841373585-Get-Zone-status">
     *     Link to the documentation
     * </a>.
     * @param name The name of the zone to enable or disable.
     * @param newValue {@code true} to enable the zone, {@code false} to disable it.
     * @return {@code true} if the zone has been enabled or disabled, {@code false} otherwise.
     * @throws IOException A network error occurred.
     */
    public boolean setZoneEnabled(@NotNull final String name,
                                  final boolean newValue) throws IOException {
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/change_disable";
        final JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("zone", name);
        bodyRequest.put("disable", (newValue ? 1 : 0));

        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(bodyRequest.toString(),
                        okhttp3.MediaType.parse("application/json")))
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();
        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            return (true);
        }
    }

    // Password

    /**
     * Retrieve the passwords of the specified zone.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419813586577-Get-Zone-passwords">
     *     Link to the documentation
     * </a>.
     * @param zone The name of the zone to get the passwords.
     * @return The list of passwords of the zone given in parameter.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> getZonePassword(@NotNull final String zone) throws IOException {
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/passwords?zone=" + zone;
        log.trace("URL: {}", url);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();
        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);

            final JSONArray passwords = new JSONObject(body).getJSONArray("passwords");
            final List<String> passwordsList = new ArrayList<>();
            for (int i = 0; i < passwords.length(); i++)
                passwordsList.add(passwords.getString(i));
            return (passwordsList);
        }
    }

    // Whitelist

    /**
     * Get the list of IP in whitelist of the specified zone.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4420106481041-Get-a-list-of-whitelisted-IPs-for-a-specific-Zone">
     *   Link to the documentation
     * </a>.
     * @param zone The name of the zone to get the list of IP in whitelist.
     * @return The list of IP in whitelist in the zone given in parameter.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> getWhitelistedIp(@NotNull final String zone) throws IOException {
        log.trace("Get whitelisted IP in zone: {}", zone);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/whitelist?zones=" + zone;
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
            if (!jsonResponse.has(zone)) return new ArrayList<>(0);
            final JSONArray jsonIpList = jsonResponse.getJSONArray(zone);
            final List<String> ipList = new ArrayList<>(jsonIpList.length());
            for (int i = 0; i < jsonIpList.length(); i++)
                ipList.add(jsonIpList.getString(i));
            return (ipList);
        }
    }

    /**
     * Return the list of IP in whitelist of all zones.
     * <a href="https://help.brightdata.com/hc/en-us/articles/4420106481041-Get-a-list-of-whitelisted-IPs-for-a-specific-Zone">
     *   Link to the documentation
     * </a>.
     * @return A map with the name of the zone as key and the list of IP in whitelist as value.
     * @throws IOException A network error occurred.
     */
    public @NotNull Map<String, List<String>> getWhitelistedInAllZones() throws IOException {
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/whitelist?zones=*";
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
            final Map<String, List<String>> mapResponse = jsonResponse.keySet().stream()
                    .collect(Collectors.toMap(
                            key -> key,
                            key -> {
                                final JSONArray jsonIpList = jsonResponse.getJSONArray(key);
                                final List<String> ipList = new ArrayList<>(jsonIpList.length());
                                for (int i = 0; i < jsonIpList.length(); i++)
                                    ipList.add(jsonIpList.getString(i));
                                return (ipList);
                            }
                    ));
            log.trace("Map response: {}", mapResponse);
            return (mapResponse);
        }
    }

    /**
     * Add to the specified zone the list of Ip specified in parameter of the method
     * to the list of IP in whitelist.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4420252781457-Add-IP-to-Zone-whitelist">
     *     Link to the documentation
     * </a>.
     * @param zone The name of the zone to add the IP.
     * @param ip The list of IP to add to the whitelist.
     * @return {@code true} if the IP has been added to the whitelist.
     * @throws IOException A network error occurred.
     */
    public boolean whitelistIp(@NotNull final String zone,
                               @NotNull final String... ip) throws IOException {
        final List<String> ips = Arrays.asList(ip);
        log.trace("Whitelist IP in zone {}: {}", zone, ips);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/whitelist";

        final JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("zone", zone.toLowerCase());
        bodyRequest.put("ip", new JSONArray(Arrays.asList(ip)));
        log.trace("Body request: {}", bodyRequest.toString());

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .post(RequestBody.create(bodyRequest.toString(),
                        okhttp3.MediaType.parse("application/json")))
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);
            return (response.code() >= 200 && response.code() < 300);
        }
    }

    /**
     * Whitelist the IP of this client to the specified zone.
     * @param zone The name of the zone to whitelist the IP.
     * @return {@code true} if the IP has been added to the whitelist.
     * @throws IOException A network error occurred.
     */
    public boolean whitelistMyIp(@NotNull final String zone) throws IOException {
        final LumTestEcho lumTestEcho = brightDataAPI.getMyIpInformation();
        return (this.whitelistIp(zone, lumTestEcho.getIp()));
    }

    /**
     * This method will whitelist all the IP to the specified zone.<br />
     * The whitelist will be disabled if you call this method.
     * @param zone The name of the zone to whitelist all the IP.
     * @return {@code true} if the IP has been added to the whitelist.
     * @throws IOException A network error occurred.
     */
    public boolean whitelistAnyIP(@NotNull final String zone) throws IOException {
        return (this.whitelistIp(zone, "any"));
    }

    // Manage allocated IP.

    /**
     * Get the list of IP of the specified zone.
     * @param zoneName The name of the zone to get the list of IP.
     * @return A list of IP.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> getIpOfZone(@NotNull final String zoneName) throws IOException {
        log.trace("Get IP of zone: {}", zoneName);
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/ips?zone=" + zoneName.toLowerCase();

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(authorizationHeader.name.utf8(), authorizationHeader.value.utf8())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final String body = response.body().string();
            brightDataAPI.assertResponse(response.code(), body);

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
     * This method will request to add the specified number of IP to the specified zone.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419834466833-Add-Static-Datacenter-ISP-IPs">
     *     Link to the documentation
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
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/ips";
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
            brightDataAPI.assertResponse(response.code(), responseBody);
            return (new AddIpInZoneResponse(new JSONObject(responseBody)));
        }
    }

    /**
     * This method will request to remove the specified IP to the specified zone.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419834577041-Remove-Static-Datacenter-ISP-IPs">
     *     Link to the documentation
     * </a>
     * @param zoneName The name of the zone to remove IP.
     * @param ips The list of IP to remove.
     * @return A new list of IP removed from the specified zone.
     * @throws IOException A network error occurred.
     */
    public @NotNull List<String> removeIpInZone(@NotNull final String zoneName,
                                                @NotNull final String... ips)
            throws IOException {
        final List<String> ipsList = Arrays.asList(ips);
        return (this.removeIpInZone(zoneName, ipsList));
    }

    /**
     * This method will request to remove the specified IP to the specified zone.<br />
     * <a href="https://help.brightdata.com/hc/en-us/articles/4419834577041-Remove-Static-Datacenter-ISP-IPs">
     *     Link to the documentation
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
        final String url = BrightDataAPI.getBrightDataHost() + "/api/zone/ips";
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
            brightDataAPI.assertResponse(response.code(), responseBody);

            final JSONArray ipsJson = new JSONObject(responseBody).getJSONArray("ips");
            return (ipsJson.toList()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
    }

}
