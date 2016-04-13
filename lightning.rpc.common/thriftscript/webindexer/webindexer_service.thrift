

include "../common/common_exception.thrift"
include "webindexer_model.thrift"

namespace java com.lighting.rpc.webindexer.service

service WebIndexerService {

	//	查询网页内容，返回摘要等信息
	webindexer_model.WIQueryResponse query(1: webindexer_model.WIQueryRequest request) throws (1: common_exception.LightningServiceException e);

	// 查询单个网页文档
	webindexer_model.WIQueryArticleResponse queryArticle(1: webindexer_model.WIQueryArticleRequest request) throws (1: common_exception.LightningServiceException e);
	
	// 批量查询多个网页文档
	webindexer_model.WIQueryBatchArticlesResponse queryBatchArticles(1: webindexer_model.WIQueryBatchArticlesRequest request) throws (1: common_exception.LightningServiceException e);

	// 查询网页内容，此乃正道接口
	webindexer_model.WIQueryWebPagesResponse queryWebpages(1: webindexer_model.WIQueryWebPagesRequest request) throws (1: common_exception.LightningServiceException e);

	// 查询网页内容，并添加时间限制
	webindexer_model.WIQueryWebPagesWithTimeRangeResponse queryWebPagesWithTimeRange(1: webindexer_model.WIQueryWebPagesWithTimeRangeRequest request) throws (1: common_exception.LightningServiceException e);


}




