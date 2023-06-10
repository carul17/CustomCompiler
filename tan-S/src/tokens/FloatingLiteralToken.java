package tokens;

import inputHandler.Locator;
import logging.TanLogger;

public class FloatingLiteralToken extends TokenImp {
	protected double value;
	
	protected FloatingLiteralToken(Locator locator, String lexeme) {
		super(locator, lexeme);
	}
	protected void setValue(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}
	
	public static FloatingLiteralToken make(Locator locator, String lexeme) {
		FloatingLiteralToken result = new FloatingLiteralToken(locator, lexeme);
		double floatValue = Double.parseDouble(lexeme);
		if(floatValue == Double.POSITIVE_INFINITY) {
			TanLogger log = TanLogger.getLogger("compiler.FloatingLiteralToken");
			log.severe("Input float value is too large ");
		}
		result.setValue(floatValue);
		return result;
	}
	
	@Override
	protected String rawString() {
		return "number, " + value;
	}
}
