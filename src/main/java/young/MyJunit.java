package young;

/**
 * @Description
 * @Author mi
 * @Date 2019/4/17
 */
public class MyJunit {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new CustomClassLoader();
        Thread.currentThread().setContextClassLoader(classLoader);
        Class<?> clazz = classLoader.loadClass("young.YoungMainTest");
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getName());
    }
}
