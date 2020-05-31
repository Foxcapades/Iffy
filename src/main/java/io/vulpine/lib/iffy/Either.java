package io.vulpine.lib.iffy;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A wrapper that MUST contain either a left value of type {@link L} or a right
 * value of type {@link R}.
 *
 * @param <L> Type for the left value
 * @param <R> Type for the right value
 */
public interface Either< L, R >
{
  /**
   * Returns an option which will contain a value if this Either is left, else
   * will be empty.
   *
   * @return An option of this Either's left type.
   */
  Optional < L > left();

  /**
   * Returns an option which will contain a value if this Either is right, else
   * will be empty.
   *
   * @return An option of this Either's right type.
   */
  Optional < R > right();

  /**
   * Returns a new Either with the left type mapped to {@link N}.
   * <p>
   * If this Either is left, the function be applied to the left value, else
   * the passed function will be ignored.
   *
   * @param fn  function to apply to the left value if this is a left Either.
   * @param <N> New left type for the returned Either
   *
   * @return A new either of type &lt;{@link N}, {@link R}&gt;
   */
  < N > Either < N, R > mapLeft(Function < L, N > fn);

  /**
   * Returns a new Either with the right type mapped to {@link N}.
   * <p>
   * If this Either is right, the function be applied to the right value, else
   * the passed function will be ignored.
   *
   * @param fn  function to apply to the right value if this is a right Either.
   * @param <N> New right type for the returned Either
   *
   * @return A new either of type &lt;{@link L}, {@link N}&gt;
   */
  < N > Either < L, N > mapRight(Function < R, N > fn);

  /**
   * Returns either the left value of this Either (if it is a left Either) or
   * the given alternate value.
   *
   * @param other alternate/default value to return if this is a right Either.
   *
   * @return either the left value of this either or the provided default value.
   */
  L leftOr(L other);

  /**
   * Returns either the right value of this Either (if it is a right Either) or
   * the given alternate value.
   *
   * @param other alternate/default value to return if this is a left Either.
   *
   * @return either the right value of this either or the provided default
   * value.
   */
  R rightOr(R other);

  /**
   * Returns either the left value of this Either (if it is a left Either) or
   * the value returned by the given function.
   *
   * @param fn alternate/default value provider used if this is a right Either.
   *
   * @return either the left value of this either or the returned value of the
   * provided function.
   */
  L leftOrGet(Supplier < L > fn);

  /**
   * Returns either the right value of this Either (if it is a right Either) or
   * the value returned by the given function.
   *
   * @param fn alternate/default value provider used if this is a left Either.
   *
   * @return either the right value of this either or the returned value of the
   * provided function.
   */
  R rightOrGet(Supplier < R > fn);

  /**
   * Returns either the left value of this Either (if it is a left Either) or
   * throws an {@link EitherUnwrapException}.
   *
   * @return left value of this either.
   */
  L leftOrThrow();

  /**
   * Returns either the right value of this Either (if it is a right Either) or
   * throws an {@link EitherUnwrapException}.
   *
   * @return right value of this either.
   */
  R rightOrThrow();

  /**
   * Returns either the left value of this Either (if it is a left Either) or
   * throws the exception returned by calling the given Supplier.
   *
   * @return left value of this either.
   */
  < E extends Throwable > L leftOrThrow(Supplier < E > fn) throws E;

  /**
   * Returns either the right value of this Either (if it is a right Either) or
   * throws the exception returned by calling the given Supplier.
   *
   * @return right value of this either.
   */
  < E extends Throwable > R rightOrThrow(Supplier < E > fn) throws E;

  /**
   * Returns whether this Either is left.
   */
  boolean isLeft();

  /**
   * Returns whether this Either is right.
   */
  boolean isRight();

  /**
   * Applies <code>fnl</code> to the left value of this either if this is a left
   * either and returns the result or applies <code>fnr</code> to the right
   * value and returns the result.
   *
   * @param fnl function that will map the value to {@link X} if this is a left
   *            either.
   * @param fnr function that will map the value to {@link X} if this is a right
   *            either.
   * @param <X> Output type of the fold.
   *
   * @return the value of either <code>fnl</code> or <code>fnr</code> depending
   * on whether this is a left or right either.
   */
  < X > X fold(Function < L, X > fnl, Function < R, X > fnr);

  /**
   * Returns an either wrapping one of <code>lVal</code> or <code>rVal</code>.
   * <p>
   * If both values are null, or both values are non-null, throws an
   * {@link IllegalArgumentException}.
   *
   * @param lVal left value
   * @param rVal right value
   * @param <L>  left value type
   * @param <R>  right value type
   *
   * @return An either wrapping the non-null argument.
   *
   * @throws IllegalArgumentException if both lVal and rVal are null or both
   * lVal and rVal are not null.
   */
  static < L, R > Either < L, R > ofEither(L lVal, R rVal) {
    return EitherFactory.either(lVal, rVal);
  }

  /**
   * Returns a new left Either wrapping the given value.
   *
   * @param value left value for the either.
   * @param <L>   Type of the left value
   * @param <R>   Type of the right value
   *
   * @return a new either wrapping the given left value.
   *
   * @throws NullPointerException if the given value is null.
   */
  static < L, R > Either < L, R > ofLeft(L value) {
    return EitherFactory.left(value);
  }

  /**
   * Returns a new right Either wrapping the given value.
   *
   * @param value right value for the either.
   * @param <L>   Type of the left value
   * @param <R>   Type of the right value
   *
   * @return a new either wrapping the given right value.
   *
   * @throws NullPointerException if the given value is null.
   */
  static < L, R > Either < L, R > ofRight(R value) {
    return EitherFactory.right(value);
  }
}
