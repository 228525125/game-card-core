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
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class DeckCreated extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		List<Character> list = getOwner().findAllByType(Character.class);
		Character character1 = list.get(0);
		Character character2 = list.get(1);
		List<Card> cards = getOwner().findAllByType(Card.class);
		List<Card> deck1 = new ArrayList<Card>();
		List<Card> deck2 = new ArrayList<Card>();
		for(Card card : cards) {
			if(character1.getPid().equals(card.getCharacter()))
				deck1.add(card);
			if(character2.getPid().equals(card.getCharacter()))
				deck2.add(card);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deck1", deck1);
		map.put("deck2", deck2);
		String desc = "【Deck创建完成】；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Deck_Created,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Battle getOwner() {
		// TODO Auto-generated method stub
		return (Battle) super.getOwner();
	}
}
