import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
package android.support.annotation;
import java.lang.annotation.Target;
import java.lang.annotation.Annotation;
@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.LOCAL_VARIABLE, java.lang.annotation.ElementType.FIELD})
public @interface ColorLong {}
