import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;

public class Post {
	static String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    //Create Post Method	
	public void createPostMethod(String userID) {

        Random rand = new Random();
        StringBuilder buffer = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
        	int limitedInt = 97 + (int) (rand.nextFloat() * (97 - 122));
        	buffer.append((char)  limitedInt);
        }
        String postID = buffer.toString();
        //The code above generates PostID
        System.out.println("1. Add description" + "\n" + "2. Add text/link");
        int option = Interface.input.nextInt();
        try {
			
			BufferedWriter writer = new BufferedWriter( new FileWriter(cwd + "\\src\\main\\resources\\post.txt", true));

			Interface.input.nextLine();
			if (option == 1) {

				writer.write(userID + "\n");
				writer.write(postID + "\n");
			    System.out.println("Please enter your description:");
			    String description = Interface.input.nextLine();
			    writer.write(description + "\n");
				System.out.println("Please insert link:");
			    String userPost = Interface.input.nextLine();
				writer.write(userPost + "\n");
				writer.close();

			} else if (option == 2) {

				writer.write(userID + "\n");
				writer.write(postID + "\n");
				writer.write("\n");
				System.out.println("Please insert link:");
			    String userPost = Interface.input.nextLine();
				writer.write(userPost + "\n");
				writer.close();	
					
			} else {
				System.out.println("Please enter 1 or 2");
			}
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Please enter 1 or 2");
		}
		try {
			Interface.mainMenu();
		} catch (IOException e) {
			System.out.println("error on mainMenu");
		}	
    }
    //Edit Method
	
    public static void editPostMethod(String userId, String postId){
	    try{
            ArrayList<String> editArrayList = new ArrayList<String>();
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(cwd + "\\src\\main\\resources\\post.txt"), "UTF-8"));
			String line;
			boolean flag = false;
            int i = 0;
            while((line = reader.readLine()) != null) {
       	        editArrayList.add(line);
       	    }
			reader.close();
            do{

                if(userId.equals(editArrayList.get(i)) && postId.equals(editArrayList.get(i+1))){
				
					flag = true;
                    System.out.println("1: Edit description \n" + "2: Edit link \n");

                    int option = Interface.input.nextInt();

                    if(option == 1){
                        System.out.println("Please enter new description for your post");
						Interface.input.nextLine();
                        String des = Interface.input.nextLine();
                        editArrayList.set(i+2, des);
						try {
							File oldfile = new File(cwd + "\\src\\main\\resources\\post.txt");
							File newfile = new File(cwd + "\\src\\main\\resources\\newpost.txt");
							BufferedWriter writer = new BufferedWriter( new FileWriter(cwd + "\\src\\main\\resources\\newpost.txt", true));
					
							for(String str: editArrayList) {
								writer.write(str + System.lineSeparator());
							   }
							writer.close();
							if(oldfile.delete()){
								System.out.println("File deleted");
							} else {
								System.out.println("file not deleted");
							}

							File dump = new File(cwd + "\\src\\main\\resources\\post.txt");
							newfile.renameTo(dump);
							System.out.println("New file created");
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
						Interface.mainMenu();
                    } else if(option == 2){
                        System.out.println("Please enter new link for your post");
                        String link = Interface.input.next();
                        editArrayList.set(i+3, link);
						try {
							File oldfile = new File(cwd + "\\src\\main\\resources\\post.txt");
							File newfile = new File(cwd + "\\src\\main\\resources\\newpost.txt");
							BufferedWriter writer = new BufferedWriter( new FileWriter(cwd + "\\src\\main\\resources\\newpost.txt", true));
					
							for(String str: editArrayList) {
								writer.write(str + System.lineSeparator());
							}
							writer.close();
							oldfile.delete();
							File dump = new File(cwd + "\\src\\main\\resources\\post.txt");
							newfile.renameTo(dump);
							System.out.println("New file created");
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
						Interface.mainMenu();
                    } else {
                        System.out.println("Wrong input. Please enter 1 or 2 \n");
						throw new IOException();
                    }

				}
                i = i + 4;
            } while(editArrayList.get(i+1) != null || flag == true/*&& editArrayList.get(i) == userId && editArrayList.get(i+1) == postId */);

        } catch (IOException e) {
			editPostMethod(userId, postId);
        }
		Post p = new Post();
		p.myPostsMenu(userId);
    }
	
	//Delete Method
	public static void deletePostMethod(String userId, String postId) {
	    try{
            ArrayList<String> editArrayList = new ArrayList<String>();
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(cwd + "\\src\\main\\resources\\post.txt"), "UTF-8"));
			String line;
			boolean flag = false;
            int i = 0;
            while((line = reader.readLine()) != null) {
       	        editArrayList.add(line);
       	    }
			reader.close();
            do{

                if(userId.equals(editArrayList.get(i)) && postId.equals(editArrayList.get(i+1))){
				    String des = editArrayList.get(i+2);
					String link = editArrayList.get(i+3);
					flag = true;
                    System.out.println("Deleting post with id "+postId);
					
                    editArrayList.remove(postId);
                    editArrayList.remove(userId);
					editArrayList.remove(des);
					editArrayList.remove(link);
				
					try {
						File oldfile = new File(cwd + "\\src\\main\\resources\\post.txt");
						File newfile = new File(cwd + "\\src\\main\\resources\\tempPost.txt");
						BufferedWriter writer = new BufferedWriter( new FileWriter(cwd + "\\src\\main\\resources\\tempPost.txt", true));
					
						for(String str: editArrayList) {
							writer.write(str + System.lineSeparator());
						}
						writer.close();
						if(oldfile.delete()){
								System.out.println("File deleted");
						} else {
								System.out.println("file not deleted");
						}

						File dump = new File(cwd + "\\src\\main\\resources\\post.txt");
						newfile.renameTo(dump);
						System.out.println("New file created");
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
						Interface.mainMenu();
                   
                } 

            i = i + 4;
            } while(editArrayList.get(i+1) != null || flag == true);

        } catch (IOException e) {
			editPostMethod(userId, postId);
        }
		Post p = new Post();
		p.myPostsMenu(userId);
    }

	//nextPost method
	public void myPostsMenu(String userId) {

		try {

			LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(cwd + "\\src\\main\\resources\\post.txt"), "UTF-8"));
        	ArrayList<String> listOfStrings = new ArrayList<String>();
			int i = 0;
			String strLine;
			boolean first = true;
			boolean flag = false;
			while((strLine = reader.readLine()) != null) {
				listOfStrings.add(strLine);
			}
		 	listOfStrings.add(null);
			reader.close();
			//Print first post
			while(listOfStrings.get(i) != null && first == true) {

				if( listOfStrings.get(i).equals(userId) ) {
					first = false;
					System.out.println(listOfStrings.get(i + 2) + "\n" + listOfStrings.get(i + 3));
				}
				i += 4;
			}
			if (first == false) {
				do {
					flag = false;
					System.out.println("1. Edit post \n" + "2. Delete post \n" + "3. Next Post \n" + "4. Go back to profile \n");
					int answer = Interface.input.nextInt();
					if (answer == 1) {
						editPostMethod(userId, listOfStrings.get(i - 3));					
					} else if (answer == 2) {
						deletePostMethod(userId, listOfStrings.get(i - 3));
					} else if (answer == 3) {

						while(listOfStrings.get(i) != null && flag == false) {

							if( listOfStrings.get(i).equals(userId) ) {
								System.out.println(listOfStrings.get(i + 2) + "\n" + listOfStrings.get(i + 3));
								flag = true;
							}
							i += 4;
						}
						if (listOfStrings.get(i) == null) {
							System.out.println("You have reached the end of your posts\n");
							do {
								System.out.println("1. Edit post \n" + "2. Delete post \n" + "3. Go back to profile \n");
								answer = Interface.input.nextInt();
								if (answer == 1) {
									editPostMethod(userId, listOfStrings.get(i - 3));					
								} else if (answer == 2) {
									deletePostMethod(userId, listOfStrings.get(i - 3));
								} else if (answer == 3) {
									Interface.profileMenu();
								} else {
									System.out.println("Wrong input. Please enter 1, 2, or 3\n");
								}
							} while (answer != 1 && answer != 2 && answer != 3);
							
						}
					} else if (answer == 4) {
						Interface.profileMenu();
					} else {
						System.out.println("Wrong input. Please enter 1, 2, 3 or 4 \n");
						myPostsMenu(userId);
					}
				} while (flag == true);
				
			} else {
				System.out.println("You dont have any posts");
				try {
					Interface.profileMenu();
				} catch (IOException e) {
				}
			}

		} catch (Exception e) {

		}
	}
}
