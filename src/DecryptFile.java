import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class DecryptFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter name of file to decrypt: ");


        String fileName = scanner.next();
        File myObj = new File(fileName + ".enc");
        System.out.println(myObj.getAbsolutePath());
        if (myObj.exists()) {

            BufferedReader br
                    = new BufferedReader(new FileReader(myObj));

            // Declaring a string variable
            String st;
            // Condition holds true till
            // there is character in a string
            int i = 0;
            String decryptedString = null;
            while ((st = br.readLine()) != null){
                if(i == 0){
                    System.out.println("Enter key to decrypt: ");
                    String key = scanner.next();
                    decryptedString = EncryptDecrypt.decrypt(st, key);
                }
                if(i == 1){
                    byte[] resultByte = Base64.getDecoder().decode(decryptedString);
                    System.out.println("Decrypted file byte: " + Arrays.toString(resultByte));
                    try (FileOutputStream stream = new FileOutputStream(st)) {
                        stream.write(resultByte);
                    }
                }
                i++;
            }


        } else {
            System.out.println("The file does not exist.");
        }
    }
}
