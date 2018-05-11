package personal.rowan.sandbox.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Rowan Hall
 */

public class NullObject {

    public static <T> T create(Class<T> clazz) {
        ClassLoader localClassLoader = clazz.getClassLoader();
        NullInvocationHandler localNullInvocationHandler = new NullInvocationHandler();
        return clazz.cast(Proxy.newProxyInstance(localClassLoader, new Class[]{clazz}, localNullInvocationHandler));
    }

    private static class NullInvocationHandler
            implements InvocationHandler {
        public Object invoke(Object object, Method method, Object[] args)
                throws Throwable {
            return null;
        }
    }

}
