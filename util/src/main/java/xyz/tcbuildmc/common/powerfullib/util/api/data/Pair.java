package xyz.tcbuildmc.common.powerfullib.util.api.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.Cleanable;
import xyz.tcbuildmc.common.powerfullib.util.impl.data.PairImpl;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Pair<L, R> extends Serializable, Cleanable, Cloneable {
    static <L, R> Pair<L, R> create() {
        return new PairImpl<>();
    }

    static <L, R> Pair<L, R> create(L left, R right) {
        return new PairImpl<>(left, right);
    }

    // Left

    @Nullable
    L getLeft();

    default boolean isLeftPresent() {
        return getLeft() != null;
    }

    default void ifLeftPresent(Consumer<L> lConsumer) {
        if (isLeftPresent()) {
            lConsumer.accept(getLeft());
        }
    }

    default void ifLeftNotPresent(Consumer<L> lConsumer) {
        if (!isLeftPresent()) {
            lConsumer.accept(getLeft());
        }
    }

    default void ifLeftPresentOrElse(Consumer<L> lConsumer1, Consumer<L> lConsumer2) {
        if (isLeftPresent()) {
            lConsumer1.accept(getLeft());
        } else {
            lConsumer2.accept(getLeft());
        }
    }

    default L getLeftOrDefault(L defaultValue) {
        return isLeftPresent() ? getLeft() : defaultValue;
    }

    default L getLeftOrDefaultGet(@NotNull Supplier<L> lSupplier) {
        return getLeftOrDefault(lSupplier.get());
    }

    default L getLeftOrThrow() {
        return getLeftOrThrow(new NullPointerException());
    }

    default <X extends Throwable> L getLeftOrThrow(X ex) throws X {
        if (!isLeftPresent()) {
            throw ex;
        }

        return getLeft();
    }

    default <X extends Throwable> L getLeftOrThrow(@NotNull Supplier<? extends X> exSupplier) throws X {
        return getLeftOrThrow(exSupplier.get());
    }

    default Optional<L> getOptionalLeft() {
        return Optional.ofNullable(getLeft());
    }

    L setLeft(L left);

    default L setLeft(L left, L origin) {
        if (getLeft() == origin) {
            return setLeft(left);
        }

        return getLeft();
    }

    default L setLeft(L left, @NotNull Predicate<L> originPredicate) {
        if (originPredicate.test(getLeft())) {
            return setLeft(left);
        }

        return getLeft();
    }

    // Right

    @Nullable
    R getRight();

    default boolean isRightPresent() {
        return getRight() != null;
    }

    default void ifRightPresent(Consumer<R> rConsumer) {
        if (isRightPresent()) {
            rConsumer.accept(getRight());
        }
    }

    default void ifRightNotPresent(Consumer<R> rConsumer) {
        if (!isRightPresent()) {
            rConsumer.accept(getRight());
        }
    }

    default void ifRightPresentOrElse(Consumer<R> rConsumer1, Consumer<R> rConsumer2) {
        if (isRightPresent()) {
            rConsumer1.accept(getRight());
        } else
            rConsumer2.accept(getRight());
    }

    default R getRightOrDefault(R defaultValue) {
        return isRightPresent() ? getRight() : defaultValue;
    }

    default R getRightOrDefaultGet(@NotNull Supplier<R> rSupplier) {
        return getRightOrDefault(rSupplier.get());
    }

    default R getRightOrThrow() {
        return getRightOrThrow(new NullPointerException());
    }

    default <X extends Throwable> R getRightOrThrow(X ex) throws X {
        if (!isRightPresent()) {
            throw ex;
        }

        return getRight();
    }

    default <X extends Throwable> R getRightOrThrow(@NotNull Supplier<? extends X> exSupplier) throws X {
        return getRightOrThrow(exSupplier.get());
    }

    default Optional<R> getOptionalRight() {
        return Optional.ofNullable(getRight());
    }

    R setRight(R right);

    default R setRight(R right, R origin) {
        if (getRight() == origin) {
            return setRight(right);
        }

        return getRight();
    }

    default R setRight(R right, @NotNull Predicate<R> originPredicate) {
        if (originPredicate.test(getRight())) {
            return setRight(right);
        }

        return getRight();
    }

    // Defaulted

    <A, B> Pair<A, B> map(@NotNull Function<L, A> leftMapper, @NotNull Function<R, B> rightMapper);

    <A, B> Pair<A, B> flatmap(@NotNull Function<L, Pair<L, A>> leftMapper, @NotNull Function<R, Pair<R, B>> rightMapper);

    default void set(@NotNull Pair<L, R> pair) {
        set(pair.getLeft(), pair.getRight());
    }

    default void set(L left, R right) {
        setLeft(left);
        setRight(right);
    }

    @Override
    default void clear() {
        set(null, null);
    }
}
