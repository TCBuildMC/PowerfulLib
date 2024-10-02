package xyz.tcbuildmc.common.powerfullib.util.impl.collection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static xyz.tcbuildmc.common.powerfullib.util.impl.Utils.assertNotNull;

public class NotNullArrayList<E> extends ArrayList<E> {
    public NotNullArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public NotNullArrayList() {
    }

    public NotNullArrayList(@NotNull Collection<? extends E> c) {
        super(c);
    }

    @Override
    public E get(int index) {
        return assertNotNull(super.get(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(index, assertNotNull(element));
    }

    @Override
    public boolean add(E e) {
        return super.add(assertNotNull(e));
    }

    @Override
    public E remove(int index) {
        return assertNotNull(super.remove(index));
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(assertNotNull(o));
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return super.removeIf(assertNotNull(filter));
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            assertNotNull(o);
        }

        return super.removeAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        for (E e : c) {
            assertNotNull(e);
        }

        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        for (E e : c) {
            assertNotNull(e);
        }

        return super.addAll(index, c);
    }
}
