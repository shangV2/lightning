package test.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.OpenCommonQueryDivideType;
import com.qing.logiclayer.OpenWebPageContent;
import com.qing.logiclayer.QueryOpenWebPageContentRequest;
import com.qing.logiclayer.QueryOpenWebPageContentResponse;
import com.qing.logiclayer.TranslateRequest;
import com.qing.logiclayer.TranslateResponse;

public class TranslateTest {

	private Logiclayer.Client client = null;
	private TTransport transport = null;
	
	@Before
	public void setUp() {
		transport = new TSocket("localhost", 8089);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}

	@After
	public void tearDown() {
		if ( transport != null ) {
			transport.close();
		}
	}


	@Test
	public void testTranslate() {
		try {
			TranslateRequest request = new TranslateRequest();
			request.setFrom("ch");
			request.setTo("uy");
			request.setText("我");
			
			TranslateResponse response = client.translate(request);
			System.out.println(response.getContent());
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testTranslate2() {
		try {
			TranslateRequest request = new TranslateRequest();
			request.setFrom("uy");
			request.setTo("ch");
			request.setText("مەن");
			
			TranslateResponse response = client.translate(request);
			System.out.println(response.getContent());
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
