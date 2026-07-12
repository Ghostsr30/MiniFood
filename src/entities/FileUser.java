package entities;

import java.time.LocalDate;


public class FileUser {

    private String nameUser;
    private LocalDate dateArq;
    private Product product;
    private Order order;

    public FileUser( LocalDate dateArq) {
    }


    public FileUser(LocalDate dateArq, Order order) {
        this.dateArq = dateArq;
        this.order = order;
    }


    public LocalDate getDateArq() {
        return dateArq;
    }

    public void setDateArq(LocalDate dateArq) {
        this.dateArq = dateArq;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "=====================\n"
                + "PEDIDO: \n"
                + "Data: " + dateArq + "\n"
                + "---------------------\n"
                + order.toString() + "\n"
                + "=====================";
    }
}
