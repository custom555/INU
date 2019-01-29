package toolkit;

public enum MessageBoxResult {
	ABORT("Przerwij"),
	RETRY("Ponów"),
	IGNORE("Ignoruj"),
	OK("OK"),
	CANCEL("Anuluj"),
	YES("Tak"),
	NO("Nie");
	
	private String text;
	MessageBoxResult(String msg){
		text = msg;
	}
	
	@Override
	public String toString() {
		return text;
	}
	public static MessageBoxResult getResult(String text) {
		for(MessageBoxResult result : MessageBoxResult.values())
			if(result.toString() == text)
				return result;
			return CANCEL;
	}
}
