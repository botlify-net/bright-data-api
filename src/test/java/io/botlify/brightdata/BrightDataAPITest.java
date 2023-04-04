package io.botlify.brightdata;

import io.botlify.brightdata.object.LumTestEcho;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BrightDataAPITest {

    @Test
    public void getBrightDataHost() {
        assertNotNull(BrightDataAPI.getBrightDataHost());
    }

    @Test
    public void getMyIpInformation() throws IOException {
        BrightDataAPI brightDataAPI = new BrightDataAPI("");
        final LumTestEcho myIpInformation = brightDataAPI.getMyIpInformation();
        assertNotNull(myIpInformation);
    }

}