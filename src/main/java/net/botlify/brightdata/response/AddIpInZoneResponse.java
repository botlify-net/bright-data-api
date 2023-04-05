package net.botlify.brightdata.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the response of the addIpInZone method.
 */
@EqualsAndHashCode
@ToString
public class AddIpInZoneResponse {

    /**
     * The list of ip that was already in the zone before the call.
     */
    @NotNull @Getter
    private final List<String> ips = new ArrayList<>();

    /**
     * The list of new IPs added.
     */
    @NotNull @Getter
    private final List<String> newIps = new ArrayList<>();

    /**
     * Constructor of the response from the JSON object.
     * @param jsonObject The JSON object to parse.
     */
    public AddIpInZoneResponse(@NotNull final JSONObject jsonObject) {
        final JSONArray ips = jsonObject.getJSONArray("ips");
        for (int i = 0; i < ips.length(); i++) {
            this.ips.add(ips.getString(i));
        }
        final JSONArray newIps = jsonObject.getJSONArray("new_ips");
        for (int i = 0; i < newIps.length(); i++) {
            this.newIps.add(newIps.getString(i));
        }
    }

}
