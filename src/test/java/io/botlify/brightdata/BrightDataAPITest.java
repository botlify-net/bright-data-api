package io.botlify.brightdata;

import io.botlify.brightdata.response.AddIpInZoneResponse;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BrightDataAPITest {

    @NotNull
    public final String brightDataApiKey = System.getenv("BRIGHT_DATA_API_KEY");

    @NotNull
    public final String brightDataZoneName = System.getenv("BRIGHT_DATA_ZONE_NAME");

    @NotNull
    public final String brightDataCustomerId = System.getenv("BRIGHT_DATA_CUSTOMER_ID");

    @Test
    public void getZoneInformation() throws IOException {
        final BrightDataAPI api = new BrightDataAPI(brightDataApiKey);
        final ZoneInformation zoneInformation = api.getZoneInformation(brightDataZoneName);
        assertNotNull(zoneInformation);
        System.out.println("zoneInformation: " + zoneInformation);
    }

    @Test
    public void getIpOfZone() throws IOException {
        final BrightDataAPI api = new BrightDataAPI(brightDataApiKey);
        final List<String> ips = api.getIpOfZone(brightDataZoneName);
        assertNotNull(ips);
        System.out.println("Ip of zone: " + ips);
    }

    @Test
    public void getBrightDataHost() {
        assertNotNull(BrightDataAPI.getBrightDataHost());
    }

    @Test
    public void addIpInZone() throws IOException {
        final int newIps = 1;
        final BrightDataAPI api = new BrightDataAPI(brightDataApiKey);
        final AddIpInZoneResponse addIpInZoneResponse = api.addIpInZone(brightDataZoneName, brightDataCustomerId, newIps);
        assertEquals(newIps, addIpInZoneResponse.getNewIps().size());
        System.out.println("addIpInZoneResponse: " + addIpInZoneResponse);
    }

    @Test
    public void removeIp() throws IOException {
        final BrightDataAPI api = new BrightDataAPI(brightDataApiKey);
        final List<String> ips = api.getIpOfZone(brightDataZoneName);

        final List<String> removed = api.removeIpInZone(brightDataZoneName, ips.get(ips.size() - 1));
        System.out.println("Ip removed: " + removed);
        assertEquals(removed.size(), 1);

    }

}