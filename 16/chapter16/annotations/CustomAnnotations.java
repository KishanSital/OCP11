package chapter16.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
public @interface CustomAnnotations {
    CustomAnnotation[] value();
}
