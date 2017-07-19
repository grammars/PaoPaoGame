package jack911.pp.message;

import jack911.pp.server.MsgUnit;

public interface IMsgHandler
{
	void handle(MsgHead head, MsgUnit msg);
}
