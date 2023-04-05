package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The type of ip allocation preset.
 */
public enum IpAllocPresetType {

    /**
     * The ip allocation preset type is shared.
     */
    SHARED_BLOCK("shared_block"),

    /**
     * The ip allocation preset type is shared residential.
     */
    SHARED_RES_BLOCK("shared_res_block");

    @NotNull
    @Getter
    private final String type;

    /**
     * Construct an ip allocation preset type.
     * @param type The string representation of the ip allocation preset type.
     */
    IpAllocPresetType(@NotNull final String type) {
        this.type = type;
    }

    /**
     * Get the ip allocation preset type from a string.
     * @param type The string to parse.
     * @return The ip allocation preset type, or null if not found.
     */
    public static @Nullable IpAllocPresetType fromString(@NotNull final String type) {
        for (IpAllocPresetType t : IpAllocPresetType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return (null);
    }


    /**
     * Get the string representation of the ip allocation preset type.
     * @return The string representation of the ip allocation preset type.
     */
    @Override
    public @NotNull String toString() {
        return (type);
    }

}
