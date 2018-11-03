package turing;

import alphabet.Alphabet;
import com.jakewharton.fliptables.FlipTable;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import tape.Movement;
import tape.Tape;
import transition.ComparableList;
import turing.evaluation.EvaluationStack;
import turing.transition.TMTransition;
import turing.transition.TMTransitionFunction;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <h2>Turing Machine</h2>
 *
 * Turing Machine is a formal
 * computation machine, that
 * has a superior computation
 * power.
 *
 * Is able to recognize recursively
 * enumerable languages.
 *
 * This class is a simulator.
 *
 * @author	Cristian Abrante
 * @version 1.0.0
 */
public class TuringMachine {

  /**
   * Variables used to store
   * the elements of the Turing
   * Machine.
   */
  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet tapeAlphabet;
  private State initialState;
  private Symbol blankSymbol;
  private SetOfStates acceptingStates;
  private TMTransitionFunction transitionFunction;

  /**
   * Variables used to store the elements
   * for a evaluation.
   */
  private State currentEvaluationState;
  private List<Tape> currentEvaluationTapes;

  private EvaluationStack evaluationStack;
  private List<List<String>> trace;

  /**
   * Constructor of the class.
   *
   * It takes the formal elements
   * of a Turing Machine.
   *
   * @param setOfStates
   * @param inputAlphabet
   * @param tapeAlphabet
   * @param initialState
   * @param blankSymbol
   * @param acceptingStates
   * @param transitionFunction
   */
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

  /**
   * Getter of the set of states.
   *
   * @return set of states.
   */
  public SetOfStates getSetOfStates() {
    return setOfStates;
  }

  /**
   * Setter of the set of states.
   */
  public void setSetOfStates(SetOfStates setOfStates) {
    Utils.checkIfNull(setOfStates, "set of states can not be null.");
    this.setOfStates = setOfStates;
  }

  /**
   * Getter of the set of input alphabet.
   *
   * @return input alphabet.
   */
  public Alphabet getInputAlphabet() {
    return inputAlphabet;
  }

  /**
   * Setter of input alphabet
   */
  public void setInputAlphabet(Alphabet inputAlphabet) {
    Utils.checkIfNull(setOfStates, "input alphabet can not be null.");
    this.inputAlphabet = inputAlphabet;
  }

  /**
   * Getter of the tape alphabet.
   *
   * @return tape alphabet.
   */
  public Alphabet getTapeAlphabet() {
    return tapeAlphabet;
  }

  /**
   * setter of the tape alphabet.
   *
   * @param tapeAlphabet we want to set.
   */
  public void setTapeAlphabet(Alphabet tapeAlphabet) {
    Utils.checkIfNull(tapeAlphabet, "tape alphabet can not be null.");
    this.tapeAlphabet = tapeAlphabet;
  }

  /**
   * Getter of the initial state.
   * @return initial state.
   */
  public State getInitialState() {
    return initialState;
  }

  /**
   * Setter of the initial state.
   *
   * @param initialState we want to set.
   */
  public void setInitialState(State initialState) {
    Utils.checkIfNull(initialState, "initial state can not be null.");
    this.initialState = initialState;
  }

  /**
   * gets the blank symbol
   * @return blank symbol
   */
  public Symbol getBlankSymbol() {
    return blankSymbol;
  }

  /**
   * sets blank symbol.
   * @param blankSymbol we want to set.
   */
  public void setBlankSymbol(Symbol blankSymbol) {
    Utils.checkIfNull(blankSymbol, "blank symbol can not be null.");
    this.blankSymbol = blankSymbol;
  }

  /**
   * gets accepting states.
   * @return accepting states.
   */
  public SetOfStates getAcceptingStates() {
    return acceptingStates;
  }

  /**
   * sets accepting states.
   * @param acceptingStates we want to set.
   */
  public void setAcceptingStates(SetOfStates acceptingStates) {
    Utils.checkIfNull(acceptingStates, "accepting states can not be null.");
    this.acceptingStates = acceptingStates;
  }

  /**
   * gets transition function.
   * @return transition fuction.
   */
  public TMTransitionFunction getTransitionFunction() {
    return transitionFunction;
  }

  /**
   * sets transition function.
   * @param transitionFunction we want to set.
   */
  public void setTransitionFunction(TMTransitionFunction transitionFunction) {
    Utils.checkIfNull(transitionFunction, "transition function can not be null.");
    this.transitionFunction = transitionFunction;
  }

  /**
   * Evaluates the list of tapes.
   * @param tapes we want to evaluate.
   * @return {@code true} if tapes belong
   *          to language.
   */
  public boolean evaluate(List<Tape> tapes) {
    Utils.checkIfNull(tapes, "evaluate tape can not be null.");
    if (!Tape.allTapesAreReset(tapes)) {
      throw new IllegalArgumentException("tapes are not resets");
    }

    currentEvaluationState = initialState;
    currentEvaluationTapes = tapes;
    evaluationStack = new EvaluationStack();
    Set<TMTransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                                            getReadSymbols());

    trace = new ArrayList<>();
    updateTrace("-",
            initialState.toString(),
            getTapesString(),
            getTransitionsString(nextTransitions));

    return evaluateAllTransitions(nextTransitions);
  }

  /**
   * Prints the last execution table in a table
   * format.
   */
  public void printLastExecutionTrace() {
    String[] headers = {"used transition", "state", "tapes", "transitions"};
    String[][] data = new String[trace.size()][headers.length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < trace.get(i).size(); j++) {
        data[i][j] = trace.get(i).get(j);
      }
    }
    System.out.println(FlipTable.of(headers, data));
  }

  /**
   * To string method.
   * @return string representation of the
   * Turing machine.
   */
  @Override
  public String toString() {
    return  "Q = " + getSetOfStates() + "\n" +
            "Σ = " + getInputAlphabet() + "\n" +
            "τ = " + getTapeAlphabet() + "\n" +
            "q0 = " + getInitialState() + "\n" +
            "b = " + getBlankSymbol() + "\n" +
            "F = " + getAcceptingStates() + "\n" +
            "δ : \n" + getTransitionFunction();
  }

  private boolean evaluateAllTransitions(Set<TMTransition> transitions) {
    Utils.checkIfNull(transitions, "transitions can not be null.");
    if (transitions.isEmpty()) {
      return checkIfAcceptance();
    } else {
      evaluationStack.push(currentEvaluationState, currentEvaluationTapes);
      for (TMTransition t : transitions) {
        if (evaluateTransition(t)) {
          return true;
        } else {
          restoreActualState();
        }
      }
      evaluationStack.pop();
      return false;
    }
  }

  private boolean evaluateTransition(TMTransition t) {
    Utils.checkIfNull(t, "transition can not be null.");
    currentEvaluationState = t.getNextStateNode();
    writeSymbols(t.getSymbolsToWrite());
    performMovements(t.getMovements());
    Set<TMTransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                                            getReadSymbols());
    updateTrace(t.toString(),
            currentEvaluationState.toString(),
            getTapesString(),
            getTransitionsString(nextTransitions));
    return evaluateAllTransitions(nextTransitions);
  }

  private ComparableList<Symbol> getReadSymbols() {
    ComparableList<Symbol> readSymbols =
            new ComparableList<>();
    for (Tape tape : currentEvaluationTapes) {
      readSymbols.add(tape.read());
    }
    return readSymbols;
  }

  private void writeSymbols(ComparableList<Symbol> symbols) {
    Utils.checkIfNull(symbols, "movements can not be null.");
    if (symbols.size() != currentEvaluationTapes.size())
      throw new IllegalArgumentException("not same movements as tapes");

    for (int i = 0; i < symbols.size(); i++)
      currentEvaluationTapes.get(i).write(symbols.get(i));
  }

  private void restoreActualState() {
    currentEvaluationState = new State(evaluationStack.peekState());
    currentEvaluationTapes = evaluationStack.peekTapes();
  }

  private void performMovements(ComparableList<Movement> movements) {
    Utils.checkIfNull(movements, "movements can not be null.");
    if (movements.size() != currentEvaluationTapes.size())
      throw new IllegalArgumentException("not same movements as tapes");

    for (int i = 0; i < movements.size(); i++)
      currentEvaluationTapes.get(i).move(movements.get(i));
  }

  private boolean checkIfAcceptance() {
    return acceptingStates.containsById(
            currentEvaluationState.toString()
    );
  }

  private void updateTrace(String chosenTransition,
                           String state,
                           String tapes,
                           String transitions) {
    String[] arrayElements = {chosenTransition,
                           state,
                           tapes,
                           transitions};
    trace.add(new ArrayList<>(Arrays.asList(arrayElements)));
  }

  private String getTapesString() {
    String tapes = "";
    for (Tape tape : currentEvaluationTapes) {
      tapes += tape.toString() + "\n";
    }
    return tapes;
  }

  private String getTransitionsString(Set<TMTransition> transitions) {
    Utils.checkIfNull(transitions, "transitions can not be null.");
    if (transitions.isEmpty()) {
      return checkIfAcceptance() ?  "ω ∈ L" : "ω ∉ L";
    }
    String transitionsStr = "";
    for (TMTransition t : transitions) {
      transitionsStr += t.toString() + "\n";
    }
    return transitionsStr;
  }
}
