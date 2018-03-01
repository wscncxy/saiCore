package com.sai.core.utils;

import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class SheetHandler extends DefaultHandler {

	private StringBuffer sb = new StringBuffer();
	private boolean nextIsString;
	private SharedStringsTable sst;
	private String value = null;
	private Map<String, HashMap<String, Object>> dataMap;
	private int rowNum;
	private String cellIndex;

	public SheetHandler(SharedStringsTable sst) {
		this.sst = sst;
		dataMap = new HashMap<String, HashMap<String, Object>>();
		rowNum = 0;
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// c => 单元格
		if (name.equals("c")) {
			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true

			String cellType = attributes.getValue("t");
			cellIndex = attributes.getValue("r").replaceAll("[^A-Z]", "");
			if (StringUtil.equals(cellType, "s")) {
				nextIsString = true;
			}
			// todo Debug用
			// int len = attributes.getLength();
			// if (cellIndex.equals("B")) {
			// for (int i = 0; i < len; i++) {
			// System.out.print(attributes.getQName(i) + ":" +
			// attributes.getValue(i) + "------------");
			// }
			// System.out.println();
			// }

			sb.delete(0, sb.length());
		}
		// 置空
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// 得到单元格内容的值
		sb.append(new String(ch, start, length));
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
		String lastContents = "";
		// System.out.println("end:name+" + name);
		if (sb.length() > 0) {
			lastContents = sb.toString();
			if (nextIsString) {
				try {
					int idx = Integer.parseInt(lastContents);
					lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
					nextIsString = false;
				} catch (Exception e) {
				}
			}
		}

		// v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
		// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
		if (name.equals("v")) {
			value = lastContents.trim();
			String rowKey = "row" + rowNum;
			HashMap<String, Object> colsMap = null;
			if (dataMap.containsKey(rowKey)) {
				colsMap = dataMap.get(rowKey);
			} else {
				colsMap = new HashMap<String, Object>();
			}
			colsMap.put(cellIndex, value);
			dataMap.put(rowKey, colsMap);
		} else if (name.equals("row")) {
			++rowNum;
		}
	}

	public Map<String, HashMap<String, Object>> getDataMap() {
		return dataMap;
	}

}
