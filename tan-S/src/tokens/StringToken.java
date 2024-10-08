package tokens;

import inputHandler.Locator;

public class StringToken extends TokenImp {
	protected String value;
	
	protected StringToken(Locator locator, String lexeme) {
		super(locator, lexeme);
	}
	protected void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	public static StringToken make(Locator locator, String lexeme) {
		//System.out.println(lexeme);
		StringToken result = new StringToken(locator, lexeme);
		result.setValue(lexeme);
		return result;
	}
	
	@Override
	protected String rawString() {
		return "String, " + value;
	}
}
