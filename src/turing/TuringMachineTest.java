package turing;

import org.junit.Before;
import org.junit.Test;
import tape.Tape;
import tape.reader.TapeReader;
import turing.reader.TMReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * <h2>TuringMachineTest</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TuringMachineTest {
  TapeReader tapeReader;
  List<Tape> tapes;
  TMReader reader;

  TuringMachine TM1;
  TuringMachine TM2;
  TuringMachine TM3;
  TuringMachine TM4;
  TuringMachine TM5;
  TuringMachine TM6;
  TuringMachine TM7;
  TuringMachine TM8;

  @Before
  public void SetUp() throws FileNotFoundException {
    tapeReader = new TapeReader("");

    reader = new TMReader(new FileReader("test/TM1.txt"));
    TM1 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TM2.txt"));
    TM2 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TM3.txt"));
    TM3 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TM4.txt"));
    TM4 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TM5.txt"));
    TM5 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TM6.txt"));
    TM6 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TMTest1.txt"));
    TM7 = reader.getReadTM();

    reader = new TMReader(new FileReader("test/TMTest2.txt"));
    TM8 = reader.getReadTM();
  }

  @Test
  public void TM1Test() {
    tapeReader.setDefinition("x x y y z");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM1.evaluate(tapes));
    TM1.printLastExecutionTrace();

    tapeReader.setDefinition("x x y y");
    tapes = tapeReader.getReadTapes();
    assertFalse(TM1.evaluate(tapes));
    TM1.printLastExecutionTrace();

    tapeReader.setDefinition("x x y y z x y");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM1.evaluate(tapes));
    TM1.printLastExecutionTrace();
  }

  @Test
  public void TM2Test() {
    tapeReader.setDefinition("x x z x x y");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM2.evaluate(tapes));
    TM2.printLastExecutionTrace();

    tapeReader.setDefinition("x x z x x z z z");
    tapes = tapeReader.getReadTapes();
    assertFalse(TM2.evaluate(tapes));
    TM2.printLastExecutionTrace();
  }

  @Test
  public void TM3Test() {
    tapeReader.setDefinition("1 1 . 1 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM3.evaluate(tapes));
    TM3.printLastExecutionTrace();

    tapeReader.setDefinition("1 1 . 1 1 1 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM3.evaluate(tapes));
    TM3.printLastExecutionTrace();

    tapeReader.setDefinition("1 . 1 1 1 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM3.evaluate(tapes));
    TM3.printLastExecutionTrace();

    tapeReader.setDefinition("1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM3.evaluate(tapes));
    TM3.printLastExecutionTrace();
  }

  @Test
  public void TM5Test() {
    tapeReader.setDefinition(" a a b b c c\n.");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM5.evaluate(tapes));
    TM5.printLastExecutionTrace();
  }

  @Test
  public void TM6Test() {
    tapeReader.setDefinition("0 0 1\n0 1 1\n.");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM6.evaluate(tapes));
    TM6.printLastExecutionTrace();
  }

  @Test
  public void TM7Test() {
    tapeReader.setDefinition("a b a b");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM7.evaluate(tapes));
    TM7.printLastExecutionTrace();

    tapeReader.setDefinition("a a b a b b b c a b");
    tapes = tapeReader.getReadTapes();
    assertFalse(TM7.evaluate(tapes));
    TM7.printLastExecutionTrace();

    tapeReader.setDefinition("");
    tapes = tapeReader.getReadTapes();
    assertFalse(TM7.evaluate(tapes));
    TM7.printLastExecutionTrace();

    tapeReader.setDefinition("a a a a b c c c a b a b a b b b");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM7.evaluate(tapes));
    TM7.printLastExecutionTrace();
  }


  @Test
  public void TM8Test() {
    tapeReader.setDefinition("0 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM8.evaluate(tapes));
    TM8.printLastExecutionTrace();

    tapeReader.setDefinition("0 1 0 0 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM8.evaluate(tapes));
    TM8.printLastExecutionTrace();

    tapeReader.setDefinition("1 1 0 0 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM8.evaluate(tapes));
    TM8.printLastExecutionTrace();

    tapeReader.setDefinition("0 0 0");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM8.evaluate(tapes));
    TM8.printLastExecutionTrace();

    tapeReader.setDefinition("1 1 1");
    tapes = tapeReader.getReadTapes();
    assertTrue(TM8.evaluate(tapes));
    TM8.printLastExecutionTrace();
  }
}
