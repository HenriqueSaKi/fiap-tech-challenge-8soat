import java.util.LinkedList;
import java.util.Queue;

public class FilaPedidos {
    private Queue<Pedido> fila;

    public FilaPedidos() {
        this.fila = new LinkedList<>();
    }

    // adiciona um pedido à fila
    public void adicionarPedido(Pedido pedido) {
        fila.add(pedido);
        System.out.printIn("Pedido adicionado: " + pedido);
    }

    // processar o próximo pedido na fila
    public Pedido processarProximoPedido() {
        Pedido pedido = fila.poll();
        if (pedido != null) {
            System.out.printIn("Processando pedido: " + pedido);
        } else {
            System.out.printIn("Nenhum pedido na fila");
        }
        return pedido;
    }
    // ver o pedido sem remove-lo da fila
    public Pedido verProximoPedido() {
        return fila.peek();
    }

    // checar se a fila está vazia
    public boolean isVazia() {
        return fila.isEmpty();
    }

    public static void main(String[] args) {
        FilaPedidos filaPedidos = new FilaPedidos();

        // pedidos de exemplo
        Pedido pedido1 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(new ItemPedido("Item1", 2, BigDecimal.valueOf(50.00))));
        Pedido pedido2 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(new ItemPedido("Item2", 1, BigDecimal.valueOf(30.00))));
        Pedido pedido3 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(new ItemPedido("Item3", 3, BigDecimal.valueOf(20.00))));

        // adicionando-os à fila
        filaPedidos.adicionarPedido(pedido1);
        filaPedidos.adicionarPedido(pedido1);
        filaPedidos.adicionarPedido(pedido1);

        // processar pedidos na fila
        filaPedidos.processarProximoPedido();
        filaPedidos.processarProximoPedido();
        filaPedidos.processarProximoPedido();
        filaPedidos.processarProximoPedido();
    }
}