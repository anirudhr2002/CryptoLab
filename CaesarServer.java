package Ciphers.CaesarCipher;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CaesarServer {
    public static void main(String[] args){
        try{

            ServerSocket serverSocket = new ServerSocket(7116);
            Socket socket = serverSocket.accept();
            DataInputStream din = new DataInputStream(socket.getInputStream());

            String cipherText = din.readUTF();

            int shiftValue = din.readInt();

            System.out.println("Encrypted Text: " + cipherText);
            String plainText = decrypt(cipherText, shiftValue);
            System.out.println("Decrypted Text: " + plainText);

            din.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static String decrypt(String ciphertext, int shift) {
        StringBuilder plaintext = new StringBuilder();
        for (char ch : ciphertext.toCharArray()) {
            int shiftedChar = (ch - 'a' - shift + 26) % 26;
            plaintext.append((char) (shiftedChar + 'a'));
        }
        return plaintext.toString();
    }
}
