package io.vulpine.lib.iffy.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import io.vulpine.lib.iffy.Either;
import io.vulpine.lib.iffy.EitherFactory;
import io.vulpine.lib.iffy.EitherUnwrapException;

public class BasicEither< L, R > implements Either < L, R >
{
  private final L lVal;

  private final R rVal;

  private final boolean isLeft;

  public BasicEither(L left, R right) {
    lVal = left;
    rVal = right;
    isLeft = Objects.isNull(right);
  }

  @Override
  public Optional < L > left() {
    return Optional.ofNullable(lVal);
  }

  @Override
  public Optional < R > right() {
    return Optional.ofNullable(rVal);
  }

  @Override
  public < N > Either < N, R > mapLeft(Function < L, N > fn) {
    return isLeft
      ? EitherFactory.left(fn.apply(lVal))
      : EitherFactory.right(rVal);
  }

  @Override
  public < N > Either < L, N > mapRight(Function < R, N > fn) {
    return isLeft
      ? EitherFactory.left(lVal)
      : EitherFactory.right(fn.apply(rVal));
  }

  @Override
  public L leftOr(L other) {
    return isLeft ? lVal : other;
  }

  @Override
  public R rightOr(R other) {
    return isLeft ? other : rVal;
  }

  @Override
  public L leftOrGet(Supplier < L > fn) {
    return isLeft ? lVal : fn.get();
  }

  @Override
  public R rightOrGet(Supplier < R > fn) {
    return isLeft ? fn.get() : rVal;
  }

  @Override
  public L leftOrThrow() {
    if (isLeft)
      return lVal;
    throw EitherUnwrapException.left();
  }

  @Override
  public R rightOrThrow() {
    if (isLeft)
      throw EitherUnwrapException.right();
    return rVal;
  }

  @Override
  public < E extends Throwable > L leftOrThrow(Supplier < E > fn) throws E {
    if (isLeft)
      return lVal;
    throw fn.get();
  }

  @Override
  public < E extends Throwable > R rightOrThrow(Supplier < E > fn) throws E {
    if (isLeft)
      throw fn.get();
    return rVal;
  }

  @Override
  public boolean isLeft() {
    return isLeft;
  }

  @Override
  public boolean isRight() {
    return !isLeft;
  }

  @Override
  public < X > X fold(
    final Function < L, X > fnl,
    final Function < R, X > fnr
  ) {
    return isLeft ? fnl.apply(lVal) : fnr.apply(rVal);
  }
}
