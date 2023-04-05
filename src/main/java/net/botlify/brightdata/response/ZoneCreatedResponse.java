package net.botlify.brightdata.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/**
 * The response of the zone creation.
 */
@EqualsAndHashCode
@ToString
public final class ZoneCreatedResponse {

    /**
     * The password of the zone.
     */
    @NotNull @Getter
    private final String password;

    /**
     * The ips of the zone.
     */
    @NotNull @Getter
    private final String ips;

    /**
     * The plan start date of the zone.
     */
    @NotNull @Getter
    private final String planStart;

    /**
     * The plan type of the zone.
     */
    @NotNull @Getter
    private final String planType;

    /**
     * The ips type of the zone.
     */
    @NotNull @Getter
    private final String ipsType;

    /**
     * The product of the zone.
     */
    @NotNull @Getter
    private final String product;

    /**
     * The bandwidth of the zone.
     */
    @NotNull @Getter
    private final String bandwidth;

    /**
     * The disable of the zone.
     */
    @Nullable @Getter
    private final String disable;

    /**
     * The alloc max available of the zone.
     */
    @Getter
    private final boolean allocMaxAvailable;

    /**
     * The no reserve of the zone.
     */
    @Getter
    private final boolean noReserve;

    /**
     * The user of the zone.
     */
    @NotNull @Getter
    private final String user;

    /**
     * The req source of the zone.
     */
    @NotNull @Getter
    private final String reqSource;

    /**
     * Constructor of the zone created response from the JSON object.
     * @param jsonObject The JSON object to parse.
     */
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
        if (plan.has("disable"))
            this.disable = plan.getString("disable");
        else
            this.disable = null;
        allocOpt = jsonObject.getJSONObject("alloc_opt");
        this.allocMaxAvailable = allocOpt.getBoolean("alloc_max_available");
        this.noReserve = allocOpt.getBoolean("no_reserve");
        this.user = allocOpt.getString("user");
        this.reqSource = allocOpt.getString("req_source");
    }

}
