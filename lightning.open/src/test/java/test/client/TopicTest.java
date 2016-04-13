package test.client;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;

import com.qing.logiclayer.Logiclayer;


public class TopicTest {

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
	
}
