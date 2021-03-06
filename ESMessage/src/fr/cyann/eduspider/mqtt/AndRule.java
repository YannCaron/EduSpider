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
public class AndRule implements Rule {

	private final Rule[] rules;

	public AndRule(Rule... rules) {
		this.rules = rules;
	}

	@Override
	public boolean predicate(Context context) {
		for (Rule rule : rules) {
			if (!rule.predicate(context)) {
				return false;
			}
		}
		return true;
	}

}
