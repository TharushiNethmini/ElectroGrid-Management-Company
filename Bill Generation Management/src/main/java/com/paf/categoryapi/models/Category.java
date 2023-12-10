package com.paf.categoryapi.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {

	private int CATEGORYID;
	private String CATEGORYNAME;
	private double FIXEDCHARGE;
	private double UNITCHARGE;
	private double TAXAMOUNT;
	private double RELIEF;

	public Category() {
		super();
	}

	public Category(int cATEGORYID, String cATEGORYNAME, double fIXEDCHARGE, double uNITCHARGE, double tAXAMOUNT,
			double rELIEF) {
		super();
		CATEGORYID = cATEGORYID;
		CATEGORYNAME = cATEGORYNAME;
		FIXEDCHARGE = fIXEDCHARGE;
		UNITCHARGE = uNITCHARGE;
		TAXAMOUNT = tAXAMOUNT;
		RELIEF = rELIEF;
	}

	public int getCATEGORYID() {
		return CATEGORYID;
	}

	public void setCATEGORYID(int cATEGORYID) {
		CATEGORYID = cATEGORYID;
	}

	public String getCATEGORYNAME() {
		return CATEGORYNAME;
	}

	public void setCATEGORYNAME(String cATEGORYNAME) {
		CATEGORYNAME = cATEGORYNAME;
	}

	public double getFIXEDCHARGE() {
		return FIXEDCHARGE;
	}

	public void setFIXEDCHARGE(double fIXEDCHARGE) {
		FIXEDCHARGE = fIXEDCHARGE;
	}

	public double getUNITCHARGE() {
		return UNITCHARGE;
	}

	public void setUNITCHARGE(double uNITCHARGE) {
		UNITCHARGE = uNITCHARGE;
	}

	public double getTAXAMOUNT() {
		return TAXAMOUNT;
	}

	public void setTAXAMOUNT(double tAXAMOUNT) {
		TAXAMOUNT = tAXAMOUNT;
	}

	public double getRELIEF() {
		return RELIEF;
	}

	public void setRELIEF(double rELIEF) {
		RELIEF = rELIEF;
	}

}
