package org.cx.game.card.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.cx.game.card.server.Battle;
import org.cx.game.command.Command;
import org.cx.game.command.exception.ExecuteValidatorException;
import org.cx.game.host.IHost;
import org.cx.game.host.IHostManager;
import org.cx.game.tools.Logger;
import org.cx.game.tools.SpringUtils;
import org.cx.game.tools.Util;

public class CreateCommand extends Command {

	@Override
	public Object execute() throws ExecuteValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		String SPACE = " ";
		String account = parameter.toString().split(SPACE)[0];
		String hostName = parameter.toString().split(SPACE)[1];
		Integer nop  = new Integer(parameter.toString().split(SPACE)[2]);
		
		IHostManager hm = SpringUtils.getBean("hostManager");
		IHost host = hm.createHost(hostName, account, nop);
		Integer troop = host.getTroopForAccount(account);
		
		Logger.debug("CreateCommand PlayNo: {}", hm.getPlayNoByCreator(account));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("playNo", host.getPlayNo());
		map.put("troop", troop);
		map.put("sequence", 0);
		map.put("hostStatus", host.getStatus());
		map.put("account", account);
		if("play".equals(hostName)) {
			Battle battle = (Battle) host;
			map.put("firstHand", battle.getFirstHand()==troop);
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		//System.out.println(r.nextInt(2));
		
		List<Integer> list = Util.generateRandomNumber(3);
		for(Integer i : list)
			System.out.println(i);
	}
}
