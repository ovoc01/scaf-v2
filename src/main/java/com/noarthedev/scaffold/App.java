package com.noarthedev.scaffold;

import java.io.IOException;
import java.sql.SQLException;


import com.noarthedev.scaffold.run.GenerationSession;

public class App {

  public static void main(String[] args) throws SQLException, IOException {
      new GenerationSession().run("generate");
  }
}
