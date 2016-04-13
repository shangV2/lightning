package com.qing.guodu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import com.qing.askengine.model.ConsistentTopicDO;
import com.qing.askengine.model.ConsistentTopicType;
import com.qing.askengine.model.DateNumberTrendDO;
import com.qing.askengine.model.TopicWebPageArticle;
import com.qing.askengine.utils.WordSplitUtils;

public class ConsistentTopicManager implements WebpageConsumer {

//	private static final String DB_FILE = "consistent_topic.top";
	
	private String topicDbFile = "consistent_topic.top";
	
	private String languageType = "cn";
	
	private Random random = new Random();

	// topicid => topic name mapping
	private Map<String, Long> topicId2Map = new TreeMap<String, Long>();
	
	// 
	private Map<Long, ConsistentTopicDO> topicDOMaps = new TreeMap<Long, ConsistentTopicDO>(); 
	
	// 
	private Map<Long, List<DateNumberTrendDO> > webpageTrendsMapLists = new TreeMap<Long, List<DateNumberTrendDO> >();

	private Map<Long, Map<String, List<TopicWebPageArticle> > > webpageTrendsMapArticles = new TreeMap<Long, Map<String, List<TopicWebPageArticle> > >();
	
	private Map<Long, List<DateNumberTrendDO> > shortMessageTrendsMapLists = new TreeMap<Long, List<DateNumberTrendDO> >();
	
	
	public ConsistentTopicManager() {
		
	}
	
	private List<DateNumberTrendDO> createTrends(String startDate, String endDate) {
		List<DateNumberTrendDO> result = new ArrayList<DateNumberTrendDO>();
		
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		try {
			int startDateNumber = Integer.parseInt(startDate);
			int syear = startDateNumber / 10000;
			int smonth = startDateNumber % 10000 / 100;
			int sday = startDateNumber % 100;
			startDay.set(syear, smonth - 1, sday);
			
			int endDateNumber = Integer.parseInt(endDate);
			int eyear = endDateNumber / 10000;
			int emonth = endDateNumber % 10000 / 100;
			int eday = endDateNumber % 100;
			endDay.set(eyear, emonth - 1, eday);
		} catch (Exception e) {
			return result;
		}
		
		Calendar now = Calendar.getInstance();
		while ( startDay.before(now) && startDay.before(endDay) ) {
			String key = String.format("%d-%02d-%02d", startDay.get(Calendar.YEAR), startDay.get(Calendar.MONTH) + 1, startDay.get(Calendar.DATE));
			int value = random.nextInt() % 333;
			value = value > 0 ? value : -value;
			result.add(new DateNumberTrendDO(key, value));
			startDay.add(Calendar.DATE, 1);
		}
		return result;
		
	}
	
	public void init() {
		load();
	}
	
	public List<DateNumberTrendDO> queryDateNumberTimeRange(ConsistentTopicType topicType, long topicId) {
		
		synchronized (this) {
			if ( !topicDOMaps.containsKey(topicId) ) {
				return new ArrayList<DateNumberTrendDO>();
			}
			ConsistentTopicDO topic = topicDOMaps.get(topicId);
			
			if ( topicType == ConsistentTopicType.CONSISTENT_TOPIC_WEBPAGE ) {
				return queryDateNumberTimeRange(webpageTrendsMapLists, topic.getTopicId(), 
						format2Haokan(topic.getStartDate()), 
						format2Haokan(topic.getEndDate())
					);	
			} else if ( topicType == ConsistentTopicType.CONSISTENT_TOPIC_SHORT_MESSAGE ) {
				return queryDateNumberTimeRange(shortMessageTrendsMapLists, topic.getTopicId(), 
						format2Haokan(topic.getStartDate()), 
						format2Haokan(topic.getEndDate())
					);
			} else {
				return new ArrayList<DateNumberTrendDO>();
			}
		}
		
	}
	
	public List<DateNumberTrendDO> queryDateNumberTimeRange(Map<Long, List<DateNumberTrendDO> > sources, long topicId, String startdate, String enddate) {
		List<DateNumberTrendDO> result = new ArrayList<DateNumberTrendDO>();
	
		if ( topicDOMaps.containsKey(topicId) ) {
			List<DateNumberTrendDO> trends = sources.get(topicId);
			int ndx = Collections.binarySearch(trends, new DateNumberTrendDO(startdate, 0));
			if ( ndx >= 0 ) {
				for ( int i = ndx; i < trends.size(); i++ ) {
					DateNumberTrendDO trend = trends.get(i);
					if ( trend.getDate().compareToIgnoreCase(enddate) <= 0 ) {
						result.add(trend);
					} else {
						break;
					}
				}
			}
		}
		
		return result;
	}
	
	public ConsistentTopicDO queryConsistentTopic(long topicId) {
		synchronized (this) {
			ConsistentTopicDO topic = topicDOMaps.get(topicId);
			if ( topic == null ) {
				return null;
			}
			return topic.clone();
		}
	}
	
	public String format2Haokan(String date) {
		
		try {
			int startDateNumber = Integer.parseInt(date);
			int syear = startDateNumber / 10000;
			int smonth = startDateNumber % 10000 / 100;
			int sday = startDateNumber % 100;
			return String.format("%d-%02d-%02d", syear, smonth, sday);
		} catch (Exception e) {
			return "";
		}
		
	}

	// ----------------------------------------------------------------------------
	
	public long createConsistentTopic(ConsistentTopicDO topic) {
		synchronized(this) {
			if ( topicId2Map.containsKey(topic.getTopicName()) ) {
				return topicId2Map.get(topic.getTopicName());
			}
			long topicId = createTopicId();
			topicId2Map.put(topic.getTopicName(), topicId);
			
			topic.setTopicId(topicId);
			topicDOMaps.put(topicId, topic.clone());
			persistent();
			
			return topicId;
		}
	}
	
	public void removeConsistentTopic(long topicId) {
		synchronized (this) {
			ConsistentTopicDO topic = topicDOMaps.get(topicId);
			if ( topic != null ) {
				topicId2Map.remove(topic.getTopicName());
				topicDOMaps.remove(topicId);
				persistent();
			}
		}
	}

	public List<ConsistentTopicDO> listTopics(int pageno, int pagesize) {
		List<ConsistentTopicDO> result = new ArrayList<ConsistentTopicDO>();
		synchronized (this) {
			int counter = 0;
			for ( Entry<Long, ConsistentTopicDO> entry : topicDOMaps.entrySet() ) {
				if ( counter >= pageno * pagesize && counter < (pageno + 1) * pagesize ) {
					result.add(entry.getValue().clone());
				}
				counter++;
				if ( counter >= (pageno + 1) * pagesize ) {
					break;
				}
			}
		}
		return result;
	}

	public int countTopics() {
		synchronized (this) {
			return topicDOMaps.size();
		}
	}
	
	private void persistent() {
		// TODO
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(topicDbFile));
			for ( Map.Entry<Long, ConsistentTopicDO> entry : topicDOMaps.entrySet() ) {
				oos.writeObject(entry.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( oos != null ) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void load() {
		
		File file = new File(topicDbFile);
		if ( !file.exists() ) {
			return;
		}
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(topicDbFile);
			ois = new ObjectInputStream(fis);
			while ( fis.available() > 0 ) {
				Object obj = ois.readObject();
				if ( obj instanceof ConsistentTopicDO ) {
					ConsistentTopicDO topic = (ConsistentTopicDO)obj;
					topicId2Map.put(topic.getTopicName(), topic.getTopicId());
					topicDOMaps.put(topic.getTopicId(), topic);
					
					webpageTrendsMapLists.put(
							topic.getTopicId(), 
							createTrends(topic.getStartDate(), topic.getEndDate())
						);
					shortMessageTrendsMapLists.put(
							topic.getTopicId(), 
							createTrends(topic.getStartDate(), topic.getEndDate())
						);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( fis != null ) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ( ois != null ) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private long createTopicId() {
		UUID uuid = UUID.randomUUID();
		long value = uuid.getLeastSignificantBits();
		return value > 0 ? value : -value;
	}
	
	public List<TopicWebPageArticle> queryTopicWebPageArticleAtTime(long topicId, String timestamp) {
		
		List<TopicWebPageArticle> result = new ArrayList<TopicWebPageArticle>();
		if ( webpageTrendsMapArticles.containsKey(topicId) ) {
			Map<String, List<TopicWebPageArticle> > topicWebpageArticleMap = webpageTrendsMapArticles.get(topicId);
			String key = convert(timestamp);
			if ( topicWebpageArticleMap.containsKey(key) ) {
				List<TopicWebPageArticle> articles = topicWebpageArticleMap.get(key);
				for ( TopicWebPageArticle article : articles ) {
					result.add(article.clone());
				}
			}
		}
		return result;
		
	}
	
	public List<TopicWebPageArticle> queryTopicWebageArticles(long topicId, int pageno, int pagesize) {
		
		List<TopicWebPageArticle> result = new ArrayList<TopicWebPageArticle>();
		if ( webpageTrendsMapArticles.containsKey(topicId) ) {
			int start = 0;
			Map<String, List<TopicWebPageArticle> > topicWebpageArticleMap = webpageTrendsMapArticles.get(topicId);
			for ( Map.Entry<String, List<TopicWebPageArticle> > entry : topicWebpageArticleMap.entrySet() ) {
				List<TopicWebPageArticle> articles = entry.getValue();
				for ( int i = 0; i < articles.size(); i++ ) {
					if ( start + i >= (pageno * pagesize) ) {
						result.add(articles.get(i).clone());
						if ( result.size() >= pagesize ) {
							return result;
						}
					}
				}
			}
		}
		return result;
		
	}
	
	// ----------------------------------------------------------------------------------------------
	public List<DateNumberTrendDO> queryQingDateNumberTimeRange(ConsistentTopicType topicType, long topicId) {
		synchronized (this) {
			if ( !topicDOMaps.containsKey(topicId) ) {
				return new ArrayList<DateNumberTrendDO>();
			}
			ConsistentTopicDO topic = topicDOMaps.get(topicId);
			
			if ( topicType == ConsistentTopicType.CONSISTENT_TOPIC_WEBPAGE ) {
//				return queryDateNumberTimeRange(webpageTrendsMapLists, topic.getTopicId(), 
//						format2Haokan(topic.getStartDate()), 
//						format2Haokan(topic.getEndDate())
//					);	
				return queryQingWebpageDateNumberTimeRange(topic.getTopicId(), topic.getStartDate(), topic.getEndDate());
				
				
			} else if ( topicType == ConsistentTopicType.CONSISTENT_TOPIC_SHORT_MESSAGE ) {
//				return queryDateNumberTimeRange(shortMessageTrendsMapLists, topic.getTopicId(), 
//						format2Haokan(topic.getStartDate()), 
//						format2Haokan(topic.getEndDate())
//					);
				return new ArrayList<DateNumberTrendDO>();
			} else {
				return new ArrayList<DateNumberTrendDO>();
			}
		}
	}
	
	
	public List<DateNumberTrendDO> queryQingWebpageDateNumberTimeRange(long topicId, String startdate, String enddate) {
		List<DateNumberTrendDO> result = new ArrayList<DateNumberTrendDO>();
		Calendar startkey = convert2Calendar(startdate);
		Calendar endkey = convert2Calendar(enddate);
		
		Map<String, List<TopicWebPageArticle> > webPageArticleMaps = webpageTrendsMapArticles.get(topicId);
		if ( webPageArticleMaps != null ) {
			while ( !startkey.after(endkey) ) {
				String key = convert2time(startkey);
				if ( webPageArticleMaps.containsKey(key) ) {
					DateNumberTrendDO trend = new DateNumberTrendDO(key, webPageArticleMaps.get(key).size());
					result.add(trend);
				} else {
					DateNumberTrendDO trend = new DateNumberTrendDO(key, 0);
					result.add(trend);
				}
				startkey.add(Calendar.DATE, 1);
			}
		} else {
			while ( !startkey.after(endkey) ) {
				String key = convert2time(startkey);
				DateNumberTrendDO trend = new DateNumberTrendDO(key, 0);
				result.add(trend);
				startkey.add(Calendar.DATE, 1);
			}
		}
		return result;
		
	}
	
	@Override
	public void consume(WebPage page) {

		for ( Map.Entry<Long, ConsistentTopicDO> entry : topicDOMaps.entrySet() ) {
			ConsistentTopicDO nowTopic = entry.getValue();
			
			if ( !inTimeInterval(page.getTimestamp(), nowTopic.getStartDate(), nowTopic.getEndDate()) ) {
				return;
			}
			
			int score = 0;
			List<String> words = nowTopic.getWords();
			StringBuilder sb = new StringBuilder();
			for ( String word : words ) {
				int idx = 0;
				String title = page.getTitle();
				if ( title != null ) {
					while ( (idx = title.indexOf(word, idx)) != -1 ) {
						idx++;
						score += 3;
					}
				}
				
				idx = 0;
				String content = page.getContent();
				if ( content != null ) {
					while ( (idx = content.indexOf(word, idx)) != -1 ) {
						idx++;
						score++;
					}
				}
				sb.append(word).append(" ");
			}
		
			if ( score > nowTopic.getPercent() ) {
				
				TopicWebPageArticle article = new TopicWebPageArticle();
				article.setContent(WordSplitUtils.generateLanguageSummary(languageType, "content", sb.toString(), page.getContent(), 80));
				article.setTitle(page.getTitle());
				article.setTimestamp(page.getTimestamp());
				article.setUrl(page.getUrl());
				article.setSource(page.getSource());
				
				if ( !webpageTrendsMapArticles.containsKey(entry.getKey()) ) {
					webpageTrendsMapArticles.put(entry.getKey(), new TreeMap<String, List<TopicWebPageArticle> >());
				}
				Map<String, List<TopicWebPageArticle> > topicWebPageArticleMap = webpageTrendsMapArticles.get(entry.getKey());
				
				if ( topicWebPageArticleMap.containsKey(page.getTimestamp()) ) {
					topicWebPageArticleMap.get(page.getTimestamp()).add(article);
				} else {
					topicWebPageArticleMap.put(page.getTimestamp(), new ArrayList<TopicWebPageArticle>());
					topicWebPageArticleMap.get(page.getTimestamp()).add(article);
				}
			}
			
		}
		
	}
	
	
	public boolean inTimeInterval(String checkpoint, String startDate, String endDate) {
		String norstart = convert(startDate);
		String norend = convert(endDate);
		return norstart.compareTo(checkpoint) <= 0 && checkpoint.compareTo(norend) <= 0;
	}
	
	public String convert(String d) {
		int value = Integer.parseInt(d);
		return String.format("%d-%02d-%02d", value / 10000, (value % 10000) / 100, value % 100);
	}
	
	public Calendar convert2Calendar(String d) {
		Calendar calendar = Calendar.getInstance();
		int value = Integer.parseInt(d);
		calendar.set(Calendar.YEAR, value / 10000);
		int month = (value % 10000) / 100;
		month = month - 1 >= 0 ? month - 1 : 0;
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, value % 100);
		return calendar;
	}

	public String convert2time(Calendar calendar) {
		return String.format("%d-%02d-%02d", calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
	}

	@Override
	public void consumeGameover() {
		// TODO Auto-generated method stub
	}

	public String getTopicDbFile() {
		return topicDbFile;
	}

	public void setTopicDbFile(String topicDbFile) {
		this.topicDbFile = topicDbFile;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
}

