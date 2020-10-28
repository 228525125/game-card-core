package org.cx.game.card.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Battle;
import org.cx.game.card.server.Card;
import org.cx.game.card.server.Character;
import org.cx.game.card.server.Skill;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class SkillsCreated extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		List<Character> list = getOwner().findAllByType(Character.class);
		Character character1 = list.get(0);
		Character character2 = list.get(1);
		List<Skill> skills = getOwner().findAllByType(Skill.class);
		List<Skill> skills1 = new ArrayList<Skill>();
		List<Skill> skills2 = new ArrayList<Skill>();
		for(Skill skill : skills) {
			if(character1.getPid().equals(skill.getCharacter()))
				skills1.add(skill);
			if(character2.getPid().equals(skill.getCharacter()))
				skills2.add(skill);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skills1", skills1);
		map.put("skills2", skills2);
		String desc = "【Skills创建完成】；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Skills_Created,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Battle getOwner() {
		// TODO Auto-generated method stub
		return (Battle) super.getOwner();
	}
}
