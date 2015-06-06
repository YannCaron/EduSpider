/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.cyann.eduspider.mqtt;

import fr.cyann.eduspider.mqtt.Context.Action;

/**
 <p>
 @author cyann
 */
public class EventRule implements Rule {
	
	private final Action action;

	public EventRule(Action action) {
		this.action = action;
	}

	@Override
	public boolean predicate(Context context) {
		return context.currentAction == action;
	}
	
}
