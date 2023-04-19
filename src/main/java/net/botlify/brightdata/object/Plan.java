package net.botlify.brightdata.object;

import net.botlify.brightdata.enums.IpType;
import net.botlify.brightdata.enums.PlanType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Objects;

/**
 * The plan object.
 */
@EqualsAndHashCode
@ToString
public class Plan {

    /**
     * The start date of the plan.
     */
    @NotNull @Getter
    private final Instant start;

    /**
     * The product of the plan.
     */
    @NotNull @Getter
    private final String product;

    /**
     * The type of the plan.
     */
    @NotNull @Getter
    private final PlanType type;

    /**
     * The ip fallback number of the plan.
     */
    @Getter @Nullable
    private final Integer ipFallback;

    /**
     * The type of ips of the plan.
     */
    @NotNull @Getter
    private final IpType ipsType;

    /**
     * The number of ips of the plan.
     */
    @Getter
    private final int ips;

    /**
     * The country code of the plan.
     */
    @NotNull @Getter
    private final String country;

    /**
     * The bandwidth of the plan.
     */
    @NotNull @Getter
    private final String bandwidth;

    /**
     * The number of new ips of the plan.
     */
    @Getter @Nullable
    private final Integer ips_new;

    /**
     * Constructor of the plan from the JSON object.
     * @param jsonObject The JSON object to parse.
     */
    public Plan(@NotNull final JSONObject jsonObject) {
        this.start = Instant.parse(jsonObject.getString("start"));
        this.product = jsonObject.getString("product");
        this.type = Objects.requireNonNull(PlanType.fromString(jsonObject.getString("type")));
        this.ipsType = Objects.requireNonNull(IpType.fromString(jsonObject.getString("ips_type")));
        this.ips = jsonObject.getInt("ips");
        this.country = jsonObject.getString("country");
        this.bandwidth = jsonObject.getString("bandwidth");
        // Ip fallback
        if (jsonObject.has("ip_fallback"))
            this.ipFallback = jsonObject.getInt("ip_fallback");
        else
            this.ipFallback = null;
        // Ips new
        if (jsonObject.has("ips_new"))
            this.ips_new = jsonObject.getInt("ips_new");
        else
            this.ips_new = null;
    }

    /**
     * Constructor of the plan.
     * @param start The start date of the plan.
     * @param product The product of the plan.
     * @param type The type of the plan.
     * @param ipFallback The ip fallback number of the plan.
     * @param ipsType The type of ips of the plan.
     * @param ips The number of ips of the plan.
     * @param country The country code of the plan.
     * @param bandwidth The bandwidth of the plan.
     * @param ips_new The number of new ips of the plan.
     */
    public Plan(@NotNull final Instant start,
                @NotNull final String product,
                @NotNull final PlanType type,
                final int ipFallback,
                @NotNull final IpType ipsType,
                final int ips,
                @NotNull final String country,
                @NotNull final String bandwidth,
                final int ips_new) {
        this.start = start;
        this.product = product;
        this.type = type;
        this.ipFallback = ipFallback;
        this.ipsType = ipsType;
        this.ips = ips;
        this.country = country;
        this.bandwidth = bandwidth;
        this.ips_new = ips_new;
    }

    /**
     * Convert the plan to a JSON object.
     * @return The JSON object of the plan.
     */
    @Deprecated
    public @NotNull JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start.toString());
        jsonObject.put("product", product);
        jsonObject.put("type", type.toString());
        jsonObject.put("ip_fallback", (ipFallback == null) ? JSONObject.NULL : ipFallback);
        jsonObject.put("ips_type", ipsType.toString());
        jsonObject.put("ips", ips);
        jsonObject.put("country", country);
        jsonObject.put("bandwidth", bandwidth);
        jsonObject.put("ips_new", (ips_new == null) ? JSONObject.NULL : ips_new);
        return jsonObject;
    }

}
