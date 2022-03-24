//==============================================================================
// Summative2022
// Eric Chen
// 2022 Jan 25
// Java Eclipse Neon
// ==============================================================================
// Problem Definition – You will plan and code a program to help a restaurant operate efficiently each day - you should give this restaurant a name and theme (ie a sushi restaurant called - K’s Sushi).
//Your challenge is to solve the requirements using functional and procedural programming along with using 2-D Arrays in a meaningful way.
//You should also use all we have learned in the class - formatting decimals, lining up columns, rounding, counters, accumulators, constants, etc.
//When  you program is first run - it should ask whether the user wants to access one of the following :
//	1.	Server Functions
//- Recording Sales
//- Calculating Bills / HST / etc.
//- Taking payment in various formats (cash, e-transfer, credit, etc.)
//	2. 	Regular Management Functions
//Scheduling of employees
//Create / Modify Employee Shifts
//Update Menu Options with Specials and Prices
//3.	Periodic Management Functions
//- What is needed to run the restaurant
//Each of these functions should have sub-sets of other functions that the restaurant would need access to.
//Input: 1 integer denoting which menu function to run
//Output: Whatever function the user wants to use
//Process: create procedural methods for displaying all the data and functional methods to do the calculations, storing data, etc.
//When the user inputs a choice, call a function depending on what number the user entered
//==============================================================================
// List of Identifiers
//let s be the class Summative2022 to access its methods
//Public variables: (For use globally to easily access information from one function to another)
//let all MENUGROUP variables be constants to indicate at which index the menu will contain that category of items
//let GROUPNUMBERS be a constant integer array to store the all the MENUGROUP vairables so we can use an index to reference the menu index
//let constants NICKEL to FIFTYBILL be the values in decimal form of coins and bill in CAD in cents and dollars
//let fullMenuPrices be a 2d array to store all prices for the full menu type double
//let itemCount be a integer array of size of TOTALGROUPS which is a constant denoting the total amount of menugroups there are
//let newMenuPrice be a double array to store a new menu price entry
//let fullMenu be a 2d String array to store all menu items
//let workers be a 2d String array to store all workers and their pins, postions, and names
//let schedule be a 2d String array containing the weekly schedule used to input the starting workers
//let allServerInvoices be a 2D String array contianing all invoices from the servers
//let invoiceList be a 2D String array to store all invoices and their content
//let monethShifts be a 3D String array contining a month's shifts
//let newMenu be a String array to store new menu entries
//let new Shift be a String array to store new shifts of workers
//let weekdays be a String array storing the names of the weekdays
//let serverInvoice be a String array to store invoices of a server
//let newInvoice be a String for storing a newInvoice and its content
//let serversPresent be a Strin garray of currently logged in servers
//access level be an integer denotign what level of access the em[ployee has (unused due to lack of time to implement)
//let constants BOSS, MANAGER, SERVER, CHEF be the access level integers for each postiton
//let NUMBILLSCOINS be a constant integer denoting the amount of coins and bills there are
//let changeAmount be a 2D array of doubles to store the number of each coin it takes for a certain amount of money
//let allServerSales be a 2D double array of all sales made by a server (not enough time to implement)
//let all Sub and Total vaiables on line 75, be subtotals and totals for each category from pizza to sides (not includiong pizza toppings, crusts, or sizes)
//let boolean checkedOut be a indicator of whether or not the customer has paid
//let dailySales be a double array of daily sales for the week
//let current server be a String for the name of the server that is taking the order
//let cusomerName be a string for the name of the customer
//let server sales be double arrays to store the sales of the serever
//==============================================================================
import java.io.*;//import from IO library to access bufferedreader

public class Summative2022V5 {//start of summative2022 class
	//declararion of vairalbes
	public final int PIZZAMENUGROUP = 1, TOPPINGMENUGROUP = 2, SIDESMENUGROUP = 3, DRINKSMENUGROUP = 4, DESSERTSMENUGROUP = 5,
			CRUSTSMENUGROUP = 6, SIZEMENUGROUP = 7, TOTALGROUPS = 7, SMALLTOPPINGCOUNT = 2, MEDTOPPINGCOUNT =3, LARGETOPPINGCOUNT = 4;
	public final int[] GROUPNUMBERS = {PIZZAMENUGROUP, TOPPINGMENUGROUP, SIDESMENUGROUP, DRINKSMENUGROUP, DESSERTSMENUGROUP, CRUSTSMENUGROUP, SIZEMENUGROUP};
	public int numOfPizzaMenuItems = 0, numOfToppings = 0, numOfSides = 0, numOfDrinks = 0, numOfDesserts = 0, numOfCrusts = 0, numWorkers = 0, numOfSize = 0, day = 1, invoice = 1000, numServers = 0;
	public final double NICKEL = 0.05, DIME = 0.1, QUARTER = 0.25;
	public final int LOONIE = 1, TOONIE = 2, FIVEBILL = 5, TENBILL = 10, TWENTYBILL = 20, FIFTYBILL = 50, COINSBILLS = 9;
	public static double[][] fullMenuPrices = new double[7][10];
	public int[] itemCount = new int[TOTALGROUPS];
    public double[] newMenuPrice = new double[10];
    public static String[][] fullMenu = new String[7][10];
	public String[][] workers = new String[10][3], schedule = new String[7][10], allServerInvoices = new String[10][10], invoiceList = new String[30][9];
	public String[][][] monthShifts = new String[4][7][10];
    public String[] newMenu = new String[10], newShift = new String[10], weekDays = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public String[] serverInvoice  = new String[10], newInvoice = new String[9], serversPresent = new String[10];
	public int accessLevel = 0;
	public final int BOSS = 3, MANAGER = 3, SERVER = 2, CHEF = 1, NUMBILLSANDCOINS = 9;
	public double[][] changeAmount = new double[NUMBILLSANDCOINS][2], allServerSales = new double[10][10];
	public double pizzaSub = 0, sidesSub = 0, dessertSub = 0, drinksSub = 0, salesToday = 0, pizzaTotal = 0, sidesTotal = 0, dessertsTotal = 0, drinksTotal = 0, totalHST = 0;
	public boolean checkedOut = false;
	public double dailySales[] = new double[7];
	public String currentServer="", customerName="";
	public double[] serverSales = {0,0,0,0,0,0,0,0,0,0};

    public static void main(String[] args) throws IOException{//start of main method
        Summative2022V5 s = new Summative2022V5();
        //delcaration of a constant for exit conditon and choice for number of choice of the user
        final int EXIT = -1;
        int choice;
		//calling the starting menu and starting workers methods
        s.startingMenu();
		s.startingWorkers();

		System.out.println("Welcome to my resturant program!");//ufp
		System.out.println("\nThe program can preform Daily Sales Functions:\n" +
				"Entry to orders for customers\n" +
				"Display of menu options with categories of food, costs, etc.\n" +
				"Totals up each category into sub totals\n" +
				"Calculates an overall total for each bill\n" +
				"Also calculates HST (13%) \n" +
				"Records an invoice number along with each purchase order\n" +
				"Displays an receipt for the order\n" +
				"Records sales by each server\n" +

				"\nRegular Management Functions:\n" +
				"Scheduling of employees\n" +
				"Create / Modify Employee Shifts\n" +
				"Update Menu Options with Specials and Prices\n" +
				"Call up sales based on invoice number\n" +

				"\nManagement Functions:\n" +
				"show total sales for the day\n" +
				"allow  user to select highest sales display\n" +
				"highest sales by category\n" +
				"highest sales within each category\n" +
				"shows total sales for the week\n" +
				"shows total sales by each server - sorted from highest to lowest\n" +
				"shows total HST collected to date\n" +
				"allow manager to add new employees or to remove current employees");
		System.out.println();
		System.out.println("Welcome to ProPizza");
		System.out.println("Which functions do you want to access? You can also enter " + EXIT+ " to exit");//ufp
		System.out.println("(1) Daily Sales functions");
		System.out.println("(2) Regular Management functions");
		System.out.println("(3) Management functions");
		choice = Integer.parseInt(s.getInput());
		while(choice!=EXIT) {//start of whileloop for looping main menu
			try {
				switch (choice) {//switch statement to determine what method to call for which number of cjoice
					case 1:
						System.out.println("Which of the following daily sales funcions do you want to access? you can also enter " + EXIT + " to eixt");//ufp
						System.out.println("(1) display menu" +
								"\n(2)Order, pay, record revenue for server " +
								"\n(This will include payment functions such as taking payment for the order, recording server revenue, taxes, subtotals, and totals as well as recording an invoice number and display the receit" +
								"\n(3) Display a receit for a certain invoice number");
						int salesChoice = Integer.parseInt(s.getInput());//geting user input for sales coice
						while (salesChoice != EXIT) {//start of inner while loop for sales functions menu
							switch (salesChoice) {//switch statement to determine what method to call for which number of cjoice
								case 1:
									s.displayFullMenu();//dispalys full menu
									break;
								case 2:
									s.accountLogin();//asks worker to login before taking an order
									//to test login scroll down to startingWorkers method or use Bob as the name and 12345 as the pin
									s.order(fullMenu, fullMenuPrices);//calling order mehtod
									break;
								case 3:
									System.out.println("Enter invoice number");//ufp
									int invoiceNum = Integer.parseInt(s.getInput());
									s.displayInvoice(invoiceNum);//calling invoice method
									break;
							}
							System.out.println("Which of the following daily sales funcions do you want to access? You can also enter " + EXIT + " to exit");//ufp
							System.out.println("(1) display menu" +
									"\n(2)Order, pay, record revenue for server " +
									"\n(This will include payment functions such as taking payment for the order, recording server revenue, taxes, subtotals, and totals as well as recording an invoice number and display the receit" +
									"\n(3) Display a receit for a certain invoice number");
							salesChoice = Integer.parseInt(s.getInput());
						}//end while loop
						break;

					case 2:
						System.out.println("Which of the following regular manage funcions do you want to access? " + EXIT + " to eixt");//ufp
						System.out.println("(1) view current scheduling of employees" +
								"\n(2) create or modify current schedule " +
								"\n(3) modify current menu" +
								"\n(4) login");
						int regChoice = Integer.parseInt(s.getInput());//getting user input for reugalr mangage chjoice
						while (regChoice != EXIT) {//statr of whiel loop to loop regular mangement functons
							switch (regChoice) {//switch to use selection for reguallr mangement menu
								case 1:
									s.displayShcedule();//displaysd the schedule
									break;
								case 2:
									s.modifyShifts();//modifyshifts
									break;
								case 3:
									s.modifyMenu();//modify menu
									break;
								case 4:
									s.accountLogin();//login
									break;
							}
							System.out.println("Which of the following regular manage funcions do you want to access? " + EXIT + " to eixt");//ufp
							System.out.println("(1) view current scheduling of employees" +
									"\n(2) create or modify current schedule " +
									"\n(3) modify current menu" +
									"\n(4) login");
							regChoice = Integer.parseInt(s.getInput());
						}//end while loop
						break;
					case 3:
						System.out.println("Which of the following manager funcions do you want to access? " + EXIT + " to eixt");//ufp
						System.out.println("(1) show total sales for the day" +
								"\n(2) show which server has the highest sales" +
								"\n(3) highest sales by category" +
								"\n(4) total sales for the week" +
								"\n(5) shows sales from servers from highest to lowest" +
								"\n(6) shows total HST collected");
						int manChoice = Integer.parseInt(s.getInput());//user inpout for manager functions
						while (manChoice != EXIT) {//start of manger functions while loop
							switch (manChoice) {//switch to user selection for manger functions
								case 1:
									s.displayDailySales();//display sales today
									break;
								case 2:
									s.findHighestServer(s.serverSales);//find highest sales server
									break;
								case 3:
									s.displayHighest();//dispaly the highest categorty
									break;
								case 4:
									s.displayWeekTotal();//dispaly weekyl sales in total
									break;
								case 5:
									s.displayServerLowHigh();//display the srever sales from low to high
									break;
								case 6:
									s.displayHST();//display total tax
									break;
								case 7:
									s.modifyWorkers(s.workers);//modify wotkers
									break;
							}
							System.out.println("Which of the following manager funcions do you want to access? " + EXIT + " to eixt");//ufp
							System.out.println("(1) show total sales for the day" +
									"\n(2) show which server has the highest sales" +
									"\n(3) highest sales by category" +
									"\n(4) total sales for the week" +
									"\n(5) shows sales from servers from highest to lowest" +
									"\n(6) shows total HST collected");
							manChoice = Integer.parseInt(s.getInput());
						}//end while loop
						break;
					default:
						System.out.println("That is not a valid option");
						break;
				}
			}catch(NumberFormatException e){//catches if the user inoputs a string for a number
				System.out.println("That is not a valid number");
			}
			System.out.println("(1) Daily Sales functions");
			System.out.println("(2) Regular Management functions");
			System.out.println("(3) Management functions");
			System.out.println("Which functions do you want to access? You can also enter " + EXIT+ " to exit");//ufp
			choice = Integer.parseInt(s.getInput());

		}
    }
	public void displayHST(){//mehtod prints out total tax
		System.out.println("The total HST collected is " + totalHST);
	}
	public void displayServerLowHigh(){//method to display from low to hifgh server sales
		Summative2022V5 s = new Summative2022V5();
		double[] orderedList = s.bubbleSort(serverSales);//calls the bubble sort meoth to buble sort the array of sales
		System.out.println("The sales from servers from lowest to highest: ");
		for(double i : orderedList){
			System.out.print(i+", ");
		}
		System.out.println();
	}
	public void displayDailySales(){//prints out the sales
		Summative2022V5 s = new Summative2022V5();
		double sales = salesToday;
		System.out.println("Our sales for today is "+sales);
	}
	public void displayShcedule(){//prints out the schedule
		for(int i = 0; i<monthShifts.length; i++){
			System.out.println("Week "+i+": ");
			for(int j = 0; j<monthShifts[i].length; j++){
				System.out.print("Day " + j + ": ");
				for(int x = 0; x<monthShifts[i][j].length; x++){
					String currentElement = monthShifts[i][j][x];
					System.out.print(currentElement+ ", ");
				}
			}
		}
	}
	public void displayInvoice(int invoiceNum){//displayts an invoice
		for(int i = 0; i<invoiceList[invoiceNum-1000].length; i++){
			if(invoiceList[invoiceNum-1000][i]!=null) {
				String currentElement = invoiceList[invoiceNum - 1000][i];
				System.out.println(currentElement);
			}
		}
	}
	public void modifyWorkers(String[][] workers)throws IOException{//modify workers method
		Summative2022V5 s = new Summative2022V5();

		System.out.println();
		System.out.println("Would you like to add or remove a worker? Enter 1 to remove, enter 2 to add");
		int choice = Integer.parseInt(s.getInput());
		if(choice==1){
			System.out.println("Which of the the current workers will be removed : ");
			for (int i = 0; i < workers.length; i++) {
				if(workers[i][0]!=null){
					String worker = workers[i][0];//sets the currenty value at the index in tthis array to a string to print out
					System.out.print("("+(i+1)+") "+worker+", ");
				}
			}
			choice = Integer.parseInt(s.getInput());
			workers[choice-1] = new String[3];//remopves the worer by emptying that index in the array
			System.out.println("The new worker list is: ");
			for (int i = 0; i < workers.length; i++) {
				if(workers[i][0]!=null){
					String worker = workers[i][0];
					System.out.print(worker+", ");
				}
			}
		} else if(choice==2){
			s.addEmployee();//calls the add employee meothod
		}
	}
	public void order(String[][] fullMenu, double[][] fullMenuPrices) throws IOException {//order method to handle ordering from whic hmenu
		Summative2022V5 s = new Summative2022V5();
		System.out.println("Hello, what is your name?");//ufp
		s.customerName = s.getInput();//user input name
		//resets all acculamtor variables and the boolean for chekedOut
		s.dessertSub = 0;
		s.sidesSub = 0;
		s.pizzaSub = 0;
		s.drinksSub = 0;
		checkedOut = false;
		int category;
		final int EXIT = -1;
		try{
			System.out.println("Hello " + customerName +", which menu would you like to order from? You can also enter "+ EXIT+" to exit");
			System.out.println("(1) Build a pizza");
			System.out.println("(2) Sides menu");
			System.out.println("(3) Drinks menu");
			System.out.println("(4) Desserts menu");

			category = Integer.parseInt(s.getInput());
			while(category!=EXIT) {//whhile loop to loop until they have ifnished ordering
				switch (category) {//switch to retermine which menu group to access
					case 1:
						s.buildPizza();
						break;
					case 2:
						s.orderMenu(SIDESMENUGROUP);
						break;
					case 3:
						s.orderMenu(DRINKSMENUGROUP);
						break;
					case 4:
						s.orderMenu(DESSERTSMENUGROUP);
						break;
					default:
						System.out.println("That is not a valid number");
						break;
				}
				System.out.println("Hello "+customerName+", Which menu would you like to order from? You can also enter "+ EXIT+" to exit");
				System.out.println("(1) Build a pizza");
				System.out.println("(2) Sides menu");
				System.out.println("(3) Drinks menu");
				System.out.println("(4) Desserts menu");
				category = Integer.parseInt(s.getInput());
			}
			s.checkout(currentServer);
		}catch(NumberFormatException e){
			System.out.println("That is not a valid option.");//catch user from inputting string for a number
		}

	}
	public void orderMenu(int menuGroup) throws IOException{//mehtod to specially order form a menu
		Summative2022V5 s = new Summative2022V5();
		String currentElement, itemName, menuGroupName = "";
		switch(menuGroup){//determine which menu category name
			case SIDESMENUGROUP:
				menuGroupName = "sides";
				break;
			case DRINKSMENUGROUP:
				menuGroupName = "drinks";
				break;
			case DESSERTSMENUGROUP:
				menuGroupName = "desserts";
				break;
		}
		int choice;
		final int EXIT = -1;
		double price, total = 0, currentPrice;
		System.out.println("Hello "+customerName+", please enter the corresponding number for the item you wish to order or -1 to return to previous screen");
		for(int i = 0; i<fullMenu[menuGroup-1].length; i++){//for loop to display the menu
			if(fullMenu[menuGroup-1][i]!=null){
				currentElement = fullMenu[menuGroup-1][i];
				currentPrice = fullMenuPrices[menuGroup-1][i];
				int itemNumber = i+1;//use +1 because 0 is not user friendly and i sarts at 0
				System.out.println("("+itemNumber+") "+currentElement+" $"+currentPrice);
			}
		}
		try {
			choice = Integer.parseInt(s.getInput());//stores user input
			while (choice != EXIT) {
				price = fullMenuPrices[menuGroup - 1][choice - 1];
				total += price;//adds the price whcih is stored in the prices array to a total
				itemName = fullMenu[menuGroup - 1][choice - 1];//access another array to find the name of item at the same index
				System.out.println("You ordered " + itemName);
				System.out.println("Please enter the corresponding number for the item you wish to order or -1 to return to previous screen");
				for (int i = 0; i < fullMenu[menuGroup - 1].length; i++) {//for loop to display the menu
					if (fullMenu[menuGroup - 1][i] != null) {
						currentElement = fullMenu[menuGroup - 1][i];
						currentPrice = fullMenuPrices[menuGroup - 1][i];
						int itemNumber = i + 1;//use +1 because 0 is not user friendly and i sarts at 0
						System.out.println("(" + itemNumber + ") " + currentElement + " $" + currentPrice);
					}
				}
				choice = Integer.parseInt(s.getInput());
			}
		}catch(NumberFormatException e){
			System.out.println("That is not a valid number");//catches when user inputs a string instead of a number
		}
		System.out.println("Your subtotal for "+ menuGroupName +" is $" + total);//ufp
		switch(menuGroupName){//switch for selection of menugroup names
			case "sides":
				sidesSub +=total;
				break;
			case "drinks":
				drinksSub +=total;
				break;
			case "desserts":
				dessertSub += total;
				break;
		}
	}
	public void buildPizza() throws IOException {//pizza building method
		Summative2022V5 s= new Summative2022V5();
		int dough, size, crust, toppingChoice, toppingLimit = 0;
		double total =0, currentPrice;
		String currentTopping, doughName, crustName, sizeName, currentElement="";
		String [] toppingNames = new String[4];//creating array to store all toppings, size is 4 because the largest size can have up to 4 toppings
		System.out.println("Which dough woudl you like?");//ufp
		for(int i = 0; i<fullMenu[PIZZAMENUGROUP-1].length; i++){//for loop through pizza menu
			if(fullMenu[PIZZAMENUGROUP-1][i]!=null){//if staetment to see if the current element exists as a real element and not null
				currentElement = fullMenu[PIZZAMENUGROUP-1][i];//stores current item name
				currentPrice = fullMenuPrices[PIZZAMENUGROUP-1][i];//stores current item price
				int itemNumber = i+1;//user friendly number
				//we have to store the elements because otherwise it will not print the value of the elemnet
				System.out.println("("+itemNumber+") "+currentElement + " $" + currentPrice);//ufp
			}
		}
		try {
			dough = Integer.parseInt(s.getInput());//user input
			System.out.println("What size of pizza?");//ufp
			for (int i = 0; i < fullMenu[SIZEMENUGROUP - 1].length; i++) {//for loop through size menu
				if (fullMenu[SIZEMENUGROUP - 1][i] != null) {//checkign to see if current elemnt is null
					//storing both current price and uitem name and using i+1 to produce a user friendly number
					currentElement = fullMenu[SIZEMENUGROUP - 1][i];
					currentPrice = fullMenuPrices[SIZEMENUGROUP - 1][i];
					int itemNumber = i + 1;
					System.out.println("(" + itemNumber + ") " + currentElement + " $" + currentPrice);
				}
			}
			size = Integer.parseInt(s.getInput());
			System.out.println("What type of crust?");
			for (int i = 0; i < fullMenu[CRUSTSMENUGROUP - 1].length; i++) {//for loop though crust menu
				if (fullMenu[CRUSTSMENUGROUP - 1][i] != null) {//check if current elemnet is null
					//storing both current price and uitem name and using i+1 to produce a user friendly number
					currentElement = fullMenu[CRUSTSMENUGROUP - 1][i];
					currentPrice = fullMenuPrices[CRUSTSMENUGROUP - 1][i];
					int itemNumber = i + 1;
					System.out.println("(" + itemNumber + ") " + currentElement + " $" + currentPrice);
				}
			}
			crust = Integer.parseInt(s.getInput());//user input
			switch (size) {//swithc to determine topping limit per size
				case 1:
					//small
					toppingLimit = SMALLTOPPINGCOUNT;
					break;
				case 2:
					//medium
					toppingLimit = MEDTOPPINGCOUNT;
					break;
				case 3:
					//large
					toppingLimit = LARGETOPPINGCOUNT;
					break;

			}
			int[] toppings = new int[toppingLimit];//creates an array the size of the topping limit
			System.out.println("What toppings would you like? You can choose up to " + toppingLimit + " toppings");
			for (int i = 0; i < toppingLimit; i++) {//for loop until topping limit
				for (int j = 0; j < fullMenu[TOPPINGMENUGROUP - 1].length; j++) {//for loop through topping menu
					if (fullMenu[TOPPINGMENUGROUP - 1][j] != null) {//check if the current element is null
						//storing both current price and uitem name and using i+1 to produce a user friendly number
						currentTopping = fullMenu[TOPPINGMENUGROUP - 1][j];
						currentPrice = fullMenuPrices[TOPPINGMENUGROUP - 1][j];
						int itemNumber = j + 1;
						System.out.println("(" + itemNumber + ") " + currentTopping + " $" + currentPrice);
					}
				}
				toppingChoice = Integer.parseInt(s.getInput());//user input
				toppings[i] = toppingChoice;//stores input to the topping array
			}
			//creates strings for all pizza component names (excluding toppigns)
			doughName = fullMenu[PIZZAMENUGROUP - 1][dough - 1];
			sizeName = fullMenu[SIZEMENUGROUP - 1][size - 1];
			crustName = fullMenu[CRUSTSMENUGROUP - 1][crust - 1];
			//adding the price of each pizza component(excluding toppings) to the total of pizza
			total += fullMenuPrices[PIZZAMENUGROUP - 1][dough - 1];
			total += fullMenuPrices[SIZEMENUGROUP - 1][size - 1];
			total += fullMenuPrices[CRUSTSMENUGROUP - 1][crust - 1];
			for (int i = 0; i < toppings.length; i++) {//for loop through topping array
				toppingNames[i] = fullMenu[TOPPINGMENUGROUP - 1][toppings[i] - 1];//gets the names of the toppings
				total += fullMenuPrices[TOPPINGMENUGROUP - 1][toppings[i] - 1];//adds the price of toppings to the total
			}
			System.out.print("Your current pizza is a ");
			System.out.print(sizeName + " with ");//ufp
			for (int i = 0; i < toppingLimit; i++) {//for loop though the names of the toppings to display the toppings chosen
				if (toppingNames[i] != null) {//chechks if the current name is null
					String toppingName = toppingNames[i];//stores the element into a stirng for printing
					if (i != toppingLimit - 1)//checks if i is the last topping
						System.out.print(toppingName + ", ");//ufp
					else
						System.out.println("and " + toppingName);//ufp
				}
			}
			System.out.println("The subtotal for your pizza is $" + total);//ufp
			pizzaSub = total;//sets subtotal for pizza to the total price of pizza so far
		}catch(NumberFormatException e){//catch user inputting string for a number
			System.out.println("That is not a valid number");
		}
	}
	public void addEmployee()throws IOException{//add employee mehtod
		Summative2022V5 s = new Summative2022V5();
		String position = "";
		System.out.println("Enter employee name");//ufp
		String name = s.getInput();//store woker name
		System.out.println("Enter  for this employee");//ufp
		String pin = s.getInput(); //store pin for owkrer
		System.out.println("Enter postition for this employee (server(1), chef(2), or manager(3))");//ufp
		try {
			int postitionChoice = Integer.parseInt(s.getInput());//user input
			switch (postitionChoice) {//swtich sleection for what positon of worker
				case 1:
					position = "server";
					break;
				case 2:
					position = "chef";
					break;
				case 3:
					position = "manager";
					break;
				default:
					System.out.println("that is not a valid option");//ufp if user inputting different number
			}
			s.addWorker(numWorkers, pin, name, position, workers);//calls add worker mehtod to add the new worker to workers array
			numWorkers++;//increment number of workers incase another worker needs to be created at thhe next available index
		}catch(NumberFormatException e){//catch user inputting string
			System.out.println("That is not a valid number");
		}
	}
	public void startingWorkers(){//starting workers method
		Summative2022V5 s = new Summative2022V5();
		//calls add worker method and passes pin, name, and positon to sotre in workers array andn increment number of workers
		s.addWorker(numWorkers, "34083", "Eric", "Boss", workers);
		numWorkers++;
		s.addWorker(numWorkers, "12344", "Jacob", "server", workers);
		numWorkers++;
		s.addWorker(numWorkers, "12345", "Bob", "server", workers);
		numWorkers++;
		s.addWorker(numWorkers, "12346", "Michael", "server", workers);
		numWorkers++;
		s.addWorker(numWorkers, "23456", "Joe", "Chef", workers);
		numWorkers++;
		s.addWorker(numWorkers, "34567", "John", "Manager", workers);
		numWorkers++;
		String [] tempWorkers = new String[10];//create array String size 10
		//temperaory store all created workers
		for(int i = 0; i<6; i++){
			tempWorkers[i] = workers[i][0];
		}
		//setting the week schedule to be tempworkers
		for(int i = 0 ; i<7; i++){
			schedule[i] = tempWorkers;
		}
		//sets the monethly shifts to being the week schedule every week in the month
		for(int i = 0; i<monthShifts.length; i++){
			monthShifts[i] = schedule;
		}

	}
	public void displayMenu(int menuGroup, String[][] fullMenu, double[][]fullMenuPrices){
		//display a speciifc menu group and pass the menu and prices
		switch(menuGroup-1){//swithc selection for which menu
			case 0:
				System.out.println("Pizza dough choices: ");
				break;
			case 1:
				System.out.println("Toppings: ");
				break;
			case 2:
				System.out.println("Sides:");
				break;
			case 3:
				System.out.println("Drinks: ");
				break;
			case 4:
				System.out.println("Desserts: ");
				break;
			case 5:
				System.out.println("Crusts: ");
				break;
			case 6:
				System.out.println("Sizes: ");
				break;
			default:
				break;
		}
		System.out.println("---------------------");
		for(int j = 0; j<TOTALGROUPS; j++){//for loop to print out the item and price
			//store price and item name
			String item = fullMenu[menuGroup-1][j];
			double itemPrice = fullMenuPrices[menuGroup-1][j];
			if(item!=null){//check if item is null
    			System.out.print(item+" $");//ufp
    			System.out.print(itemPrice);
    			if(itemPrice%1==0||itemPrice%0.5==0||itemPrice%0.1==0)
    				System.out.print("0");
    			System.out.println();//new line since we used print not println
			}
		}
		System.out.println("----------------------");
	}
    public void displayFullMenu(){//displaying full menu method
		boolean lastPage = false;//creates a boolean to check if last page
    	for(int i = 0; i<TOTALGROUPS; i++){//for loop to loop through all groups in the menu
    		switch(i){//swtich sselcting ufps for menu category
    			case 0:
    				System.out.println("Pizza dough choices: ");
    				break;
    			case 1:
    				System.out.println("Toppings: ");
    				break;
    			case 2:
    				System.out.println("Sides:");
    				break;
    			case 3:
    				System.out.println("Drinks: ");
    				break;
    			case 4:
    				System.out.println("Desserts: ");
    				break;
    			case 5:
    				System.out.println("Crusts: ");
    				break;
				case 6:
					System.out.println("Sizes");
					break;
    		}
    		System.out.println("---------------------");
    		for(int j = 0; j<TOTALGROUPS; j++){//for loop to loop thoguh the menu in the index of i
    			String item = fullMenu[i][j];//stores itemname
				lastPage = true;//sets last page to true which will change if the item is not null because item will be null if it is the last page
    			if(item!=null){//check if item is null
					lastPage = false;//not last page if item is not null
					double itemPrice = fullMenuPrices[i][j];//stores price
	    			System.out.print(item+" $");//ufp
	    			System.out.print(itemPrice);
	    			if(itemPrice%1==0||itemPrice%0.5==0||itemPrice%0.1==0)
	    			System.out.print("0");
	    			System.out.println();
    			}
    		}
			if(lastPage){
    			System.out.println("----------------------");//ufp for last page
			}
    	}
    }

	public void accountLogin() throws IOException{//login method
		String name, pin, position;
		Summative2022V5 s = new Summative2022V5();
		System.out.println("Employee login");
		System.out.println("Please enter your name.");//ufp
		name = s.getInput();//user input name
		System.out.println("Please enter your pin number");//ufp
		pin = s.getInput();//user input pin
		position = s.checkPosition(name, pin, workers);//calls checkposition mehtod to check the postion of the worker
		if(position.equals("customer")){
			System.out.println("Unsucessful Login.");//ufp if login failed
		} else{
			System.out.println("Sucessful Login. Welcome back.");//ufp indicatin login success
			if(position.equals("server")) {//check if worker is a server
				currentServer = name;//sets the current server to the name
				serversPresent = s.addServer(numServers, currentServer, serversPresent);//add to the array of present servers
				numServers++;//increment number of servers
			}
		}
		switch(position){//swtich selecting access level depending on postion
			case "Boss":
				accessLevel = BOSS;
				break;
			case "Chef":
				accessLevel = CHEF;
				break;
			case "server":
				accessLevel = SERVER;
				break;
			case "Manager":
				accessLevel = MANAGER;
		}
	}
    public void startingMenu(){//startign menu
    	Summative2022V5 s = new Summative2022V5();
		//all code below is to create the starting menu
		//pizza dough
		newMenu = s.addMenu(numOfPizzaMenuItems, "new york", newMenu);
		//sets new menu to what addMenu return given the index at the n umber of pizza emnu items and item name, and to store in newMenu array
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		//stores the new menu into the full menu at the pizza menu group as the index
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 3, newMenuPrice);
		//new menu price at the index of number of pizza menu items given the price and sotres it in newMenuPirce
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		//stores new menu price in the full menu of prices at pizza menu group as the index
		numOfPizzaMenuItems++;//increment the number of pizza menu items
		//all code below does this but with different categories
		newMenu = s.addMenu(numOfPizzaMenuItems, "double dough", newMenu);//
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 3.50, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfPizzaMenuItems++;
		newMenu = s.addMenu(numOfPizzaMenuItems, "flat bread", newMenu);
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 2.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfPizzaMenuItems++;
		newMenu = s.addMenu(numOfPizzaMenuItems, "whole wheat", newMenu);
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 2.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfPizzaMenuItems++;
		newMenu = s.addMenu(numOfPizzaMenuItems, "neapolitian style", newMenu);
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 3.25, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfPizzaMenuItems++;
		newMenu = s.addMenu(numOfPizzaMenuItems, "chicago deep-dish", newMenu);
		fullMenu = s.addToFullMenu(PIZZAMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfPizzaMenuItems, 3, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(PIZZAMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfPizzaMenuItems++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		//toppings
		newMenu = s.addMenu(numOfToppings, "sausage", newMenu);
		fullMenu = s.addToFullMenu(TOPPINGMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfToppings, 0.85, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(TOPPINGMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfToppings++;
		newMenu = s.addMenu(numOfToppings, "pepperoni", newMenu);
		fullMenu = s.addToFullMenu(TOPPINGMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfToppings, 0.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(TOPPINGMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfToppings++;
		newMenu = s.addMenu(numOfToppings, "cheese", newMenu);
		fullMenu = s.addToFullMenu(TOPPINGMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfToppings, 0.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(TOPPINGMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfToppings++;
		newMenu = s.addMenu(numOfToppings, "pineapple", newMenu);
		fullMenu = s.addToFullMenu(TOPPINGMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfToppings, 0.55, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(TOPPINGMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfToppings++;
		newMenu = s.addMenu(numOfToppings, "peppers", newMenu);
		fullMenu = s.addToFullMenu(TOPPINGMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfToppings, 0.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(TOPPINGMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfToppings++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		//sides
		newMenu = s.addMenu(numOfSides, "fries", newMenu);
		fullMenu = s.addToFullMenu(SIDESMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSides, 2.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIDESMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSides++;
		newMenu = s.addMenu(numOfSides, "buffalo chicken wing", newMenu);
		fullMenu = s.addToFullMenu(SIDESMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSides, 1.25, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIDESMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSides++;
		newMenu = s.addMenu(numOfSides, "honey garlic chicken wing", newMenu);
		fullMenu = s.addToFullMenu(SIDESMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSides, 1.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIDESMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSides++;
		newMenu = s.addMenu(numOfSides, "poutine", newMenu);
		fullMenu = s.addToFullMenu(SIDESMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSides, 1.25, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIDESMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSides++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		//drinks
		newMenu = s.addMenu(numOfDrinks, "nestea", newMenu);
		fullMenu = s.addToFullMenu(DRINKSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDrinks, 1.80, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DRINKSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDrinks++;
		newMenu = s.addMenu(numOfDrinks, "mountain dew", newMenu);
		fullMenu = s.addToFullMenu(DRINKSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDrinks, 1.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DRINKSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDrinks++;
		newMenu = s.addMenu(numOfDrinks, "coca cola", newMenu);
		fullMenu = s.addToFullMenu(DRINKSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDrinks, 1.70, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DRINKSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDrinks++;
		newMenu = s.addMenu(numOfDrinks, "sprite", newMenu);
		fullMenu = s.addToFullMenu(DRINKSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDrinks, 1.65, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DRINKSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDrinks++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		//desserts
		newMenu = s.addMenu(numOfDesserts, "milk shake", newMenu);
		fullMenu = s.addToFullMenu(DESSERTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDesserts, 2.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DESSERTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDesserts++;
		newMenu = s.addMenu(numOfDesserts, "blueberry muffin", newMenu);
		fullMenu = s.addToFullMenu(DESSERTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDesserts, 2.20, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DESSERTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDesserts++;
		newMenu = s.addMenu(numOfDesserts, "sundae", newMenu);
		fullMenu = s.addToFullMenu(DESSERTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDesserts, 2.15, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DESSERTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDesserts++;
		newMenu = s.addMenu(numOfDesserts, "chocolate chip cookie", newMenu);
		fullMenu = s.addToFullMenu(DESSERTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfDesserts, 1.5, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(DESSERTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfDesserts++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		newMenu = s.addMenu(numOfCrusts, "stuffed", newMenu);
		fullMenu = s.addToFullMenu(CRUSTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfCrusts, 2.00, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(CRUSTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfCrusts++;
		newMenu = s.addMenu(numOfCrusts, "regular", newMenu);
		fullMenu = s.addToFullMenu(CRUSTSMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfCrusts, 1.75, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(CRUSTSMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfCrusts++;
		newMenu = new String[10];
		newMenuPrice = new double[10];
		//size of pizza
		newMenu = s.addMenu(numOfSize, "Small", newMenu);
		fullMenu = s.addToFullMenu(SIZEMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSize, 2, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIZEMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSize++;
		newMenu = s.addMenu(numOfSize, "Medium", newMenu);
		fullMenu = s.addToFullMenu(SIZEMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSize, 2.50, newMenuPrice);
		fullMenuPrices = s.addToFullMenuPrices(SIZEMENUGROUP, newMenuPrice, fullMenuPrices);
		numOfSize++;
		newMenu = s.addMenu(numOfSize, "Large", newMenu);
		fullMenu = s.addToFullMenu(SIZEMENUGROUP, newMenu, fullMenu);
		newMenuPrice = s.addMenuPrice(numOfSize, 3, newMenuPrice);
		numOfSize++;
		fullMenuPrices = s.addToFullMenuPrices(SIZEMENUGROUP, newMenuPrice, fullMenuPrices);
		//itemcoutn will contian all the starting numbers of the number of items for each menu
        itemCount = new int[]{numOfPizzaMenuItems, numOfToppings, numOfSides, numOfDrinks, numOfDesserts, numOfCrusts, numOfSize};
    }
	public void displayWeekTotal(){//displaying the weeks total sales method
		Summative2022V5 s = new Summative2022V5();
		double total = s.totalWeeklySales(dailySales);//stores the returned value from totalWeeklySales method given the daily sales
		System.out.println("The total weekly sales is " + total);//ufp
	}

    public void modifyMenu() throws IOException{//modifying menu method
    	Summative2022V5 s = new Summative2022V5();
		//declares variables for item name, price, cateogry, and user choicce
    	String itemName;
    	double itemPrice;
    	int category, choice;
		try {
			System.out.println("Which category do you wish to modify?");//ufp
			System.out.println("(1) Pizza dough");
			System.out.println("(2) Toppings Menu");
			System.out.println("(3) Sides Menu");
			System.out.println("(4) Drinks Menu");
			System.out.println("(5) Desserts Menu");
			System.out.println("(6) Pizza Crusts");
			System.out.println("(7) Pizza sizes");
			category = Integer.parseInt(s.getInput());//user input
			System.out.println("The current menu for that category is: ");
			s.displayMenu(category, fullMenu, fullMenuPrices);//dusplays the menu of user choice
			System.out.println("Would you like to add or delete an item? Please enter 1 for add, 2 for delete, 3 for edit, and 4 to cancel");
			choice = Integer.parseInt(s.getInput());//ufp and user input
			switch (choice) {//swithc selecting the option of chocie
				case 1:
					System.out.println("Please enter item name");//ufp
					itemName = s.getInput();//user input
					System.out.println("Please enter item price");//ufp
					itemPrice = Double.parseDouble(s.getInput());//user input
					newMenu = s.addMenu(itemCount[category - 1], itemName, newMenu);//adds the item to the newMenu array
					fullMenu = s.addToFullMenu(GROUPNUMBERS[category - 1], newMenu, fullMenu);//adds the newMenu to the full menu
					System.out.println(itemName + " is now an option in this menu for $" + itemPrice);//ufp
					break;
				case 2:
					System.out.println("Enter the corresponding number for the item you wish to delete");//ufp
					for (int i = 0; i < fullMenu[choice - 1].length; i++) {//for loop through the fullmenu at the menugroup of choice
						if (fullMenu[choice - 1][i] != null) {//checks if current element is null
							String currentItem = fullMenu[choice - 1][i];//stores current element in a stirng
							int itemNumber = i + 1;//ufp number
							System.out.println("(" + itemNumber + ") " + currentItem);//ufp
						}
					}
					int itemMod = Integer.parseInt(s.getInput());//user input
					String deleteName = fullMenu[choice - 1][itemMod - 1];//stores the itemname of the item to be deleted
					fullMenu[choice - 1][itemMod - 1] = "";//deletes item by empying that index in the full menu
					fullMenuPrices[choice - 1][itemMod - 1] = 0;//delete the price of that item
					System.out.println(deleteName + " was removed from the menu");//ufp
					break;
				case 3:
					int editChoice = 0;//declare editchoice variable to determine how the item will be deited
					System.out.println("Enter the corresponding number for the item you wish to edit");//ufp
					for (int i = 0; i < fullMenu[choice - 1].length; i++) {//for loop to loop thoguh the current chosen menu
						if (fullMenu[choice - 1][i] != null) {//check current if it is null
							String currentItem = fullMenu[choice - 1][i];//stores current
							int itemNumber = i + 1;//ufp number
							System.out.println("(" + itemNumber + ") " + currentItem);//ufp
						}
					}
					itemMod = Integer.parseInt(s.getInput());//user input
					System.out.println("Editing (1)name or (2)price, or both (3)?");//ufp
					editChoice = Integer.parseInt(s.getInput());//user input for choice
					if (editChoice == 1) {//selection if the choce is 1
						System.out.println("What should be the new name for this item?");
						String name = s.getInput();//user input
						fullMenu[choice - 1][itemMod - 1] = name;//changes name of the index slected to name
					}
					if (editChoice == 2) {//slecton if choice is 2
						System.out.println("What should the new price be for this item?");
						double price = Double.parseDouble(s.getInput());//user input for price
						fullMenuPrices[choice - 1][itemMod - 1] = price;//sets price to the price that user inputted
					}
					if (editChoice == 3) {//secltion for choice is 3
						System.out.println("What should be the new name for this item?");//ufp
						String name = s.getInput();//stores input
						fullMenu[choice - 1][itemMod - 1] = name;//sets name of the chosen menu item to the name
						System.out.println("What should the new price be for this item?");//ufp
						double price = Double.parseDouble(s.getInput());//stores user input in pirce
						fullMenuPrices[choice - 1][itemMod - 1] = price;//sets price of item to the price inputted
					}
					break;
				default:
					System.out.println("That is not a valid option.");//ufp
					break;
			}
		}catch(NumberFormatException e){
			System.out.println("That is not a valid number");//catches user inputting String for a number
		}
    }

	public void modifyShifts() throws IOException{//modify shifts method
		Summative2022V5 s = new Summative2022V5();
		System.out.println("For which week of this month would you like to access(1-4)?");//ufp to determine which week to access
		String weekDay="";//declare stirng variable for weekday name

		int week = Integer.parseInt(s.getInput());//user input
		System.out.println("Which day of this week woudl you like to modify?");//ufp
		for(int i = 0; i<weekDays.length; i++) {//for loop to print all weekdays
			System.out.println("(" + (i + 1) + ") " + weekDays[i]);
		}
		try {
			int day = Integer.parseInt(s.getInput());
			weekDay = weekDays[day-1];//sets the weekday to the user inptuted day number
			System.out.println("How would you like to modify shifts for this day?");
			System.out.println("Enter 1 for removing a shift, 2 for adding a shift, or 3 to cancel");//ufp
			int choice = Integer.parseInt(s.getInput());//user input choice
			switch (choice) {//switch selection for choice number
				case 1:
					System.out.println("The current schedule for " + weekDay + " of week " + week + " is ");//ufp
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {//for loop to loop thoguh monthShifts for slected index
						if(monthShifts[week-1][day-1][i]!=null) {//checks if current is null
							String currentShift = monthShifts[week-1][day-1][i];//stores current
							System.out.println(currentShift);//prints the shift
						}
					}
					System.out.println("Whose shift will be removed from this day?");//ufp
					String name = s.getInput();//user input
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {
						//for loop to loop though the shifts at this index and remove the shift that has the name that was inputted
						if(monthShifts[week-1][day-1][i]!=null) {//cehcks if current is null
							String currentShift = monthShifts[week-1][day-1][i];//stores current
							if (currentShift.equals(name)) {//checks if the current shift matches up with the name
								monthShifts[week-1][day-1][i] = null;//removes the shift by setting that shift to null
							}
						}
					}
					System.out.println("The new shift for week " + week +" "+weekDay+" is ");//ufp
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {//for loop to loop throguh the moenth shifts
						if(monthShifts[week-1][day-1][i]!=null) {//checks if current elemnet is null
							String currentShift = monthShifts[week-1][day-1][i];//stores current
							System.out.println(currentShift);//prints current
						}
					}
					break;
				case 2:
					System.out.println("The current schedule for " + weekDay + " of week " + week + " is ");//ufp
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {//for loop for month shifts
						if(monthShifts[week-1][day-1][i]!=null) {//check if current is null
							String currentShift = monthShifts[week-1][day-1][i];//stores current
							System.out.println(currentShift);//prints
						}
					}
					System.out.println("Please enter name of employee that is being added ");//ufp
					name = s.getInput();//user input
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {//loop though current shift
						if (monthShifts[week-1][day-1][i] == null) {//checks for next null element
							monthShifts[week-1][day-1][i] = name;//sets the null elemnt to name
							i=monthShifts[week-1][day-1].length;//forces out of the loop
						}
					}
					System.out.println("The new schedule for " + weekDay + " of week" + week + " is ");//ufp
					for (int i = 0; i < monthShifts[week-1][day-1].length; i++) {//ufp loop to display new shift
						if(monthShifts[week-1][day-1][i]!=null) {
							if (monthShifts[week-1][day-1][i] != null) {
								String currentShift = monthShifts[week-1][day-1][i];
								System.out.println(currentShift);
							}
						}
					}
					break;
				case 3:
					break;
				default:
					System.out.println("That is not a valid number");//ufp
					break;
			}
		}catch(NumberFormatException e){
			System.out.println("Please enter a number");//catch user input string for number
		}

	}
    public String getInput() throws IOException {//getInput method
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//br object for bufferedreader
        input = br.readLine();//get input
        return input;//return input
    }
    public String checkPosition(String name, String pin, String[][]workers){//checkpostiion method returning stirng
		String position = "customer";//sets deualt poston to a customer
    	for(int i = 0; i<workers.length; i++){//loop rhogu workers array to find the name give
			if(name.equals(workers[i][0])) {//once name is found, check for the pin
				if (pin.equals(workers[i][1])){
					position = workers[i][2];//gets the postion from workers array
				}
			}
    	}
    	return position;//returns the postion
    }
	public void checkout(String currentServer)throws IOException{//checkout method
		Summative2022V5 s = new Summative2022V5();
		//declare variables for subtotals, totals, tip, tax, etc.
		double subtotal = pizzaSub+drinksSub+sidesSub+dessertSub, total, tip =0, tax;
		double[] subtotals = {pizzaSub, sidesSub, drinksSub, dessertSub};
		String category="";
		//cacluate the tax, subtotals, and toatlas and round them off using roundOff method
		tax = s.getTax(subtotal);
		total = s.calculateTax(subtotal);
		total = s.roundOff(total);
		subtotal = s.roundOff(subtotal);
		tax = s.roundOff(tax);
		int paymentChoice, tipChoice;//delcare choice variables for user input
		if(subtotal!=0) {//only go thoguh if customer bougt something
			//print all info regarding payment
			System.out.println("Your subtotal is " + subtotal);
			System.out.println("Tax is " + tax);
			System.out.println("Subtotals for each category are ");
			for (int i = 0; i < subtotals.length; i++) {//looping though all the subtotal categories
				switch (i) {//swithc slection for cateogry name
					case 0:
						category = "pizza";
						break;
					case 1:
						category = "sides";
						break;
					case 2:
						category = "drinks";
						break;
					case 3:
						category = "desserts";
						break;
				}
				System.out.print(category + ": $");//ufp
				System.out.print(subtotals[i]);
				System.out.println();
			}
			System.out.println("Would you like to tip? Enter 1 for yes, enter 2 for no");//ufp
			try {
				tipChoice = Integer.parseInt(getInput());//user inputstore into integer
				if (tipChoice == 1) {//secltion if user wants to tip
					System.out.println("How much would you like to tip?");
					tip = Double.parseDouble(s.getInput());//gets user input
					total += tip;//adds the tip to total bill
				}
				//ufp and displaying payment methods
				System.out.println("Your total is " + total);
				System.out.println("How would you like to pay?");
				System.out.println("(1) cash");
				System.out.println("(2) credit/debit card");
				System.out.println("(3) e-transfer");
				System.out.println("(4) gift card");
				paymentChoice = Integer.parseInt(s.getInput());//user input of choice integer for payment method
				switch (paymentChoice) {//switch selecting which payment method and passe sin total
					case 1:
						s.payCash(total);
						break;
					case 2:
						s.payCard(total);
						break;
					case 3:
						s.payE(total);
						break;
					case 4:
						s.payGift(total);
						break;
					default:
						break;
				}
			} catch (NumberFormatException e) {
				System.out.println("That is not a valid number");//catvhes user input string for a number
			}
			//prints invoice info
			System.out.println("Customer name: " + customerName);
			System.out.println("Invoice number: " + invoice);
			System.out.println("Subtotals: ");
			for (int i = 0; i < subtotals.length; i++) {//for loop to print all subtotals
				switch (i) {//swithc slecting cateogry name
					case 1:
						category = "pizza";
						break;
					case 2:
						category = "sides";
						break;
					case 3:
						category = "drinks";
						break;
					case 4:
						category = "desserts";
						break;
				}
				System.out.print(category + ": ");
				System.out.print(subtotals[i]);
				System.out.println();
			}
			//pritns tax, tip, subtoa, total , and server name
			System.out.println("HST tax : " + tax);
			System.out.println("Tip :" + tip);
			System.out.println("subtotal : " + subtotal);
			System.out.println("total : " + total);
			System.out.println("Server name: " + currentServer);
			//adds the invoive number to a serverInvoice array to sotre to all server invoice array using addInvoice method
			serverInvoice = s.addInvoice(invoice, currentServer, serverInvoice);
			//adds the server invoice array to the arrya containing all server invoices using addToFullInvoices method
			allServerInvoices = s.addToFullInvoices(invoice, serverInvoice, allServerInvoices);
			//adds the invoice info to the invoice array using sotreInvoive mehtod
			newInvoice = s.storeInvoice(invoice, customerName, tax, subtotal, pizzaSub, sidesSub, drinksSub, dessertSub, tip, total, currentServer, newInvoice);
			//adds the invoice to the full invoices list
			invoiceList = s.addToFullInvoices(invoice, newInvoice, allServerInvoices);
			//changes the invoice number by incrementing it
			invoice++;
			salesToday += total;//adds the total to the sales made today vairable
			int serverid = 0;//delcare server id variable
			for (int i = 0; i < serversPresent.length; i++) {//for loop to loop though present servers to find the index of the current one who took the order
				if (serversPresent[i].equals(currentServer)) {//chekcing if the current elemnt is the current server
					serverid = i;//if the current leemnt is the current server, we found the index
				}
			}
			serverSales[serverid] += total;//adds the total to the array storing the serversales at the index of the current server
			//code below adds the subtotals to the totals for each category
			pizzaTotal += pizzaSub;
			sidesTotal += sidesSub;
			drinksTotal += drinksSub;
			dessertsTotal += dessertSub;
			//setting the value in daily sales array at the index of the current day-1 to the sales made today
			dailySales[day - 1] = salesToday;

			totalHST += tax;//adds tax to the total HST so far
		}
	}
	public void payCash(double cost) throws IOException{//payment via cash method
		Summative2022V5 s = new Summative2022V5();
		changeAmount = s.calculateChange(cost);//calls the calcualte change method to caluclate the amount of bills and coins need to pay
		double count, pay = 0;//delcares a count vairable as the current amount of the current bill or coin needed
		//delcares pay vairalbe to store how much money the user will give in coins and bills
		System.out.print("Via cash, you need ");//ufp
		for(int i = 0; i <NUMBILLSANDCOINS; i++){//for loop to loop until the number of bills and coins is reached
			switch(i){//switch to print out each type of bill and coin and how manu of them the customer needs to pay
				//if the count is 0, we dont print it out
				case 0:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $50 bills, ");
					break;
				case 1:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $20 bills, ");
					break;
				case 2:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $10 bills, ");
					break;
				case 3:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $5 bills, ");
					break;
				case 4:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $2 coins, ");
					break;
				case 5:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" $1 coins, ");
					break;
				case 6:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" 25 cents coints, ");
					break;
				case 7:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" 10 cents coins, ");
					break;
				case 8:
					count = changeAmount[i][1];
					if(count!=0)
						System.out.print((int)count+" 5 cents coins");
					break;
			}
		}
		System.out.println();//newline because we used pirnt not println
		//ufp
		System.out.println("Will you pay with these conditions? Enter 1 for yes, enter 2 for no or 3 if you would like to pay with larger bills");
		try {
			int choice = Integer.parseInt(s.getInput());//user input
			if (choice == 1) {//selection if choice  is 1
				System.out.println("Payment success, have a nice day");//ufp
				checkedOut = true;//sets checkedout to true as the customer has paid
			} else if (choice == 2) {//if the choice chosen is 2
				System.out.println("Payment unsucessful");//ufp
			} else if (choice == 3) {//if choice is 3 we need to get bills and coin from the user
				for (int i = 0; i < NUMBILLSANDCOINS; i++) {//for loop until all coins and bill hae been looped though
					switch (i) {//swtich to go through all postible bills and coins
						//ufp to tell user which type of bill/coin
						//then get user input for quanitity of that bill/coin
						//then add the quanitiy mutliplied by the value of the bill/coin to the pay vairable =
						case 0:
							System.out.println("How many $50 bills");
							choice = Integer.parseInt(s.getInput());
							pay += choice * FIFTYBILL;
							break;
						case 1:
							System.out.println("How many $20 bills");
							choice = Integer.parseInt(s.getInput());
							pay += choice * TWENTYBILL;
							break;
						case 2:
							System.out.println("How many $10 bills");
							choice = Integer.parseInt(s.getInput());
							pay += choice * TENBILL;
							break;
						case 3:
							System.out.println("How many $5 bills");
							choice = Integer.parseInt(s.getInput());
							pay += choice * FIVEBILL;
							break;
						case 4:
							System.out.println("How many $2 coins");
							choice = Integer.parseInt(s.getInput());
							pay += choice * TOONIE;
							break;
						case 5:
							System.out.println("How many $1 coins");
							choice = Integer.parseInt(s.getInput());
							pay += choice * LOONIE;
							break;
						case 6:
							System.out.println("How many 25 cents coins");
							choice = Integer.parseInt(s.getInput());
							pay += choice * QUARTER;
							break;
						case 7:
							System.out.println("How many 10 cents coins");
							choice = Integer.parseInt(s.getInput());
							pay += choice * DIME;
							break;
						case 8:
							System.out.println("How many 5 cents coins");
							choice = Integer.parseInt(s.getInput());
							pay += choice * NICKEL;
							break;
					}
				}
				pay = s.calculateChangeDue(pay, cost);//calculate the cahnge owed
				if (pay > 0) {//only go though if pay is greater than 0 so we need to give change
					changeAmount = s.calculateChange(pay);//we call on calcualte change method and pas sin the change due stored in pay
					System.out.print("Your change is ");//ufp
					for (int i = 0; i < NUMBILLSANDCOINS; i++) {//for loop to loop though all bills and coins
						switch (i) {//swithc to get every case for every bill/coin
							//if the count is 0, we dont pritn it out
							case 0:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $50 bills, ");
								break;
							case 1:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $20 bills, ");
								break;
							case 2:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $10 bills, ");
								break;
							case 3:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $5 bills, ");
								break;
							case 4:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $2 coins, ");
								break;
							case 5:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " $1 coins, ");
								break;
							case 6:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " 25 cents coints, ");
								break;
							case 7:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " 10 cents coins, ");
								break;
							case 8:
								count = changeAmount[i][1];
								if (count != 0)
									System.out.print((int) count + " 5 cents coins");
								break;
						}
					}
					System.out.println();
				}
				if (pay == 0) {
					System.out.println("Payment successful");//ufp
					checkedOut = true;//customer paid so set checkedout to true;
				}
			}
		}catch(NumberFormatException e){
			System.out.println("That is not a valid number");//catch if user inputs string for a nnubmer
		}
	}
	public void payCard(double cost) throws IOException{//payment via vard mehtod
		Summative2022V5 s = new Summative2022V5();
		boolean valid = false;//booelan indicating card as valid or not
		double[][] cards = {{1234678, 1234}, {87654321, 4321}, {00000000, 9999}};//double arrya of cards and their pin
		System.out.println("Please enter card number");//ufp
		int cardNumber = Integer.parseInt(s.getInput());//user input parsed to integer
		for(int i = 0; i<cards.length; i++){//for loop to loop the cards array
			if((long)cards[i][0]==cardNumber){//check if the cardnumber matches the current array element
				System.out.println("Enter pin number");//ufp
				int pin = Integer.parseInt(s.getInput());//user input number for pin
				for(int j = 0; j<cards[i].length; j++){//iterate rhough cardss array at index of i
					if(cards[i][j]==pin){//check if the current element is equal to the pin entered by the user
						System.out.println("Payment successful.");//ufp
						valid = true;//sets valid to true
						checkedOut =true;//sets checkedOut to true;
					}
				}
			}
		}
		if(!valid){
			System.out.println("Card declined");//ufp if not vlaid card
		}

	}
	public void payE(double cost) throws IOException{//payment vie e-transfer emthod
		Summative2022V5 s = new Summative2022V5();
		double moneyLeft = 0;//doubel for money left in account
		boolean paid = false;//booelan for whether or not customer paid
		double[][] pincodes = {{1234, 50}, {0000, 100}, {9999, 150}, {0000, 200}};//arrray of accoutn numbers and balances
		System.out.println("Please enter account pincode");//ufp
		try {
			int code = Integer.parseInt(s.getInput());//user input code for accoutn
			for (int i = 0; i < pincodes.length; i++) {//for loop to loop through all pincodes
				if ((int) pincodes[i][0] == code) {//checks if the code is equal to the element in the pincodes array at index of i and 0
					pincodes[i][1] -= cost;//if pincode matches, subtract the cost from the balance
					moneyLeft = pincodes[i][1];//sets moneyleft to the rest of the balance
					if(moneyLeft>=0)//if check
						paid = true;
				}
			}
			if (paid) {
				checkedOut = true;
				System.out.println("Thank you for using e-transfer, your card has $" + moneyLeft + " money left.");
			} else
				System.out.println("That is not a valid card");
		}catch(NumberFormatException e){
			System.out.println("That is not a valid number");
		}
	}
	public void payGift(double cost) throws IOException{
		Summative2022V5 s = new Summative2022V5();
		double moneyLeft = 0;
		double[][] giftcardCodes = {{12345678, 50}, {314159265, 100}, {99999999, 150}, {00000000, 200}};
		System.out.println("Please enter giftcard code");
		try {
			long code = Long.parseLong(s.getInput());
			for (int i = 0; i < giftcardCodes.length; i++) {
				if ((long) giftcardCodes[i][0] == code) {
					giftcardCodes[i][1] -= cost;
					moneyLeft = giftcardCodes[i][1];
				}
			}
			if (moneyLeft > 0) {
				System.out.println("Thank you for using giftcards, your card has " + moneyLeft + " money left.");
				checkedOut = true;
			} else if (moneyLeft == 0) {
				System.out.println("Thank you for using giftcards.");
				checkedOut = true;
			} else if (moneyLeft < 0) {
				System.out.println("Your gift card does not have enough money. Please pay with a different payment method.");

			}
		}catch(NumberFormatException e){
			System.out.println("That is not a valid number");
		}
	}
	public void findHighestServer(double []serverSales){
		Summative2022V5 s = new Summative2022V5();
		double[] tempSales = serverSales;
		int index = 0;
		double highest = s.getHighest(serverSales);
		for(int i = 0; i<tempSales.length; i++){
			if(highest==tempSales[i]){
				index = i;
			}
		}
		String highestServer = serversPresent[index];
		System.out.println("The server that has the highest sales is " + highestServer+" who made $" + highest);
	}
	public void displayHighest(){
		Summative2022V5 s = new Summative2022V5();
		double highest = s.highestCategory(pizzaTotal, sidesTotal, drinksTotal, dessertsTotal);
		String category = "";
		if(highest==pizzaTotal){
			category = "pizza";

		}else if(highest == sidesTotal){
			category = "sides";
		}else if(highest == drinksTotal){
			category = "drinks";
		}else if(highest== dessertsTotal){
			category = "desserts";
		}
		System.out.println("The highest selling category is " + category + " for " + highest);
	}
    public double roundOff(double num){
        num*=100;
        num=(int)num;
        num/=100;
        return num;
    }
    public double getTax(double price){
        final double HST = 0.13;
        price=price*HST;
        return price;
    }
	public double calculateTax(double price){
		final double HST = 0.13;
		price=(price*HST)+price;
		return price;
	}
    public boolean checkPayment(double given, double cost){
        if(given<cost){
            return false;
        }
        return true;
    }
    public double calculateChangeDue(double given, double cost){
    	return given-cost;
    }
    public double[][] calculateChange(double changeDue){

        double modFifty, modTwenty, modTen, modFive, modTwo, modOne, modNick, modDime, modQuarter;
        changeDue+=0.001;//prevent data loss
        double[][] changeAmount = new double[COINSBILLS][2];
        if(FIFTYBILL<changeDue) {
            modFifty = changeDue % FIFTYBILL;
            modFifty = (changeDue - modFifty)/FIFTYBILL;
            changeDue -= modFifty * FIFTYBILL;

            changeAmount[0][1] = modFifty;
        }
        changeAmount[0][0] = FIFTYBILL;
        if(TWENTYBILL<changeDue) {
            modTwenty = changeDue % TWENTYBILL;
            modTwenty = (changeDue - modTwenty)/TWENTYBILL;
            changeDue -= modTwenty * TWENTYBILL;

            changeAmount[1][1] = modTwenty;
        }
        changeAmount[1][0] = TWENTYBILL;
        if(TENBILL<changeDue) {
            modTen = changeDue % TENBILL;
            modTen = (changeDue - modTen)/TENBILL;
            changeDue -= modTen * TENBILL;
            changeAmount[2][1] = modTen;
        }
        changeAmount[2][0] = TENBILL;
        if(FIVEBILL<changeDue) {
            modFive = changeDue % FIVEBILL;
            modFive = (changeDue - modFive)/FIVEBILL;
            changeDue -= modFive * FIVEBILL;

            changeAmount[3][1] = modFive;
        }
        changeAmount[3][0] = FIVEBILL;
        if(TOONIE<changeDue) {
            modTwo = changeDue % TOONIE;
            modTwo = (changeDue - modTwo)/TOONIE;
            changeDue -= modTwo * TOONIE;

            changeAmount[4][1] = modTwo;
        }
        changeAmount[4][0] = TOONIE;
        if(LOONIE<changeDue) {
            modOne = changeDue % LOONIE;
            modOne = (changeDue - modOne)/LOONIE;
            changeDue -= modOne * LOONIE;

            changeAmount[5][1] = modOne;
        }
        changeAmount[5][0] = LOONIE;
        if(QUARTER<changeDue) {
            modQuarter = changeDue % QUARTER;
            modQuarter = (changeDue - modQuarter)/QUARTER;
            changeDue -= modQuarter * QUARTER;

            changeAmount[6][1] = modQuarter;
        }
        changeAmount[6][0] = QUARTER;
        if(DIME<changeDue) {
            modDime = changeDue % DIME;
            modDime = (changeDue - modDime)/DIME;
            changeDue -= modDime * DIME;

            changeAmount[7][1] = modDime;
        }
        changeAmount[7][0] = DIME;
        if(NICKEL<changeDue) {
            modNick = changeDue % NICKEL;
            modNick = (changeDue - modNick)/NICKEL;

            changeAmount[8][1] = modNick;
        }
        changeAmount[8][0] = NICKEL;

        return changeAmount;
    }
	public double getHighest(double[] sales){
		double max = sales[0];
		for(int i = 0; i<sales.length; i++){
			if(sales[i]>max){
				max = sales[i];
			}
		}
		return max;
	}
	public double[] bubbleSort(double[] array){
		for(int i = 0; i<array.length; i++){
			for(int j = 0; j<array.length; j++){
				if(j!=i){
					if(array[j]>array[i]){
						double temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
		}
		return array;
	}
	public String[] storeInvoice (int invoiceNum, String customerName, double tax, double subtotal, double pizzaSub, double sidesSub, double drinksSub, double dessertSub,
								  double tip, double total, String serverName, String[]invoiceList){
		String taxString = "Tax: "+ tax;
		String subString = "Subtotal: " + subtotal;
		String pizzaString = "Pizza subtotal: " + pizzaSub;
		String sideString = "Sides subtotal: " + sidesSub;
		String drinkString = "Drinks subtotal: " + drinksSub;
		String dessertString = "Desserts subtotal: " + dessertSub;
		String tipString = "Tip: " +tip;
		String totalString = "Total: " + total;
		invoiceList = new String[]{taxString, subString, pizzaString, sideString, drinkString, dessertString, tipString, totalString, serverName};
		return invoiceList;
	}
	public double totalWeeklySales(double[] dailySales){
		double total = 0;
		for(double i : dailySales){
			total+=i;
		}
		return total;
	}
	public String[] addServer(int serverNum, String serverName, String[]presentServers){
		presentServers[serverNum] = serverName;
		return presentServers;
	}
	public String[][] storeAllInvoice(int invoiceNum, String[] invoiceList, String[][]allInvoices){
		allInvoices[invoiceNum-1000] = invoiceList;
		return allInvoices;
	}
	public String[] addInvoice(int invoiceNum, String serverName, String[] serverInvoices){
		serverInvoices[invoiceNum-1000] = serverName;
		return serverInvoices;
	}
	public String[][] addToFullInvoices(int invoiceNum, String[]invoiceList, String[][]allServerInvoices){
		allServerInvoices[invoiceNum-1000] = invoiceList;
		return allServerInvoices;
	}
    public double addToTotal(double cost, double total){
    	total+=cost;
    	return total;
    }
	public String[][] addWorker(int workerNum, String pin, String name, String position, String[][]newWorker){
		newWorker[workerNum] = new String[]{name, pin, position};
		return newWorker;
	}
    public double[] addMenuPrice(int itemNumber, double price, double[] menuGroup){
    	menuGroup[itemNumber] = price;
    	return menuGroup;
    }
    public double[][] addToFullMenuPrices(int menuPage, double[]menu, double[][]fullMenu){
    	fullMenu[menuPage-1] = menu;
    	return fullMenu;
    }
    public String[] addMenu(int itemNumber, String itemName, String[] menu){
    	menu[itemNumber] = itemName;
    	return menu;
    }
    public String[][] addToFullMenu(int menuPage, String[]menu, String[][]fullMenu){
    	fullMenu[menuPage-1] = menu;
    	return fullMenu;
    }
	public double highestCategory(double pizzaTotal, double sidesTotal, double drinksTotal, double dessertTotal){
		double[] list = {pizzaTotal, sidesTotal, drinksTotal, dessertTotal};
		Summative2022V5 s = new Summative2022V5();
		double max = s.getHighest(list);
		return max;
	}
	public double recordSales(double revenue, double totalSales){
		totalSales+=revenue;
		return totalSales;
	}
}
