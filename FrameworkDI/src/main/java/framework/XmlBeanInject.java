package framework;


import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import metier.IMetier;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import dao.IDAO;

public class XmlBeanInject {
    
    private Map<String, Object> XmlbeanMap = new HashMap<String, Object>();

    public void init(String xml) {
    
        try {
            SAXReader read = new SAXReader();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // charger le fichier de configuration xml (config.xml)
            InputStream ins = classLoader.getResourceAsStream(xml);
            Document doc = read.read(ins);
            Element root = doc.getRootElement();
            Element foo;     
            for (Iterator i = root.elementIterator("bean"); i.hasNext();) {
                foo = (Element) i.next();
                Attribute id = foo.attribute("id");
                Attribute cls = foo.attribute("class");
                // retrouver l'objet de type Class en utilisant la technique de réflexion
                Class bean = Class.forName(cls.getText());              
                // Acceder a toutes les informations de cette classe
               BeanInfo info = java.beans.Introspector.getBeanInfo(bean);
               PropertyDescriptor pd[] = info.getPropertyDescriptors();
                Method mt = null;
                Object obj = bean.newInstance();// instanciation de ld objet de cette classe
                // lire les proprietés de ce bean 
                for (Iterator ite = foo.elementIterator("property"); ite.hasNext();) {
                    Element foo2 = (Element) ite.next();
                    //recupérer le nom du champ pour l'instancié
                    Attribute name = foo2.attribute("name");
                    //recupérer la reference de ce champ
                    Attribute ref = foo2.attribute("ref");
                    for (int k = 0; k <pd.length; k++) {
                        if (pd[k].getName().equalsIgnoreCase(name.getText())) {                        	
                            mt = pd[k].getWriteMethod();
                            mt.invoke(obj, XmlbeanMap.get(ref.getData()));}
                    }
                }
                XmlbeanMap.put(id.getText(), obj); //Ici on stock l'id et l'objet
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public Object getBean(String beanName) {Object obj = XmlbeanMap.get(beanName); return obj;
    }

    public static void main(String[] args) {
    	try {
    		
    		
     
		} catch (Exception e) {
			 System.out.println (e.getMessage()) ;
			 System.exit (-1) ;
		}
    }
}

