package ru.mikheev;

import ru.mikheev.annotation.Log;
import ru.mikheev.impl.TestLogImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LogProxyLoader {

    private LogProxyLoader() {
    }

    static TestLog getTestLog() throws NoSuchMethodException {
        InvocationHandler handler = new TestLogInvocationHandler(new TestLogImpl());
        return (TestLog) Proxy.newProxyInstance(LogProxyLoader.class.getClassLoader(),
                new Class<?>[]{TestLog.class}, handler);
    }

    static class TestLogInvocationHandler implements InvocationHandler {
        private final TestLog testLog;
        private final Set<Method> annotatedMethods = new HashSet<>();

        TestLogInvocationHandler(TestLog testLog) throws NoSuchMethodException {
            this.testLog = testLog;
            Class<?>[] declaredInterfaces = testLog.getClass().getInterfaces();
            Optional<Class<?>> testLogInterface = Arrays.stream(declaredInterfaces)
                    .filter(declaredInterface -> declaredInterface.getName().equals(TestLog.class.getName()))
                    .findFirst();

            if (testLogInterface.isPresent()) {
                Method[] objectMethods = testLog.getClass().getMethods();
                for (Method method : objectMethods) {
                    if (method.isAnnotationPresent(Log.class)) {
                        annotatedMethods.add(testLogInterface.get().getMethod(method.getName(), method.getParameterTypes()));
                    }
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethods.contains(method)) {
                System.out.println("Executed method: " + method.getName() + " with params: " + Arrays.toString(args));
            }
            return method.invoke(testLog, args);
        }
    }
}
