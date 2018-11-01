package turing.evaluation;

import org.javatuples.Pair;
import state.State;
import tape.Tape;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <h2>EvaluationStack</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class EvaluationStack
        extends Stack<Pair<State, List<Tape>>> {

  public void push(State state, List<Tape> tapes) {
    // TODO: check if it is actually a copy.
    List<Tape> copyTapes = new ArrayList<>();
    for (Tape tape : tapes)
      copyTapes.add(new Tape(tape));
    Pair<State, List<Tape>> pair = new Pair<>(state, copyTapes);
    push(pair);
  }

  public Pair<State, List<Tape>> pop() {
    return super.pop();
  }

  public State peekState() {
    return peek().getValue0();
  }

  public List<Tape> peekTapes() {
    return peek().getValue1();
  }
}
