package test.file;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * Dec 10, 2013 2:02:59 PM
 * @author 
 * TODO 导出Excel，主要使用POI和Java的反射、泛型，没有对Excel表格进行样式设置
 */
public class ExportExcelUtil<T> {
	
	/**
	 * 
	 * @param headers 表格列名
	 * @param dataset 数据集合
	 * @param out 流对象
	 * @return boolean 是否成功生成报表
	 */
	public boolean exportExcel(List<ExportHeader> headers,
			Collection<T> dataset, OutputStream out){
		return exportExcel("Sheet1", headers, dataset, out, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param title 表格标题
	 * @param headers 表格列名
	 * @param dataset 数据集合
	 * @param out 流对象
	 * @return boolean 是否成功生成报表
	 */
	public boolean exportExcel(String title, List<ExportHeader> headers,
			Collection<T> dataset, OutputStream out){
		return exportExcel(title, headers, dataset, out, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param headers 表格列名
	 * @param dataset 数据集合
	 * @param out 流对象
	 * @param datetimeFormat 时间格式
	 * @return boolean 是否成功生成报表
	 */
	public boolean exportExcel(List<ExportHeader> headers,
			Collection<T> dataset, OutputStream out, String datetimeFormat){
		return exportExcel("Sheet1", headers, dataset, out, datetimeFormat);
	}

	/**
	 * 
	 * @param title
	 *            表格标题
	 * @param headers
	 *            表格列名
	 * @param dataset
	 *            数据集合
	 * @param out
	 *            流对象
	 * @param datetimeFormat
	 *            时间格式
	 * @return boolean
	 *            是否成功生成报表
	 */
	public boolean exportExcel(String title, List<ExportHeader> headers,
			Collection<T> dataset, OutputStream out, String datetimeFormat) {
		if(headers.size() == 0)
			return false;
		// 创建一个Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在工作簿中创建一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 在表格中创建一行
		HSSFRow row = sheet.createRow(0);
		// 创建表头
		boolean b = false;
		List<String> propertyList = new ArrayList<String>();
		for (int i = 1; i <= headers.size(); i++) {
			HSSFCell cell = row.createCell(i - 1);
			HSSFRichTextString text = null;
			b = false;
			for (int j = 0; j < headers.size() && b == false; j++) {
				ExportHeader h = headers.get(j);
				if (h.getNo() == i) {
					text = new HSSFRichTextString(h.getHeader());
					propertyList.add(h.getProperty());
					b = true;
				}
			}

			cell.setCellValue(text);
		}

		// 遍历数据集合
		Iterator<T> it = dataset.iterator();
		int i = 0;
		String property = null;
		try {
			while (it.hasNext()) {
				i++;
				row = sheet.createRow(i);
				T t = (T) it.next();

				// 获取所有的属性
				Field[] fields = t.getClass().getDeclaredFields();

				// 遍历需要导出的属性
				for (int index = 0; index < propertyList.size(); index++) {
					HSSFCell cell = row.createCell(index);
					property = propertyList.get(index);

					Field field = t.getClass().getDeclaredField(property);
					// 根据属性的类型获取get方法，boolean类型为is
					String getMethodName = "get"
							+ property.substring(0, 1).toUpperCase()
							+ property.substring(1);
					if (field.getType() == boolean.class
							|| field.getType() == Boolean.class) {
						getMethodName = "is"
								+ property.substring(0, 1).toUpperCase()
								+ property.substring(1);
					}
					Class cls = t.getClass();
					Method getMethod = cls.getMethod(getMethodName,
							new Class[] {});
					// 利用反射获取属性值
					Object value = getMethod.invoke(t, new Object[] {});
					String txtValue = null;
					// 处理日期，进行格式化
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								datetimeFormat);
						txtValue = dateFormat.format(date);
					} else {
						if(value != null)
							txtValue = value.toString();
						else
							txtValue = "";
					}

					if (txtValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
						Matcher matcher = p.matcher(txtValue);
						if (matcher.matches()) {
							cell.setCellValue(Double.parseDouble(txtValue));
						} else {
							HSSFRichTextString textString = new HSSFRichTextString(
									txtValue);
							cell.setCellValue(textString);
						}
					}

				}
			}
		} catch (NoSuchFieldException e) {//捕捉异常
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 写入文件
			try {
				workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				// 不关闭输出流out，应在外部关闭，请注意
			}
		}
		return true;
	}
	
	public boolean exportExcel(String title, List<ExportHeader> headerList,
			List<Map> dataset, OutputStream out, String datetimeFormat){ //, String[] headers, String[] cols
//		if(headers.length == 0)
//			return false;
//		if(headers.length != cols.length)
//			return false;
		
		if(headerList == null || headerList.size() == 0)
			return false;
//		if(headers.length != cols.length)
//			return false;
		
		// 创建一个Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在工作簿中创建一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 在表格中创建一行
		HSSFRow row = sheet.createRow(0);
		// 创建表头
		boolean b = false;
		HSSFCell cell = null;
		HSSFRichTextString text = null;
		String value = null;
		for (int i = 1; i <= headerList.size(); i++) {
			cell = row.createCell(i - 1);
			text = new HSSFRichTextString(headerList.get(i-1).getHeader());
			cell.setCellValue(text);
		}
		for(int i = 0; i < dataset.size(); i++){
			row = sheet.createRow(i+1);
			Map map = dataset.get(i);
			for(int j = 0; j < headerList.size(); j++){
				cell = row.createCell(j);
//				Object obj = map.get(cols[i]);
				Object obj = map.get(headerList.get(j).getProperty());
//				if(obj instanceof Date){
//					value = 
//				}
//				System.out.println(i + "   " + j + "   " + (String) obj);
				text = new HSSFRichTextString(StringUtil.ToBeString(obj));//headerList.get(j).getProperty()
				cell.setCellValue(text);
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			// 不关闭输出流out，应在外部关闭，请注意
		}
		return true;
	}
	
}
