

// ########################################
模块结构的划分
1. 
2. 
3. 
4. 
5. 
6. 

// ####################################

// @brief	首先我要获取所有热点词
QueryHotWordList()

// @param	传过去一个热点词，把相关互联网和短信数据传给我
// @return	标题、日期（有就返回没有为空）和出处
QueryPageByWord()

// @param 	一个热点词，你返回该词每天的总量
queryDayFreqByWord()


namespace java com.qing.logiclayer

struct WordInfo {
	1: string word
	2: i32 freq
}

struct PageInfo {
	1: string title
	2: string url
	3: string date
}


struct WordTimeTrend {
	1: string date 
	2: i32 total
}

struct WordSplitTimeTrend {
	1: string date
	2: i32 smsfreq
	3: i32 pagefreq
}

// @notice
//struct Sms

struct SearchSummary {
	1: string title
	2: string url
	3: string content
}

struct BriefReport {
	1: string title
	2: string url
	3: string content
}

service Logiclayer {
	
	list<WordInfo> queryHotWordList();
	list<PageInfo> queryPageByWord(1: string word);
	i32 queryDayFreqByWord(1: string word);
	
	
	list<WordTimeTrend> queryWordTrend(1: string word, 2: i32 numdate);
	list<WordSplitTimeTrend> queryWordSplitTrend(1: string word, 2: i32 numdate);


	list<SearchSummary> querySearchSummary(1: string query, 2: i32 pageno, 3: i32 limit);			
	list<BriefReport> queryBriefReport(1: i32 pageno, 2: i32 limit);

}

// #####################################


AskEngine
 
	交互通过rpc的方式来实现
 
代理层proxy
	
	





