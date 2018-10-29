package turing;

import alphabet.Alphabet;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import turing.transition.TMTransitionFunction;
import utils.Utils;

public class TuringMachine {

  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet tapeAlphabet;
  private State initialState;
  private Symbol blankSymbol;
  private SetOfStates acceptingStates;
  private TMTransitionFunction transitionFunction;

  public TuringMachine(SetOfStates setOfStates,
                       Alphabet inputAlphabet,
                       Alphabet tapeAlphabet,
                       State initialState,
                       Symbol blankSymbol,
                       SetOfStates acceptingStates,
                       TMTransitionFunction transitionFunction) {
    setSetOfStates(setOfStates);
    setInputAlphabet(inputAlphabet);
    setTapeAlphabet(tapeAlphabet);
    setInitialState(initialState);
    setBlankSymbol(blankSymbol);
    setAcceptingStates(acceptingStates);
    setTransitionFunction(transitionFunction);
  }

  public SetOfStates getSetOfStates() {
    return setOfStates;
  }

  public void setSetOfStates(SetOfStates setOfStates) {
    Utils.checkIfNull(setOfStates, "set of states can not be null.");
    this.setOfStates = setOfStates;
  }

  public Alphabet getInputAlphabet() {
    return inputAlphabet;
  }

  public void setInputAlphabet(Alphabet inputAlphabet) {
    Utils.checkIfNull(setOfStates, "input alphabet can not be null.");
    this.inputAlphabet = inputAlphabet;
  }

  public Alphabet getTapeAlphabet() {
    return tapeAlphabet;
  }

  public void setTapeAlphabet(Alphabet tapeAlphabet) {
    Utils.checkIfNull(tapeAlphabet, "tape alphabet can not be null.");
    this.tapeAlphabet = tapeAlphabet;
  }

  public State getInitialState() {
    return initialState;
  }

  public void setInitialState(State initialState) {
    Utils.checkIfNull(initialState, "initial state can not be null.");
    this.initialState = initialState;
  }

  public Symbol getBlankSymbol() {
    return blankSymbol;
  }

  public void setBlankSymbol(Symbol blankSymbol) {
    Utils.checkIfNull(blankSymbol, "blank symbol can not be null.");
    this.blankSymbol = blankSymbol;
  }

  public SetOfStates getAcceptingStates() {
    return acceptingStates;
  }

  public void setAcceptingStates(SetOfStates acceptingStates) {
    Utils.checkIfNull(acceptingStates, "accepting states can not be null.");
    this.acceptingStates = acceptingStates;
  }

  public TMTransitionFunction getTransitionFunction() {
    return transitionFunction;
  }

  public void setTransitionFunction(TMTransitionFunction transitionFunction) {
    Utils.checkIfNull(transitionFunction, "transition function can not be null.");
    this.transitionFunction = transitionFunction;
  }
}
