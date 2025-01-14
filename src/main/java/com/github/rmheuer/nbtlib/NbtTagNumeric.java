package com.github.rmheuer.nbtlib;

public abstract class NbtTagNumeric extends NbtTag {
    public abstract byte getByte();
    public abstract short getShort();
    public abstract int getInt();
    public abstract long getLong();
    public abstract float getFloat();
    public abstract double getDouble();
}
