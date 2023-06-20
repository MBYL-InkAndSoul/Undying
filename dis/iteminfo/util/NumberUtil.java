package inkandsoul.iteminfo.util;

import java.util.Objects;

public final class NumberUtil {
	/*
	 * I = 1 V = 5 X = 10 L = 50 C = 100 D = 500 M = 1000
	 */

	public static int evalRoman(String num) {
		int i = 0;
		int h = 0;
		int t = 0;
		int u = 0;
		int n;

		num = num.toUpperCase();
		while (num.charAt(i) == 'M') {
			i++;
		} 
		n = i * 1000;

		if ("CM".equals(num.substring(i, 2))) {
			h = 9;
			i += 2;
		}
		else if ("D".equals(num.substring(i, 1))) {
			h = 5;
			i++;
		}
		else if ("CD".equals(num.substring(i, 2))) {
			h = 4;
			i += 2;
		} 
		if (h == 0 || h == 5) {
			while (num.charAt(i) == 'C') {
				h++;
				i++;
			}
		} 
		n += h * 100;

		if ("XC".equals(num.substring(i, 2))) {
			t = 9;
			i += 2;
		}
		else if ("L".equals(num.substring(i, 1))) {
			t = 5;
			i++;
		}
		else if ("XL".equals(num.substring(i, 2))) {
			t = 4;
			i += 2;
		} 
		if (t == 0 || t == 5) {
			while (num.charAt(i) == 'X') {
				t++;
				i++;
			}
		} 
		n += t * 10;

		if ("IX".equals(num.substring(i, 2))) {
			u = 9;
			i += 2;
		}
		else if ("V".equals(num.substring(i, 1))) {
			u = 5;
			i++;
		}
		else if ("IV".equals(num.substring(i, 2))) {
			u = 4;
			i += 2;
		} 
		if (u == 0 || u == 5) {
			while (num.charAt(i) == 'I') {
				u++;
				i++;
			}
		} 
		n += u;

		if (!((Objects.equals(num, convert1000s(n) + convert100s(n) + convert10s(n) + convert1s(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100s(n) + convert10s(n) + convert1a(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100s(n) + convert10a(n) + convert1s(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100s(n) + convert10a(n) + convert1a(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100a(n) + convert10s(n) + convert1s(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100a(n) + convert10s(n) + convert1a(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100a(n) + convert10a(n) + convert1s(n)))
				|| (Objects.equals(num, convert1000s(n) + convert100a(n) + convert10a(n) + convert1a(n))))) {
			n = -1;
		}

		return (n);
	}

	public static String converts(int n) {
		return (convert1000s(n) + convert100s(n) + convert10s(n) + convert1s(n));
	}

	public static String convert1000s(int n) {
		return ("MMMM".substring(0,  (n / 1000)));
	}

	public static String convert100s(int h) {
		var m = "";
		h = ((h % 1000) / 100);
		if (h == 9) {
			m = "CM";
		}
		else if (h > 4) {
			m = "DCCC".substring(0, h - 4);
		}
		else if (h == 4) {
			m = "CD";
		}
		else {
			m = "CCC".substring(0, h);
		} 
		return m;
	}

	public static String convert100a(int h) {
		var m = "";
		h =  ((h % 1000) / 100);
		if (h > 4) {
			m = "DCCCC".substring(0, h - 4);
		}
		else {
			m = "CCCC".substring(0, h);
		} 
		return m;
	}

	public static String convert10s(int t) {
		var m = "";
		t =  ((t % 100) / 10);
		if (t == 9) {
			m = "XC";
		}
		else if (t > 4) {
			m = "LXXX".substring(0, t - 4);
		}
		else if (t == 4) {
			m = "XL";
		}
		else {
			m = "XXX".substring(0, t);
		} 
		return m;
	}

	public static String convert10a(int t) {
		var m = "";
		t =  ((t % 100) / 10);
		if (t > 4) {
			m = "LXXXX".substring(0, t - 4);
		}
		else {
			m = "XXXX".substring(0, t);
		}
		return m;
	}

	public static String convert1s(int u) {
		var m = "";
		u = u % 10;
		if (u == 9) {
			m = "IX";
		}
		else if (u > 4) {
			m = "VIII".substring(0, u - 4);
		}
		else if (u == 4) {
			m = "IV";
		}
		else {
			m = "III".substring(0, u);
		}
		return m;
	}

	public static String convert1a(int u) {
		var m = "";
		u = u % 10;
		if (u > 4) {
			m = "VIIII".substring(0, u - 4);
		}
		else {
			m = "IIII".substring(0, u);
		}
		return m;
	}
}
