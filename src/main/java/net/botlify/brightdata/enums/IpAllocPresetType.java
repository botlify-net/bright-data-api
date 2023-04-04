package net.botlify.brightdata.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum IpAllocPresetType {

    SHARED_BLOCK("shared_block"),
    SHARED_RES_BLOCK("shared_res_block");

    @NotNull
    @Getter
    private final String type;

    IpAllocPresetType(@NotNull final String type) {
        this.type = type;
    }

    public static @Nullable IpAllocPresetType fromString(@NotNull final String type) {
        for (IpAllocPresetType t : IpAllocPresetType.values()) {
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
