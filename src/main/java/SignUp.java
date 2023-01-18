import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.FileOutputStream;

import java.util.Scanner;

public class SignUp {
	static int id = 1;
	public String signUp() {
		String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
		String path = cwd + "\\src\\main\\resources\\users.txt";
	

		System.out.println("Please enter a username");
		boolean x = true;
		String username ="";
		String line;
		
		username = Interface.input.next();

		try {

			do
			{
				
				FileReader fr = new FileReader(cwd + "\\src\\main\\resources\\users.txt");
				BufferedReader br = new BufferedReader(fr);
				
				while((line = br.readLine()) != null) {
					
					id++;
					String[] column = line.split(",");

					if (username.equals(column[1])) {
						System.out.println("Someone else is already using this username. Please enter a new one.");
						x = false;
						signUp();
					} else {
						x = true;
					}
				}
				br.close();
			} while (x == false);
			
		}catch (FileNotFoundException e) {
			System.err.println("Unable to open file " + path);
		} catch (Exception e) {
			System.err.println("An error has occurred");
		}

		System.out.println("Please enter a password");
	    String password = Interface.input.next();
		id += 1;

		try {
			File file = new File(path);
			PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
			String dataToBeSaved = (id + "," + username + "," + password);
			pw.append(dataToBeSaved + "\n");
			pw.close();

		} catch (FileNotFoundException e) {
			System.err.println("Unable to open file ");

		} catch (Exception e) {
			System.err.println("An error has occurred");
		} 

		String userid = String.valueOf(id);
		Profile.createProfile(userid);
		return userid;
	}
}

