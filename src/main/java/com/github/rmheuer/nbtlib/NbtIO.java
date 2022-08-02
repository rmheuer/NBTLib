package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class NbtIO {
    private static final Map<Class<? extends NbtTag>, Integer> typeToId = new HashMap<>();
    private static final Map<Integer, Function<String, NbtTag>> creators = new HashMap<>();

    static {
        register( 1, NbtTagByte.class,      NbtTagByte::new);
        register( 2, NbtTagShort.class,     NbtTagShort::new);
        register( 3, NbtTagInt.class,       NbtTagInt::new);
        register( 4, NbtTagLong.class,      NbtTagLong::new);
        register( 5, NbtTagFloat.class,     NbtTagFloat::new);
        register( 6, NbtTagDouble.class,    NbtTagDouble::new);
        register( 7, NbtTagByteArray.class, NbtTagByteArray::new);
        register( 8, NbtTagString.class,    NbtTagString::new);
        register( 9, NbtTagList.class,      NbtTagList::new);
        register(10, NbtTagCompound.class,  NbtTagCompound::new);
        register(11, NbtTagIntArray.class,  NbtTagIntArray::new);
        register(12, NbtTagLongArray.class, NbtTagLongArray::new);
    }

    private static void register(int id, Class<? extends NbtTag> type, Function<String, NbtTag> creator) {
        typeToId.put(type, id);
        creators.put(id, creator);
    }

    /**
     * Reads in a single tag from a DataInput. This will always be a
     * {@link NbtTagCompound} or {@code null} for NBT tags from Minecraft.
     * A return value of {@code null} represents a {@code TAG_End}.
     *
     * @param in data input
     * @return NBT tag read
     * @throws IOException if an IO error occurs while reading
     */
    public static NbtTag readTag(DataInput in) throws IOException {
        int typeId = in.readByte();
        if (typeId == 0) {
            return null;
        }

        String name = in.readUTF();

        NbtTag tag = createTag(typeId, name);
        tag.read(in);

        return tag;
    }

    /**
     * Writes out a single tag to a DataOutput. If the input tag is
     * {@code null}, this will write out a {@code TAG_End}.
     *
     * @param tag tag to write
     * @param out data output
     * @throws IOException if an IO error occurs while writing
     */
    public static void writeTag(NbtTag tag, DataOutput out) throws IOException {
        if (tag == null) {
            out.writeByte(0);
            return;
        }

        out.writeByte(getId(tag.getClass()));
        out.writeUTF(tag.getName());
        tag.write(out);
    }

    public static NbtTag createTag(int id, String name) {
        return creators.get(id).apply(name);
    }

    public static int getId(Class<? extends NbtTag> type) {
        return typeToId.get(type);
    }

    private NbtIO() {
        throw new AssertionError();
    }
}
