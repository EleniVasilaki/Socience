import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Interaction {
	static String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    static File fileName = new File(cwd + "\\src\\main\\resources\\interactions.txt"); // The file that the interactions data is saved.
    static boolean fileCreated = false;

    public static void checkInteractions(String uid, String pid) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            boolean found = false;
            // Checks if any interaction by any user has ever happened in any post.
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(uid) && values[1].equals(pid)) {
                    found = true;
                    break;
                }
            }
            br.close();
            // If it hasn't, it sets the interactions to false.
            if (!found) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
                bw.write(uid + "," + pid + ",false," + "" + ",false,false");
                bw.newLine();
                bw.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
        }
    }

    public static void like(String uid, String pid) {
        checkFile();
        checkInteractions(uid, pid);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder fileContent = new StringBuilder();
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(uid) && values[1].equals(pid) && values[2].equals("false")) {
                    found = true;
                    line = uid + "," + pid + ",true," + values[3] + "," + values[4] + "," + values[5];
                    System.out.println("Post successfully liked!");
                }
                fileContent.append(line);
                fileContent.append(System.lineSeparator());
            }
            br.close();
            if (found) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(fileContent.toString());
                fw.close();
            } else {
                System.out.println("You have already liked this post.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
        }
    }

    // Counts how many times a post (pid) has been liked.
    public static int likes(String pid) {
        checkFile();
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(pid) && values[2].equals("true")) {
                    count++;
                }
            }
            br.close();
            return count;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
            return 0;
        }
    }

    public static void comment(String uid, String pid) {
        checkFile();
        checkInteractions(uid, pid);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder fileContent = new StringBuilder();
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(uid) && values[1].equals(pid) && (values[3].equals("") || !values[3].equals(""))) {
                    found = true;

                    System.out.println("Post a comment:");
                    Scanner in = new Scanner(System.in);
                    String comment = in.nextLine();

                    line = uid + "," + pid + "," + values[2] + "," + comment + "," + values[4] + "," + values[5];
                }
                fileContent.append(line);
                fileContent.append(System.lineSeparator());
            }
            br.close();
            if (found) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(fileContent.toString());
                fw.close();
            } else {
                System.out.println("New comment added!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
        }
    }

    // Counts how many comments a post (pid) has got.
    public static int comments(String pid) {
        checkFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(pid) && !values[3].equals("")) {
                    count++;
                }
            }
            br.close();
            return count;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
            return 0;
        }
    }

    public static void seeComments(String pid) {
        checkFile();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean flag = false;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(pid)) {
                	if(!values[3].equals("")) {
                		System.out.println(values[3]);
                		flag = true;
                	}
                    
                } 
                    
            }
            if(flag == false) {
            	System.out.println("There are no comments");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void report(String uid, String pid) {
        checkFile();
        checkInteractions(uid, pid);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder fileContent = new StringBuilder();
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(uid) && values[1].equals(pid) && values[5].equals("false")) {
                    found = true;
                    line = uid + "," + pid + "," + values[2] + "," + values[3] + "," + values[4] + ",true";
                    System.out.println("Post #" + pid + " successfully reported!");
                }
                fileContent.append(line);
                fileContent.append(System.lineSeparator());
            }
            br.close();
            if (found) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(fileContent.toString());
                fw.close();
            } else {
                System.out.println("Post #" + pid + " already reported!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
        }
    }

    public static int reports(String pid) {
        checkFile();
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(pid) && values[5].equals("true")) {
                    count++;
                }
            }
            br.close();
            return count;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println("An error may have occurred: " + e.getMessage());
            return 0;
        }
    }

    public static void checkFile() {
        if (!fileCreated) {
            fileCreated = true;
            try {
                if (fileName.createNewFile()) {
                    System.out.println("File created");
                }
            } catch (IOException e) {
                System.out.println("Error on creating file");
            }
        }
    }

    public static void deleteByReports(String uid, String pid) {
        if (reports(pid) >= 10) {
            Post.deletePostMethod(pid, uid);
        }
    }
}
