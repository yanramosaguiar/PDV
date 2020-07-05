package com.brl.pdv.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatadorDinheiro {

	public static String formata(BigDecimal dinheiro) {
		return "R$".concat(new DecimalFormat("#,###,##0.00").format(dinheiro));
	}
}
