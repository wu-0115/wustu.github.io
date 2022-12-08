# IO概述

1. 问题：数据是存储到内存中的，当我们程序运行结束时，数据就没了，此时就需要把数据**持久化**存储起来。
2. I：input 输入（读）
3. O：output 输出（写）
4. java.io 包

# File 类

1. File 类：文件、目录的一个抽象封装

2. 文件路径分隔符：

   - windows:使用 \ 分割，java 中要使用\\\ ,分割属性；（不区分大小写）
   - Unix 系统：/ 分割（区分大小写）

3. 获取当前系统的分割符：

   ```java
   //获取分割属性
   File.pathSeparator;
   File.pathSeparatorChar;
   //分割符
   File.separator;
   File.separatorChar;
   ```

4. 获取路径和名称

   - 相对路径：参考一个坐标，与这个坐标的路径

     ```java
     . 代表当前目录
     ..返回上一层
     ../test 上一层目录下面的 test 目录
     注意，现在拷贝的时候，尽量使用绝对路径来做，以后到框架阶段，用相对路径来做
     ```

     

   - 绝对路径：从磁盘的跟目录一直到你的文件

     ```java
     //获取绝对路径
     public String getAbsolutePath()
     //获取文件路径
     public File getAbsoluteFile()
     //获取文件路径
     public String getPath()
     //获取上级目录文件
     public File getParentFile()
     //获取上级目录路径
     public String getParent()
     ```

5. 构造方法

   ```java
   //直接使用路径创建文件 pathname 可以是文件也可以是目录
   public File(String pathname)
   //从目录对象下创建文件对象
   public File(File parent, String child)
   //目录路径和文件路径分开传
   public File(String parent, String child)
   ```

6. 方法

   ```java
   //创建新文件
   public boolean createNewFile()
   //判断是否为目录
   public boolean isDirectory() 
   //判断是否为文件
   public boolean isFile()
   //删除文件
   public boolean delete()
   //判断文件是否存在
   public boolean exists()
   //创建文件目录
   public boolean mkdir()
   //创建多个目录
   public boolean mkdirs() 
   //获取当前层级的文件名
   public String[] list() 
   //重命名（可以做拷贝）
   public boolean renameTo(File dest) 
   ```

# 递归操作文件

1. 需求：列出指定目录中的所有文件，包括子文件夹中的所有文件及其子文件。（Recursion） 递归

   - 递归回顾：

     ```java
     public static int recursion(int num){
             if(num == 1){
                 return 1;
             }
             return num * recursion(--num);
         }
     ```

   - 需求实现

     ```java
     public static void allFiles(File file, String path) {
             //寻找第一层的所有儿子
             File[] files = file.listFiles();
             System.out.println(path + file.getAbsolutePath());
             for (File f : files) {
                 if (f.isDirectory()) {
                     //如果是目录就需要递归
                     allFiles(f, path + path + "   -");
                 } else {
                     System.out.println(path + path + f.getName());
                 }
             }
         }
     ```

2. 需求：批量修改文件名

   ```java
   public static void rename(String deleteName,String newName,File file){
           File[] files = file.listFiles();
           for (File f : files) {
               if(f.isFile()){
                   if(f.getName().contains(deleteName)){
                       //替换名字字符串
                       String replace = f.getName().replace(deleteName, newName);
                       //创建替换文件对象
                       File file1 = new File(f.getParent(), replace);
                       f.renameTo(file1);
                   }
               }else{
                   //递归
                   rename(deleteName,newName,f);
               }
           }
       }
   ```

   

# 文件过滤器（FilenameFilter）

1. 需求：打印指定目录下以.avi 结尾的资源

   ```java
   public static void filterFiles(List<File> list, File file, String filterName){
           File[] files1 = file.listFiles();
           int count = 0;
           for (File file1 : files1) {
               if(file1.isFile()){
                   File[] files = file.listFiles(new FilenameFilter() {
                       @Override
                       public boolean accept(File dir, String name) {
                           return new File(dir, name).isFile() && name.endsWith(filterName);
                       }
                   });
                   if(count > 0){
                       continue;
                   }
                   for (File file2 : files) {
                       list.add(file2);
                   }
                   count++;
               }else{
                   filterFiles(list,file1,filterName);
               }
           }
       }
   ```

2. 文件过滤器（FilenameFilter）规范，需要自己实现逻辑。

   ```java
   FilenameFilter filter = new FilenameFilter() {
       @Override
       public boolean accept(File dir, String name) {
           return false;
       }
   };
   ```

   

