package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagInt extends NbtTagNumeric {
    private int value;

    public NbtTagInt(String name) {
        super(name);
        value = 0;
    }

    public NbtTagInt(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public byte getByte() {
        return (byte) value;
    }

    @Override
    public short getShort() {
        return (short) value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        value = in.readInt();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
