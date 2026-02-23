package app;

import controller.AppController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        AppController controller = new AppController(view);
        controller.start();
    }
}