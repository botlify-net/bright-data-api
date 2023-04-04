package net.botlify.brightdata.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class AddIpInZoneResponse {

    @NotNull @Getter
    private final List<String> ips = new ArrayList<>();

    @NotNull @Getter
    private final List<String> newIps = new ArrayList<>();

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
