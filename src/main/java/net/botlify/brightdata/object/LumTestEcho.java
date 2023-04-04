package net.botlify.brightdata.object;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * This class parse the response from this
 * <a href="https://lumtest.com/echo.json">link</a>.
 */
public final class LumTestEcho {

    @NotNull @Getter
    private final String ip;

    @NotNull @Getter
    private final String country;

    @Getter
    private final int asnum;

    @NotNull @Getter
    private final String orgName;

    @NotNull @Getter
    private final String city;

    @NotNull @Getter
    private final String region;

    @NotNull @Getter
    private final String regionName;

    @NotNull @Getter
    private final String postalCode;

    @Getter
    private final double latitude;

    @Getter
    private final double longitude;

    @NotNull @Getter
    private final String tz;

    @NotNull @Getter
    private final String lumCity;

    @NotNull @Getter
    private final String lumRegion;

    @NotNull @Getter
    private final String method;

    @NotNull @Getter
    private final String httpVersion;

    @NotNull @Getter
    private final JSONObject headers;

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
