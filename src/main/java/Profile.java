import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.FileOutputStream;

public class Profile{
	private String firstName;
	private String lastName;
	private String fieldOfInterest;
	private String dayOfBirth;
	private String monthOfBirth;
	private String yearOfBirth;
	private String levelOfEducation;
	static String cwd = Paths.get(".").toAbsolutePath().normalize().toString();

	public Profile(String firstName, String lastName, String fieldOfInterest,
	                String dayOfBirth, String monthOfBirth, String yearOfBirth,
	                String levelOfEducation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fieldOfInterest = fieldOfInterest;
		this.dayOfBirth = dayOfBirth;
		this.monthOfBirth = monthOfBirth;
		this.yearOfBirth = yearOfBirth;
		this.levelOfEducation = levelOfEducation;
	}

	//Getters to handle the private values
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFieldOfInterest() {
		return fieldOfInterest;
	}

	public String getDayOfBirth() {
		return dayOfBirth;
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public String getLevelOfEducation() {
		return levelOfEducation;
	}
	//creates profile and saves data to the txt
	public static void createProfile(String userId) {
			System.out.println("Create your Bio");
			System.out.println("First name:");
			String fn = Interface.input.next();
			System.out.println("Last name:");
			String ln = Interface.input.next();
			System.out.println("Field of interest:");
			Interface.input.nextLine();
			String fOFi = Interface.input.nextLine();
			System.out.println("Birthday:");
			System.out.println("Day:");
			String dOFb = Interface.input.next();
			System.out.println("Month:");
			String mOFb = Interface.input.next();
			System.out.println("Year:");
			String yOFb = Interface.input.next();
			int answ = 0;
			String lOFe = "";
			do {
				System.out.println("Level of education:");
			    System.out.println("1.High School");
			    System.out.println("2.Bsc");
			    System.out.println("3.Msc");
			    System.out.println("4.Phd");
			   answ = Interface.input.nextInt();
			   switch(answ) {
				   case 1:
				   lOFe = "High School";
				   break;
				   case 2:
				   lOFe = "Bsc";
				   break;
				   case 3:
				   lOFe = "Msc";
				   break;
				   case 4:
				   lOFe = "Phd";
				   break;
				   default:
				   System.out.println("Wrong input. Try again");
			   }
			 } while(answ != 1 && answ != 2 && answ != 3 && answ != 4);
			Profile p = new Profile(fn , ln , fOFi , dOFb , mOFb , yOFb , lOFe);
			String d0 = userId;
			String d1 = p.getFirstName();
			String d2 = p.getLastName();
			String d3 = p.getFieldOfInterest();
			String d4 = p.getDayOfBirth();
			String d5 = p.getMonthOfBirth();
			String d6 = p.getYearOfBirth();
			String d7 = p.getLevelOfEducation();

			try {
				File f = new File(cwd + "\\src\\main\\resources\\ProfileData.txt");
				PrintWriter pw = new PrintWriter(new FileOutputStream(f,true));
				String dataToBeSaved
				= (d0 + "," + d1 + "," + d2 +
				"," + d3 + "," + d4 + "," + d5 +
				"," + d6 + "," + d7);
				pw.append(dataToBeSaved + "\n");
				pw.close();
				System.out.println("You have completed your Bio");
				Interface.mainMenu();
			} catch (IOException e1) {
				System.out.println("An error has occurred");
			}
	}
	//returns the values of a profile
	public static String[] getProfile(String userId) {
		String[] data = new String[8];
				String currentLine;
				boolean found = false;
				try {
					FileReader fr = new FileReader(cwd + "\\src\\main\\resources\\ProfileData.txt");
					BufferedReader br = new BufferedReader(fr);
					while((currentLine = br.readLine()) != null) {
						data = currentLine.split(",");
						if(userId == data[0]) {
							found = true;
							break;
						}
					}
					br.close();
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
					return null;
				} catch (IOException e1) {
					System.out.println("An error has occurred");
				}

		return data;
	}


	// shows the profile
	public static void viewProfile(String userId) {

		String[] d = getProfile(userId);
		if(d.length == 8) {
		     System.out.println("My Bio");
		     System.out.println("First name: " + d[1]);
		     System.out.println("Last name: " + d[2]);
		     System.out.println("Field of interest: " + d[3]);
		     System.out.println("Birthday: " +
		     d[4] + "/" + d[5] + "/" + d[6]);
		     System.out.println("Level of education: " + d[7]);
		 } else {
			 System.out.println("Could not find rec");
		 }
	}
	// makes changes in txt file by replacing the old line
	public static void changeProfile(String userId) {

		viewProfile(userId);
		String[] data = new String[8];
		data = getProfile(userId);
	    System.out.println('\n' + "1. Change first name");
	    System.out.println("2. Change last name");
		System.out.println("3. Change field of interest");
	    System.out.println("4. Change day of birth");
		System.out.println("5. Change month of birth");
		System.out.println("6. Change year of birth");
		System.out.println("7. Change level of education");
		int ans = Interface.input.nextInt();
		System.out.println("Enter the new value:");
		String newValue = "";
		int answ = 0;
		if(ans == 7) {
			do {
				System.out.println("Level of education:");
				System.out.println("1.High School");
			    System.out.println("2.Bsc");
				System.out.println("3.Msc");
				System.out.println("4.Phd");
				answ = Interface.input.nextInt();
				switch(answ) {
					case 1:
					newValue = "High School";
					break;
					case 2:
				    newValue = "Bsc";
					break;
					case 3:
					newValue = "Msc";
					break;
					case 4:
					newValue = "Phd";
					break;
					default:
					System.out.println("Wrong input. Please try again");
				}
			} while(answ != 1 && answ != 2 && answ != 3 && answ != 4);
		} else {
			newValue = Interface.input.next();
		}
	    String dataToBeSaved = data[0];
		String oldData = data[0];
	    for(int i = 1; i <8; i++) {
			oldData += ("," + data[i]);
			if(i == ans) {
				dataToBeSaved += ("," + newValue );
			} else {
				dataToBeSaved += ("," + data[i]);
			}
		}
		try {
			Scanner sc = new Scanner(new File(cwd + "\\src\\main\\resources\\ProfileData.txt"));
			StringBuffer buffer = new StringBuffer();
			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine()+System.lineSeparator());
			}
			String fileContents = buffer.toString();
			sc.close();
			fileContents = fileContents.replaceAll(oldData, dataToBeSaved);
			FileWriter writer = new FileWriter(cwd + "\\src\\main\\resources\\ProfileData.txt");
			writer.append(fileContents);
			writer.flush();
			writer.close();
			System.out.println("You have successfully changed your Bio");
			Interface.profileMenu();
		} catch(IOException e) {
			System.out.println("An error has occurred");
		}
	}

	//changes the username
	public static void changeUsername(String userId) {
		String[] data = new String[3];
		String currentLine;
		boolean found = false;
		String oldLine = "";
		String newLine = "";
		String newUN ;
		try {
			FileReader fr = new FileReader(cwd + "\\src\\main\\resources\\users.txt");
			BufferedReader br = new BufferedReader(fr);
			while((currentLine = br.readLine()) != null) {
				data = currentLine.split(",");
				if(userId.equals(data[0])) {
					found = true;
					break;
				}
			}
			br.close();
			if(found) {
				System.out.println("My username: " + data[1]);
	            System.out.println("Please enter new username");
                newUN = Interface.input.next();
                oldLine = data[0] + "," + data[1] + "," + data[2];
                newLine = data[0] + "," + newUN + "," + data[2];
                Scanner s2 = new Scanner(new File(cwd + "\\src\\main\\resources\\users.txt"));
                StringBuffer buffer = new StringBuffer();
                while(s2.hasNextLine()) {
					buffer.append(s2.nextLine() + System.lineSeparator());
				}
				String fileContents = buffer.toString();
				s2.close();
				fileContents = fileContents.replaceAll(oldLine, newLine);
				FileWriter writer = new FileWriter(cwd + "\\src\\main\\resources\\users.txt");
				writer.append(fileContents);
				writer.flush();
				System.out.println("You have successfully changed your username");
				writer.close();
			}
			Interface.profileMenu();
		} catch(FileNotFoundException e1) {
			System.out.println("File Not Found");
		} catch(IOException e2) {
			System.out.println("Error");
		}
	}

	//verifies current password and changes it
	public static void changePassword(String userId) {
		String[] data = new String[3];
		String currentLine;
		boolean found = false;
		boolean pwans = false;
		String oldLine = "";
		String newLine = "";
		String newPW;
		String currPW;
		try {
			FileReader fr = new FileReader(cwd + "\\src\\main\\resources\\users.txt");
			BufferedReader br = new BufferedReader(fr);
			while((currentLine = br.readLine()) != null) {
				data = currentLine.split(",");
				if(userId.equals(data[0])) {
					found = true;
					break;
				}
			}
			br.close();
			if(found) {

				System.out.println("Please enter current password:");
				currPW = Interface.input.next();
				if(currPW.equals(data[2])) {
					System.out.println("Please enter new password:");
					newPW = Interface.input.next();
					pwans = true;
				} else {
					System.out.println("Wrong password");
					System.out.println("You can't change the password");
					newPW = data[2];
				}
				oldLine = data[0] + "," + data[1] + "," + data[2];
				newLine = data[0] + "," + data[1] + "," + newPW;
				Scanner s2 = new Scanner(new File(cwd + "\\src\\main\\resources\\users.txt"));
				StringBuffer buffer = new StringBuffer();
				while(s2.hasNextLine()) {
					buffer.append(s2.nextLine() + System.lineSeparator());
				}
				String fileContents = buffer.toString();
				s2.close();
				fileContents = fileContents.replaceAll(oldLine, newLine);
				FileWriter writer = new FileWriter(cwd + "\\src\\main\\resources\\users.txt");
				writer.append(fileContents);
				writer.flush();
				writer.close();
				if(pwans) {
					System.out.println("You have successfully changed your password");
				}
			}
			Interface.profileMenu();
		} catch(FileNotFoundException e1) {
			System.out.println("File Not Found");
		} catch(IOException e2) {
			System.out.println("Error");
		}
	}
}
