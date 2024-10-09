package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagByte extends NbtTagNumeric {
    private byte value;

    public NbtTagByte() {
        value = 0;
    }

    public NbtTagByte(byte value) {
        this.value = value;
    }

    public NbtTagByte(boolean value) {
        this.value = (byte) (value ? 1 : 0);
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public boolean getBoolean() {
        return value != 0;
    }

    @Override
    public byte getByte() {
        return value;
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
        value = in.readByte();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeByte(value);
    }

    @Override
    public String toSnbt() {
        return value + "b";
    }
}
