package wc;

import java.io.*;
import java.util.ArrayList;

import org.omg.CORBA.Request;



public class WordCount {

	public static ArrayList<String> Path = new ArrayList<String>();   //用于存放操作文件路径
	public static ArrayList<String> Name = new ArrayList<String>();   //用于存放操作文件名
	
	public static int c(String FilePath){           //实现读取字符个数的功能
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*创建二进制输入流*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*循环读取并输出信息*/
            int temp;
            while((temp=dis.read())!=-1){
                result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}
	
	public static int w(String FilePath){               //实现读取单词个数的功能
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*创建二进制输入流*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*循环读取并输出信息*/
            int temp = 0;
            while(temp != -1 ){   //循环跳过连接的无效字符和有效字符用于计数
            	while((temp = dis.read())!=-1 && (temp == '\n' || temp == '\t' || temp == ' ' || temp == ',' || temp == '\r'))
            		;
            	if(temp == -1)
            		result--;
            	while((temp = dis.read())!=-1 && temp != '\n' && temp != '\t' && temp != ' ' && temp != ',' && temp != '\r')
            		;
            	result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}
	
	public static int l(String FilePath){          //实现读取行数功能
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*创建二进制输入流*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*循环读取并输出信息*/
            int temp = 0;
            while(temp != -1){     //循环跳过连接的无效字符和有效字符用于计数
            	while((temp = dis.read())!=-1 && (temp == '\n' || temp == '\r' || temp == '\t' || temp == ' '))
            		;
            	if(temp == -1)
            		result--;
            	while((temp = dis.read())!=-1 && temp != '\n' && temp != '\r')
            		;
            	result++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}
	    
	
	public static int[] a(String FilePath){          //实现读取空行，代码行，和注释行的功能。
		int result = 1;
		int code = 0,emp = 0,note = 0;
		boolean empline = true;                 //空行为true
		boolean codeline = false;               //代码行为true
		boolean starnote = false;				//星号注释的判断，注释行为true
		boolean starend = false;				//用于注释尾部判断
		boolean starstart = false;				//用于注释头部判断
		boolean dounote = false;				//双斜杠类型注释判断
		
		
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists()){
			int[] a = {code,emp,note}; 
			return a;
		}
		try {
            /*创建二进制输入流*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*循环读取并输出信息*/
            int temp = 0;
            while((temp = dis.read())!=-1){
            	if(temp == '\n'){
            		if(empline == true)
            			emp++;
            		else if(codeline == true)
            			code++;
            		else
            			note++;
            		empline = true;
            		codeline = false;
            		dounote = false;
            	}
            	else if(temp ==  ' ' || temp == '\t' || temp == '\r' || temp == '{')
            		;
            	else if(codeline == false){
            		empline =false;
            		if(starnote == true){
            			if(temp == '*')           //为*将标志starend置位true
            				starend = true;
            			else if(temp == '/' && starend == true){  //上一次为*，且这一次为/，则将starnote置位false
            				starnote = false;
            				starend = false;
            			}
            			else 
            				starend = false;
            		}
            		else if(temp == '/'){
            			if(starstart == true)      //两次//且前面没有有效字符表示当前行注释
            				dounote = true;
            			else
            				starstart = true;
            		}
            		else if(temp == '*'){
            			if(starstart == true)     //上次为/ 这次为*，表示注释开始
            				starnote = true;
            			else
            				codeline = true;		//否则为代码行
            		}
            		else if(temp == '}')
            			;
            		else if(dounote == false)
            			codeline = true;
            	}
            }
            if(empline == true)
    			emp++;
    		else if(codeline == true)
    			code++;
    		else
    			note++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		int[] a = {code,emp,note}; 
		return a;
	}
	
	public static int e(String FilePath,String StopFile){      //实现读取除停用词以外单词的个数的功能
		int result = 0;
		
		String a = "";
		
		String[] stopword = new String[10];
		for(int m = 0;m < 10;m++){
			stopword[m] = "";
		}
		
		
		File f = new File(FilePath);
		File Stop = new File(StopFile);
		DataInputStream dis_1 = null;
		DataInputStream dis_2 = null;
		if(!f.isFile() && f.exists())
			return -1;
		if(!Stop.isFile() && Stop.exists())
			return -1;
		try {
            /*创建二进制输入流*/
            dis_1 = new DataInputStream(new FileInputStream(f));
            dis_2 = new DataInputStream(new FileInputStream(Stop));
            /*循环读取并输出信息*/
            int S = 0,i = 0;
            while((S = dis_2.read())!=-1){
            	if(S != ' ')
            		stopword[i] = stopword[i] + (char)S;
            	else
            		i++;
            }
            
            int temp = 0,j = 0;
            while(temp != -1 ){                          //第一个循环跳过空格 换行 逗号等  第二个循环跳过正常字符
            	while((temp = dis_1.read())!=-1 && (temp == '\n' || temp == '\t' || temp == ' ' || temp == ',' || temp == '\r'))
            		a = "";
            	if(temp == -1)							//当最后一次以空格结尾时，-1表示不计算本次
            		result--;
            	else
            		a = a + (char)temp;					//每当经过一次两个循环时，意味着单词数+1
            	while((temp = dis_1.read())!=-1 && temp != '\n' && temp != '\t' && temp != ' ' && temp != ',' && temp != '\r')
            		a = a + (char)temp;
            	result++;
            	for(j = 0;j <= i;j++){
            		if(stopword[j].equals(a)){
            			result--;
            			break;
            		}
            	}
            	
            }
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (dis_1!=null) {
                try {
                    dis_1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis_2!=null) {
                try {
                    dis_2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}
	
	public static boolean output(String Str,String FilePath){     //实现输出字符串到指定文件的功能
		File f = new File(FilePath);
		int length = Str.length();
		DataOutputStream dis = null;
		try {
			if(!f.exists())
				f.createNewFile();
			dis = new DataOutputStream(new FileOutputStream(f));
			for(int i = 0;i < length;i++)                           //对于文件中的每个字符都传输进目标文件中
				dis.write(Str.charAt(i));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		finally{
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return true;
	}
	public static void showName(String FilePath){
		File f = new File(FilePath);
		Name.add(f.getName());
	}
	
	
	public static void showFile(String NameMatch,String FilePath){       //实现带*文件的匹配和遍历文件夹的功能
		File f = new File(FilePath);
		if(f.isFile()){
			if(f.getName().contains(NameMatch)){           //对匹配的文件将其信息添加到动态数组中
				Path.add(FilePath);
				Name.add(f.getName());
			}
		}
		if(f.isDirectory()){
			File[] array = f.listFiles();
			for(int i=0;i<array.length;i++){
				showFile(NameMatch,array[i].getPath());    //递归调用，对该文件夹下的每个项目再次执行此函数。
				//System.out.println(array[i].getName());
			}
		}
	}
	
	
	
	public static void main(String args[]){
		int length = args.length;
		int i = 0;
		String aimFile = null,stopFile = null,outputFile = null;
		//boolean stop = false,output = false;
		boolean[] flag = new boolean[7];           //作为命令判断标志，如果存在命令，则将相应位置位为true
		for(i = 0;i < 7;i++)
			flag[i] = false;
		for(i = 0;i < length;i++){
			if(args[i].equals("-c"))
				flag[0] = true;
			else if(args[i].equals("-w"))
				flag[1] = true;
			else if(args[i].equals("-l"))
				flag[2] = true;
			else if(args[i].equals("-a"))
				flag[3] = true;
			else if(args[i].equals("-s"))
				flag[4] = true;
			else if(args[i].equals("-e")){
				flag[5] = true;
				i++;
				stopFile = args[i];             //停用词命令后是停用词文件路径
			}
			else if(args[i].equals("-o")){
				flag[6] = true;
				i++;
				outputFile = args[i];          //输出命令后是输出目的文件
			}
			else{
				//Path.add(args[i]);
				//int op = 0;
				//aimFile = args[i];
				//System.out.println(Path);
				File directory = new File(".");
				String tPath = "";
				try {
					tPath = directory.getCanonicalPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String part = args[i].replace("*", "");
				showFile(part,tPath);
				//System.out.println(Path);
			}
		}
		
		String total = "";
		/*
		if(flag[4] == true){
			String bag = System.getProperty("user.dir");
			bag = bag.replace(cs, css);
			System.out.println(aimFile);
			if(aimFile.contains("*")){
				aimFile = aimFile.replace("*", "");
				showFile("aimFile","bag");
			}
			else{
				showFile("aimFile","bag");
			}
			System.out.println(aimFile);
		}
		else{
			File f = new File(aimFile);
			Path.add(aimFile);
			Name.add(f.getName());
		}*/
		
		for(int k = 0;k < Path.size();k++){
			showName(Path.get(k));
			if(flag[0] == true){
				total += Name.get(k) + ", character : " + c(Path.get(k)) + "\r\n" ;
			}
			if(flag[1] == true){
				total += Name.get(k) + ", word : " + w(Path.get(k)) + "\r\n";
			}
			if(flag[2] == true){
				total += Name.get(k) + ", line : " + l(Path.get(k)) + "\r\n";
			}
			if(flag[3] == true){
				total += Name.get(k) + ", code line/empty line/note line : " + a(Path.get(k))[0] + "/" + a(Path.get(k))[1] + "/" + a(Path.get(k))[2] + "\r\n";
			}
			if(flag[5] == true){
				total += Name.get(k) + ", word(remove stopword) : " + e(Path.get(k),stopFile) + "\r\n";
			}
		}
		if(flag[6] == false)
			System.out.println(total);
		if(flag[6] == true)
			output(total,outputFile);
	}
}
