package framework;



import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class AnnotationBeanInject{
    private Map<String, Object> AnnotationMapObject = new HashMap<String, Object>();
    ArrayList<String> path = new ArrayList<String>();
    ArrayList<File> packageList = new ArrayList<File>();

	public void Init(String ... pckg)  throws ExceptionBean
	{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for (String p : pckg)
			path.add(p.replace('.', '/'));
		for (String s : path) 
			packageList.add(new File(classLoader.getResource(s).getFile()));
	    for (File file : packageList) {
	    	for (String string : file.list()) {
	    		if(string.endsWith(".class")) { 
	    		
	    			try {
	    				String lc=string.substring(0, string.length()-6);
	    				Class cl=Class.forName(file.getName()+"."+lc);
	    				if(cl.isAnnotationPresent(interfaceOtmane.class))
	    				{
	    					Class c[]=cl.getInterfaces();
	    					if(c.length==1) 
	    					{			
	    						Object obj = cl.newInstance();
        						 Field champs[] = cl.getDeclaredFields() ;
        						 for (Field field : champs) {
        							 //Injecter l'objet en utilisant l'introspection ^^
            						 if(field.isAnnotationPresent(beanOtmane.class))
            						 {
            							 field.setAccessible(true) ;
            							 field.set(obj,getBean(field.getType().getSimpleName())); 
            						 }
								}					
        						 //throw an exception if there is more than class that implement the interface
        						 if(AnnotationMapObject.containsKey(c[0].getSimpleName())){
        							 throw new ExceptionBean("Exception : Plusieure classe implement l'interface "+c[0].getSimpleName());

        						 }else  AnnotationMapObject.put(c[0].getSimpleName(),obj);
	    					}

	    				}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
	    			} 
			}
	    }
	}
	 public Object getBean(Class clc) {
		    
	        Object obj = AnnotationMapObject.get(clc.getSimpleName());
	        System.out.println(clc.getSimpleName());
	        return obj;
	    }
    public Object getBean(String beanName) {
    
        Object obj = AnnotationMapObject.get(beanName);
       // System.out.println(clc.getSimpleName());

        return obj;
    }
}