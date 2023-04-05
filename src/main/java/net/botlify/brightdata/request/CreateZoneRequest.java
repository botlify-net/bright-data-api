package net.botlify.brightdata.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.botlify.brightdata.enums.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.List;

/**
 * This class is the request of the createZone method.
 */
public class CreateZoneRequest {

    /**
     * The name of the zone.
     */
    @NotNull @Getter
    private final String name;

    /**
     * The plan of the zone.
     */
    @NotNull @Getter
    private final PlanType type;

    /**
     * The type of ips to use.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private IpType ipsType;

    /**
     * The bandwidth type.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private BandwidthType bandwidthType;

    /**
     * The ip allocation preset type.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private IpAllocPresetType ipAllocPresetType;

    /**
     * The number of ip to allocate.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Integer ipToAllocate;

    /**
     * The country code of the zone ips.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private String country;

    /**
     * The country city of the zone ips.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private String countryCity;

    /**
     * The boolean to use mobile ips.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Boolean mobile;

    /**
     * The serp boolean.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Boolean serp;

    /**
     * The city targeting permission boolean.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Boolean cityTargetingPermission;

    /**
     * The asn targeting permission boolean.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Boolean enablingAsnTargetingPermission;

    /**
     * The boolean to use vip ips.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Boolean vip;

    /**
     * The vip type.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private VipsType vipsType;

    /**
     * The number of vip ips to use.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private Integer vips;

    /**
     * The list of domains to whitelist.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private List<String> domainWitelist;

    /**
     * The vip country code.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private String vipCountry;

    /**
     * The vip country city.
     */
    @Nullable @Getter @Setter(AccessLevel.PUBLIC)
    private String vipCountryCity;

    /**
     * Create a new request.
     * @param name The name of the zone.
     * @param planType The plan type.
     */
    public CreateZoneRequest(@NotNull final String name,
                             @NotNull final PlanType planType) {
        this.name = name;
        this.type = planType;
    }

    /**
     * Convert this object to a JSON object for the request.
     * @return The JSON object.
     */
    public @NotNull JSONObject toJSONObject() {
        final JSONObject result = new JSONObject();
        // Zone object.
        final JSONObject zoneObject = new JSONObject();
        zoneObject.put("name", name);
        result.put("zone", zoneObject);
        // Plan object.
        final JSONObject plan = new JSONObject();
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
        result.put("plan", plan);
        return (result);
    }

}
