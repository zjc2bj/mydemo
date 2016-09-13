package cn.zjc.testdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyLoader {
	public static List<Object> parseDataFile(String filePath) throws IOException {
		InputStream inputstream = GroovyLoader.class.getClassLoader().getResourceAsStream(filePath);
		if (inputstream == null)
			throw new MagicException("没有发现文件" + filePath);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputstream, "utf-8"));
			InlineDataHeader dataheader = readHeader(reader);
			return loadTextAndRunScript(reader, dataheader.getScript(), dataheader.getTokenCount());
		} finally {
			if (inputstream != null)
				try {
					inputstream.close();
				} catch (Exception exception1) {
				}
			if (reader != null)
				try {
					reader.close();
				} catch (Exception exception2) {
				}
		}
	}

	public static List<Object> loadTextAndRunScript(BufferedReader bufferedreader, String strScript, int i)
			throws IOException {
		CSVReader csvreader = new CSVReader(bufferedreader);
		if (i > 0)
			csvreader.setTokenCount(i);
		List<String[]> list = csvreader.readAll();
		int currentCount = 0;

		List<Object> resList = new ArrayList<Object>();
		for (; currentCount < list.size(); currentCount++) {
			String lines[] = list.get(currentCount);
			try {
				Object result = runScript(strScript, lines);
				resList.add(result);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return resList;
	}

	private static InlineDataHeader readHeader(BufferedReader bufferedreader) {
		InlineDataHeader inlinedataheader = new InlineDataHeader();
		try {
			String s = "<script";
			String line = bufferedreader.readLine();
			if (line == null || !line.startsWith(s))
				throw new MagicException("错误的文件头,文件头必须是" + s);
			String tokenCount = readAttr(line, "columns");
			if (tokenCount != null)
				inlinedataheader.setTokenCount(Integer.parseInt(tokenCount));
			line = bufferedreader.readLine();
			StringBuffer stringbuffer = new StringBuffer();
			String header = "</script>";
			while (line != null && !line.startsWith(header)) {
				stringbuffer.append(line);
				stringbuffer.append("\n\r");
				line = bufferedreader.readLine();
			}

			if (!line.startsWith(header))
				throw new MagicException("错误的文件头，没有结束字符串:" + header);
			inlinedataheader.setScript(stringbuffer.toString());
		} catch (Exception exception) {
			throw new MagicException("解析文件头失败",exception);
		}
		return inlinedataheader;
	}

	private static String readAttr(String line, String s1) {
		String s2 = " " + s1 + "=\"";
		int index = line.indexOf(s2);
		if (index > 0) {
			int j = line.indexOf("\"", index + s2.length());
			return line.substring(index + s2.length(), j);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		Object importDataFile = parseDataFile("cn/zjc/testdata/Cat.txt");
		System.out.println(importDataFile);
	}

	private static void test1() {
		String line = "tom,2,2015-02-05,120,true,2.5";
		String script = "import cn.zjc.bean.Cat;Cat e = new Cat();e.name=r[0];e.age=r[1];e.birthday=cn.zjc.util.DateUtils.str2Date(r[2],\"yyyy-MM-dd\");e.price=Long.parseLong(r[3]);e.isalive=r[4];e.weight=Float.valueOf(r[5]);return e;";
		String[] split = line.split(",");

		Object value = runScript(script, split);
		System.out.println(value);
	}

	private static Object runScript(String script, String[] split) {
		Binding binding = new Binding();
		binding.setVariable("r", split);
		GroovyShell shell = new GroovyShell(binding);
		return shell.evaluate(script);
	}
}
