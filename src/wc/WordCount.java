package wc;

import java.io.*;
import java.util.ArrayList;

import org.omg.CORBA.Request;



public class WordCount {

	public static ArrayList<String> Path = new ArrayList<String>();   //���ڴ�Ų����ļ�·��
	public static ArrayList<String> Name = new ArrayList<String>();   //���ڴ�Ų����ļ���
	
	public static int c(String FilePath){           //ʵ�ֶ�ȡ�ַ������Ĺ���
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*����������������*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*ѭ����ȡ�������Ϣ*/
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
	
	public static int w(String FilePath){               //ʵ�ֶ�ȡ���ʸ����Ĺ���
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*����������������*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*ѭ����ȡ�������Ϣ*/
            int temp = 0;
            while(temp != -1 ){   //ѭ���������ӵ���Ч�ַ�����Ч�ַ����ڼ���
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
	
	public static int l(String FilePath){          //ʵ�ֶ�ȡ��������
		int result = 0;
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists())
			return -1;
		try {
            /*����������������*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*ѭ����ȡ�������Ϣ*/
            int temp = 0;
            while(temp != -1){     //ѭ���������ӵ���Ч�ַ�����Ч�ַ����ڼ���
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
	    
	
	public static int[] a(String FilePath){          //ʵ�ֶ�ȡ���У������У���ע���еĹ��ܡ�
		int result = 1;
		int code = 0,emp = 0,note = 0;
		boolean empline = true;                 //����Ϊtrue
		boolean codeline = false;               //������Ϊtrue
		boolean starnote = false;				//�Ǻ�ע�͵��жϣ�ע����Ϊtrue
		boolean starend = false;				//����ע��β���ж�
		boolean starstart = false;				//����ע��ͷ���ж�
		boolean dounote = false;				//˫б������ע���ж�
		
		
		File f = new File(FilePath);
		DataInputStream dis = null;
		if(!f.isFile() && f.exists()){
			int[] a = {code,emp,note}; 
			return a;
		}
		try {
            /*����������������*/
            dis = new DataInputStream(new FileInputStream(f));
            
            /*ѭ����ȡ�������Ϣ*/
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
            			if(temp == '*')           //Ϊ*����־starend��λtrue
            				starend = true;
            			else if(temp == '/' && starend == true){  //��һ��Ϊ*������һ��Ϊ/����starnote��λfalse
            				starnote = false;
            				starend = false;
            			}
            			else 
            				starend = false;
            		}
            		else if(temp == '/'){
            			if(starstart == true)      //����//��ǰ��û����Ч�ַ���ʾ��ǰ��ע��
            				dounote = true;
            			else
            				starstart = true;
            		}
            		else if(temp == '*'){
            			if(starstart == true)     //�ϴ�Ϊ/ ���Ϊ*����ʾע�Ϳ�ʼ
            				starnote = true;
            			else
            				codeline = true;		//����Ϊ������
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
	
	public static int e(String FilePath,String StopFile){      //ʵ�ֶ�ȡ��ͣ�ô����ⵥ�ʵĸ����Ĺ���
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
            /*����������������*/
            dis_1 = new DataInputStream(new FileInputStream(f));
            dis_2 = new DataInputStream(new FileInputStream(Stop));
            /*ѭ����ȡ�������Ϣ*/
            int S = 0,i = 0;
            while((S = dis_2.read())!=-1){
            	if(S != ' ')
            		stopword[i] = stopword[i] + (char)S;
            	else
            		i++;
            }
            
            int temp = 0,j = 0;
            while(temp != -1 ){                          //��һ��ѭ�������ո� ���� ���ŵ�  �ڶ���ѭ�����������ַ�
            	while((temp = dis_1.read())!=-1 && (temp == '\n' || temp == '\t' || temp == ' ' || temp == ',' || temp == '\r'))
            		a = "";
            	if(temp == -1)							//�����һ���Կո��βʱ��-1��ʾ�����㱾��
            		result--;
            	else
            		a = a + (char)temp;					//ÿ������һ������ѭ��ʱ����ζ�ŵ�����+1
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
	
	public static boolean output(String Str,String FilePath){     //ʵ������ַ�����ָ���ļ��Ĺ���
		File f = new File(FilePath);
		int length = Str.length();
		DataOutputStream dis = null;
		try {
			if(!f.exists())
				f.createNewFile();
			dis = new DataOutputStream(new FileOutputStream(f));
			for(int i = 0;i < length;i++)                           //�����ļ��е�ÿ���ַ��������Ŀ���ļ���
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
	
	
	public static void showFile(String NameMatch,String FilePath){       //ʵ�ִ�*�ļ���ƥ��ͱ����ļ��еĹ���
		File f = new File(FilePath);
		if(f.isFile()){
			if(f.getName().contains(NameMatch)){           //��ƥ����ļ�������Ϣ��ӵ���̬������
				Path.add(FilePath);
				Name.add(f.getName());
			}
		}
		if(f.isDirectory()){
			File[] array = f.listFiles();
			for(int i=0;i<array.length;i++){
				showFile(NameMatch,array[i].getPath());    //�ݹ���ã��Ը��ļ����µ�ÿ����Ŀ�ٴ�ִ�д˺�����
				//System.out.println(array[i].getName());
			}
		}
	}
	
	
	
	public static void main(String args[]){
		int length = args.length;
		int i = 0;
		String aimFile = null,stopFile = null,outputFile = null;
		//boolean stop = false,output = false;
		boolean[] flag = new boolean[7];           //��Ϊ�����жϱ�־����������������Ӧλ��λΪtrue
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
				stopFile = args[i];             //ͣ�ô��������ͣ�ô��ļ�·��
			}
			else if(args[i].equals("-o")){
				flag[6] = true;
				i++;
				outputFile = args[i];          //�������������Ŀ���ļ�
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
