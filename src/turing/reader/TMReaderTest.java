package turing.reader;

import alphabet.Alphabet;
import org.junit.Before;
import org.junit.Test;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import tape.Movement;
import transition.ComparableList;
import transition.Transition;
import turing.TuringMachine;
import turing.transition.TMTransition;
import turing.transition.TMTransitionFunction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class TMReaderTest {

  TMReader reader;
  TuringMachine TM1;

  @Before
  public void setUp() throws FileNotFoundException {
    reader = new TMReader(new FileReader("test/TM1.txt"));
    TM1 = reader.getReadTM();
  }

  @Test
  public void TM1CreationTest() {
    // assert set of states.
    List<State> states = new ArrayList<>();
    states.add(new State("q0"));
    states.add(new State("q1"));

    assertEquals(new SetOfStates(states), TM1.getSetOfStates());

    // assert input alphabet.
    List<Symbol> inputSymbols = new ArrayList<>();
    inputSymbols.add(new Symbol("x"));
    inputSymbols.add(new Symbol("y"));
    inputSymbols.add(new Symbol("z"));

    assertEquals(new Alphabet(inputSymbols), TM1.getInputAlphabet());

    // assert input alphabet.
    List<Symbol> tapeSymbols = new ArrayList<>();
    tapeSymbols.add(new Symbol("x"));
    tapeSymbols.add(new Symbol("y"));
    tapeSymbols.add(new Symbol("z"));
    tapeSymbols.add(Symbol.EMPTY_SYMBOL);

    assertEquals(new Alphabet(tapeSymbols), TM1.getTapeAlphabet());

    // assert initial state.
    assertEquals(TM1.getSetOfStates().getById("q0"), TM1.getInitialState());

    // assert blank symbol.
    assertEquals(Symbol.EMPTY_SYMBOL, TM1.getBlankSymbol());

    // assert accepting states.
    List<State> acceptingStates = new ArrayList<>();
    acceptingStates.add(new State("q1"));

    assertEquals(new SetOfStates(acceptingStates), TM1.getAcceptingStates());

    // Assert transitions
    Set<TMTransition> transitions = new TreeSet<>();

    ComparableList<Symbol> readSymbols = new ComparableList<>();
    readSymbols.add(new Symbol("x"));
    ComparableList<Symbol> toWriteSymbols = new ComparableList<>();
    toWriteSymbols.add(new Symbol("x"));
    ComparableList<Movement> movements = new ComparableList<>();
    movements.add(Movement.RIGHT);

    transitions.add(new TMTransition(
      TM1.getInitialState(),
      readSymbols,
      TM1.getInitialState(),
      toWriteSymbols,
      movements));

    ComparableList<Symbol> readSymbols1 = new ComparableList<>();
    readSymbols1.add(new Symbol("y"));

    ComparableList<Symbol> toWriteSymbols1 = new ComparableList<>();
    toWriteSymbols1.add(new Symbol("y"));

    ComparableList<Movement> movements1 = new ComparableList<>();
    movements1.add(Movement.RIGHT);

    transitions.add(new TMTransition(
      TM1.getInitialState(),
      readSymbols1,
      TM1.getInitialState(),
      toWriteSymbols1,
      movements1));

    ComparableList<Symbol> readSymbols2 = new ComparableList<>();
    readSymbols2.add(new Symbol("z"));

    ComparableList<Symbol> toWriteSymbols2 = new ComparableList<>();
    toWriteSymbols2.add(new Symbol("z"));

    ComparableList<Movement> movements2 = new ComparableList<>();
    movements2.add(Movement.RIGHT);

    transitions.add(new TMTransition(
      TM1.getSetOfStates().getById("q0"),
      readSymbols2,
      TM1.getSetOfStates().getById("q1"),
      toWriteSymbols2,
      movements2));

    assertEquals(new TMTransitionFunction(transitions), TM1.getTransitionFunction());
  }
}
