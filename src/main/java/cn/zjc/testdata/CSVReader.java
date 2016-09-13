// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) definits fieldsfirst ansi space safe 

package cn.zjc.testdata;

import java.lang.RuntimeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public static void main(String[] args) {
		System.out.println(DEFAULT_QUOTE_CHARACTER);
	}

	private BufferedReader C = null;
	private boolean isNotEnd = false;
	/**
	 * separator
	 */
	private char E = 0;
	/**
	 * quote
	 */
	private char D = 0;
	private int tokenCount = 0;
	public static final char DEFAULT_SEPARATOR = 44;
	public static final char DEFAULT_QUOTE_CHARACTER = 34;

	public CSVReader(BufferedReader bufferedreader) {
		this(bufferedreader, DEFAULT_SEPARATOR);
	}

	public CSVReader(BufferedReader bufferedreader, char separator) {
		this(bufferedreader, separator, DEFAULT_QUOTE_CHARACTER);
	}

	public CSVReader(BufferedReader bufferedreader, char separator, char quote) {
		isNotEnd = true;
		C = bufferedreader;
		E = separator;
		D = quote;
	}

	public List<String[]> readAll() throws IOException {
		ArrayList<String[]> arraylist = new ArrayList<String[]>();
		while (isNotEnd) {
			String as[] = readNext();
			if (as != null)
				arraylist.add(as);
		}
		return arraylist;
	}

	public String[] readNext() throws IOException {
		String s = readNotNullLine();
		return isNotEnd ? readLineBySeparator(s) : null;
	}

	private String readNotNullLine() throws IOException {
		String s = C.readLine();
		if (s != null)
			s = s.trim();
		while (s != null && (s.length() == 0 || s.startsWith("//"))) {
			s = C.readLine();
			if (s != null)
				s = s.trim();
		}
		if (s == null)
			isNotEnd = false;
		return isNotEnd ? s : null;
	}

	private String[] readLineBySeparator(String line) throws IOException {
		if (line == null)
			return null;
		ArrayList<String> arraylist = new ArrayList<String>();
		StringBuffer stringbuffer = new StringBuffer();
		boolean flag = false;
		boolean flag1 = false;
		do {
			if (flag) {
				stringbuffer.append("\n");
				line = readNotNullLine();
				if (line == null)
					break;
			}
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (c == D) {
					if (flag && line.length() > i + 1 && line.charAt(i + 1) == D) {
						stringbuffer.append(line.charAt(i + 1));
						i++;
					} else {
						flag = !flag;
						if (!flag)
							flag1 = true;
					}
				} else if (c == E && !flag) {
					String s2 = stringbuffer.toString().trim();
					arraylist.add(s2.length() != 0 ? s2 : null);
					stringbuffer = new StringBuffer();
					flag1 = false;
				} else {
					if (flag1 && c != ' ')
						throw new MagicException("CSV parse error: quoted string is not well finished \n   "+line);
					stringbuffer.append(c);
				}
			}
		} while (flag);
		String s1 = stringbuffer.toString().trim();
		arraylist.add(s1.length() != 0 ? s1 : null);
		String as[];
		if (tokenCount > 0) {
			as = new String[tokenCount];
			int j = arraylist.size();
			for (int k = 0; k < tokenCount; k++)
				if (j > k)
					as[k] = arraylist.get(k);
				else
					as[k] = null;

		} else {
			as = arraylist.toArray(new String[0]);
			tokenCount = as.length;
		}
		return as;
	}

	public void close() throws IOException {
		C.close();
	}

	public int getTokenCount() {
		return tokenCount;
	}

	public void setTokenCount(int i) {
		tokenCount = i;
	}
}
