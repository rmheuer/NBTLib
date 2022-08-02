package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NbtTag {
    private final String name;

    public NbtTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void read(DataInput in) throws IOException;
    public abstract void write(DataOutput out) throws IOException;
}
