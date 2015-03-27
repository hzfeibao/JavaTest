package test;

public class TestInnerClass {
	
	
	public static void main(String[] args){
		// 第一种方式：
		Outter outter = new Outter();
		Outter.Inner inner = outter.new Inner(); // 必须通过Outter对象来创建

		// 第二种方式：
		Outter.Inner inner1 = outter.getInnerInstance();
		
		Man m = new Man();
		m.getWoman();
	}
	
	class Circle {
		private double radius = 0;

		public Circle(double radius) {
			this.radius = radius;
			getDrawInstance().drawSahpe(); // 必须先创建成员内部类的对象，再进行访问
		}

		private Draw getDrawInstance() {
			return new Draw();
		}

		class Draw { // 内部类
			public void drawSahpe() {
				System.out.println(radius); // 外部类的private成员
			}
		}
	}
	
}

class Outter {
	private Inner inner = null;

	public Outter() {

	}

	public Inner getInnerInstance() {
		if (inner == null)
			inner = new Inner();
		return inner;
	}

	class Inner {
		public Inner() {

		}
	}
}

class People {
	public People() {

	}
}

class Man {
	public Man() {

	}

	public People getWoman() {
		class Woman extends People { // 局部内部类
			int age = 0;
		}
		return new Woman();
	}
}
