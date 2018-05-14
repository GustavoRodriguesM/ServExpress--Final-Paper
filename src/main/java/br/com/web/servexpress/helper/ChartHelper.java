package br.com.web.servexpress.helper;

public class ChartHelper {

	private String month;
	private Long amount;

	public String getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		if (month == 0)
			this.month = "Janeiro";
		else if (month == 1)
			this.month = "Fevereiro";
		else if (month == 2)
			this.month = "Março";
		else if (month == 3)
			this.month = "Abril";
		else if (month == 4)
			this.month = "Maio";
		else if (month == 5)
			this.month = "Junho";
		else if (month == 6)
			this.month = "Julho";
		else if (month == 7)
			this.month = "Agosto";
		else if (month == 8)
			this.month = "Setembro";
		else if (month == 9)
			this.month = "Outubro";
		else if (month == 10)
			this.month = "Novembro";
		else if (month == 11)
			this.month = "Dezembro";
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
