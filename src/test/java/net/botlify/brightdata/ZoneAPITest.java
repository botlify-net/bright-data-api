package net.botlify.brightdata;

import net.botlify.brightdata.enums.PlanType;
import net.botlify.brightdata.object.ZoneInformation;
import net.botlify.brightdata.request.CreateZoneRequest;
import net.botlify.brightdata.response.AddIpInZoneResponse;
import net.botlify.brightdata.response.ZoneCreatedResponse;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ZoneAPITest {

    @NotNull
    public final String brightDataApiKey = System.getenv("BRIGHT_DATA_API_KEY");

    @NotNull
    public final String brightDataZoneName = System.getenv("BRIGHT_DATA_ZONE_NAME");

    @NotNull
    public final String brightDataCustomerId = System.getenv("BRIGHT_DATA_CUSTOMER_ID");

    // Create zone.

    /**
     * This test will try to create a zone and delete it.
     */
    @Test
    public void createZoneAndDeleteIt() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();

        // random alpha string

        final String zoneName = randomZoneName();

        // Create the zone.
        final CreateZoneRequest czr = CreateZoneRequest.builder()
                .withName(zoneName)
                .withType(PlanType.STATIC)
                .build();
        final ZoneCreatedResponse zoneInformation = api.createZone(czr);
        System.out.println("zoneInformation: " + zoneInformation);
        assertNotNull(zoneInformation);
        // Delete the zone.
        assertTrue(api.deleteZone(zoneName));
    }

    // Zone information.

    @Test
    public void getZoneInformation() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final ZoneInformation zoneInformation = api.getZoneInformation(brightDataZoneName);
        assertNotNull(zoneInformation);
        System.out.println("zoneInformation: " + zoneInformation);
    }

    @Test
    public void getZoneInformationNotExist() throws IOException {
        final String badZoneName = UUID.randomUUID().toString();
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final ZoneInformation zoneInformation = api.getZoneInformation(badZoneName);
        assertNotNull(zoneInformation);
        System.out.println("zoneInformation: " + zoneInformation);
    }

    // Manage IP.

    @Test
    public void getIpOfZone() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final List<String> ips = api.getIpOfZone(brightDataZoneName);
        assertNotNull(ips);
        System.out.println("Ip of zone: " + ips);
    }

    @Test
    public void addIpInZone() throws IOException {
        final int newIps = 1;
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final AddIpInZoneResponse addIpInZoneResponse = api.addIpInZone(brightDataZoneName, brightDataCustomerId, newIps);
        assertEquals(newIps, addIpInZoneResponse.getNewIps().size());
        System.out.println("addIpInZoneResponse: " + addIpInZoneResponse);
    }

    @Test
    public void removeIp() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final List<String> ips = api.getIpOfZone(brightDataZoneName);

        final List<String> removed = api.removeIpInZone(brightDataZoneName, ips.get(ips.size() - 1));
        System.out.println("Ip removed: " + removed);
        assertEquals(removed.size(), 1);

    }

    // Whitelist.

    @Test
    public void getWhitelistedIp() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final List<String> whitelistedIp = api.getWhitelistedIp(brightDataZoneName);
        System.out.println("Whitelisted ip: " + whitelistedIp);
        assertNotNull(whitelistedIp);
    }

    @Test
    public void getWhitelistedInAllZones() throws IOException {
        final ZoneAPI api = new BrightDataAPI(brightDataApiKey).getZoneAPI();
        final Map<String, List<String>> whitelistedIp = api.getWhitelistedInAllZones();
        assertNotNull(whitelistedIp);
    }

    /**
     * Generate a random zone name.
     * @return A random zone name as {@link String}.
     */
    private @NotNull String randomZoneName() {
        final int LENGTH = 10;
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            char randomChar = (char)(random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }

}