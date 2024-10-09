package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NbtTag {
    public abstract void read(DataInput in) throws IOException;
    public abstract void write(DataOutput out) throws IOException;

    public abstract String toSnbt();

    @Override
    public String toString() {
        return toSnbt();
    }
}
