package cn.partner.algorithms.interview;

/**
 * 写一个单例
 * 要求懒加载 线程安全的
 */
public class DCLSingleton {
    /**
     * new DCLSingleton()过程中CPU执行的指令:
     *    1. 创建空间
     *    2. 初始化
     *    3. 引用->对象
     * 多线程情况下当2和3进行重排序，另外线程会得到一个还没有初始化的对象引用
     *
     *
     * 面试官补充，会有以下几种打破单例的办法:
     *    1. clone() --> 重写clone，抛异常
     *    2. 序列化 + 反序列化，添加readResolve()方法返回instance，序列化时候会调用
     *    3. 反射，如何处理？
     */
    private static transient volatile DCLSingleton instance;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }




    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public DCLSingleton readResolve() {
        return instance;
    }
}


/**
 * enum implementation
 */
enum EnumSingleton {
    INSTANCE
}
