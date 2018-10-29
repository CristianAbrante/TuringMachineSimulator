package turing.transition;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import state.State;
import symbol.Symbol;
import tape.Movement;
import transition.ComparableList;
import transition.Transition;

/**
 * <h2>TMTransition</h2>
 *
 * This class is used to represent
 * a multitape transition of a
 * Turing machine.
 *
 * Multitape transitions read from
 * a defined list of tapes.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TMTransition
        extends Transition {

  /**
   * Constructor of the class.
   * <p>
   * It takes the tuple of the
   * parameters and the tuple of
   * the result.
   *
   * @param currentState current state of the transition.
   * @param nextState    next state of the transition.
   * @throws NullPointerException if currentState or nextState
   *                              is {@code null}.
   */
  public TMTransition(
          Triplet<State, ComparableList<Symbol>, ComparableList<Movement>> currentState,
          Pair<State, ComparableList<Symbol>> nextState) {
    super(currentState, nextState);
  }

  /**
   * Constructor of the class.
   *
   * It takes the parameter of the
   * transition by separate.
   *
   * @param currentState current state of the transition.
   * @param readSymbolsOnTapes symbols read on the several tapes.
   * @param movements movements to be aplied on the several tapes.
   * @param nextState next state to transition.
   * @param writeSymbolsOnTapes symbols to be written on the tape.
   */
  public TMTransition(State currentState,
                      ComparableList<Symbol> readSymbolsOnTapes,
                      ComparableList<Movement> movements,
                      State nextState,
                      ComparableList<Symbol> writeSymbolsOnTapes) {
    super(new Triplet<>(currentState, readSymbolsOnTapes, movements),
            new Pair<>(nextState, writeSymbolsOnTapes));
  }

  /**
   * Sets the current state of the
   * transition.
   *
   * @param currentState current state of
   * @param readSymbolsOnTapes list of read symbols.
   * @param movements list of movements to perform.
   */
  public void setCurrentState(State currentState,
                              ComparableList<Symbol> readSymbolsOnTapes,
                              ComparableList<Movement> movements) {
    super.setCurrentState(new Triplet<>(currentState, readSymbolsOnTapes, movements));
  }

  /**
   * Sets the next state of the
   * transition.
   *
   * @param nextState of the transition
   * @param writeSymbolsOnTapes list of symbols to write.
   */
  public void setNextState(State nextState,
                           ComparableList<Symbol> writeSymbolsOnTapes) {
    super.setNextState(new Pair<>(nextState, writeSymbolsOnTapes));
  }

  /**
   * Return the current state node.
   *
   * @return current state node.
   */
  public State getCurrentStateNode() {
    return (State) getCurrentState().getValue(0);
  }

  /**
   * Return the read symbols.
   *
   * @return read symbols.
   */
  public ComparableList<Symbol> getReadSymbols() {
    return (ComparableList<Symbol>) getCurrentState().getValue(1);
  }

  /**
   * Return the movements to perform.
   *
   * @return movements to perform.
   */
  public ComparableList<Movement> getMovements() {
    return (ComparableList<Movement>) getCurrentState().getValue(2);
  }

  /**
   * Return next state node.
   *
   * @return next state node.
   */
  public State getNextStateNode() {
    return (State) getNextState().getValue(0);
  }

  /**
   * Return symbols to write.
   * @return symbols to write.
   */
  public ComparableList<Symbol> getSymbolsToWrite() {
    return (ComparableList<Symbol>) getNextState().getValue(1);
  }
}
