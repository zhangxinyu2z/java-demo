package proxy.cglib.demo;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * 说jdk的动态代理的区别是：cglib无法代理final对象与final方法
 */
public class Test {

	public static void main(String[] args) {
		//class 文件缓存目录，如果不研究动态类的源码可以不设置
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\cglib_classes");
		//用于创建代理对象的增强器，可以对目标对象进行扩展
		Enhancer enhancer = new Enhancer();
		//将目标对象设置为父类
		enhancer.setSuperclass(Atm.class);
		//设置目标拦截器
		enhancer.setCallback(new AtmInterceptor());
		// 创建代理对象
		Atm atm = (Atm)enhancer.create();
		// 通过代理对象调用目标方法
		Object result = atm.withdraw(100);
		atm.checkBalance();
	}
}

