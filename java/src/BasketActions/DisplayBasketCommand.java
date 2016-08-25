package BasketActions;

import java.util.List;

import Ui.UserCommand;
import Ui.UserInput;

import Basket.Basket;
import Basket.BasketItem;

class DisplayBasketCommand implements UserCommand {

	private Basket basket;

	DisplayBasketCommand(Basket basket) {
		this.basket = basket;
	}

	@Override
	public void run(UserInput cmd) {
		List<BasketItem> items = basket.list();
		if (items.isEmpty()) {
			System.out.println("Your basket is empty");
			return;
		}
		for (BasketItem item : items)
			System.out.printf("£%5.02f x%d\t%s\n", item.price / 100.0, item.count, item.title);
		int total = basketTotal(items);
		if (total > 2000) {
			int discount = total/10;
			System.out.printf("£%5.02f   \t10%% discount\n", discount / -100.0);
			total -= discount;
		}
		System.out.println("---------------");
		System.out.printf("£%5.02f total\n", total / 100.0);
	}

	private int basketTotal(List<BasketItem> items) {
		int total = 0;
		for (BasketItem item : items)
			total += item.price * item.count;
		return total;
	}

}