package net.botlify.brightdata.object;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * This class parse the response from this
 * <a href="https://lumtest.com/echo.json">link</a>.
 */
public final class LumTestEcho {

    /**
     * The ip found.
     */
    @NotNull @Getter
    private final String ip;

    /**
     * The country of the ip.
     */
    @NotNull @Getter
    private final String country;

    /**
     * The asnum of the ip.
     */
    @Getter
    private final int asnum;

    /**
     * The organisation name of the ip.
     */
    @NotNull @Getter
    private final String orgName;

    /**
     * The city of the ip.
     */
    @NotNull @Getter
    private final String city;

    /**
     * The region of the ip.
     */
    @NotNull @Getter
    private final String region;

    /**
     * The region name of the ip.
     */
    @NotNull @Getter
    private final String regionName;

    /**
     * The postal code of the ip.
     */
    @NotNull @Getter
    private final String postalCode;

    /**
     * The latitude of the ip.
     */
    @Getter
    private final double latitude;

    /**
     * The longitude of the ip.
     */
    @Getter
    private final double longitude;

    /**
     * The timezone of the ip.
     */
    @NotNull @Getter
    private final String tz;

    /**
     * The brightdata city of the ip.
     */
    @NotNull @Getter
    private final String lumCity;

    /**
     * The brightdata region of the ip.
     */
    @NotNull @Getter
    private final String lumRegion;

    /**
     * The method of the request.
     */
    @NotNull @Getter
    private final String method;

    /**
     * The http version of the request.
     */
    @NotNull @Getter
    private final String httpVersion;

    /**
     * The headers of the request.
     */
    @NotNull @Getter
    private final JSONObject headers;

    /**
     * Constructor of the class from a {@link JSONObject} to parse.
     * @param jsonObject The {@link JSONObject} to parse.
     */
    public LumTestEcho(@NotNull final JSONObject jsonObject) {
        final JSONObject asn;
        final JSONObject geo;
        ip = jsonObject.getString("ip");
        country = jsonObject.getString("country");
        asn = jsonObject.getJSONObject("asn");
        asnum = asn.getInt("asnum");
        orgName = asn.getString("org_name");
        geo = jsonObject.getJSONObject("geo");
        city = geo.getString("city");
        region = geo.getString("region");
        regionName = geo.getString("region_name");
        postalCode = geo.getString("postal_code");
        latitude = geo.getDouble("latitude");
        longitude = geo.getDouble("longitude");
        tz = geo.getString("tz");
        lumCity = geo.getString("lum_city");
        lumRegion = geo.getString("lum_region");
        method = jsonObject.getString("method");
        httpVersion = jsonObject.getString("httpVersion");
        headers = jsonObject.getJSONObject("headers");
    }

}
