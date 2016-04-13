

// ########################################
ģ��ṹ�Ļ���
1. 
2. 
3. 
4. 
5. 
6. 

// ####################################

// @brief	������Ҫ��ȡ�����ȵ��
QueryHotWordList()

// @param	����ȥһ���ȵ�ʣ�����ػ������Ͷ������ݴ�����
// @return	���⡢���ڣ��оͷ���û��Ϊ�գ��ͳ���
QueryPageByWord()

// @param 	һ���ȵ�ʣ��㷵�ظô�ÿ�������
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
 
	����ͨ��rpc�ķ�ʽ��ʵ��
 
�����proxy
	
	





