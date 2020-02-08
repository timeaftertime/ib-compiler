package cn.milai.ib.compiler.frontend.lex;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 处理 String 类型的 Input
 * @author milai
 * @date 2020.02.04
 */
public class StringInput extends Input<Character> {

	private static final char EOF = Char.EOF;

	public StringInput(String str) {
		super(toChracterArray(str));
	}

	/**
	 * 尝试获取下一个元素，若已经没有元素将返回 Char.EOF
	 */
	@Override
	public Character getNext() {
		if (index >= elements.size()) {
			return EOF;
		}
		return super.getNext();
	}

	private static Character[] toChracterArray(String str) {
		List<Character> list = Lists.newArrayList();
		for (char ch : str.toCharArray()) {
			list.add(ch);
		}
		return list.toArray(new Character[0]);
	}

}
