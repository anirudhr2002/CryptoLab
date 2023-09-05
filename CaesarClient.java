package Ciphers.CaesarCipher;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CaesarClient {
    private static String plainText;
    private static int shiftValue;
    public static void main(String[] args){
        try{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter plain text: ");
            plainText = scanner.nextLine();

            System.out.print("Enter the shift value: ");
            shiftValue = scanner.nextInt();

            scanner.close();

            String cipherText = encrypt(plainText, shiftValue);
            System.out.println("Encrypted Text: " + cipherText);

            Socket socket = new Socket("localhost", 7116);
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(cipherText);
            dout.flush();

            dout.writeInt(shiftValue);

            dout.close();
            socket.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (char ch : plaintext.toCharArray()) {
            char shiftedChar = (char) (((ch - 'a' + shift) % 26) + 'a');
            ciphertext.append(shiftedChar);
        }
        return ciphertext.toString();
    }

}
