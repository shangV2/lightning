

namespace java com.lighting.rpc.common.exception

exception LightningServiceException {
	1: required i64 logid,
	2: required i32 errorCode,
	3: required string errorMessage
}

