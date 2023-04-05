package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The type of plan.
 */
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

    /**
     * Construct a plan type.
     * @param type The string representation of the plan type.
     */
    PlanType(@NotNull final String type) {
        this.type = type;
    }

    /**
     * Get the plan type from a string.
     * @param type The string to parse.
     * @return The {@link PlanType}, or {@code null} if not found.
     */
    public static @Nullable PlanType fromString(@NotNull final String type) {
        for (PlanType t : PlanType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return null;
    }

    /**
     * Get the string representation of the plan type.
     * @return The string representation of the plan type.
     */
    @Override
    public @NotNull String toString() {
        return (type);
    }

}
