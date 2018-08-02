package cn.com.unary.initcopy.entity;

public final class Constants {

	public static class GlobalVar {
		public static int MAX_PACK_SIZE = 4096;
	}
	private Constants () {};
	
	public static enum MODE {
		SERVER, CLIENT
	}
	
	public static enum RequestType {
		ADD, QUERY, DELETE,
	}
	public static enum FileType {
		REGULAR_FILE, DIR, SYMBOLIC_LINK, OTHER,
	}
	public static enum PackType {
		REQ_INIT(0x01), // 请求-初始化
		REQ_DELETE_TASK(0x02),	// 请求-删除任务
		
		DATA_PACK_OF_SYNC_ALL (0x40),	// 数据包
		DATA_PACK_OF_SYNC_DIFF (0x41),
		
		RESP_INIT(0x80),	// 响应-初始化
		
		ERROR(0xA1),	// 错误
		;
		private byte value;
		private PackType (int b) {			value = (byte)b;		}
		public static PackType valueOf(byte b) {
			for (PackType type : PackType.values()) {
				if (type.value == b) {
					return type;
				}
			}
			return null;
		}
		public byte getValue () {			return value;		}
	}
}
