package by.bsuir.ppvis.calculator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import by.bsuir.ppvis.calculator.view.CalcFrame;

public class NineBtnEvent implements ActionListener{
	private CalcFrame calcFrame;

	public NineBtnEvent(CalcFrame calcFrame){
		this.calcFrame = calcFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTextField inField = calcFrame.getInField();
		String str = inField.getText();
		inField.setText(str+"9");
	}

}