package jack911.pp.server;

/** 服务器连接状态listener */
public interface ServerConnListener
{
	/** 服务器已互相连接识别 */
	void connectedHandler(byte serverId);
}
