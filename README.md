# FrameworkDI
This project is PoC (Proof of Concept) of Dependency Injection in java using two methods :<br>


<hr>
1- XML File configuration<br>
2- Annotation

for xml file configuration the framwork inject the field using setter method and the introspection for the annotation.

<hr>

## How to use

### + Using XML File configuration

1. You have to define in xml file all references of classes that you want inject to
<br>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
	<bean id="d" class="dao.DAOImple"></bean>
	<bean id="metier" class="metier.MetierImple">
		<property name="dao" ref="d"></property>
	</bean>
</beans>
```
<br>
2. This method __getBean("metier")__  will return the instance of class that defined in xml file and whose id "metier"
<br>

Main test :

```java
public class main {
	public static void main(String[] args) {

		XmlBeanInject inject = new XmlBeanInject();
		inject.init("config.xml");
		IMetier metier = (IMetier) inject.getBean("metier");
	}
} 
```

### + Using annotation

The __@beanOtmane__ annotation is applied on a field ,this annotation tells the framework that this field must be injected by an instance of a class that implements the interface of field type.
 
The __@interfaceOtmane__ annotation is applied on a class that we want to get an instance of it , and inject into field.

### Exemple

__IDAO__ interface :
```java

public interface IDAO {
	public  double getValue();
}
```
The class that implement __IDAO__ interface should be annotated by  __@interfaceOtmane__ ,like that the framework know that it is necessary to instantiate this class and keep it,to inject it later:
```java
@interfaceOtmane(msg = "dao")
public class DAOImple implements IDAO {
	public double getValue() {
		double data = 0.25;
		return data;
	}
}
```


The field __IDAO__ field should have __@beanOtmane__ annotation to be injected by instance of __DAOImple__.

```java
public class MetierImple {

	@beanOtmane(msg = "dao")
	IDAO dao;
}
```
Main test:
<br>
The __init__ method will scan all  packages that it receives in parameter and then the framwork will automatically search about annotation in classes and fields.
The __getBean(IDAO.class)__ will return the instance of class that implement IDAO interface 


```java
public class main {
	public static void main(String[] args){

		AnnotationBeanInject inject = new AnnotationBeanInject();
		inject.Init("dao");
		IDAO dao=(IDAO) inject.getBean(IDAO.class);	
	}
}
```

