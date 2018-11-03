package main;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {
  @Parameter(names = {"-v", "--verbose"},
    description = "trace mode, with specified tape.")
  public boolean verbose = false;

  @Parameter(names = {"-d", "--description"},
    description = "file name containing Turing machine description",
    required = true)
  public String descriptionFile;

  @Parameter(names = {"-t", "--tapes"},
  description = "file name containing the tapes description.")
  public String tapesFile;

  @Parameter(names = {"-h", "--help"},
    description = "help description",
    help = true)
  public boolean help;
}
