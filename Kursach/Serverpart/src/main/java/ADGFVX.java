package cryptor;

import java.util.Random;
public class ADGFVX {
    public static String Cryptor(String message){  // шифровальная таблица
        message = message.toUpperCase();
        char[] messageArray = message.toCharArray();

        char[] alphabet = getAlphabet();


        char[][] field = new char[7][7];
        field[0][0] = '-';

        char[] colrows = {'A','D','G','F','V','X'};
        int a = 0;
        for(int i = 1; i < 7; i++) {
            field[i][0] = colrows[a];
            field[0][i] = colrows[a];
            a++;
        }
        a = 0;

        for (int i = 1; i < 7; i++){
            for (int j = 1; j < 7; j++ ){
                field[i][j] = alphabet[a];
                a++;
            }
        }

        System.out.println("cipher table:");
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        StringBuilder cypher = new StringBuilder();

        for (int b = 0; b < messageArray.length; b++){
            cypher.append(StringRunner(field,messageArray, b));
        }

        message = cypher.toString();
        System.out.println("message is encrypted");
        System.out.println(message);
        System.out.println("-----------------------");

        return message;
    }

    private static String StringRunner(char[][] table, char[] message, int b){ // шифровка
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < 7; i++){
            for (int j = 1; j < 7; j++){
                if(message[b] == table[i][j]) {
                    result.append(table[i][0]);
                    result.append(table[0][j]);
                }
            }
        }

        String letter = result.toString();

        return letter;
    }

    private static char[] getAlphabet() {  // перемешивание
        char[] Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        for (int i = 0; i < Alphabet.length; i++){
            Random a = new Random();
            int j = a.nextInt(Alphabet.length - 1);

            char temp = Alphabet[j];
            Alphabet[j] = Alphabet[i];
            Alphabet[i] = temp;
        }
        return Alphabet;
    }

}
