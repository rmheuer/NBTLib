package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagByteArray extends NbtTag {
    private byte[] value;

    public NbtTagByteArray() {
        value = new byte[0];
    }

    public NbtTagByteArray(byte... value) {
        this.value = value.clone();
    }

    public byte[] getValue() {
        return value.clone();
    }

    public void setValue(byte... value) {
        this.value = value.clone();
    }

    public byte get(int index) {
        return value[index];
    }

    public void set(int index, byte value) {
        this.value[index] = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        int length = in.readInt();
        value = new byte[length];
        in.readFully(value);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(value.length);
        out.write(value);
    }

    @Override
    public String toSnbt() {
        StringBuilder builder = new StringBuilder("[B;");
        boolean comma = false;
        for (byte b : value) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }

            builder.append(b);
            builder.append('b');
        }
        builder.append("]");

        return builder.toString();
    }
}
