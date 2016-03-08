package sample.web.ui.enums;

public enum Available {

	/** 可用 */
	YES,
	/** 不可用  */
	NO;

	@Override
	public String toString() {
		return this.name();
	}
}
