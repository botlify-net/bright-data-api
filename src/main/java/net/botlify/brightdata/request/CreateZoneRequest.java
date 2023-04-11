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
    @Nullable @Getter
    private IpType ipsType;

    /**
     * The bandwidth type.
     */
    @Nullable @Getter
    private BandwidthType bandwidthType;

    /**
     * The ip allocation preset type.
     */
    @Nullable @Getter
    private IpAllocPresetType ipAllocPresetType;

    /**
     * The number of ip to allocate.
     */
    @Nullable @Getter
    private Integer ipToAllocate;

    /**
     * The country code of the zone ips.
     */
    @Nullable @Getter
    private String country;

    /**
     * The country city of the zone ips.
     */
    @Nullable @Getter
    private String countryCity;

    /**
     * The boolean to use mobile ips.
     */
    @Nullable @Getter
    private Boolean mobile;

    /**
     * The serp boolean.
     */
    @Nullable @Getter
    private Boolean serp;

    /**
     * The city targeting permission boolean.
     */
    @Nullable @Getter
    private Boolean cityTargetingPermission;

    /**
     * The asn targeting permission boolean.
     */
    @Nullable @Getter
    private Boolean enablingAsnTargetingPermission;

    /**
     * The boolean to use vip ips.
     */
    @Nullable @Getter
    private Boolean vip;

    /**
     * The vip type.
     */
    @Nullable @Getter
    private VipsType vipsType;

    /**
     * The number of vip ips to use.
     */
    @Nullable @Getter
    private Integer vips;

    /**
     * The list of domains to whitelist.
     */
    @Nullable @Getter
    private List<String> domainWhitelist;

    /**
     * The vip country code.
     */
    @Nullable @Getter
    private String vipCountry;

    /**
     * The vip country city.
     */
    @Nullable @Getter
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
        if (domainWhitelist != null)
            plan.put("domain_whitelist", domainWhitelist);
        if (vipCountry != null)
            plan.put("vip_country", vipCountry);
        if (vipCountryCity != null)
            plan.put("vip_country_city", vipCountryCity);
        result.put("plan", plan);
        return (result);
    }

    /**
     * Set the country code of the zone ips.
     * @param ipsType The country code of the zone ips.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setIpsType(@Nullable final IpType ipsType) {
        this.ipsType = ipsType;
        return (this);
    }

    /**
     * Set the bandwidth type.
     * @param bandwidthType The bandwidth type.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setBandwidthType(@Nullable final BandwidthType bandwidthType) {
        this.bandwidthType = bandwidthType;
        return (this);
    }

    /**
     * Set the ip allocation preset type.
     * @param ipAllocPresetType The ip allocation preset type.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setIpAllocPresetType(@Nullable final IpAllocPresetType ipAllocPresetType) {
        this.ipAllocPresetType = ipAllocPresetType;
        return (this);
    }

    /**
     * Set the number of ip to allocate.
     * @param ipToAllocate The number of ip to allocate.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setIpToAllocate(@Nullable final Integer ipToAllocate) {
        this.ipToAllocate = ipToAllocate;
        return (this);
    }

    /**
     * Set the country code.
     * @param country The country code.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setCountry(@Nullable final String country) {
        this.country = country;
        return (this);
    }

    /**
     * Set the country city.
     * @param countryCity The country city.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setCountryCity(@Nullable final String countryCity) {
        this.countryCity = countryCity;
        return (this);
    }

    /**
     * Set the mobile boolean.
     * @param mobile The mobile boolean.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setMobile(@Nullable final Boolean mobile) {
        this.mobile = mobile;
        return (this);
    }

    /**
     * Set the serp boolean.
     * @param serp The serp boolean.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setSerp(@Nullable final Boolean serp) {
        this.serp = serp;
        return (this);
    }

    /**
     * Set the city targeting permission boolean.
     * @param cityTargetingPermission The city targeting permission boolean.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setCityTargetingPermission(@Nullable final Boolean cityTargetingPermission) {
        this.cityTargetingPermission = cityTargetingPermission;
        return (this);
    }

    /**
     * Set the asn targeting permission boolean.
     * @param enablingAsnTargetingPermission The asn targeting permission boolean.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setEnablingAsnTargetingPermission(@Nullable final Boolean enablingAsnTargetingPermission) {
        this.enablingAsnTargetingPermission = enablingAsnTargetingPermission;
        return (this);
    }

    /**
     * Set the domain whitelist.
     * @param vip The boolean to use vip ips.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setVip(@Nullable final Boolean vip) {
        this.vip = vip;
        return (this);
    }

    /**
     * Set the vip type.
     * @param vipsType The vip type.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setVipsType(@Nullable final VipsType vipsType) {
        this.vipsType = vipsType;
        return (this);
    }

    /**
     * Set the number of vip ips to use.
     * @param vips The number of vip ips to use.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setVips(@Nullable final Integer vips) {
        this.vips = vips;
        return (this);
    }

    /**
     * Set the domain whitelist.
     * @param domainWhitelist The domain whitelist.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setDomainWhitelist(@Nullable final List<String> domainWhitelist) {
        this.domainWhitelist = domainWhitelist;
        return (this);
    }

    /**
     * Set the vip country.
     * @param vipCountry The vip country.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setVipCountry(@Nullable final String vipCountry) {
        this.vipCountry = vipCountry;
        return (this);
    }

    /**
     * Set the vip country city.
     * @param vipCountryCity The vip country city.
     * @return This request.
     */
    public @NotNull CreateZoneRequest setVipCountryCity(@Nullable final String vipCountryCity) {
        this.vipCountryCity = vipCountryCity;
        return (this);
    }

}
