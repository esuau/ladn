package fr.ladn.carsharingclub.ing1.model;

import java.sql.Date;

public class ParamNbOp {
	private Date début, fin;
	private Technician tech;
	private Failure fail;
	public ParamNbOp(Date début2, Date fin2, Technician tech, Failure fail){
		this.début=début2;
		this.fin=fin2;
		this.tech=tech;
		this.fail=fail;
	}
	/**
	 * @return the début
	 */
	public Date getDébut() {
		return début;
	}
	/**
	 * @param début the début to set
	 */
	public void setDébut(Date début) {
		this.début = début;
	}
	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}
	
	/**
	 * @param fin the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}
	/**
	 * @return the tech
	 */
	public Technician getTech() {
		return tech;
	}
	/**
	 * @param tech the tech to set
	 */
	public void setTech(Technician tech) {
		this.tech = tech;
	}
	/**
	 * @return the fail
	 */
	public Failure getFail() {
		return fail;
	}
	/**
	 * @param fail the fail to set
	 */
	public void setFail(Failure fail) {
		this.fail = fail;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParamNbOp [début=" + début + ", fin=" + fin + ", tech=" + tech + ", fail=" + fail + "]";
	}
}
