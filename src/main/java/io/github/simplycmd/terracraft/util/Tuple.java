package io.github.simplycmd.terracraft.util;

import java.util.Arrays;

public class Tuple {
    final Object[] objects;

    public Tuple(Object... objects) {
        this.objects = objects;
    }

    public Object[] gets() {
        return objects;
    }

    public Object get(int index) {
        return objects[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple tuple)) return false;
        return Arrays.equals(objects, tuple.objects);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(objects);
    }
}
