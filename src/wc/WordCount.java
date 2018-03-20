package wc;

import java.io.*;
import java.util.ArrayList;



public class WordCount {

	public static int c(String FilePath){
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
	
	public static int w(String FilePath){
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
            while(temp != -1 ){
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
	
	public static int l(String FilePath){
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
            while(temp != -1){
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
	    
	public static int Countline(String FilePath){
		int result = 1;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*创建二进制输入流*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*循环读取并输出信息*/
            int temp = 0;
            while((temp = dis.read())!=-1){
            	if(temp == '\n')
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
	
	public static int[] Countnote(String FilePath){
		int result = 1;
		int code = 0,emp = 0,note = 0;
		boolean empline = true;
		boolean codeline = false;
		boolean starnote = false;
		boolean starend = false;
		boolean starstart = false;
		boolean dounote = false;
		//boolean noteflag = false,line = false;
		//boolean nahead = false,nahead1 = false;
		//boolean dounote = false;
		
		
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
            			if(temp == '*')
            				starend = true;
            			else if(temp == '/' && starend == true){
            				starnote = false;
            				starend = false;
            			}
            			else 
            				starend = false;
            		}
            		else if(temp == '/'){
            			if(starstart == true)
            				dounote = true;
            			else
            				starstart = true;
            		}
            		else if(temp == '*'){
            			if(starstart == true)
            				starnote = true;
            			else
            				codeline = true;
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
	
	public static int e(String FilePath,String StopFile){
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
            while(temp != -1 ){
            	while((temp = dis_1.read())!=-1 && (temp == '\n' || temp == '\t' || temp == ' ' || temp == ',' || temp == '\r'))
            		a = "";
            	if(temp == -1)
            		result--;
            	else
            		a = a + (char)temp;
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
	
	public static boolean output(String Str,String FilePath){
		File f = new File(FilePath);
		int length = Str.length();
		DataOutputStream dis = null;
		try {
			if(!f.exists())
				f.createNewFile();
			dis = new DataOutputStream(new FileOutputStream(f));
			for(int i = 0;i < length;i++)
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
	
	public static void main(String args[]){
		/*
		int[] result = {0,0,0};
		result = Countnote("D:\\test.txt");
		System.out.println(result[0] + "  " + result[1] + "  " + result[2]);
		
		int stop = 0;
		stop = e("D:\\test.txt","wo");
		System.out.println(stop);
		//int zz = 0;
		//zz = Countnote("D:\\test.txt");
		//System.out.println(zz);
		*/
		
		//int re = e("D:\\test.txt","D:\\test1.txt");
		//System.out.println(re);
		
		
		
		
		int length = args.length;
		int i = 0;
		String aimFile = null,stopFile = null,outputFile = null;
		//boolean stop = false,output = false;
		boolean[] flag = new boolean[7];
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
				stopFile = args[i];
			}
			else if(args[i].equals("-o")){
				flag[6] = true;
				i++;
				outputFile = args[i];
			}
			else{
				aimFile = args[i];
			}
		}
		
		String total = "";
		
		if(flag[0] == true){
			if(flag[6] == false)
				System.out.println(aimFile + "， 字符数：" + c(aimFile));
			if(flag[6] == true)
				total += aimFile + "， 字符数：" + c(aimFile);
				//output(aimFile + "， 字符数：" + c(aimFile),outputFile);
		}
		if(flag[1] == true){
			if(flag[6] == false)
				System.out.println(aimFile + "， 单词数：" + w(aimFile));
			if(flag[6] == true)
				total += "\r\n" + aimFile + "， 单词数：" + w(aimFile);
				//output(aimFile + "， 单词数：" + w(aimFile),outputFile);
		}
		if(flag[2] == true){
			if(flag[6] == false)
				System.out.println(aimFile + "， 行数：" + l(aimFile));
			if(flag[6] == true)
				total += "\r\n" + aimFile + "， 行数：" + l(aimFile);
				//output(aimFile + "， 行数：" + l(aimFile),outputFile);
		}
			
		output(total,outputFile);
		//if(!output("shit","D:\\test1.txt"))
			//System.out.println("file");
		//for(i = 0;i < 7;i++){
			//System.out.println(flag[i]+stopFile+aimFile+outputFile);
		//}
	
	}
}
