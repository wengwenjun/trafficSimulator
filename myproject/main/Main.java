package myproject.main;


import myproject.main.Control;
import myproject.ui.UI;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    UI ui;
	    ui = (UI) new myproject.ui.Menu();

	    Control control = new Control(ui);
	    control.run();
	}
}
