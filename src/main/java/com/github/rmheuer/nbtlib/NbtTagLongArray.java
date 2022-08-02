package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagLongArray extends NbtTag {
    private long[] value;

    public NbtTagLongArray(String name) {
        super(name);
        value = new long[0];
    }

    public NbtTagLongArray(String name, long... value) {
        super(name);
        this.value = value.clone();
    }

    public long[] getValue() {
        return value.clone();
    }

    public void setValue(long... value) {
        this.value = value.clone();
    }

    public long get(int index) {
        return value[index];
    }

    public void set(int index, long value) {
        this.value[index] = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        int len = in.readInt();
        value = new long[len];
        for (int i = 0; i < len; i++) {
            value[i] = in.readLong();
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(value.length);
        for (long l : value) {
            out.writeLong(l);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[L;");
        boolean comma = false;
        for (long l : value) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }

            builder.append(l);
        }
        builder.append("]");

        return builder.toString();
    }
}
