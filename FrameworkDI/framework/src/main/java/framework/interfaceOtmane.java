package framework;

import java.lang.annotation.Retention;
import java.lang.annotation.* ;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE} )
public @interface interfaceOtmane {


	String msg();

}
