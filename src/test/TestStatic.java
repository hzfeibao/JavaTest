package test;

public class TestStatic extends Base {
	SubPersion sp = new SubPersion("abc");
	static {
		System.out.println("test static");
	}
	{
		System.out.println("test {}");
	}
	public TestStatic() {
		System.out.println("test constructor");
	}

	public static void main(String[] args) {
		new TestStatic();
	}
}

class Base {
	Person p = new Person("123");
	static {
		System.out.println("base static");
	}
	{
		System.out.println("test base {}");
	}
	public Base() {
		System.out.println("base constructor");
	}
}

class Person{
	private String name;
	static{
		System.out.println("Person static");
	}
	{
		System.out.println("Person {}");
	}
	public Person(String n){
		this.name = n;
		System.out.println("Person name: " + n);
	}
}

class SubPersion{
	private String name;
	static{
		System.out.println("SubPersion static");
	}
	{
		System.out.println("SubPersion {}");
	}
	public SubPersion(String n){
		this.name = n;
		System.out.println("SubPersion name: " + n);
	}
}