package com.zhitianweilai.qing.url;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.zhitianweilai.qing.utils.UrlDetailHelper;

public class TagUrlExtractor implements IUrlExtractor {

	/**
	 * @brief	host
	 * 
	 * @param host
	 * @param content
	 * @return
	 */
	public List<String> extract(String parentUrl, String content) {
		
		if ( !parentUrl.startsWith("http://") ) {
			parentUrl = "http://" + parentUrl;
		}
		
		int pos = parentUrl.indexOf('?');
		if ( pos != -1 ) {
			parentUrl = parentUrl.substring(0, pos);
		}
		pos = parentUrl.indexOf('#');
		if ( pos != -1 ) {
			parentUrl = parentUrl.substring(0, pos);
		}
		if ( parentUrl.endsWith("/") ) {
			parentUrl = parentUrl.substring(0, parentUrl.length() - 1);
		}
		
		// ---------------------------------------------------------------------
		
		List<String> urls = new ArrayList<String>();

		Parser parser = new Parser();
		try {
			parser.setInputHTML(content);
			
			NodeFilter filter = new NodeClassFilter(LinkTag.class);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			
			for ( int i = 0; i < list.size(); i++ ) {
				Node node = list.elementAt(i);
				if ( node instanceof LinkTag ) {
					String url = ((LinkTag)node).getAttribute("href");
					if ( url != null ) {
						if ( isValidUrl(url) ) {
							if ( url.startsWith("http://") ) {
								urls.add(url);
							} else if ( url.startsWith("/") ) {
								 UriDetail uriDetail = UrlDetailHelper.parse(parentUrl);
								 urls.add("http://" + uriDetail.getHost() + url);
							} else {
								//URL durl = new URL(host, url);
								UriDetail uriDetail = UrlDetailHelper.parse(parentUrl);
								String path = uriDetail.getPath();
								if ( path != null ) {
									while ( true ) {
										if ( url.startsWith("./") ) {
											url = url.substring(2);
//											int lastIndex = path.lastIndexOf('/');
//											if ( lastIndex != -1 ) {
//												path = path.substring(0, lastIndex);
//											}
										} else if ( url.startsWith("../") ) {
											url = url.substring(3);
											int lastIndex = path.lastIndexOf('/');
											if ( lastIndex != -1 ) {
												path = path.substring(0, lastIndex);
											}
										} else {
											int lastIndex = path.lastIndexOf('/');
											if ( lastIndex != -1 ) {
												path = path.substring(0, lastIndex);
											} else {
												path = "";
											}
											break;
										}
									}
									if ( path.length() == 0 ) {
										urls.add("http://" + uriDetail.getHost() + "/" + url);
									} else {
										urls.add("http://" + uriDetail.getHost() + "/" + path + "/" + url);
									}
								} else {
									while ( true ) {
										if ( url.startsWith("./") ) {
											url = url.substring(2);
										} else if ( url.startsWith("../") ) {
											url = url.substring(3);
										} else {
											break;
										}
									}
									urls.add("http://" + uriDetail.getHost() + "/" + url);
								}
							} 
						}
					}
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return urls;
		
	}

	
	
	@Override
	public List<String> extract(String content) {
		
		List<String> urls = new ArrayList<String>();

		Parser parser = new Parser();
		try {
			parser.setInputHTML(content);
			
			NodeFilter filter = new NodeClassFilter(LinkTag.class);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			
			for ( int i = 0; i < list.size(); i++ ) {
				Node node = list.elementAt(i);
				if ( node instanceof LinkTag ) {
					String url = ((LinkTag)node).getAttribute("href");
					if ( url != null ) {
						if ( isValidUrl(url) ) {
							urls.add(url);
						}
					}
				}
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return urls;
		
	}
	
	public boolean isValidUrl(String url) {
		if ( url.startsWith("javascript") ) {
			return false;
		} else if ( url.startsWith("mailto") ) {
			return false;
		} else if ( url.startsWith("#") ) {
			return false;
		}
		if ( url.startsWith("http://") || url.startsWith("/") ||  url.startsWith("./") || url.startsWith("../") ) {
			return true;
		}
		if ( url.endsWith(".htm") || url.endsWith(".html") ) {
			return true;
		}
		return false;
	}
	
}


/*
url: /backend.php
url: /commentrss.php
url: http://www.cnbeta.com
url: http://www.cnbeta.com/
url: http://www.cnbeta.com/deliver
url: http://www.cnbeta.com/topics/306.htm
url: http://www.cnbeta.com/soft.htm
url: http://www.cnbeta.com/topics.htm
url: http://www.cnbeta.com/top10.htm
url: http://www.cnbeta.com/about/index.htm
url: http://www.cnbeta.com/topics/9.htm
url: http://www.cnbeta.com/topics/52.htm
url: http://www.cnbeta.com/topics/305.htm
url: http://www.cnbeta.com/topics/448.htm
url: http://www.cnbeta.com/topics/487.htm
url: http://www.cnbeta.com/topics/70.htm
url: http://www.cnbeta.com/topics/353.htm
url: http://www.cnbeta.com/topics/371.htm
url: http://www.cnbeta.com/topics/453.htm
url: http://www.cnbeta.com/topics/243.htm
url: http://www.cnbeta.com/topics/147.htm
url: http://www.cnbeta.com/topics/422.htm
url: http://www.cnbeta.com/topics/45.htm
url: http://www.cnbeta.com/topics/39.htm
url: http://www.cnbeta.com/topics/469.htm
url: http://www.cnbeta.com/topics/197.htm
url: http://www.cnbeta.com/topics/144.htm
url: http://www.cnbeta.com/topics/326.htm
url: http://www.cnbeta.com/topics/506.htm
url: http://www.cnbeta.com/topics/439.htm
url: http://www.cnbeta.com/topics/4.htm
url: http://www.cnbeta.com/topics/184.htm
url: http://www.cnbeta.com/topics/444.htm
url: http://www.cnbeta.com/topics/91.htm
url: http://www.cnbeta.com/topics/32.htm
url: http://www.cnbeta.com/topics/138.htm
url: http://www.cnbeta.com/topics/331.htm
url: http://www.cnbeta.com/topics/464.htm
url: http://www.cnbeta.com/topics.htm
url: javascript:;
url: http://www.cnbeta.com/
url: #comment
url: /topics/243.htm
url: http://english.analysys.com.cn/article.php?aid=173200
url: ' + google_ads[0].url + '
url: ' + google_ads[0].url + '
url: ' + google_ads[i].url + '
url: ' + google_ads[i].url + '
url: http://www.wy.cn/
url: /user/login/popup
url: javascript:void(0)
url: javascript:;
url: javascript:void(0)
url: http://thewind.hk/
url: http://www.apppoo.com/
url: http://weibo.com/ictech
url: http://news.mydrivers.com/
url: http://www.bio360.net/
url: http://google.org.cn/
url: http://www.weiphone.com/
url: http://www.huxiu.com/
url: http://www.yseeker.com/
url: http://www.ownlinux.org/
url: http://www.leica.org.cn/
url: http://www.geekpark.net/
url: http://www.macx.cn/
url: http://www.verycloud.cn/
url: http://www.whozen.com/
url: http://www.gigelayer.com/
url: http://www.hypo.cn/
url: http://www.wy.com.cn/cn2.aspx
url: http://www.miibeian.gov.cn
url: /about/index.htm
url: /about/cooperation.htm
url: mailto:ugmbbc@gmail.com

-------------------------------------------

url: ./201103/t20110303_944014.htm
url: ./201102/t20110223_933138.htm
url: ./201102/t20110223_933137.htm
url: ./201102/t20110223_933136.htm

 */





