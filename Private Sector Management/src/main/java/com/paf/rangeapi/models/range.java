package com.paf.rangeapi.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class range {

	private int RANGEID;
	private int UPPERLIMIT;
	private int LOWERLIMIT;
	private double UNITPRICE;

	public range() {
		super();
	}

	public range(int rANGEID, int uPPERLIMIT, int lOWERLIMIT, double uNITPRICE) {
		super();
		RANGEID = rANGEID;
		UPPERLIMIT = uPPERLIMIT;
		LOWERLIMIT = lOWERLIMIT;
		UNITPRICE = uNITPRICE;
	}

	public int getRANGEID() {
		return RANGEID;
	}

	public void setRANGEID(int rANGEID) {
		RANGEID = rANGEID;
	}

	public int getUPPERLIMIT() {
		return UPPERLIMIT;
	}

	public void setUPPERLIMIT(int uPPERLIMIT) {
		UPPERLIMIT = uPPERLIMIT;
	}

	public int getLOWERLIMIT() {
		return LOWERLIMIT;
	}

	public void setLOWERLIMIT(int lOWERLIMIT) {
		LOWERLIMIT = lOWERLIMIT;
	}

	public double getUNITPRICE() {
		return UNITPRICE;
	}

	public void setUNITPRICE(double uNITPRICE) {
		UNITPRICE = uNITPRICE;
	}

}
