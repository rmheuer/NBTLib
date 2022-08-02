package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagDouble extends NbtTagNumeric {
    private double value;

    public NbtTagDouble(String name) {
        super(name);
        value = 0;
    }

    public NbtTagDouble(String name, double value) {
        super(name);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
        return (int) value;
    }

    @Override
    public long getLong() {
        return (long) value;
    }

    @Override
    public float getFloat() {
        return (float) value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        value = in.readDouble();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(value);
    }

    @Override
    public String toString() {
        return value + "d";
    }
}
