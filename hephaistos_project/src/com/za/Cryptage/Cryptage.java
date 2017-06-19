package com.za.Cryptage;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class Cryptage{
	
	public static String encrypt(String password,String key){
		try
		{
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE,clef);
			return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e){
			return null;
		}
	}
	
	public static String decrypt(String password,String key){
		try{
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE,clef);
			return new String(cipher.doFinal(password.getBytes()));
		}
		catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public static void main(String[] args){
		String key = "hephaistos";
		String password = "azertyuiopqsdfghjklmwxcvbn";
		String chainCrypt = encrypt(password,key);
		String chainDecrypt = decrypt(chainCrypt,key);
		System.out.println(password);
		System.out.println(chainCrypt);
		System.out.println(chainDecrypt);
		
	}
}