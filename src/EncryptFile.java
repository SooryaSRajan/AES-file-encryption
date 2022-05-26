import java.io.*;
import java.util.Base64;
import java.util.Scanner;

public class EncryptFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a file name in the current folder to encrypt: (No spaces) ");

        String fileName = scanner.next();

        File myObj = new File("src/" + fileName);
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("File size in bytes " + myObj.length());

            FileInputStream fl = new FileInputStream(myObj);
            byte[] arr = new byte[(int)myObj.length()];

            int a = fl.read(arr);
            fl.close();

            String base64File = Base64.getEncoder().encodeToString(arr);
            System.out.println("Encoded value: " + base64File);

            System.out.println("Enter a secret key to encrypt the file: ");
            final String secretKey = scanner.next();
            String encryptedString = EncryptDecrypt.encrypt(base64File, secretKey);

            String[] fileInfo = myObj.getName().split("\\.");

            try {
                File file = new File(fileInfo[0] + ".enc");
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(encryptedString);
                myWriter.write("\n");
                myWriter.write(myObj.getName());
                System.out.println(file.setReadable(true));
                System.out.println(file.setWritable(true));
                System.out.println(file.setExecutable(true));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


        } else {
            System.out.println("The file does not exist.");
        }
    }
}
