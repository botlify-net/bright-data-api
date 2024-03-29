package net.botlify.brightdata.response.account;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * This class is the response of the getBalance method.
 */
public final class BalanceResponse {

    /**
     * The amount of money in your account.
     */
    @Getter
    private final int balance;

    /**
     * The amount of money you will be billed for, as of this moment, in the next billing cycle.
     */
    @Getter
    private final int pendingCosts;

    /**
     * Construct a balance response from a JSON object.
     * @param response The JSON object to parse.
     */
    public BalanceResponse(@NotNull final JSONObject response) {
        this.balance = response.getInt("balance");
        this.pendingCosts = response.getInt("pending_costs");
    }

}
