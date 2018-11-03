package main;

import com.beust.jcommander.JCommander;
import tape.Tape;
import tape.reader.TapeReader;
import turing.TuringMachine;
import turing.reader.TMReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] argv) throws FileNotFoundException {
    Args args = new Args();
    JCommander.newBuilder()
      .addObject(args)
      .build()
      .parse(argv);

    if (args.help) {
      JCommander jc = new JCommander(args);
      jc.setProgramName("TuringMachineSimulator.jar");
      jc.usage();
      return;
    }

    TMReader reader = new TMReader(new FileReader(args.descriptionFile));
    TuringMachine turingMachine = reader.getReadTM();

    TapeReader tapesReader;
    List<Tape> tapes;
    if (args.tapesFile != null) {
      tapesReader = new TapeReader(new FileReader(args.tapesFile));
    } else {
      Scanner scanner = new Scanner(System.in);
      System.out.println("introduce tape (separated by spaces) > ");
      tapesReader = new TapeReader(scanner.nextLine());
    }
    tapes = tapesReader.getReadTapes();

    boolean evaluationResult = turingMachine.evaluate(tapes);

    if (args.verbose) {
      turingMachine.printLastExecutionTrace();
    }
    System.out.println("Turing machine definition:");
    System.out.println(turingMachine.toString());

    System.out.println("Turing machine evaluation determine that tapes "
      + (evaluationResult ? "" : "do NOT ")
      + "belong to the language.");
    System.out.println();
    System.out.println("tapes:");
    for (Tape tape : tapes) {
      System.out.println(tape.toString());
    }

    System.out.println();
    System.out.println("Done by: Cristian Abrante");
  }
}
