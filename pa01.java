/*=============================================================================
 * |   Assignment:  pa01 - Encrypting a plaintext file using the Vigenere cipher
 * |
 * |       Author:  Guraashish Kainth
 * |     Language:  Java
 * |
 * |   To Compile:  javac pa01.java
 * |                
 * |   To Execute:  java -> java pa01 kX.txt pX.txt 
 * |                         where kX.txt is the keytext file
 * |                         and pX.txt is plaintext file
 * |
 * |         Note:  All input files are simple 8 bit ASCII input
 * |
 * |        Class:  CIS3360 - Security in Computing - Fall 2021
 * |   Instructor:  McAlpin
 * |     Due Date:  Oct 26
 * |
 * +=============================================================================*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pa01 {
	
	//Global constants for the max length and
	// the shift to go from upper to lower case
	final static int MAX_CHAR_PLAINTEXT = 512;
	final static int LOWERCASE_SHIFT = 32;
	
	public static void main(String args[]) throws FileNotFoundException{  
		
		//Declaring all of the char arrays
		char plainText[] = new char[MAX_CHAR_PLAINTEXT];
		char key[] = new char[MAX_CHAR_PLAINTEXT];
		char cipherText[] = new char[MAX_CHAR_PLAINTEXT];
		
		
	    File input = new File(args[1]);
	    File keyText = new File(args[0]);
	    	    
	    //Setting up the scanner
		Scanner fileInput = new Scanner(input);		
		Scanner fileKey = new Scanner(keyText);		
	    fileInput.useDelimiter("");	
	    fileKey.useDelimiter("");
	    
	    //Calling the function to copy the text to the plaintext array
	    plainText = copyLowerCasePlain(fileInput, plainText);	   
	    
	  //Calling the function to copy the text to the key array
	    key = copyLowerCaseKey(fileKey, key);
	    
	    //Calling the function to encrypt the text
	    cipherText = encrytPlainText(plainText, key, cipherText);
	    
	    //Printing all of the arrays
	    printAll(plainText, key, cipherText);
	}

	private static void printAll(char[] plainText, char[] key, char[] cipherText) {
		int keyCounter = 0;
		System.out.printf("Key:\n\n");
		
		//Making sure the while loop stops at the last defined element in the char array
		while(key[keyCounter] != '\0' && keyCounter != MAX_CHAR_PLAINTEXT) {
			System.out.printf("%c", key[keyCounter]);
			keyCounter++;
		}
		
		//For loop to print all of the plaintext
		System.out.printf("\n\nPlain Text:\n\n");
		for(int i = 0; i < MAX_CHAR_PLAINTEXT; i++) {
			 System.out.printf("%c", plainText[i]);
		}
		
		//For loop to print all of the ciphertext
		System.out.printf("\n\nCipher Text:\n\n");
		for(int i = 0; i < MAX_CHAR_PLAINTEXT; i++) {
			 System.out.printf("%c", cipherText[i]);
		}
		
	}

	private static char[] encrytPlainText(char[] plainText, char[] key, char[] cipherText) {
		int keyCounter = 0;		
		int temp = 0;
		
		for(int i = 0; i < MAX_CHAR_PLAINTEXT; i++) {
			//Use keyCounter to make sure we loop back around when we reach the end of the key
			if(key[keyCounter] == '\0') {
				keyCounter = 0;
			}
			
			//Doing all of the shifting and mod in a separate variable then placing in the char array
			temp = ((((int) plainText[i] - 97) +  ((int) key[keyCounter] - 97)) % 26);
			temp = temp + 97;
			cipherText[i] = (char) temp;
			keyCounter++;
		}
		return cipherText;
	}

	private static char[] copyLowerCaseKey(Scanner fileKey, char[] key) {
		
		//Array counter
		int totalInputChars = 0;
	    String temp;
	    while(fileKey.hasNext()) {
	    	//Grabbing the next char
	    	temp = fileKey.next();
	    	int test = temp.charAt(0);
	    	
	    	//Checking where it is in the bounds for what we want then doing the approriate logic
	    	if((test > 96) && (test < 123)) {
	    		key[totalInputChars] = temp.charAt(0);
	    		totalInputChars++;
	    	}
	    	else if((test > 64) && (test < 91)) {
	    		test += LOWERCASE_SHIFT;	    			    		
	    		key[totalInputChars] = (char) test;
	    		totalInputChars++;
	    	}		    	
	    }
		return key;
	}

	private static char[] copyLowerCasePlain(Scanner fileInput, char[] plainText) {
		//Array counter
		int totalInputChars = 0;
	    String temp;
	    while(fileInput.hasNext()) {
	    	//Grabbing the next char
	    	temp = fileInput.next();
	    	int test = temp.charAt(0);
	    	
	    	//Checking where it is in the bounds for what we want then doing the approriate logic
	    	if((test > 96) && (test < 123)) {
	    		plainText[totalInputChars] = temp.charAt(0);
	    		totalInputChars++;
	    	}
	    	else if((test > 64) && (test < 91)) {
	    		test += LOWERCASE_SHIFT;	    			    		
	    		plainText[totalInputChars] = (char) test;
	    		totalInputChars++;
	    	}	    		    	
	    }
	    
	    //Finally using the char counter to make sure that the remaining empty elements are padded with x
	    for(int i = totalInputChars; i < MAX_CHAR_PLAINTEXT; i++) {
    		plainText[i] = 'x';
    	}
	    
		return plainText;
	}
}	

/*=============================================================================
 * |     I Guraashish Kainth (gu277976) affirm that this program is
 * | entirely my own work and that I have neither developed my code together with
 * | any another person, nor copied any code from any other person, nor permitted
 * | my code to be copied  or otherwise used by any other person, nor have I
 * | copied, modified, or otherwise used programs created by others. I acknowledge
 * | that any violation of the above terms will be treated as academic dishonesty.
 * +=============================================================================*/
