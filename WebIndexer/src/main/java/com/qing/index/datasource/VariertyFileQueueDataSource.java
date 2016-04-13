package com.qing.index.datasource;

import java.io.File;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lightning.common.filequeue.impl.FMQDecoder;
import com.lightning.common.filequeue.impl.VariertyFileQueue;
import com.lightning.web.proto.WebPageProto.WebPageMessage;
import com.qing.index.handler.IWebPageHandler;
import com.qing.index.model.WebPage;

public class VariertyFileQueueDataSource implements IIndexDataSource {

	private String rootDir;
	
	public VariertyFileQueueDataSource(String rootDir) {
		this.rootDir = rootDir;
	}
	
	public void loadData(IWebPageHandler handler) {
		loadData(rootDir, handler);
	}

	public void loadData(String dirname, IWebPageHandler handler) {
		File dirFile = new File(dirname);
		if ( dirFile.isDirectory() && !dirFile.isHidden() ) {
			String[] childFiles = dirFile.list();
			if ( childFiles != null ) {
				for ( String filename : childFiles ) {
					if ( filename == null || filename.equals(".") || filename.equals("..") ) {
						continue;
					}
					File file = new File(dirname, filename);
					if ( file.isDirectory() && !file.isHidden() ) {
						loadData(file.getAbsolutePath(), handler);
					} else if ( file.isFile() && !file.isHidden() && file.getName().endsWith(".fmq") )  {
						VariertyFileQueue vfq = new VariertyFileQueue(file.getAbsolutePath());
						VariertyFileQueue.Source<WebPageMessage> source = vfq.source(new FMQDecoder<WebPageMessage>() {
							@Override
							public WebPageMessage parse(byte[] buf, int off, int len) {
								try {
									return WebPageMessage.parseFrom(ByteString.copyFrom(buf, off, len));
								} catch (InvalidProtocolBufferException e) {
									e.printStackTrace();
								}
								return null;
							}
						});
						
						while ( source.hasNext() ) {
							WebPageMessage message = source.next();
							
							if ( message == null ) {
								break;
							}
							
							WebPage webpage = new WebPage();
							webpage.setUrl(message.getUrl());
							webpage.setSource(message.getSource().toStringUtf8());
							webpage.setTitle(message.getTitle().toStringUtf8());
							webpage.setTimestamp(message.getTimestamp());
							webpage.setContent(message.getContent().toStringUtf8());
							handler.onHandleWebPage(webpage);
						}
						source.close();
					
					}
				}
			}
		}
	}
	
}
