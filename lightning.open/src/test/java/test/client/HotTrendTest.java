package test.client;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qing.logiclayer.FightingServiceException;
import com.qing.logiclayer.Logiclayer;
import com.qing.logiclayer.QueryHotWordListResponse;
import com.qing.logiclayer.QueryHotWordTrendRequest;
import com.qing.logiclayer.QueryHotWordTrendResponse;
import com.qing.logiclayer.QueryHotWordlistRequest;
import com.qing.logiclayer.WordTimeTrend;

public class HotTrendTest {

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
	public void testQueryHotwordList() {
		
		QueryHotWordlistRequest request = new QueryHotWordlistRequest();
		request.setTimestamp("2014-03-30");
		try {
			QueryHotWordListResponse response = client.queryHotWordList(request);
			List<String> words = response.getWords();
			for ( String word : words ) {
				System.out.println(word);
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryHotwordTrends() {
		
		QueryHotWordTrendRequest request = new QueryHotWordTrendRequest();
		request.setWord("新疆");
		request.setStartdate("2014-01-20");
		request.setEnddate("2014-03-30");
		try {
			QueryHotWordTrendResponse response = client.queryHotWordTrend(request);
			List<WordTimeTrend> trends = response.getTrends();
			for ( WordTimeTrend trend : trends ) {
				System.out.println(trend.getDate() + ":" + trend.getTotal());
			}
		} catch (FightingServiceException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
