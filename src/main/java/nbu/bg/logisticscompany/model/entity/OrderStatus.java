package nbu.bg.logisticscompany.model.entity;

/**
 * The enum Order status.
 */
public enum OrderStatus {
    /**
     * The Sent.
     */
// Created order but not delivered or picked by courier
    SENT,
    /**
     * The In progress.
     */
// Courier is delivering the order
    IN_PROGRESS,
    /**
     * The Delivered.
     */
// Courier has delivered or client has picked up from office
    DELIVERED;
}
