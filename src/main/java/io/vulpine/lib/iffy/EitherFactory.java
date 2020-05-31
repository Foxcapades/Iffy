package io.vulpine.lib.iffy;

import java.util.Objects;

import io.vulpine.lib.iffy.impl.BasicProvider;

/**
 * EitherFactory is a customizable constructor for Either implementations.
 */
public final class EitherFactory
{
  private static EitherProvider provider = new BasicProvider();

  private EitherFactory(){}

  /**
   * Sets the provider for new Either instances to the given value.
   *
   * @param provider new provider to use when constructing Either instances.
   */
  public static void setProvider(EitherProvider provider) {
    EitherFactory.provider = provider;
  }

  /**
   * Resets the provider for new Either instances to the default value.
   */
  public static void resetProvider() {
    provider = new BasicProvider();
  }

  /**
   * @see Either#ofEither(Object, Object)
   */
  public static < L, R > Either < L, R > either(L lVal, R rVal) {
    var l = Objects.isNull(lVal);
    var r = Objects.isNull(rVal);

    if (l == r)
      throw new IllegalArgumentException("of lVal and rVal, one must be null " +
        "and one must not be null.");

    return l ? right(rVal) : left(lVal);
  }

  /**
   * @see Either#ofRight(Object)
   */
  public static < L, R > Either < L, R > left(L value) {
    return provider.left(Objects.requireNonNull(value));
  }

  /**
   * @see Either#ofLeft(Object)
   */
  public static < L, R > Either < L, R > right (R value) {
    return provider.right(Objects.requireNonNull(value));
  }
}
