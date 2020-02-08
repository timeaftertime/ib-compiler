package cn.milai.ib.compiler.frontend.lex;

import java.util.Set;

import cn.milai.ib.compiler.ex.IBCompilerException;

/**
 * 连接不同状态的边
 * @author milai
 * @date 2020.02.05
 */
public class Edge {

	private boolean epsilon = false;

	private Set<Character> acceptedChars;

	private Status targetStatus;

	/**
	 * 获取一个接受指定字符集合的边
	 * @param acceptedChars
	 * @param status 通往的状态
	 */
	public Edge(Set<Character> acceptedChars, Status status) {
		this.acceptedChars = acceptedChars;
		this.targetStatus = status;
	}

	/**
	 * 获取一个接受空串的边
	 * @param status 通往的状态
	 */
	public Edge(Status status) {
		this.epsilon = true;
		this.targetStatus = status;
	}

	/**
	 * 是否允许指定字符通过
	 * @param ch
	 * @return
	 */
	public boolean accept(char ch) {
		if (isEpsilon()) {
			throw new IBCompilerException("接受空串的边不能消费任何字符");
		}
		return acceptedChars.contains(ch);
	}

	/**
	 * 是否为接受空串的边
	 * @return
	 */
	public boolean isEpsilon() {
		return epsilon;
	}

	/**
	 * 获取边通往的状态
	 * @return
	 */
	public Status getTargetStatus() {
		return targetStatus;
	}

	/**
	 * 获取接受的字符集合，若为 isEpsilon() == true ，返回 null
	 * @return
	 */
	public Set<Character> getAcceptChars() {
		return acceptedChars;
	}

	@Override
	public String toString() {
		if (isEpsilon()) {
			return "--" + "ϵ" + "-->" + targetStatus.getId();
		}
		return "--" + acceptedChars + "-->" + targetStatus.getId();
	}

}
