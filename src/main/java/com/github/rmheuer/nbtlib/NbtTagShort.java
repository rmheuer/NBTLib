package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagShort implements NbtTagNumeric {
    private short value;

    public NbtTagShort() {
        value = 0;
    }

    public NbtTagShort(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override
    public byte getByte() {
        return (byte) value;
    }

    @Override
    public short getShort() {
        return value;
    }

    @Override
    public int getInt() {
        return value;
    }

    @Override
    public long getLong() {
        return value;
    }

    @Override
    public float getFloat() {
        return value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        value = in.readShort();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeShort(value);
    }

    @Override
    public String toString() {
        return value + "s";
    }
}
