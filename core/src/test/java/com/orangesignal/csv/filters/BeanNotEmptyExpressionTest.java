/*
 * Copyright (c) 2009 OrangeSignal.com All rights reserved.
 * 
 * これは Apache ライセンス Version 2.0 (以下、このライセンスと記述) に
 * 従っています。このライセンスに準拠する場合以外、このファイルを使用
 * してはなりません。このライセンスのコピーは以下から入手できます。
 * 
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 * 
 * 適用可能な法律がある、あるいは文書によって明記されている場合を除き、
 * このライセンスの下で配布されているソフトウェアは、明示的であるか暗黙の
 * うちであるかを問わず、「保証やあらゆる種類の条件を含んでおらず」、
 * 「あるがまま」の状態で提供されるものとします。
 * このライセンスが適用される特定の許諾と制限については、このライセンス
 * を参照してください。
 */

package com.orangesignal.csv.filters;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.orangesignal.csv.entity.Price;
import com.orangesignal.csv.filters.BeanNotEmptyExpression;

/**
 * {@link BeanNotEmptyExpression} クラスの単体テストです。
 * 
 * @author 杉澤 浩二
 */
public class BeanNotEmptyExpressionTest {

	@Test(expected = IllegalArgumentException.class)
	public void testBeanNotEmptyExpressionIllegalArgumentException() {
		new BeanNotEmptyExpression(null);
	}

	@Test
	public void testAccep() throws Exception {
		assertFalse(new BeanNotEmptyExpression("symbol").accept(new Price(null, "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
		assertFalse(new BeanNotEmptyExpression("symbol").accept(new Price("", "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
		assertTrue(new BeanNotEmptyExpression("symbol").accept(new Price("GCX09", "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
		assertFalse(new BeanNotEmptyExpression("name").accept(new Price("GCX09", null, 1088.70, 100, new Date())));
		assertFalse(new BeanNotEmptyExpression("name").accept(new Price("GCX09", "", 1088.70, 100, new Date())));
		assertTrue(new BeanNotEmptyExpression("name").accept(new Price("GCX09", "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
		assertFalse(new BeanNotEmptyExpression("price").accept(new Price("GCX09", "COMEX 金 2009年11月限", null, 100, new Date())));
		assertTrue(new BeanNotEmptyExpression("price").accept(new Price("GCX09", "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
		assertFalse(new BeanNotEmptyExpression("date").accept(new Price("GCX09", "COMEX 金 2009年11月限", 1088.70, 100, null)));
		assertTrue(new BeanNotEmptyExpression("date").accept(new Price("GCX09", "COMEX 金 2009年11月限", 1088.70, 100, new Date())));
	}

	@Test
	public void testToString() {
		assertThat(new BeanNotEmptyExpression("symbol").toString(), is("BeanNotEmptyExpression"));
		
	}

}
