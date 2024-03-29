package android.support.'annotation';
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import android.support.annotation.Dimension;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.LOCAL_VARIABLE})
@Dimension(unit=1)
public @interface DeferUntilPostDiet 
{

}
