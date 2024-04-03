package com.github.rmheuer.nbtlib;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface NbtTag {
    void read(DataInput in) throws IOException;
    void write(DataOutput out) throws IOException;
}
