package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum PlanType {

    /**
     * The plan type for a static plan.
     * It will use datacenter ips.
     */
    STATIC("static"),

    /**
     * It will use residential ips.
     */
    RESIDENT("resident"),

    /**
     * It will use a random ip to unblock websites in
     * case of a block.
     */
    UNBLOCKER("unblocker");

    @NotNull
    @Getter
    private final String type;

    PlanType(@NotNull final String type) {
        this.type = type;
    }

    public static @Nullable PlanType fromString(@NotNull final String type) {
        for (PlanType t : PlanType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return null;
    }


    @Override
    public @NotNull String toString() {
        return (type);
    }

}
