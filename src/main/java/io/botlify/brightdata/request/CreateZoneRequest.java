package io.botlify.brightdata.request;

import io.botlify.brightdata.enums.*;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.List;

@Builder
public class CreateZoneRequest {

    @Nullable @Getter
    private final String name;

    @Nullable @Getter
    private final PlanType type;

    @Nullable @Getter
    private final IpType ipsType;

    @Nullable @Getter
    private final BandwidthType bandwidthType;

    @Nullable @Getter
    private final IpAllocPresetType ipAllocPresetType;

    @Nullable @Getter
    private final Integer ipToAllocate;

    @Nullable @Getter
    private final String country;

    @Nullable @Getter
    private final String countryCity;

    @Nullable @Getter
    private final Boolean mobile;

    @Nullable @Getter
    private final Boolean serp;

    @Nullable @Getter
    private final Boolean cityTargetingPermission;

    @Nullable @Getter
    private final Boolean enablingAsnTargetingPermission;

    @Nullable @Getter
    private final Boolean vip;

    @Nullable @Getter
    private final VipsType vipsType;

    @Nullable @Getter
    private final Integer vips;

    @Nullable @Getter
    private final List<String> domainWitelist;

    @Nullable @Getter
    private final String vipCountry;

    @Nullable @Getter
    private final String vipCountryCity;

    /**
     * Convert this object to a JSON object for the request.
     * @return The JSON object.
     */
    public @NotNull JSONObject toJSONObject() {
        final JSONObject plan = new JSONObject();
        if (type != null)
            plan.put("type", type.toString());
        if (ipsType != null)
            plan.put("ips_type", ipsType.toString());
        if (bandwidthType != null)
            plan.put("bandwidth", bandwidthType.toString());
        if (ipAllocPresetType != null)
            plan.put("ip_alloc_preset", ipAllocPresetType.toString());
        if (ipToAllocate != null)
            plan.put("ips", ipToAllocate);
        if (country != null)
            plan.put("country", country);
        if (countryCity != null)
            plan.put("country_city", countryCity);
        if (mobile != null)
            plan.put("mobile", mobile);
        if (serp != null)
            plan.put("serp", serp);
        if (cityTargetingPermission != null)
            plan.put("city", cityTargetingPermission);
        if (enablingAsnTargetingPermission != null)
            plan.put("asn", enablingAsnTargetingPermission);
        if (vip != null)
            plan.put("vip", vip);
        if (vipsType != null)
            plan.put("vips_type", vipsType.toString());
        if (vips != null)
            plan.put("vips", vips);
        if (domainWitelist != null)
            plan.put("domain_whitelist", domainWitelist);
        if (vipCountry != null)
            plan.put("vip_country", vipCountry);
        if (vipCountryCity != null)
            plan.put("vip_country_city", vipCountryCity);
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("plan", plan);
        return (jsonObject);

    }

}
