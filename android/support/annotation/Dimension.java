import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
package android.support.annotation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.LOCAL_VARIABLE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
public @interface Dimension
{
  public static final int A = 2;
  public static final int L = 0;
  public static final int TRUE = 1;
  int unit();
}
