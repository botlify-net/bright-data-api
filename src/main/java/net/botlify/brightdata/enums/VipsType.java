package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The type of vips.
 */
public enum VipsType {

    /**
     * The vips type is shared.
     */
    SHARED("shared"),

    /**
     * The vips type is dedicated.
     */
    DEDICATED("domain");

    /**
     * The string representation of the vips type.
     */
    @NotNull @Getter
    private final String type;

    /**
     * Construct a vips type from a string.
     * @param type The string to parse.
     */
    VipsType(@NotNull final String type) {
        this.type = type;
    }

    /**
     * Get the vips type from a string.
     * @param type The string to parse.
     * @return The {@link VipsType}, or {@code null} if not found.
     */
    public static @Nullable VipsType fromString(@NotNull final String type) {
        for (VipsType t : VipsType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return (null);
    }

    /**
     * Get the string representation of the vips type.
     * @return The string representation of the vips type.
     */
    @Override
    public @NotNull String toString() {
        return (type);
    }

}
