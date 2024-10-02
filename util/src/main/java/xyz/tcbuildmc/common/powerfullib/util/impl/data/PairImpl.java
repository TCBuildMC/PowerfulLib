package xyz.tcbuildmc.common.powerfullib.util.impl.data;

import lombok.Getter;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.data.Pair;

import java.util.function.Function;

@ApiStatus.Internal
@Getter
public final class PairImpl<L, R> implements Pair<L, R> {
    @Nullable
    private L left;
    @Nullable
    private R right;

    public PairImpl() {
        this(null, null);
    }

    public PairImpl(@Nullable L left, @Nullable R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L setLeft(L left) {
        this.left = left;
        return this.left;
    }

    @Override
    public R setRight(R right) {
        this.right = right;
        return this.right;
    }

    @Override
    public <A, B> Pair<A, B> map(@NotNull Function<L, A> leftMapper, @NotNull Function<R, B> rightMapper) {
        return new PairImpl<>(leftMapper.apply(this.left), rightMapper.apply(this.right));
    }

    @Override
    public <A, B> Pair<A, B> flatmap(@NotNull Function<L, Pair<L, A>> leftMapper, @NotNull Function<R, Pair<R, B>> rightMapper) {
        return new PairImpl<>(leftMapper.apply(this.left).getRight(), rightMapper.apply(this.right).getRight());
    }
}
