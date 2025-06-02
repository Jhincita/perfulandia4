package duoc.perfulandia.model;

public enum OrderStatus {
    PAYMENT_PENDING, // al crear orden
    PROCESSING, // al pagar
    SHIPPED, // al enviar
    COMPLETED, //  cuando le llega al usuario, revisar cómo hacer esa lógica. Tal vez settear manualmente
    CANCELLED // cancelar
}
