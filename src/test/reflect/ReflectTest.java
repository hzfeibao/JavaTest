package test.reflect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
	
	public static void main(String[] args){
		testField();
		testConstructor();
		testParentAndInterface();
		testMethod();
	}
	
	public static void testMethod(){
		A t = new A();
		Class c = t.getClass();
		System.out.println("method");
		Method[] methods = c.getMethods();
		for(int i = 0; i < methods.length; i++){
			Method method = methods[i];
			System.out.print(method.getName() + " : ");
			Class[] paramTypes = method.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++)
				System.out.print(paramTypes[j].getName() + " ");
			System.out.println();
		}
		
		System.out.println("declaredMethod");
		Method[] declareMethods = c.getDeclaredMethods();
		for(int i = 0; i < declareMethods.length; i++){
			Method method = declareMethods[i];
			System.out.print(method.getName() + " : ");
			Class[] paramTypes = method.getParameterTypes();
			for(int j = 0; j < paramTypes.length; j++)
				System.out.print(paramTypes[j].getName() + " ");
			System.out.println();
		}
	}
	
	public static void testParentAndInterface(){
		A t = new A();
		Class c = t.getClass();
		Class[] interfaces = c.getInterfaces();
		System.out.println("interface:");
		for(int i = 0; i < interfaces.length; i++){
			Class itface = interfaces[i];
			System.out.println(itface.getName());
		}
		System.out.println("parent class");
		Class p = c.getSuperclass();
		System.out.println(p.getName());
	}
	
	public static void testConstructor(){
		A t = new A();
		Class c = t.getClass();
		String className = c.getName();
		String simpleName = c.getSimpleName();
		System.out.println("name: " + className + " simpleName: " + simpleName);
		
		System.out.println("constructor");
		Constructor[] cons = c.getConstructors();
		for(int i = 0; i< cons.length; i++){
			Constructor con = cons[i];
			Class[] paramTypes = con.getParameterTypes();
			System.out.print(con.getName() + " : ");
			for(int j = 0; j < paramTypes.length; j++)
				System.out.print(paramTypes[j].getName() + " ");
			System.out.println();
		}
	}
	
	public static void testField(){
		A t = new A();
		Class aClass = t.getClass();
		
		try{
			System.out.println("get public fields:");
			Field[] fields = aClass.getFields();
			for(int i = 0; i < fields.length; i++){
				Field f = fields[i];
				System.out.println("name: " + f.getName() + " type: " + f.getType());
			}
			
			System.out.println("get all fileds: ");
			Field[] allFields = aClass.getDeclaredFields();
			for(int i = 0; i < allFields.length; i++){
				Field f = allFields[i];
				System.out.println("name: " + f.getName() + " type: " + f.getType() + " modifiers:" + f.getModifiers());
			}
			System.out.println("get private fileds: ");
			Field f = aClass.getDeclaredField("a");
			System.out.println("name: " + f.getName() + " type: " + f.getType() + " modifiers:" + f.getModifiers());
			f.setAccessible(true);
			Integer i = (Integer) f.get(t);
			System.out.println("a: " + i);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class A extends Object implements ActionListener {
	private int a = 3;
	public Integer b = new Integer(4);

	public A() {
	}

	public A(int id, String name) {
	}

	public int abc(int id, String name) {
		return 0;
	}

	public void actionPerformed(ActionEvent e) {
	}
	
	private void test(){
		
	}
}



class LoadMethod {
	public Object Load(String cName, String MethodName, String[] type,
			String[] param) {
		Object retobj = null;
		try {
			// 加载指定的Java类
			Class cls = Class.forName(cName);

			// 获取指定对象的实例
			Constructor ct = cls.getConstructor(null);
			Object obj = ct.newInstance(null);

			// 构建方法参数的数据类型
			Class partypes[] = this.getMethodClass(type);

			// 在指定类中获取指定的方法
			Method meth = cls.getMethod(MethodName, partypes);

			// 构建方法的参数值
			Object arglist[] = this.getMethodObject(type, param);

			// 调用指定的方法并获取返回值为Object类型
			retobj = meth.invoke(obj, arglist);

		} catch (Throwable e) {
			System.err.println(e);
		}
		return retobj;
	}

	// 获取参数类型Class[]的方法
	public Class[] getMethodClass(String[] type) {
		Class[] cs = new Class[type.length];
		for (int i = 0; i < cs.length; i++) {
			if (!type[i].trim().equals("") || type[i] != null) {
				if (type[i].equals("int") || type[i].equals("Integer")) {
					cs[i] = Integer.TYPE;
				} else if (type[i].equals("float") || type[i].equals("Float")) {
					cs[i] = Float.TYPE;
				} else if (type[i].equals("double") || type[i].equals("Double")) {
					cs[i] = Double.TYPE;
				} else if (type[i].equals("boolean")
						|| type[i].equals("Boolean")) {
					cs[i] = Boolean.TYPE;
				} else {
					cs[i] = String.class;
				}
			}
		}
		return cs;
	}

	// 获取参数Object[]的方法
	public Object[] getMethodObject(String[] type, String[] param) {
		Object[] obj = new Object[param.length];
		for (int i = 0; i < obj.length; i++) {
			if (!param[i].trim().equals("") || param[i] != null) {
				if (type[i].equals("int") || type[i].equals("Integer")) {
					obj[i] = new Integer(param[i]);
				} else if (type[i].equals("float") || type[i].equals("Float")) {
					obj[i] = new Float(param[i]);
				} else if (type[i].equals("double") || type[i].equals("Double")) {
					obj[i] = new Double(param[i]);
				} else if (type[i].equals("boolean")
						|| type[i].equals("Boolean")) {
					obj[i] = new Boolean(param[i]);
				} else {
					obj[i] = param[i];
				}
			}
		}
		return obj;
	}
}