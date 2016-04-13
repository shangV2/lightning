package com.qing.guodu;

import java.io.File;
import java.util.Iterator;

public class FileDataSource {

	private String rootDir;
	
	public FileDataSource () {
		
	}
	
	public FileDataSource(String rootDir) {
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
					} else if ( file.isFile() && !file.isHidden() )  {
//						WebPage webPage = new WebPage();
						WebPage webPage = WebPageParser.parse(file.getAbsolutePath());
						handler.onHandleWebPage(webPage);
					}
				}
			}
		}
	}
	
	// ��� ������ӿ���ʱ����
	public Iterator<WebPage> iterators() {
		return new Iterator<WebPage>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public WebPage next() {
				return null;
			}

			@Override
			public void remove() {
			}
		};
	}
	
}
