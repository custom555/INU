package toolkit;

public enum MessageBoxButtons {
	ABORT_RETRY_IGNORE("Przerwij","Ponów","Ignoruj"),
	OK("OK"),
	OK_CANCEL("OK","Anuluj"),
	RETRY_CANCEL("Ponów","Anuluj"),
	YES_NO("Tak","Nie");
	
	private String[] text = new String[3];
	private int count;
	
	MessageBoxButtons(String...strings ){
		for(int i=0; i<strings.length; i++) {
			text[i] = strings[i];
			count = strings.length;
		}
	}
	
	@Override
	public String toString() {return text[0];}
	public String getText(int i) {return text[i];}
	public int getCount() {return count;}
}
