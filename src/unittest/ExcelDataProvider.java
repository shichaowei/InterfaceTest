package unittest;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;

import jxl.*;
/**   
* ���������� excel�ļ���ȡ
* �����ˣ��뽨��  
* ����ʱ�䣺2016��2��3�� ����3:20:09  
* �޸��ˣ��뽨��  
* �޸�ʱ�䣺2016��2��3�� ����3:20:09  
* �޸ı�ע��  ����
*  
* Excel����Data�ļ�����</p>
* Excel������ʽ����������.xls</p>
* Excel��sheet������ʽ�����Է�����</p>
* Excel��һ��ΪMap��ֵ</p>
* ����ο�֣��־��Blog
* {@link www.zhenghongzhi.cn/post/42.html}
* 
* 
*
*/
public class ExcelDataProvider implements Iterator<Object[]> {

    private Workbook book         = null;
    private Sheet    sheet        = null;
    private int      rowNum       = 0;
    private int      currentRowNo = 0;
    private int      columnNum    = 0;
    private String[] columnnName;

    public ExcelDataProvider(String classname, String methodname) {
    	//System.out.println(methodname);

        try {
        	
            String path = "data/" + classname + ".xls";
            InputStream inputStream = new FileInputStream(path);
            book = Workbook.getWorkbook(inputStream);
            //sheet = book.getSheet(methodname) ���ʹ��������Ǳ�ʾÿ��sheet��һ��test����ʹ�ã��˴�������һ��test������Ӧ��һ��
            //excel��methodname��ʵ���Բ�����
            //sheet = book.getSheet(methodname);
            sheet = book.getSheet(0);
            rowNum = sheet.getRows();
            Cell[] cell = sheet.getRow(0);
            columnNum = cell.length;
            columnnName = new String[cell.length];

            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString();
            }
            this.currentRowNo++;

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("unable to read Excel data");
        }
    }

    public boolean hasNext() {

        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {

            try {
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet��һ������Ϊ���ж�����
            if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
                return false;
            return true;
        }
    }

	public Object[] next() {

        Cell[] c = sheet.getRow(this.currentRowNo);
        Map<String, String> data = new HashMap<String, String>();
        // List<String> list = new ArrayList<String>();

        for (int i = 0; i < this.columnNum; i++) {

            String temp = "";

            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }

            // if(temp != null&& !temp.equals(""))
            // list.add(temp);
            data.put(this.columnnName[i], temp);
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return object;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}