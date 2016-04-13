

include "../common/common_exception.thrift"
include "webindexer_model.thrift"

namespace java com.lighting.rpc.webindexer.service

service WebIndexerService {

	//	��ѯ��ҳ���ݣ�����ժҪ����Ϣ
	webindexer_model.WIQueryResponse query(1: webindexer_model.WIQueryRequest request) throws (1: common_exception.LightningServiceException e);

	// ��ѯ������ҳ�ĵ�
	webindexer_model.WIQueryArticleResponse queryArticle(1: webindexer_model.WIQueryArticleRequest request) throws (1: common_exception.LightningServiceException e);
	
	// ������ѯ�����ҳ�ĵ�
	webindexer_model.WIQueryBatchArticlesResponse queryBatchArticles(1: webindexer_model.WIQueryBatchArticlesRequest request) throws (1: common_exception.LightningServiceException e);

	// ��ѯ��ҳ���ݣ����������ӿ�
	webindexer_model.WIQueryWebPagesResponse queryWebpages(1: webindexer_model.WIQueryWebPagesRequest request) throws (1: common_exception.LightningServiceException e);

	// ��ѯ��ҳ���ݣ������ʱ������
	webindexer_model.WIQueryWebPagesWithTimeRangeResponse queryWebPagesWithTimeRange(1: webindexer_model.WIQueryWebPagesWithTimeRangeRequest request) throws (1: common_exception.LightningServiceException e);


}




