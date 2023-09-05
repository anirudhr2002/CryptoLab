package Ciphers.AffineCipher;

import Ciphers.Utils;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AffineServer {
    private static int a, b;
    private static String cipherText;
    private static String plainText;
    public static void main(String[] args){
        try{

            ServerSocket serverSocket = new ServerSocket(7118);
            Socket socket = serverSocket.accept();
            DataInputStream din = new DataInputStream(socket.getInputStream());

            cipherText = din.readUTF();

            a = din.readInt();
            b = din.readInt();

            System.out.println("Encrypted Text: " + cipherText);
            plainText = decrypt(cipherText, a, b);
            System.out.println("Decrypted Text: " + plainText);

            din.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();
        int aInverse = Utils.modInverse(a, 26);
        if (aInverse == -1) {
            return "a value is not invertible in modulo 26";
        }

        for (char ch : ciphertext.toCharArray()) {
                int decryptedChar = (aInverse * (ch - 'a' - b + 26)) % 26;
                if (decryptedChar < 0) {
                    decryptedChar += 26;
                }
                plaintext.append((char) (decryptedChar + 'a'));
        }
        return plaintext.toString();
    }
}
