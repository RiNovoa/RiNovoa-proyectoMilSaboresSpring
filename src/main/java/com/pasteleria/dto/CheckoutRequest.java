package com.pasteleria.dto;

import java.util.List;

public class CheckoutRequest {

    private Integer idUsuario;     // id del CLIENTE
    private String medioPago;      // "paypal", "debito", etc.
    private List<CheckoutItemDTO> items;

    public CheckoutRequest() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public List<CheckoutItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CheckoutItemDTO> items) {
        this.items = items;
    }
}
