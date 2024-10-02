package xyz.tcbuildmc.common.powerfullib.util.api.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.Cleanable;
import xyz.tcbuildmc.common.powerfullib.util.impl.data.SeveralImpl;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Several<L, M, R> extends Serializable, Cleanable, Cloneable {
    static <L, M, R> Several<L, M, R> create() {
        return new SeveralImpl<>();
    }

    static <L, M, R> Several<L, M, R> create(@Nullable L left, @Nullable M middle, @Nullable R right) {
        return new SeveralImpl<>(left, middle, right);
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

    // Middle

    // Left

    @Nullable
    M getMiddle();

    default boolean isMiddlePresent() {
        return getMiddle() != null;
    }

    default void ifMiddlePresent(Consumer<M> mConsumer) {
        if (isMiddlePresent()) {
            mConsumer.accept(getMiddle());
        }
    }

    default void ifMiddleNotPresent(Consumer<M> mConsumer) {
        if (!isMiddlePresent()) {
            mConsumer.accept(getMiddle());
        }
    }

    default void ifMiddlePresentOrElse(Consumer<M> mConsumer1, Consumer<M> mConsumer2) {
        if (isMiddlePresent()) {
            mConsumer1.accept(getMiddle());
        } else {
            mConsumer2.accept(getMiddle());
        }
    }

    default M getMiddleOrDefault(M defaultValue) {
        return isMiddlePresent() ? getMiddle() : defaultValue;
    }

    default M getMiddleOrDefaultGet(@NotNull Supplier<M> mSupplier) {
        return getMiddleOrDefault(mSupplier.get());
    }

    default M getMiddleOrThrow() {
        return getMiddleOrThrow(new NullPointerException());
    }

    default <X extends Throwable> M getMiddleOrThrow(X ex) throws X {
        if (!isMiddlePresent()) {
            throw ex;
        }

        return getMiddle();
    }

    default <X extends Throwable> M getMiddleOrThrow(@NotNull Supplier<? extends X> exSupplier) throws X {
        return getMiddleOrThrow(exSupplier.get());
    }

    default Optional<M> getOptionalMiddle() {
        return Optional.ofNullable(getMiddle());
    }

    M setMiddle(M middle);

    default M setMiddle(M middle, M origin) {
        if (getMiddle() == origin) {
            return setMiddle(middle);
        }

        return getMiddle();
    }

    default M setMiddle(M middle, @NotNull Predicate<M> originPredicate) {
        if (originPredicate.test(getMiddle())) {
            return setMiddle(middle);
        }

        return getMiddle();
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

    <A, B, C> Several<A, B, C> map(@NotNull Function<L, A> leftMapper, @NotNull Function<M, B> middleMapper, @NotNull Function<R, C> rightMapper);

    default void set(@NotNull Several<L, M, R> several) {
        set(several.getLeft(), several.getMiddle(), several.getRight());
    }

    default void set(L left, M middle, R right) {
        setLeft(left);
        setMiddle(middle);
        setRight(right);
    }

    @Override
    default void clear() {
        set(null, null, null);
    }
}
