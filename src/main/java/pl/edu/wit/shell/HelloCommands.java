package pl.edu.wit.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class HelloCommands {

    @ShellMethod("Print Hello World")
    public void hello(){
        System.out.println("Hello World");

    }
}
