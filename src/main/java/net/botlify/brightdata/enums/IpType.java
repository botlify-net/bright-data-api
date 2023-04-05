package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The different types of ip addresses.
 */
public enum IpType {

    /**
     * The ip type is shared.
     */
    SHARED("shared"),

    /**
     * The ip type is dedicated.
     */
    DEDICATED("dedicated"),

    /**
     * The ip type is selective.
     */
    SELECTIVE("selective");

    /**
     * The string representation of the ip type.
     */
    @NotNull @Getter
    private final String type;

    /**
     * Construct an ip type from a string.
     * @param type The string to parse.
     */
    IpType(@NotNull final String type) {
        this.type = type;
    }

    /**
     * Get the ip type from a string.
     * @param type The string to parse.
     * @return The ip type, or null if not found.
     */
    public static @Nullable IpType fromString(@NotNull final String type) {
        for (final IpType t : IpType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return (null);
    }

    /**
     * Get the string representation of the ip type.
     * @return The string representation of the ip type.
     */
    @Override
    public @NotNull String toString() {
        return (type);
    }

}