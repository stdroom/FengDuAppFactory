/**
 * 工程名: xiaomayasi
 * 文件名: TestJsonParse.java
 * 包名: xiaomayasi
 * 日期: 2015年12月4日上午10:30:52
 * QQ: 378640336
 *
*/


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fengdu.volley.VolleyManager;

import android.test.AndroidTestCase;
import junit.framework.TestCase;

/**
 * 类名: TestJsonParse <br/>
 * 功能: TODO 添加功能描述. <br/>
 * 日期: 2015年12月4日 上午10:30:52 <br/>
 *
 * @author   leixun
 * @version  	 
 */
public class TestJsonParse extends AndroidTestCase {
	boolean parseFlag = false;
	RequestQueue mQueue;
	@Override
	protected void setUp() throws Exception {
//		mQueue = Volley.newRequestQueue(getApplication());
		mQueue.start();
	}
	
	// 测试解析正常不正常
	public void testJsoParse(){
		try {   
            // URL   
            URL url = new URL("http://192.168.1.24:8080/xmys/wordManage/searchWord?word=lecture");   
//            VolleyManager.getInstance().beginSubmitRequest();
        } catch (Exception e) {   
        	parseFlag = false;
            e.printStackTrace();   
        }   
		assertEquals(true, parseFlag);
    }  
}

