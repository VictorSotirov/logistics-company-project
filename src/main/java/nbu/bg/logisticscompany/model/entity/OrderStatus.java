package nbu.bg.logisticscompany.model.entity;

public enum OrderStatus {
    // Created order but not delivered or picked by courier
    SENT,
    // Courier is delivering the order
    IN_PROGRESS,
    // Courier has delivered or client has picked up from office
    DELIVERED;
}
