package com.lightning.datacenter.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.kvstore.api.ISortedKeyValueStoreEngine;
import com.lightning.common.kvstore.model.KVResult;
import com.lightning.common.kvstore.model.KeyValuePair;
import com.lightning.datacenter.model.HotWordTrendVO;
import com.lightning.datacenter.model.HotWordVO;
import com.lightning.datacenter.trend.proto.HotTrendData.BPHotWordList;
import com.lightning.datacenter.utils.TimestampGenerator;

public class HotWordManager {

	@Resource
	public ISortedKeyValueStoreEngine storeKeyValueEngine;

	public HotWordManager() {
	}

	public void writeWordList(String timestamp, List<String> words) {
		BPHotWordList.Builder builder = BPHotWordList.newBuilder();
		builder.setTimestamp(timestamp);
		for (String word : words) {
			builder.addWords(word);
		}
		// dc:hotweb:eventlist:%s
		String key = String.format("dc:hotword:wordlist:%s", timestamp);
		KVResult<Void> rc = storeKeyValueEngine.set(key, builder.build()
				.toByteArray());
		if (rc.isSuccess()) {
		} else {
			// TODO
		}
	}

	public List<String> readWordList(String timestamp) {
		List<String> words = new ArrayList<String>();
		String key = String.format("dc:hotword:wordlist:%s", timestamp);
		KVResult<byte[]> rc = storeKeyValueEngine.get(key);
		if ( rc.isSuccess() ) {
			byte[] datas = rc.getValue();
			try {
				BPHotWordList hwl = BPHotWordList.parseFrom(datas);
				for ( String word : hwl.getWordsList() ) {
					words.add(word);
				}
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		}
		return words;
	}
	
	public void batchWriteWordFreqs(String timestamp, List<HotWordVO> hotwords) {
		byte[] intBytes = new byte[4];
		for (HotWordVO vo : hotwords) {
			String key = String.format("dc:hotword:wordfreq:%s:%s", vo.getWord(), timestamp);
			int value = vo.getFreq();
			intBytes[0] = (byte) (0xff & (value >> 24));
			intBytes[1] = (byte) (0xff & (value >> 16));
			intBytes[2] = (byte) (0xff & (value >> 8));
			intBytes[3] = (byte) (0xff & value);
			KVResult<Void> rc = storeKeyValueEngine.set(key, intBytes);
			if ( rc.isSuccess() ) {
			} else {
				// TODO
			}
		}
	}
	
	public int convert2Int(byte[] values) {
		if ( values == null || values.length != 4 ) {
			return 0;
		}
		return ((values[0] << 24) & 0xFF000000)
				| ((values[1] << 16) & 0xFF0000) 
				| ((values[2] << 8) & 0xFF00)
				| (values[3] & 0xFF);
	}
	
	// -----------------------------------------------
	public List<HotWordTrendVO> batchReadWordFreqs(String word, String startDate, String endDate) {
		
		List<HotWordTrendVO> vos = new ArrayList<HotWordTrendVO>();
		
		String startKey = String.format("dc:hotword:wordfreq:%s:%s", word, startDate);
		String endKey = String.format("dc:hotword:wordfreq:%s:%s", word, endDate);
		
		KVResult<List<KeyValuePair>> rc = storeKeyValueEngine.getRange(startKey, endKey);
		if ( rc.isSuccess() ) {
			List<KeyValuePair> kyps = rc.getValue();
			TimestampGenerator generator = new TimestampGenerator(startDate);
			int index = 0;
			do {
				String curnow = generator.next();
				if ( curnow.compareTo(endDate) > 0 ) {
					break;
				}
				
				String curkey = String.format("dc:hotword:wordfreq:%s:%s", word, curnow);
				
				boolean flag = false;
				while ( index < kyps.size() ) {
					KeyValuePair kvp = kyps.get(index);
					if ( curkey.compareTo(kvp.getKey()) < 0 ) {
						break;
					} else if ( curkey.compareTo(kvp.getKey()) == 0 ) {
						int freq = convert2Int(kvp.getValue());
						flag = true;
						vos.add(new HotWordTrendVO(curnow, freq));
						index++;
						break;
					} else {
						index++;
					}
				}
				if ( flag == false ) {
					vos.add(new HotWordTrendVO(curnow, 0));
				}
			} while (true);
		} else {
			TimestampGenerator generator = new TimestampGenerator(startDate);
			do {
				String curnow = generator.next();
				if ( curnow.compareTo(endDate) > 0 ) {
					break;
				}
				vos.add(new HotWordTrendVO(curnow, 0));
			} while (true);
		}
		
		return vos;
		
	}

}
