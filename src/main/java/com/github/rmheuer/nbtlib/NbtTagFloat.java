package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagFloat extends NbtTagNumeric {
    private float value;

    public NbtTagFloat(String name) {
        super(name);
        value = 0;
    }

    public NbtTagFloat(String name, float value) {
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
        return (int) value;
    }

    @Override
    public long getLong() {
        return (long) value;
    }

    @Override
    public float getFloat() {
        return value;
    }

    @Override
    public double getDouble() {
        return value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        value = in.readFloat();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeFloat(value);
    }

    @Override
    public String toString() {
        return value + "f";
    }
}
