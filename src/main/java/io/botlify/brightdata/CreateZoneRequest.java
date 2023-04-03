package io.botlify.brightdata;

import io.botlify.brightdata.enums.*;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

@Builder
public class CreateZoneRequest {

    String name;

    PlanType type;

    IpType ipsType;

    BandwidthType bandwidthType;

    IpAllocPresetType ipAllocPresetType;

    Integer ipToAllocate;

    String country;

    String countryCity;

    Boolean mobile;

    Boolean serp;

    Boolean cityTargetingPermission;

    Boolean enablingAsnTargetingPermission;

    Boolean vip;

    VipsType vipsType;

    Integer vips;

    List<String> domainWitelist;

    String vipCountry;

    String vipCountryCity;

    public @NotNull JSONObject toJSONObject() {
        JSONObject plan = new JSONObject();
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
        if (vips != null)
            plan.put("vips_type", vipsType.toString());
        if (vips != null)
            plan.put("vips", vips);
        if (domainWitelist != null)
            plan.put("domain_whitelist", domainWitelist);
        if (vipCountry != null)
            plan.put("vip_country", vipCountry);
        if (vipCountryCity != null)
            plan.put("vip_country_city", vipCountryCity);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("plan", plan);
        return (jsonObject);

    }

}
