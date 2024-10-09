package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagString extends NbtTag {
    private String value;

    public NbtTagString() {
        value = "";
    }

    public NbtTagString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        value = in.readUTF();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(value);
    }

    @Override
    public String toSnbt() {
        return SnbtIO.quoteStringIfNeeded(value);
    }
}
