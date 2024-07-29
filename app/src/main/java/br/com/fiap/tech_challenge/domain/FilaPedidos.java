import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain.ItemPedido;
import br.com.fiap.tech_challenge.domain.Pedido;
import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;

public class FilaDePedidos {
    private Queue<Pedido> fila;

    public FilaDePedidos() {
        this.fila = new LinkedList<>();
    }

    // Adicionar um pedido à fila
    public void adicionarPedido(Pedido pedido) {
        fila.add(pedido);
        System.out.println("Pedido adicionado: " + pedido);
    }

    // Processar o próximo pedido na fila
    public Pedido processarProximoPedido() {
        Pedido pedido = fila.poll();
        if (pedido != null) {
            System.out.println("Processando pedido: " + pedido);
        } else {
            System.out.println("Nenhum pedido na fila.");
        }
        return pedido;
    }

    // Ver o próximo pedido sem remover da fila
    public Pedido verProximoPedido() {
        return fila.peek();
    }

    // Verificar se a fila está vazia
    public boolean isFilaVazia() {
        return fila.isEmpty();
    }

    public static void main(String[] args) {
        FilaDePedidos filaDePedidos = new FilaDePedidos();

        // Criar alguns pedidos de exemplo
        ItemPedido item1 = new ItemPedido();
        item1.setDescricao("Hamburguer");
        item1.setValorUnitario(BigDecimal.valueOf(20.00));
        item1.setQuantidade(2);

        ItemPedido item2 = new ItemPedido();
        item2.setDescricao("Batata Frita");
        item2.setValorUnitario(BigDecimal.valueOf(10.00));
        item2.setQuantidade(1);

        Pedido pedido1 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(item1, item2));
        Pedido pedido2 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(item1));
        Pedido pedido3 = new Pedido(new Date(), SituacaoPedido.PENDENTE, List.of(item2));

        // Adicionar pedidos à fila
        filaDePedidos.adicionarPedido(pedido1);
        filaDePedidos.adicionarPedido(pedido2);
        filaDePedidos.adicionarPedido(pedido3);

        // Processar pedidos na fila
        filaDePedidos.processarProximoPedido();
        filaDePedidos.processarProximoPedido();
        filaDePedidos.processarProximoPedido();
        filaDePedidos.processarProximoPedido(); // Tentativa de processar um pedido quando a fila está vazia
    }
}
