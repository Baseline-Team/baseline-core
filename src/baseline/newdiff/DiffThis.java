package baseline.newdiff;

import baseline.newdiff.Diff;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DiffThis {
	Class value() default baseline.newdiff.Diff.class;

}
