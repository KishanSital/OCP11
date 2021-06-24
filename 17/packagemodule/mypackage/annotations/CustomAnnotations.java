package mypackage.annotations;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
public @interface CustomAnnotations {
    CustomAnnotation[] value();
}
