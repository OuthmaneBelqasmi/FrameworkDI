package dao;

import framework.interfaceOtmane;


@interfaceOtmane(msg = "dao")
public class DAOImple implements IDAO {
	public double getValue() {
		double data = 0.25;
		return data;
	}
}
