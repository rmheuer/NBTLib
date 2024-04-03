package com.github.rmheuer.nbtlib;

public interface NbtTagNumeric extends NbtTag {
    byte getByte();
    short getShort();
    int getInt();
    long getLong();
    float getFloat();
    double getDouble();
}
