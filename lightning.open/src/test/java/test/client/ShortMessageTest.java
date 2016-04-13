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
import com.qing.logiclayer.SmsFriend;
import com.qing.logiclayer.SmsSummary;

public class ShortMessageTest {
	
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
	
	
	@Test
	public void testQuerySmsSummaryByPhone() {
		List<SmsSummary> smses2;
		try {
			smses2 = client.querySmsSummaryByPhone("13812345678", 0, 10);
			for ( SmsSummary summary : smses2 ) {
				System.out.println(summary.getSendMobile() + " => "+ summary.getSendUsername());
				System.out.println("\t" + summary.getRecvMobile() + " => " + summary.getRecvUsername());
				System.out.println("\t\t" + summary.getTimestamp() + " => " + summary.getContent());
			}
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuerySmsFriendByPhone() {
		List<SmsFriend> friends;
		try {
			friends = client.querySmsFriendByPhone("13812345678");
			for ( SmsFriend friend : friends ) {
				System.out.println(friend.getPhoneno() + ":" + friend.getUsername() + " => " + friend.getContactNum());
			}
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}
