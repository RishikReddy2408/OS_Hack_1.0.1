package android.support.annotation;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
public @interface LongDef
{
  boolean flag();
  
  long[] value();
}
