package net.botlify.brightdata.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the zone information object.
 * It contains all the information about a zone.
 */
@EqualsAndHashCode
@ToString
public class ZoneInformation {

    /**
     * The password list for this zone.
     */
    @NotNull @Getter
    private final List<String> password;

    /**
     * The allowed ips list for this zone.
     */
    @NotNull @Getter
    private final List<String> ips;

    /**
     * The plan of this zone.
     */
    @NotNull @Getter
    private final Plan plan;

    /**
     * Constructor of the zone information from the JSON object.
     * @param jsonObject The JSON object to parse.
     */
    public ZoneInformation(@NotNull final JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("password");
        this.password = new ArrayList<>();
        jsonArray.forEach(o -> password.add((String) o));
        this.ips = new ArrayList<>();
        jsonArray = jsonObject.getJSONArray("ips");
        jsonArray.forEach(o -> ips.add((String) o));
        JSONObject plan = jsonObject.getJSONObject("plan");
        this.plan = new Plan(plan);
    }

}
