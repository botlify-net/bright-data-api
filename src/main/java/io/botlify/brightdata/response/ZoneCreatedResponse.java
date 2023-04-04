package io.botlify.brightdata.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * The response of the zone creation.
 */
@EqualsAndHashCode
@ToString
public final class ZoneCreatedResponse {

    @NotNull @Getter
    private final String password;

    @NotNull @Getter
    private final String ips;

    @NotNull @Getter
    private final String planStart;

    @NotNull @Getter
    private final String planType;

    @NotNull @Getter
    private final String ipsType;

    @NotNull @Getter
    private final String product;

    @NotNull @Getter
    private final String bandwidth;

    @NotNull @Getter
    private final String disable;

    @NotNull @Getter
    private final boolean allocMaxAvailable;

    @NotNull @Getter
    private final boolean noReserve;

    @NotNull @Getter
    private final String user;

    @NotNull @Getter
    private final String reqSource;

    public ZoneCreatedResponse(@NotNull final JSONObject jsonObject) {
        final JSONObject zone;
        final JSONObject plan;
        final JSONObject allocOpt;
        zone = jsonObject.getJSONObject("zone");
        this.password = zone.getJSONArray("password").getString(0);
        this.ips = zone.getString("ips");
        plan = zone.getJSONObject("plan");
        this.planStart = plan.getString("start");
        this.planType = plan.getString("type");
        this.ipsType = plan.getString("ips_type");
        this.product = plan.getString("product");
        this.bandwidth = plan.getString("bandwidth");
        this.disable = plan.getString("disable");
        allocOpt = jsonObject.getJSONObject("alloc_opt");
        this.allocMaxAvailable = allocOpt.getBoolean("alloc_max_available");
        this.noReserve = allocOpt.getBoolean("no_reserve");
        this.user = allocOpt.getString("user");
        this.reqSource = allocOpt.getString("req_source");
    }

}
