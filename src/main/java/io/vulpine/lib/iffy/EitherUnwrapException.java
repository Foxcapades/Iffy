package io.vulpine.lib.iffy;

/**
 * Exception for the case when an Either instance is unwrapped for a value of
 * the incorrect side.
 */
public class EitherUnwrapException extends RuntimeException
{
  public EitherUnwrapException(String message) {
    super(message);
  }

  public static EitherUnwrapException left() {
    return new EitherUnwrapException("Attempted to unwrap the left value of a right either.");
  }

  public static EitherUnwrapException right() {
    return new EitherUnwrapException("Attempted to unwrap the right value of a left either.");
  }
}
