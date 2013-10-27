package notepad.view;
import java.awt.FontMetrics;

import by.bsuir.ppvis.utls.KeyChar;

import notepad.model.Document;

public class Carret {
	private int currentLine;
	private int pos;
	private int x, y;
	private Document doc;
	private FontMetrics fontMetrics;
	public Carret(Document d){
		x = 0;
		y = 0;
		doc = d;
		currentLine = 1;
		pos = 0;
	}
	
	public void setFontMetrics(FontMetrics fm){
		fontMetrics = fm;
	}
	
	public void setPos(int p){
		if(p<0) pos = 0;
		else if(p>doc.getLength()) pos = doc.getLength()-1;
		else pos = p;
	}
	
	public void setLine(int l){
		currentLine = l;
	}
	
	public void incLineNumber(){
		++currentLine;
	}
	
	public void decLineNumber(){
		currentLine = (currentLine>0)?(currentLine-1):(currentLine);
	}
	
	public int getPos(){
		return pos;
	}
	
	public int getLineCount(){
		int ln = 1, p = 0;
	    while (p < doc.getLength()) {
			if (doc.getCharAt(p) == KeyChar.NEWLINE)
			    ++ln;
			++p;
	    }
		return ln;
	}
	
	public void inc(){
		if(pos<=doc.getLength()-1) ++pos;
	}
	
	public void dec(){
		if(pos>0)--pos;
	}
	
	public int getLineNumber(){
		int ln = 1, p = 0;
	    while (p < pos) {
			if (doc.getCharAt(p) == KeyChar.NEWLINE)
			    ++ln;
			++p;
	    }
	    currentLine = ln;
	    
		return currentLine;
	}
	
	public int getLineByY(int y){
		int ln = 1;
		while(ln<getLineCount()){
			if(y<=ln*doc.getLineHeight()&&y>=ln*doc.getLineHeight()-doc.getLineHeight()){
				break;
			}
			++ln;
		}
		return ln;
	}
	
	public int getPos(int x, int line){
		int p = 0;
	    int n = 1, curXPos = 0;
	   
	    while (p < doc.getLength()) {
		    if (n != line) {
				if (doc.getCharAt(p) == KeyChar.NEWLINE)
				    ++n;
		    } else {
		    	break;
		    }
		    ++p;
		}
		while (curXPos<x&&p<doc.getLength()) {
		    if(doc.getCharAt(p)!=KeyChar.NEWLINE) curXPos += fontMetrics.charWidth(doc.getCharAt(p++)) + 1;
		    else break;
		}
		
		return p;
	}
	
	public void setXPos(int x){
		this.x = x;
	}
	
	public void setYPos(int y){
		this.y = y;
	}
	
	public int getXPos(){
		int p = 0;
	    int n = 1, curXPos = 0;
	   
	    while (p < doc.getLength()) {
		    if (n != getLineNumber()) {
				if (doc.getCharAt(p) == KeyChar.NEWLINE)
				    ++n;
		    } else {
		    	break;
		    }
		    ++p;
		}
		while (p < this.pos) {
		    curXPos += fontMetrics.charWidth(doc.getCharAt(p++)) + 1;
		}
		
		return curXPos;
	}
	
	public int getYPos(){
		int i = 0, lineNumb = 1;
		while(i<pos){
			if (doc.getCharAt(i) == KeyChar.NEWLINE)
			    ++lineNumb;
			++i;
		}
		
		return lineNumb*doc.getLineHeight();
	}
	
	public int getPosByCoords(int x, int y){
		return getPos(x,getLineByY(y));
	}
	
	public void moveUp(){
		int p = 0;
		int n = 1, curXPos = 0;
		boolean status = false;
		
		curXPos = getXPos();
		int newXPos = 0;
		while (p < doc.getLength()) {
		    if (n != getLineNumber()-1) {
			if (doc.getCharAt(p) == KeyChar.NEWLINE)
			    ++n;
		    } else {
		    	status = true;
		    	break;
		    }
		    ++p;
		}
		if (status) {
		    while (newXPos < curXPos) {
				if (doc.getCharAt(p) == KeyChar.NEWLINE)
				    break;
				newXPos += fontMetrics.charWidth(doc.getCharAt(p++)) + 1;
		    }
		}
		this.pos = p;
		decLineNumber();
	}
	
	public void moveDown(){
		int p = 0;
		int n = 1, curXPos = 0;
		boolean status = false;
		
		curXPos = getXPos();
		int newXPos = 0;

		while (p < doc.getLength()) {
		    if (n != getLineNumber()+1) {
				if (doc.getCharAt(p) == KeyChar.NEWLINE)
				    ++n;
		    } else {
				status = true;
				break;
		    }
		    ++p;
		}
		if (status) {
		    while (newXPos < curXPos) {
				if (doc.getCharAt(p) == KeyChar.NEWLINE)
				    break;
				newXPos += fontMetrics.charWidth(doc.getCharAt(p++)) + 1;
		    }
		}
		this.pos = p;
		incLineNumber();

	}
}
