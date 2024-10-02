package xyz.tcbuildmc.common.powerfullib.util.impl.data;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.powerfullib.util.api.data.Several;

import java.util.function.Function;

@ApiStatus.Internal
@Getter
public final class SeveralImpl<L, M, R> implements Several<L, M, R> {
    @Nullable
    private L left;
    @Nullable
    private M middle;
    @Nullable
    private R right;

    public SeveralImpl() {
        this(null, null, null);
    }

    public SeveralImpl(@Nullable L left, @Nullable M middle, @Nullable R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    @Override
    public L setLeft(L left) {
        this.left = left;
        return this.left;
    }

    @Override
    public M setMiddle(M middle) {
        this.middle = middle;
        return this.middle;
    }

    @Override
    public R setRight(R right) {
        this.right = right;
        return this.right;
    }

    @Override
    public <A, B, C> Several<A, B, C> map(@NotNull Function<L, A> leftMapper, @NotNull Function<M, B> middleMapper, @NotNull Function<R, C> rightMapper) {
        return new SeveralImpl<>(leftMapper.apply(this.left), middleMapper.apply(this.middle), rightMapper.apply(this.right));
    }
}
