package turing.transition;

import org.javatuples.Pair;
import state.State;
import transition.ComparableList;
import transition.Transition;
import transition.TransitionFunction;
import utils.Utils;

import java.util.Collection;
import java.util.Set;

/**
 * <h2>Turing machine transition function</h2>
 *
 * This transition function is used
 * to change the internal state
 * of the Turing machine depending
 * on some parameters.
 *
 * function depends on the current
 * state and of the read symbols on
 * the tape, and then it returns
 * a new state, the symbols to be
 * written on the tape and the list
 * of movements.
 *
 * (q, {a1, ..., an}) -> (q', {b1,...,bn}, {m1,...,mn})
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TMTransitionFunction
  extends TransitionFunction {

  /**
   * Default constructor of the class.
   */
  public TMTransitionFunction() {
    super();
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes only one transition.
   *
   * @param transition that the function is going
   *                   to store.
   */
  public TMTransitionFunction(TMTransition transition) {
    super(transition);
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes a whole collection of
   * transitions, that the function is
   * going to store.
   *
   * @param transitions collection of transitions
   *                    to add.
   */
  public TMTransitionFunction(Collection<TMTransition> transitions) {
    super(transitions);
  }

  /**
   * Returns if the function has a
   * return value depending on the
   * specified current state.
   *
   * @param state current state of transition.
   * @param readSymbols list of read symbols.
   * @return {@code true} if function has the next
   * state.
   */
  public boolean hasNextState(State state,
                              ComparableList readSymbols) {
    Utils.checkIfNull(state, "state can not be null");
    Utils.checkIfNull(readSymbols, "read symbols can not be null");

    return super.hasNextState(new Pair<>(state, readSymbols));
  }

  /**
   * Returns a set of next states given a current
   * state.
   *
   * @param state current state of transition.
   * @param readSymbols list of read symbols.
   * @return set of transition with this state.
   */
  public Set<TMTransition> getNextState(State state,
                                      ComparableList readSymbols) {
    Utils.checkIfNull(state, "state can not be null");
    Utils.checkIfNull(readSymbols, "read symbols can not be null");

    return (Set<TMTransition>) super.getNextState(new Pair<>(state, readSymbols));
  }
}
