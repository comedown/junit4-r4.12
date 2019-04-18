package young;

import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = getClassFromStream(name);
        if (clazz == null) {
            super.loadClass(name);
        }
        return clazz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return getClassFromStream(name);
    }

    private Class<?> getClassFromStream(String name) throws ClassNotFoundException {
        String fileName = name.replaceAll("\\.", "/") + ".class";
        InputStream is = getResourceAsStream("target/classes/" + fileName);
        if (is == null) {
            is = getResourceAsStream("target/test-classes/" + fileName);
            if (is == null) {
                throw new ClassNotFoundException(name);
            }
        }
        try {
            byte[] b = new byte[is.available()];
            is.read();
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
}
