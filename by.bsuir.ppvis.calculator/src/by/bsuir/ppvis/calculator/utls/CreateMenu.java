package by.bsuir.ppvis.calculator.utls;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import by.bsuir.ppvis.calculator.controller.Controller;

public class CreateMenu {
	public static JMenu createMenu(MainMenuEnum item, Controller controller){
		ResourceBundle bundle = ResourceBundle.getBundle("enbase", Locale.getDefault());
		JMenu menu = new JMenu(bundle.getString(item.getLabel()));
		ChildMenus m;
		JMenuItem tempItem;
		for(int i=0;i<item.getChildList().size();i++){
			m = item.getChildList().get(i);
			tempItem = new JMenuItem(bundle.getString(m.getLabel()),m.getIcon());
			tempItem.addActionListener(controller.getListener(m));
			menu.add(tempItem);
		}
		
		return menu;
	}
	
}
