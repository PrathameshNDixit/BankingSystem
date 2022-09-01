package javaapplication48;
import java.util.*;
import java.io.*;
class Account implements Serializable
{
    Integer accno;
    String name;
    double balance;
    public Account(){}
    public Account(String n,Integer acno,double b)
    {
	accno=acno;
	name=n;
	balance=b;
    }
    @Override
    public String toString()
    {
        return "AccNo:-"+accno+"\n"+"Name:-"+name+"\n"+"Balance:-"+balance;
    }
}
public class JavaApplication48 {

    public static void main(String[] args)throws Exception
    {
        Scanner s=new Scanner(System.in);
        Account acc=null;	
        HashMap<Integer,Account> hm=new HashMap<>();
        try
	{
            FileInputStream fis=new FileInputStream("Account.txt");
            ObjectInputStream ois=new ObjectInputStream(fis);
            int count=ois.readInt();
            for(int i=0;i<count;i++)
            {
		acc=(Account)ois.readObject();
		System.out.println(acc);
                hm.put(acc.accno,acc);
            }
            fis.close();
            ois.close();
        }
        catch(Exception e){}
	FileOutputStream fos=new FileOutputStream("Account.txt");
	ObjectOutputStream oos=new ObjectOutputStream(fos);
        System.out.println("Menu");
	int choice;
	Integer ano;
	String n;
	double bal;
	do{
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. View Account");
            System.out.println("4. View  All Accounts");
            System.out.println("5. Save Accounts");
            System.out.println("6. Exit");            
            System.out.println("Enter your choice ");
            choice=s.nextInt();
            
            s.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
         
            switch(choice)   
            {
            	case 1:System.out.println("Enter Name,AccountNo and Balance");
            	n=s.nextLine();
                ano=s.nextInt();
            	bal=s.nextDouble();
            	acc=new Account(n,ano,bal);
            	hm.put(ano,acc);
            	System.out.println("Account Created Successfully");
            	System.out.println("AccNo:-"+acc.accno);
            	break;
            	
            	case 2:System.out.println("Enter Account number");
            	ano=s.nextInt();
            	hm.remove(ano);
            	break;
            	
            	case 3:System.out.println("Enter Acc Number");
            	ano=s.nextInt();
            	acc=hm.get(ano);
            	System.out.println(acc);
            	break;
            	
            	case 4:
            	for(Account a:hm.values())
            	{
                    System.out.println(a);
            	}
            	break;
            	
            	case 5:
            	
            	case 6:oos.writeInt(hm.size());
            	for(Account a:hm.values())
            		oos.writeObject(a);
            	break;
                
            }
            oos.writeInt(hm.size());
            for(Account a:hm.values())
                oos.writeObject(a);
        }while(choice!=6);
        oos.flush();
	oos.close();
	fos.close();
    }
    
}
