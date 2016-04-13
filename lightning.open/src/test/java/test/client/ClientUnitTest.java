package test.client;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.SmsSummary;


public class ClientUnitTest {

	private static Logiclayer.Client client = null;
	
	@BeforeClass
	public static void setUp() {
		TTransport transport = new TSocket("localhost", 9090);
		try {
			transport.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Logiclayer.Client(protocol);
	}
	
	@AfterClass
	public static void tearDown() {
		
	}
	
	
	
	
	
	@Test
	public void test1001() {
		System.out.println("test....");
	}
	
	@Test
	public void testQuerySearchSmsSummary1001() {
		List<SmsSummary> smses;
		try {
			smses = client.querySearchSmsSummary("安全", 0, 10);
			for ( SmsSummary sms : smses ) {
				System.out.println(sms.getSendMobile() + " -> "
						+ sms.getRecvMobile() + " at " + sms.getTimestamp()
						+ " ----> " + sms.getContent());
				
				System.out.println(sms.getSendUsername() + ":" + sms.getRecvUsername());
				System.out.println(sms.getTimestamp());
			}
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
