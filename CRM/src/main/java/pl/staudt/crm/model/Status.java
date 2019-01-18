package pl.staudt.crm.model;

public class Status {
    private int id;
    private int order_id;
    private int status;

    public Status() {
    }

    public Status(int order_id, int status) {
        this.order_id = order_id;
        this.status = status;
    }

    public Status(int id, int order_id, int status) {
        this.id = id;
        this.order_id = order_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
