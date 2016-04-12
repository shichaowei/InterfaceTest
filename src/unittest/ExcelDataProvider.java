package unittest;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;

import jxl.*;
/**   
* 功能描述： excel文件读取
* 创建人：冯建峰  
* 创建时间：2016年2月3日 下午3:20:09  
* 修改人：冯建峰  
* 修改时间：2016年2月3日 下午3:20:09  
* 修改备注：  新增
*  
* Excel放在Data文件夹下</p>
* Excel命名方式：测试类名.xls</p>
* Excel的sheet命名方式：测试方法名</p>
* Excel第一行为Map键值</p>
* 代码参考郑鸿志的Blog
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
            //sheet = book.getSheet(methodname) 如果使用这个，是表示每个sheet供一个test方法使用，此处我们是一个test方法对应了一张
            //excel表，methodname其实可以不用做
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
            // sheet下一行内容为空判定结束
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