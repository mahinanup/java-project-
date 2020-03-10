import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
class invalid_input extends Exception
{
  public invalid_input()
  {
    super(" You have given invalid input \n");
  }
}
class balanceerror extends Exception
{
  public balanceerror()
  {
    super(" THERE IS INSUFFICIENT BALANCE IN YOUR ACCOUNT \n");
  }
}
public class Bank
{
	private int accno;
	private String name;
	private int balance;

	Scanner sc=new Scanner(System.in);

	//method to open an account
	void openAccount()
	{
    String total_content = "";
		String line;

		try {
			BufferedReader br = new BufferedReader(new FileReader("bank.txt"));
			while((line = br.readLine())!=null){
				total_content += line + "\n";
			}

br.close();


		} catch (Exception e) {
		}
    try{
    System.out.print("Enter Account No: ");
		accno=sc.nextInt();

		System.out.print("Enter Name: ");
		name=sc.next();
		System.out.print("Enter Balance: ");
		balance=sc.nextInt();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    total_content += "Account No.: " + accno + "\t Customer name: " + name  + "\t Customer Balance: " + balance + "\n" ;
    try{
    FileWriter f1 = new FileWriter("C:/Users/mahin anup/Desktop/java project/bank.txt");
    BufferedWriter b1 = new BufferedWriter(f1);

    f1.write(total_content);
    f1.close();
    }
    catch(IOException e)
    {}
		System.out.println(" \n");
	}

	//method to display account details
	void showAccount()
	{
		System.out.println(accno+","+name+","+balance);
		System.out.println(" \n");
	}

	//method to deposit money
	void deposit()
	{
		int amt;
		System.out.println("Enter Amount that you Want to Deposit : ");
		amt=sc.nextInt();
		System.out.println(" \n");
		balance=balance+amt;
		System.out.println("The new balance in the account is: "+balance);
		System.out.println(" \n");
	}
  void interest()
  {
    float amt,si,r,t,ci;
    System.out.println("Enter the principle amount.\n");
    amt=sc.nextFloat();
    System.out.println("Enter the rate of interest.\n");
    r=sc.nextFloat();
    System.out.println("Enter the time period.\n");
    t=sc.nextFloat();
    if(amt<=20000)
    {
      System.out.println("Simple interest will be calculated.");
      si=(amt*r*t)/100;
      System.out.println("The simple interest is calculated as follows"+si);
    }
    else
    {
      System.out.println("Compound interest will be calculated.\n");
      ci=(float)(amt*Math.pow((1+r/100),t));
      System.out.println("The compound interest is calculated as follows."+ci);
    }
  }
	//method to withdraw money
	void withdrawal() throws balanceerror
	{
		int amt;
		System.out.println("Enter Amount U Want to withdraw : ");
		amt=sc.nextInt();
		System.out.println(" \n");
		if(balance>=amt)
		{
			balance=balance-amt;
			System.out.println("The new balance in the account is: "+balance);
			System.out.println(" \n");
		}
		else
		{
			throw new balanceerror();
		}
	}
  //method to delete
  void delete()
  {
    balance=0;
    System.out.println("\nThe account has been deleted.");
    System.out.println(" \n");
  }

	//method to search an account number
	boolean search(int acn)
	{
		if(accno==acn)
		{
      if(balance==0)
				return false;
      showAccount();
			return(true);
		}
		return(false);
	}
}

class test
{
	public static void main(String arg[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("    WELCOME TO THE BANK   \n");
		System.out.println("   ");
		System.out.println("1.ENTER AS AN ADMIN\n2.ENTER AS AN CUSTOMER\n");
		int x =sc.nextInt();
		if(x==2)
		{
			System.out.println(" no accounts added till now\n");
			return;
		}

		//create initial accounts
		System.out.print("How Many Customers do you want to Insert : ");
		int n=sc.nextInt();
		Bank  C[]=new Bank[n];
		for(int i=0;i<C.length;i++)
		{
			C[i]=new Bank();
			C[i].openAccount();
		}
		System.out.println("DO YOU WANT TO ENTER AS A CUSTOMER\n1.YES\n2.NO");
		int y = sc.nextInt();
		if(y==2)
		{
			System.out.println("  THANK YOU  \n");
			return;
		}
		//run loop until menu 5 is not pressed
		int ch;
		do
		{
			System.out.println("Main Menu\n1.Display All\n2.Search By Account\n3.Deposit\n4.Withdrawal\n5.Simple interest\n6.Delete Account\n7.Exit");
			System.out.println(" \n");
			System.out.println(" Please Select One of the Options from Above  :");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					System.out.println("  This is a admin privilage.\nPlease enter your password\n");
					int z =sc.nextInt();
					if(z!=000)
					{
						System.out.println(" Wrong password\n");
						break;
					}
					System.out.println("the accounts are :\n");
					System.out.println(" ");
					for(int i=0;i<C.length;i++)
					{
						C[i].showAccount();
					}
					break;

				case 2:
					System.out.print("Enter the Account number to be searched :  ");
					int acn=sc.nextInt();
					boolean found=false;
					for(int i=0;i<C.length;i++)
					{
						found=C[i].search(acn);
						if(found)
						{
							break;
						}
					}
					if(!found)
					{
						System.out.println("Search Failed...\nAccount does not Exist...\n");
					}
					break;

				case 3:
					System.out.print("Enter Account No : ");
					acn=sc.nextInt();
					found=false;
					for(int i=0;i<C.length;i++)
					{
						found=C[i].search(acn);
						if(found)
						{
							C[i].deposit();
							break;
						}
					}
					if(!found)
					{
						System.out.println("Search Failed....\nAccount Does Not Exist..\n");
					}
					break;

				case 4:
					System.out.print("Enter Account No : ");
					acn=sc.nextInt();
					found=false;
					for(int i=0;i<C.length;i++)
					{
						found=C[i].search(acn);
						if(found)
						{
							try{
							C[i].withdrawal();
							}
							catch(balanceerror e)
							{
								System.out.println(e);
							}
							break;
						}
					}
					if(!found)
					{
						System.out.println("Search Failed....\nAccount Does Not Exist..\n");
					}
					break;

				case 5:
					{
            for(int i=0;i<C.length;i++)
  					{
  						C[i].interest();
  					}
            break;
          }
        case 6:
          {
            System.out.print("Enter Account No : ");
  					acn=sc.nextInt();
  					found=false;
  					for(int i=0;i<C.length;i++)
  					{
  						found=C[i].search(acn);
  						if(found)
  						{
  							C[i].delete();
  							break;
  						}
  					}
  					if(!found)
  					{
  						System.out.println("Search Failed....\nAccount Does Not Exist....\n");
  					}
  					break;
          }

        case 7:
        {
          System.out.println("   THANK YOU FOR USING OUR SERVICES \n");
					break;
        }

			}
		}
		while(ch!=7);
	}
}
