package com.dsh105.echopet.compat.nms.v1_9_R2.metadata;

import lombok.*;

import com.google.common.base.Preconditions;

import net.minecraft.server.v1_9_R2.DataWatcher;
import net.minecraft.server.v1_9_R2.Entity;

@RequiredArgsConstructor
public class WrappedDataWatcher {
    private final DataWatcher handle;
    private final Object lock = new Object();

    public WrappedDataWatcher(Entity entity) {
        this(new DataWatcher(entity));
    }

    public <T> T get(MetadataKey<T> key) {
        Object obj = handle.get(key.getHandle());
        return key.cast(obj);
    }

    public <T> void set(MetadataKey<T> key, T value) {
        handle.set(key.getHandle(), key.cast(value));
    }

    public <T> void register(MetadataKey<T> key, T initialValue) {
        handle.register(key.getHandle(), key.cast(initialValue));
    }
}
