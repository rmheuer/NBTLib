package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class NbtTagIntArray implements NbtTag {
    private int[] value;

    public NbtTagIntArray() {
        value = new int[0];
    }

    public NbtTagIntArray(int... value) {
        this.value = value.clone();
    }

    public int[] getValue() {
        return value.clone();
    }

    public void setValue(int... value) {
        this.value = value.clone();
    }

    public int get(int index) {
        return value[index];
    }

    public void set(int index, int value) {
        this.value[index] = value;
    }

    @Override
    public void read(DataInput in) throws IOException {
        int len = in.readInt();
        value = new int[len];
        for (int i = 0; i < len; i++) {
            value[i] = in.readInt();
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(value.length);
        for (int i : value) {
            out.writeInt(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[I;");
        boolean comma = false;
        for (int i : value) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }

            builder.append(i);
        }
        builder.append("]");

        return builder.toString();
    }
}
