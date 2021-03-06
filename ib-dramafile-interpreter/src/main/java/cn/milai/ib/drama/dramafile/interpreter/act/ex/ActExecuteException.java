package cn.milai.ib.drama.dramafile.interpreter.act.ex;

import cn.milai.ib.drama.dramafile.interpreter.act.Act;

/**
 * 动作执行时发生的异常
 *
 * 2019.12.19
 *
 * @author milai
 */
public class ActExecuteException extends ActException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActExecuteException(Act act, Throwable e) {
		super(String.format("执行 Act 时发生异常, act = %s", act.getCode()), e);
	}
	
	public ActExecuteException(Act act, String msg) {
		super(String.format("执行 Act 时发生异常, act = %s, error = (%s)", act.getCode(), msg));
	}

}
