package toolkit;


public enum MessageBoxIcon {
	INFORMATION("StatusInformation_64x.png"),
	WARNING("StatusWarning_64x.png"),
	ALERT("StatusAlert_64x.png"),
	CRITICAL_ERROR("StatusCriticalError_64x.png"),
	CONFIRM("StatusHelp_64x.png");
	private String text;
	
	MessageBoxIcon(String msg){
		text = msg;
	}
	@Override
	public String toString() {
		return text;
	}
}

