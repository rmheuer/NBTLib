package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class NbtIO {
    private static final Map<Class<? extends NbtTag>, Integer> typeToId = new HashMap<>();
    private static final Map<Integer, Supplier<NbtTag>> creators = new HashMap<>();

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

    private static void register(int id, Class<? extends NbtTag> type, Supplier<NbtTag> creator) {
        typeToId.put(type, id);
        creators.put(id, creator);
    }

    /**
     * Reads in a single tag from a DataInput, in file NBT format.
     *
     * @param in data input
     * @return NBT tag read
     * @throws IOException if an IO error occurs while reading
     */
    public static NbtTagCompound readTagFile(DataInput in) throws IOException {
        NbtTagCompound root = new NbtTagCompound();
        root.read(in);

        return root;
    }

    /**
     * Writes out a single tag to a DataOutput in file format. If the input
     * tag is {@code null}, this will write out a {@code TAG_End}.
     *
     * @param tag tag to write
     * @param out data output
     * @throws IOException if an IO error occurs while writing
     */
    public static void writeTagFile(NbtTagCompound tag, DataOutput out) throws IOException {
        if (tag == null) {
            out.writeByte(0);
            return;
        }

        tag.write(out);
    }

    /**
     * Reads in a single tag from a DataInput in Network NBT format.
     * A return value of {@code null} represents a {@code TAG_End}.
     *
     * @param in data input
     * @return NBT tag read
     * @throws IOException if an IO error occurs while reading
     */
    public static NbtTag readTagNetwork(DataInput in) throws IOException {
        int typeId = in.readByte();
        if (typeId == 0) {
            return null;
        }

        NbtTag tag = createTag(typeId);
        tag.read(in);

        return tag;
    }

    /**
     * Writes out a single tag to a DataOutput in Network NBT format. If the
     * input tag is {@code null}, this will write out a {@code TAG_End}.
     *
     * @param tag tag to write
     * @param out data output
     * @throws IOException if an IO error occurs while writing
     */
    public static void writeTagNetwork(NbtTag tag, DataOutput out) throws IOException {
        if (tag == null) {
            out.writeByte(0);
            return;
        }

        out.writeByte(getId(tag.getClass()));
        tag.write(out);
    }

    public static NbtTag createTag(int id) {
        return creators.get(id).get();
    }

    public static int getId(Class<? extends NbtTag> type) {
        return typeToId.get(type);
    }

    private NbtIO() {
        throw new AssertionError();
    }
}
