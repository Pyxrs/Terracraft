package io.github.simplycmd.terracraft;

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
}
