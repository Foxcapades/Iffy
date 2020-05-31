package io.vulpine.lib.iffy.impl;

import java.util.Objects;

import io.vulpine.lib.iffy.Either;
import io.vulpine.lib.iffy.EitherProvider;

public class BasicProvider implements EitherProvider
{
  @Override
  public < L, R > Either < L, R > left(L value) {
    return new BasicEither <>(Objects.requireNonNull(value), null);
  }

  @Override
  public < L, R > Either < L, R > right(R value) {
    return new BasicEither <>(null, Objects.requireNonNull(value));
  }
}
