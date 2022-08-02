package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class NbtTagCompound extends NbtTag {
    private final Map<String, NbtTag> value;

    public NbtTagCompound(String name) {
        super(name);
        value = new LinkedHashMap<>();
    }

    public void add(NbtTag tag) {
        value.put(tag.getName(), tag);
    }

    public NbtTag get(String key) {
        return value.get(key);
    }

    public boolean containsKey(String key) {
        return value.containsKey(key);
    }

    public Set<String> keySet() {
        return value.keySet();
    }

    public Collection<NbtTag> values() {
        return value.values();
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public void read(DataInput in) throws IOException {
        int typeId;

        while ((typeId = in.readByte()) != 0) {
            String name = in.readUTF();

            NbtTag tag = NbtIO.createTag(typeId, name);
            tag.read(in);

            add(tag);
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        for (NbtTag tag : values()) {
            out.writeByte(NbtIO.getId(tag.getClass()));
            out.writeUTF(tag.getName());
            tag.write(out);
        }
        out.writeByte(0); // TAG_End
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        boolean comma = false;
        for (NbtTag tag : values()) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }

            builder.append(tag.getName());
            builder.append(":");
            builder.append(tag);
        }
        builder.append("}");

        return builder.toString();
    }
}
