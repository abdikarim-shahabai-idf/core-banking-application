package idf.kz.library.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtil {

  private ReflectionUtil() {
  }

  public static Type getClassByTypeParameterPosition(Class<?> clazz, int position){
    return ((ParameterizedType) clazz.getGenericSuperclass())
        .getActualTypeArguments()[position];
  }
}
