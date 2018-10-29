package tape;

/**
 * Enum representing
 * the possible movements
 * of a tape.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public enum Movement {
  LEFT(-1, "L"),
  RIGHT(1, "R"),
  STOP(0, "S");

  /**
   * Value of the movement.
   */
  public int value;
  public String representation;

  /**
   * Private constructor
   * of the elements.
   *
   * @param value that the element
   *              is going to have.
   */
  private Movement(int value, String representation) {
    this.value = value;
    this.representation = representation;
  }

  public static Movement of(String representation) {
    for (Movement movement : Movement.values()) {
      if (movement.representation.equals(representation))
        return movement;
    }
    return null;
  }
}