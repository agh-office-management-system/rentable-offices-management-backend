package pl.edu.agh.rentableoffices.common.communication;


public interface AbstractListener {
    /**
     * Add @RabbitListener(queues = "${queue.name}") on implementation to connect to RabbitMQ queue
     * @param text
     */
    void read(String text);
}
