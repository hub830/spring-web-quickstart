package sample.web.ui.enums;

/**
 * 订单状态
 * @author fbi
 *
 */
public enum TransferStatus {

	/** 等待申请 */
	INIT,
	/** 等待处理  */
	WAIT,
	/** 处理成功 */
	SUCCESS;

	@Override
	public String toString() {
		return this.name();
	}
}
