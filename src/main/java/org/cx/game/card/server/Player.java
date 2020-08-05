package org.cx.game.card.server;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.command.CommandBuffer;
import org.cx.game.core.GameObject;
import org.cx.game.host.IHost;
import org.cx.game.host.IPlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player extends GameObject implements IPlayer {

	public static final String Neutral = "neutral";
	
	private Integer troop = null;
	private String name = null;
	
	public Player(Integer troop, String account, IHost host) {
		// TODO Auto-generated constructor stub
		this.troop = troop;
		this.name = account;
		setHost(host);
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof IPlayer) {
			IPlayer player = (IPlayer) arg0;
			return player.getTroop().equals(getTroop());
		}
		return super.equals(arg0);
	}
}
