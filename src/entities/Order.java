package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
	

	List<OrderItem> list = new ArrayList<>();
	
	public void addList(OrderItem item) {
		list.add(item);
	}
	public double total() {
		double sum = 0;
		for(OrderItem or : list) {
			sum += or.subTotal();
		}
		return sum;
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(OrderItem item : list) {
			sb.append(item).append("\n");
		}
		sb.append("Total: ").append(total());
		
		return sb.toString();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
