package io.vulpine.lib.iffy;

/**
 * The definition of a provider that is used to construct left or right Either
 * instances.
 */
public interface EitherProvider
{
  /**
   * @see Either#ofLeft(Object)
   */
  < L, R > Either < L, R > left(L value);

  /**
   * @see Either#ofRight(Object)
   */
  < L, R > Either < L, R > right(R value);
}
