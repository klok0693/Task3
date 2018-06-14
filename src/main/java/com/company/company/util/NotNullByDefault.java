package com.company.company.util;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by klok on 15.7.17.
 * This annotation can be applied to a package, class or method to indicate that the class fields,
 * method return types and parameters in that element are not null by default
 */
@Documented
@Nonnull
@TypeQualifierDefault(
        {
                ElementType.ANNOTATION_TYPE,
                ElementType.CONSTRUCTOR,
                ElementType.FIELD,
                ElementType.LOCAL_VARIABLE,
                ElementType.METHOD,
                ElementType.PACKAGE,
                ElementType.PARAMETER,
                ElementType.TYPE
        })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullByDefault {
}
