package turing.reader;

import alphabet.Alphabet;
import reader.AbstractReader;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import tape.Movement;
import tape.Tape;
import transition.ComparableList;
import transition.Transition;
import transition.TransitionFunction;
import turing.TuringMachine;
import turing.transition.TMTransition;
import turing.transition.TMTransitionFunction;
import utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TMReader extends AbstractReader {

  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet tapeAlphabet;
  private State initialState;
  private Symbol blankSymbol;
  private SetOfStates acceptingStates;
  private TMTransitionFunction transitionFunction;

  private int numberOfTapes;

  private TuringMachine turingMachine;

  public TMReader(FileReader file) {
    super(file);
  }

  public TMReader(String definition) {
    super(definition);
  }

  public TuringMachine getReadTM() {
    return turingMachine;
  }

  @Override
  public void setDefinition(String definition) {
    super.setDefinition(definition);

    String currentLine;

    // Line 0 contains the definition of the set of states.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the set of states.");

    setOfStates = new SetOfStates(super.readStates(currentLine));

    // line 1 contains the input alphabet.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the input alphabet.");

    inputAlphabet = new Alphabet(super.readSymbols(currentLine));

    // line 2 contains the tape alphabet.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the tape alphabet.");

    tapeAlphabet = new Alphabet(super.readSymbols(currentLine));

    // line 3 contains the initial state
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the initial state.");

    initialState = getInitialState(currentLine);

    // Line 4 contains the blank symbol.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the blank symbol.");

    blankSymbol = getBlankSymbol(currentLine);
    tapeAlphabet.add(blankSymbol);
    Tape.BLANK_SYMBOL = blankSymbol;

    // Line 5 contains the final states definition.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the set of states.");

    acceptingStates = new SetOfStates(getAcceptingStates(currentLine));

    // Line 6 contains the number of tapes that the turing machine accepts.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected number of tapes accepted.");

    numberOfTapes = Integer.parseInt(currentLine);

    // Line 7 and beyond defines the transition.
    List<TMTransition> transitions = new ArrayList<>();
    while (hasMoreLines()) {
      currentLine = getNextLine();
      TMTransition t = (TMTransition) readTransition(currentLine);
      transitions.add(t);
    }
    transitionFunction = new TMTransitionFunction(transitions);

    turingMachine = new TuringMachine(setOfStates,
      inputAlphabet,
      tapeAlphabet,
      initialState,
      blankSymbol,
      acceptingStates,
      transitionFunction);
  }

  @Override
  protected Transition readTransition(String transitionString) {
    List<String> tokenizedString = super.tokenizeString(transitionString);

    // Each transition must have 3 times the number of
    // tapes adding 2 by the specified states.
    if (tokenizedString.size() != 2 + 3 * numberOfTapes)
      throw new IllegalArgumentException
        ("Transition must specify as elements as the number of tapes");

    // First element is the current state of the transition.
    State currentState = setOfStates.getById(tokenizedString.get(0));

    // Second element is the list of read symbols.
    ComparableList<Symbol> readSymbols = new ComparableList<>();
    for (int i = 1; i <= numberOfTapes; ++i) {
      Symbol s = inputAlphabet.getByValue(tokenizedString.get(i));
      Utils.checkIfNull(s, "symbol not belongs to input alphabet.");
      readSymbols.add(s);
    }

    // Third is the next state of the transition.
    State nextState = setOfStates.getById(tokenizedString.get(numberOfTapes + 1));

    // Fourth are the symbols to write on the tape.
    ComparableList<Symbol> toWriteSymbols = new ComparableList<>();
    for (int i = numberOfTapes + 2; i <= 2 * numberOfTapes + 1; ++i) {
      Symbol s = tapeAlphabet.getByValue(tokenizedString.get(i));
      Utils.checkIfNull(s, "symbol not belongs to tape alphabet.");
      toWriteSymbols.add(s);
    }

    // Fifth are the movements to perform
    ComparableList<Movement> movements = new ComparableList<>();
    for (int i = 2 * numberOfTapes + 2; i < tokenizedString.size(); ++i) {
      Movement m = Movement.of(tokenizedString.get(i));
      Utils.checkIfNull(m, "not valid movement.");
      movements.add(m);
    }

    return new TMTransition(currentState,
                            readSymbols,
                            nextState,
                            toWriteSymbols,
                            movements);
  }

  private State getInitialState(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    if (tokenizedString.size() != 1)
      throw new IllegalArgumentException("defining more than one initial state.");

    return setOfStates.getById(tokenizedString.get(0));
  }

  private Symbol getBlankSymbol(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    if (tokenizedString.size() != 1)
      throw new IllegalArgumentException("defining more than one blank symbol.");

    String symbol = tokenizedString.get(0);
    return symbol.equals(Symbol.EMPTY_SYMBOL_VALUE) ?
      Symbol.EMPTY_SYMBOL :
      new Symbol(symbol);
  }

  private List<State> getAcceptingStates(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    List<State> states = new ArrayList<>();
    for (String token : tokenizedString) {
      State acceptingState = setOfStates.getById(token);
      if (acceptingState != null) {
        states.add(acceptingState);
      } else {
        throw new IllegalArgumentException("accepting state " + token + " is not defined on the set of states");
      }
    }
    return states;
  }
}
