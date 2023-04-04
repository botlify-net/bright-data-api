package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum IpType {

    SHARED("shared"),

    DEDICATED("dedicated"),

    SELECTIVE("selective");

    @NotNull
    @Getter
    private final String type;

    IpType(@NotNull final String type) {
        this.type = type;
    }

    public static @Nullable IpType fromString(@NotNull final String type) {
        for (final IpType t : IpType.values()) {
            if (t.type.equalsIgnoreCase(type))
                return (t);
        }
        return (null);
    }


    @Override
    public @NotNull String toString() {
        return (type);
    }
}