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
	
	public static int e(String FilePath,String stopword){
		int result = 0;
		
		String a = new String();
		
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
            	else
            		a = a + Integer.toString(temp);
            	while((temp = dis.read())!=-1 && temp != '\n' && temp != '\t' && temp != ' ' && temp != ',' && temp != '\r')
            		a = a + Integer.toString(temp);
            	result++;
            	if(stopword.equals(a))
            		result--;
            	a = "";
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
	
	public static void main(String args[]){
		int[] result = {0,0,0};
		result = Countnote("D:\\test.txt");
		System.out.println(result[0] + "  " + result[1] + "  " + result[2]);
		
		int stop = 0;
		stop = e("D:\\test.txt","wo");
		System.out.println(stop);
		//int zz = 0;
		//zz = Countnote("D:\\test.txt");
		//System.out.println(zz);
	}
	
}
