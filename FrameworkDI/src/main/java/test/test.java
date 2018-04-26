package test;

import metier.IMetier;
import framework.AnnotationBeanInject;
import framework.ExceptionBean;
import framework.XmlBeanInject;

public class test {
	public static void main(String[] args) throws ExceptionBean {
		AnnotationBeanInject inject = new AnnotationBeanInject();
		inject.Init("dao","metier");
		IMetier metier = (IMetier) inject.getBean("IMetier");
		System.out.println(metier.calcule());
		System.out.println("ddd   ");
		

	}
	

}
