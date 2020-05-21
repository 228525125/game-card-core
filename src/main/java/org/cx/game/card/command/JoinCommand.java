package org.cx.game.card.command;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.server.Host;
import org.cx.game.command.Command;
import org.cx.game.command.exception.ExecuteValidatorException;
import org.cx.game.host.IHostManager;
import org.cx.game.tools.SpringUtils;

public class JoinCommand extends Command {

	@Override
	public Object execute() throws ExecuteValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		IHostManager hm = SpringUtils.getBean("hostManager");
		String SPACE = " ";
		String account = parameter.toString().split(SPACE)[0];
		String hostName = parameter.toString().split(SPACE)[1];
		String playNo = hm.getPlayNoByHostName(hostName);
		Host host = (Host) hm.joinGame(playNo, account);
		Integer troop = host.getTroopForAccount(account);
		Boolean firstHand = host.getFirstHand()==0 ? true : false;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("playNo", playNo);
		map.put("troop", troop);
		map.put("sequence", 0);
		map.put("firstHand", firstHand);
		map.put("hostStatus", host.getStatus());
		map.put("account", account);
		return map;
	}
}
