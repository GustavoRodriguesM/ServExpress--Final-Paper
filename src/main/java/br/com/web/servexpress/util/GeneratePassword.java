package br.com.web.servexpress.util;



import org.springframework.stereotype.Component;

@Component
public class GeneratePassword {

	public static String getAlphanumericPassword(int digits) {

		String codigo = "";
		while (codigo.length() < digits) {
			int num = (int) (Math.random() * 122);
			if (num >= 48 && num <= 57)
				codigo += (char) num;
			else if (num >= 97 && num <= 122)
				codigo += (char) num;
			else if (num < 97)
				num = (int) (Math.random() * 122);
		}
		return codigo;
	}
	
	public static String getUpperCaseCode(int digits) {

		String codigo = "";
		while (codigo.length() < digits) {
			int num = (int) (Math.random() * 122);
			if (num >= 48 && num <= 57)
				codigo += (char) num;
			else if (num >= 65 && num <= 90)
				codigo += (char) num;
			else if (num < 90)
				num = (int) (Math.random() * 90);
		}
		return codigo;
	}

}

