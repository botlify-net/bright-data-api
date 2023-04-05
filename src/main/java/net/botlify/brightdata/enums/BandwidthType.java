package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The type of bandwidth used in a zone.
 */
public enum BandwidthType {

    /**
     * The bandwidth type pay per usage mode.
     */
    PAYPERUSAGE("payperusage"),

    /**
     * The bandwidth type is unlimited. Limited to 100gb.
     */
    UNLIMITED("unlimited");

    @NotNull @Getter
    private final String type;

    BandwidthType(@NotNull final String type) {
        this.type = type;
    }

    /**
     * Get the bandwidth type from a string.
     * @param type The string to parse.
     * @return The bandwidth type, or null if not found.
     */
    public static @Nullable BandwidthType fromString(@NotNull final String type) {
        for (BandwidthType t : BandwidthType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return (null);
    }

    /**
     * Get the string representation of the bandwidth type.
     * @return The string representation of the bandwidth type.
     */
    @Override
    public @NotNull String toString() {
        return (type);
    }

}
