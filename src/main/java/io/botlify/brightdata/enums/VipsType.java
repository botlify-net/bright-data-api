package io.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum VipsType {

    SHARED("shared"),
    DEDICATED("domain");

    @NotNull @Getter
    private final String type;

    VipsType(@NotNull final String type) {
        this.type = type;
    }

    public static @Nullable VipsType fromString(@NotNull final String type) {
        for (VipsType t : VipsType.values()) {
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
