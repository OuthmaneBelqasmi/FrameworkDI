package metier;


import dao.IDAO;
import framework.beanOtmane;
import framework.interfaceOtmane;


@interfaceOtmane(msg="metier")
public class MetierImple implements IMetier{

	@beanOtmane(msg = "dao")
	IDAO dao;
	
	public double calcule() {
		return dao.getValue() * 4;
	}
	public IDAO getDao(IDAO d) {
		return  this.dao;
	}


}
