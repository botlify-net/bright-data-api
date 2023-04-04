package net.botlify.brightdata.object;

import net.botlify.brightdata.enums.IpType;
import net.botlify.brightdata.enums.PlanType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Objects;

@EqualsAndHashCode
@ToString
public class Plan {

    @NotNull @Getter
    private final Instant start;

    @NotNull @Getter
    private final String product;

    @NotNull @Getter
    private final PlanType type;

    @Getter
    private final int ipFallback;

    @NotNull @Getter
    private final IpType ipsType;

    @Getter
    private final int ips;

    @NotNull @Getter
    private final String country;

    @NotNull @Getter
    private final String bandwidth;

    @Getter
    private final int ips_new;

    public Plan(@NotNull final JSONObject jsonObject) {
        this.start = Instant.parse(jsonObject.getString("start"));
        this.product = jsonObject.getString("product");
        this.type = Objects.requireNonNull(PlanType.fromString(jsonObject.getString("type")));
        this.ipFallback = jsonObject.getInt("ip_fallback");
        this.ipsType = Objects.requireNonNull(IpType.fromString(jsonObject.getString("ips_type")));
        this.ips = jsonObject.getInt("ips");
        this.country = jsonObject.getString("country");
        this.bandwidth = jsonObject.getString("bandwidth");
        this.ips_new = jsonObject.getInt("ips_new");
    }

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

    @Deprecated
    public @NotNull JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start.toString());
        jsonObject.put("product", product);
        jsonObject.put("type", type.toString());
        jsonObject.put("ip_fallback", ipFallback);
        jsonObject.put("ips_type", ipsType.toString());
        jsonObject.put("ips", ips);
        jsonObject.put("country", country);
        jsonObject.put("bandwidth", bandwidth);
        jsonObject.put("ips_new", ips_new);
        return jsonObject;
    }

}
