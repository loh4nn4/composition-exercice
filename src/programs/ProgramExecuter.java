package programs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class ProgramExecuter {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String name = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.next();
		System.out.print("Birth date(dd/MM/yyyy): ");
		Date birthDate = sdf2.parse(scanner.next());
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		scanner.nextLine();
		String status = scanner.nextLine();
		Order order = new Order(new Date(), OrderStatus.valueOf(status), client);
		
		System.out.print("How many itens to this order? ");
		int n = scanner.nextInt();
		
		for(int i=0; i<n; i++) {
			scanner.nextLine();
			System.out.println("Enter #"+(i+1)+" data: ");
			System.out.print("Product name: ");
			String prodName = scanner.nextLine();
			System.out.print("Product price: ");
			double prodPrice = scanner.nextDouble();
			System.out.print("Quantity: ");
			int quantity = scanner.nextInt();
			OrderItem item = new OrderItem(quantity,
					prodPrice,
					new Product(prodName, prodPrice));
			order.addItem(item);
		}
		
		System.out.println("");
		System.out.println("ORDER SUMARY: ");
		System.out.println("Order moment: "+sdf.format(order.getMoment()));
		System.out.println("Order Status: "+order.getStatus());
		System.out.println("Client: "+order.getClient().getName()
				+"("+sdf.format(order.getClient().getBirthDate())+")"
				+ " - " +order.getClient().getEmail());
		System.out.println("Order itens: ");
		for(OrderItem it: order.getItens() ) {
			System.out.println(it.getProduct().getName()+", "
					+it.getPrice()+", Quantity: $"
					+it.getQuantity()+", Subtotal: $"
					+it.subtotal());
		}
		System.out.print("Total price: $"+order.total());
		
		scanner.close();
	}

}
