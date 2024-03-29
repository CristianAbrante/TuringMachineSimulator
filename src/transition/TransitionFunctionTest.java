package transition;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import static org.junit.Assert.*;

import org.javatuples.Tuple;
import org.junit.Before;
import org.junit.Test;
import state.State;
import symbol.Symbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class TransitionFunctionTest {

  ArrayList<Transition> test1Transitions;

  State q0 = new State("q0");
  State q1 = new State("q1");
  State q2 = new State("q2");

  Symbol a = new Symbol("a");
  Symbol b = new Symbol("b");
  Symbol A = new Symbol("A");
  Symbol B = new Symbol("B");
  Symbol S = new Symbol("S");

  @Before
  public void testSetting() {
    test1Transitions = new ArrayList<Transition>();

    Transition t1t1 = new Transition(
      new Triplet<>(q0, a, S),
      new Pair<>(q0, A));
    test1Transitions.add(t1t1);

    Transition t1t2 = new Transition(
      new Triplet<>(q0, a, A),
      new Pair<>(q0, A));
    test1Transitions.add(t1t2);

    Transition t1t3 = new Transition(
      new Triplet<>(q0, b, A),
      new Pair<>(q1, Symbol.EMPTY_SYMBOL));
    test1Transitions.add(t1t3);

    Transition t1t4 = new Transition(
      new Triplet<>(q1, b, A),
      new Pair<>(q1, Symbol.EMPTY_SYMBOL));
    test1Transitions.add(t1t4);

    Transition t1t5 = new Transition(
      new Triplet<>(q1, Symbol.EMPTY_SYMBOL, S),
      new Pair<>(q2, S));
    test1Transitions.add(t1t5);
  }

  @Test
  public void creationTest() {
    TransitionFunction test1Func = new TransitionFunction(test1Transitions);
  }

  @Test(expected = NullPointerException.class)
  public void creationTestError() {
    Transition t = null;
    new TransitionFunction(t);
  }

  @Test
  public void addTransitionTest() {
    TransitionFunction test1function = new TransitionFunction(test1Transitions);

    assertEquals(5, test1function.numberOfTransitions());

    Transition t1t4 = new Transition(
      new Triplet<>(q2, b, Symbol.EMPTY_SYMBOL),
      new Pair<>(q1, B));
    test1function.add(t1t4);
    assertEquals(6, test1function.numberOfTransitions());
  }

  @Test
  public void addSameTransitionMultipleTimesTest() {
    TransitionFunction test1function = new TransitionFunction(test1Transitions);

    assertEquals(5, test1function.numberOfTransitions());

    int times = 6;
    for (int i = 0; i < times; i++)
      test1function.add(test1Transitions.get(0));
    assertEquals(5, test1function.numberOfTransitions());
  }

  @Test
  public void addNonDeterministicTransitionsTest() {
    TransitionFunction test1function = new TransitionFunction(test1Transitions);
    assertEquals(5, test1function.numberOfTransitions());

    Transition tr1 = new Transition(test1Transitions.get(0).getCurrentState(),
      new Pair<>(q1, S));
    Transition tr2 = new Transition(test1Transitions.get(0).getCurrentState(),
      new Pair<>(q1, A));

    test1function.add(tr1);
    test1function.add(tr2);
    assertEquals(7, test1function.numberOfTransitions());
    assertEquals(3, test1function.getNextState(test1Transitions.get(0).getCurrentState()).size());
  }
}
