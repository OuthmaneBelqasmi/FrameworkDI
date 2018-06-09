package test;

import metier.IMetier;
import framework.BeanContainer;
import framework.ExceptionBean;

public class test {
	public static void main(String[] args) throws ExceptionBean {
		BeanContainer inject = new BeanContainer();
		inject.InitBean("dao","metier");
		IMetier metier = (IMetier) inject.getBean("IMetier");
		System.out.println(metier.calcule());
		System.out.println("ddd   ");  
		
	}
}
