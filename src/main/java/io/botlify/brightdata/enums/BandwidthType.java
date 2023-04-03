package io.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum BandwidthType {

    PAYPERUSAGE("payperusage"),
    UNLIMITED("unlimited");

    @NotNull @Getter
    private final String type;

    BandwidthType(@NotNull final String type) {
        this.type = type;
    }

    public static @Nullable BandwidthType fromString(@NotNull final String type) {
        for (BandwidthType t : BandwidthType.values()) {
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
