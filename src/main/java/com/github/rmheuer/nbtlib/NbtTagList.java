package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class NbtTagList extends NbtTag implements Iterable<NbtTag> {
    private final List<NbtTag> value;
    private Class<? extends NbtTag> type;

    public NbtTagList(String name) {
        super(name);
        type = null;
        value = new ArrayList<>();
    }

    public NbtTagList(String name, NbtTag... value) {
        this(name);
        setValue(value);
    }

    public NbtTagList(String name, List<NbtTag> value) {
        this(name);
        setValue(value);
    }

    public List<NbtTag> getValue() {
        return new ArrayList<>(value);
    }

    public void setValue(NbtTag... value) {
        clear();
        for (NbtTag tag : value) {
            add(tag);
        }
    }

    public void setValue(List<NbtTag> value) {
        clear();
        for (NbtTag tag : value) {
            add(tag);
        }
    }

    public void add(NbtTag tag) {
        Class<? extends NbtTag> type = tag.getClass();
        if (this.type == null) {
            this.type = type;
        }

        if (!this.type.equals(type)) {
            throw new IllegalArgumentException("Tag of type " + type + " not compatible with list of type " + this.type);
        }

        value.add(tag);
    }

    public void clear() {
        type = null;
        value.clear();
    }

    @Override
    public Iterator<NbtTag> iterator() {
        return value.iterator();
    }

    @Override
    public void read(DataInput in) throws IOException {
        int typeId = in.readByte();
        int length = in.readInt();

        if (length <= 0)
            return;

        clear();
        for (int i = 0; i < length; i++) {
            NbtTag tag = NbtIO.createTag(typeId, "");
            tag.read(in);

            add(tag);
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        if (type == null || value.isEmpty()) {
            out.writeByte(0); // TAG_End
            out.writeInt(0); // Length 0
            return;
        }

        out.writeByte(NbtIO.getId(type));
        out.writeInt(value.size());

        for (NbtTag tag : value) {
            tag.write(out);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        boolean comma = false;
        for (NbtTag tag : value) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }

            builder.append(tag);
        }
        builder.append("]");

        return builder.toString();
    }
}
