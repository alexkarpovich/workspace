package notepad.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import notepad.controller.Controller.KeyListen;
import notepad.controller.Controller.MyMouseAdapter;
import notepad.controller.Controller.MyMouseMotion;
import by.bsuir.ppvis.utls.KeyChar;
import notepad.model.Document;

public final class TextView extends JPanel {
	private Document doc;
	private Carret carret;
	private MainFrame parent;
	
	
	public TextView(){
		setLayout(new FlowLayout());
		setSize(1000,1000);
		doc = new Document();
		carret = new Carret(doc);
		setBackground(Color.WHITE);
		addKeyListener(new KeyListen(this));
		addMouseMotionListener(new MyMouseMotion(this));
		addMouseListener(new MyMouseAdapter(this));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		carret.setFontMetrics(g.getFontMetrics());
		doc.setFontMetrics(g.getFontMetrics());
		carret.setLine(1);
		redrawArea(g);
	}
	
	public Document getDocument(){
		return doc;
	}
	
	public Carret getCarret(){
		return carret;
	}
	
	public void clearSpace(){
		doc.setModel(null);	
	}
	
	public int getCarretPos(){
		return carret.getPos();
	}
	
	public void setParent(MainFrame frame){
		parent = frame;
	}
	
	public MainFrame parent(){
		return parent;
	}
	
	private void redrawArea(Graphics g){
		int curX = 0, curPos = 0 ;
		char symbol[] = new char[1];
		int line = 1;
		
		while (curPos < doc.getLength()) {
		    symbol[0] = doc.getCharAt(curPos);
		    
		    switch (doc.getCharAt(curPos)) {
			    case KeyChar.NEWLINE: {
					++line;
					carret.incLineNumber();
					curX = 0;
					//carret.setY((line - 1) * line_height + 5);
					break;
			    }
			    			    
			    case KeyChar.TAB: {
					int step = 4 * doc.getCharWidth(' ') + 4;
					curX += step;
					break;
			    }
			    default: {
					int step = doc.getCharWidth(symbol[0]) + 1;
					
					defaultDrawAction(g, curX, line, step, curPos);
					
					g.drawChars(symbol, 0, 1, curX, line * (doc.getLineHeight()));
					curX += step;
					carret.setXPos(curX);
					carret.setYPos((line - 1) * doc.getLineHeight() + 5);
					
			    }
		    }
			++curPos;
			
			if (curPos == carret.getPos()) {
				g.drawLine(carret.getXPos(), carret.getYPos(), carret.getXPos(),
			   			carret.getYPos() - doc.getLineHeight());
			}
		}
		
	}
	
	private void defaultDrawAction(Graphics g, int curX, int line, int step, int i){
		if (!(doc.getBegSelectPos() == -1 || doc.getEndSelectPos() == -1)) {
		    if (doc.getBegSelectPos() > doc.getEndSelectPos()) {
			if (i >= doc.getEndSelectPos() && i <= doc.getBegSelectPos())
			    drawSelection(g, curX, line, step);
		    } else if (i <= doc.getEndSelectPos() && i >= doc.getBegSelectPos())
		    	drawSelection(g, curX, line, step);
		}
	}
	
	private void drawSelection(Graphics g, int x, int line, int step) {
		g.setColor(Color.magenta);
		g.fillRect(x, (line-1) * doc.getLineHeight(), step, doc.getLineHeight()+doc.getFontMetrics().getDescent());
		g.setColor(Color.black);
	}
	
	
}
