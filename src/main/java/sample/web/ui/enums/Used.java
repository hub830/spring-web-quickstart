package sample.web.ui.enums;

/**
 * 是否已经使用
 * @author fbi
 *
 */
public enum Used {

	/** 已用 */
	YES,
	/** 未用  */
	NO;

	@Override
	public String toString() {
		return this.name();
	}
}
