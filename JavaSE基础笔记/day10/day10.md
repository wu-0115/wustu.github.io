# Object 类

Object 是所有类的超类。

**可以通过Ctrl + H 查看类继承结构观察**

Object 类的常用方法：

- toString() 打印当前对象（直接打印对象就是打印对象的 toString 方法）

  ```java
   public String toString() {
      return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  ```

- getClass() 获取类的字节码对象

  ```java
  public final native Class<?> getClass();
  aClass.getName()//获取类的权限定类名
  ```

- hashCode(); 获取对象的 哈希值,存到HashTables

  ```java
  public native int hashCode();
  ```

- finalize() 当对象不被引用时，要被 gc 不定时回收，gc 去调用这个方法（程序员是不用自己调用，自动调用）

  ```java
  protected void finalize() throws Throwable { }
  ```

- equals() 方法，比较是否是同一个对象

  ```java
  public boolean equals(Object obj) {
      return (this == obj);//== 比较的是地址值
  }
  ```

- equals 方法 和 == 区别面试题

  - == 比较地址值
  - equals 没有重写之前，比较的也是地址值
  - 一般我们都会重写，只关心内容而不关心地址值

- 注意：

  - 如果 equals 方法没有重写时，== 和equals 方法没有区别
  - 如果 equals 方法重写，我们只关心内容而不关心地址值

# 多态

1. 什么是多态？为啥产生多态：**学生是学生，学生也是人** is a

2. 语法：

   ```java
   public class Student extends Person{
       
   }
   
   public class Person{
       
   }
   
   Student stu = new Student();
   //多态
   父类 变量名 = new 子类();
   Person p = new Student();
   ```

3. 通过实现接口或者继承类，都可以产生多态

4. 在实际开发中，要求面向（多态）接口编程

5. 多态调用，如果父类（接口）没有子类的字段或者方法，也不能去调用

6. 多态类型转换

   ```java
   Person p = new Student();
   真实类型  变量名   真实类型 变量名
   Student stu =   (Student) p;
   ```

7. 面向多态（接口）编程的意义：

   - 不需要面向具体	
   - 多态的作用是降低程序耦合度，提高程序扩展力

# super 关键字

1. 需求：子类对象重写父类方法时，调用的还是父类对象的方法

2. 使用 super 关键字解决

   ```java
   @Override
   public void study() {
       super.study();
       System.out.println("学生在学习");
   }
   ```

   

# 内部类

1. 成员内部类（类里面存在新的类）

   ```java
   public class 外部类名{
    	public class 内部类名{
           
       }
   }
   ```

   - 访问方式
     - 外部对象 变量名 = new 外部对象();
     - 外部类名.内部类名 内部类变量名 = 变量名.new 内部对象()；

2. 匿名内部类(底层自动去帮我们生成了一个实现类)

   ```java
   public interface 接口名{
    	void user();   
   }
   
   接口名 变量名 = new 接口名(){
       public void user(){
           //具体逻辑
       }
   }
   ```

   

3. 局部内部类（**不要去使用**）局部代码块里面的类

# 匿名对象

1. 匿名对象：不给对象一个变量名

   ```java
   Student stu = new Student();
   
   //不需要变量名
   new Student();
   ```

2. 作用和意义：少开辟空间，可以有效节约空间（GC可以不定期的回收）

# 代码块

1. 静态代码块

   ```java
   //类中直接存在
   static{
      
   }
   ```

2. 构造代码块

   ```java
   {
       //构造代码块
   }
   ```

3. 局部代码块

   ```java
   public static void main(String[] args) {
           Test test = new Test();
           {
               
           }
   }
   ```

   - fori 典型的局部代码块

# 执行顺序

1. 需求：写一个类继承另一个类，每一个类提供静态代码块，构造代码块，构造方法，普通方法，静态方法。
2. 执行顺序：
   - 父类静态代码块 -> 子类静态代码块 ->父类的构造代码块 ->父类的构造器 ->子类的构造代码块 -> 子类的构造器 ->子类的普通方法
3. 注意：
   - 构造代码块使用并不是很多，可以用构造器代替
   - 子类不能够重写父类的 static 方法（static 修饰的是数据类，而不是对象）
   - 静态方法和普通方法没有必然的先后顺序，根据我们的调用顺序决定

