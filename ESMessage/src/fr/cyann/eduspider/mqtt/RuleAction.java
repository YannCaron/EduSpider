/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 <p>
 @author cyann
 */
public abstract class RuleAction implements Rule {

	private final Rule rule;

	public RuleAction(Rule rule) {
		this.rule = rule;
	}

	@Override
	public boolean predicate(Context context) {
		return rule.predicate(context);
	}

	public abstract void run(Context context);
}
