package utfpr.dainf.ct.ed.exemplo;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore binária de pesquisa.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós da árvore
 */
public class ArvoreBinariaPesquisa<E extends Comparable<E>> extends ArvoreBinaria<E> {

    protected ArvoreBinariaPesquisa<E> pai;

    /**
     * Cria uma árvore com valor nulo na raiz.
     */
    public ArvoreBinariaPesquisa() {
    }

    /**
     * Cria uma árvore com o valor especificado na raiz.
     *
     * @param valor O valor armazenado na raiz.
     */
    public ArvoreBinariaPesquisa(E valor) {
        super(valor);
    }

    /**
     * Inicializa o nó pai deste nó.
     *
     * @param pai O nó pai.
     */
    protected void setPai(ArvoreBinariaPesquisa<E> pai) {
        this.pai = pai;
    }

    /**
     * Retorna o nó pai deste nó.
     *
     * @return O nó pai.
     */
    protected ArvoreBinariaPesquisa<E> getPai() {
        return pai;
    }

    /**
     * Retorna o nó da árvore cujo valor corresponde ao especificado.
     *
     * @param valor O valor a ser localizado.
     * @return A raiz da subárvore contendo o valor ou {@code null}.
     */
    public ArvoreBinariaPesquisa<E> pesquisa(E valor) {
        ArvoreBinariaPesquisa<E> aux;
        if (pai == null) {
            return null;
        } else {
            if (pai.getValor().compareTo(valor) == 0) {
                aux = pai;
                pai = this;
                return aux;
            } else if (pai.getValor().compareTo(valor) > 0) {
                pai = (ArvoreBinariaPesquisa<E>) pai.getEsquerda();
                return pesquisa(valor);
            } else if (pai.getValor().compareTo(valor) < 0) {
                pai = (ArvoreBinariaPesquisa<E>) pai.getDireita();
                return pesquisa(valor);
            }
        }
        aux = pai;
        pai = this;
        return aux;
    }

    /**
     * Retorna o nó da árvore com o menor valor.
     *
     * @return A raiz da subárvore contendo o valor mínimo
     */
    public ArvoreBinariaPesquisa<E> getMinimo() {
        ArvoreBinariaPesquisa<E> aux;
        pai = this;
        if (pai != null) {
            while (pai.esquerda != null) {
                pai = (ArvoreBinariaPesquisa<E>) pai.esquerda;
            }
        }
        aux = pai;
        pai = this;
        return aux;
    }
    public ArvoreBinariaPesquisa<E> getMinimo(ArvoreBinariaPesquisa<E> raiz) {
        while(raiz != null && raiz.getEsquerda()!= null){
            raiz = (ArvoreBinariaPesquisa<E>) raiz.getEsquerda();
        }
        return raiz;
    }
   

    /**
     * Retorna o nó da árvore com o maior valor.
     *
     * @return A raiz da subárvore contendo o valor máximo
     */
    public ArvoreBinariaPesquisa<E> getMaximo() {
        ArvoreBinariaPesquisa<E> aux;
        pai = this;
        if (pai != null) {
            while (pai.direita != null) {
                pai = (ArvoreBinariaPesquisa<E>) pai.direita;
            }
        }
        aux = pai;
        pai = this;
        return aux;
    }
    public ArvoreBinariaPesquisa<E> getMaximo(ArvoreBinariaPesquisa<E> raiz) {
        while(raiz != null && raiz.getDireita()!= null){
            raiz = (ArvoreBinariaPesquisa<E>) raiz.getDireita();
        }
        return raiz;
    }

    /**
     * Retorna o nó sucessor do nó especificado.
     *
     * @param node
     * @return O sucessor do no ou {
     * @null}.
     */
    public ArvoreBinariaPesquisa<E> sucessor(ArvoreBinariaPesquisa<E> node) {
        if(this.getMaximo() == node){
            return null;
        }
        if (node != null && node.direita != null){
            return getMinimo((ArvoreBinariaPesquisa<E>) node.getDireita());
        }
   pai = node.getPai();
   while(pai != null && node == pai.getDireita()){
       node = pai;
       pai = pai.getPai();
   }
   
   return pai;
    }

    /**
     * Retorna o nó predecessor do nó especificado.
     *
     * @param node
     * @return O predecessor do nó ou {
     * @null}.
     */
    public ArvoreBinariaPesquisa<E> predecessor(ArvoreBinariaPesquisa<E> node) {
        if(this.getMinimo() == node){
           return null;
        }
        if(node != null && node.getEsquerda() != null){
            return getMaximo((ArvoreBinariaPesquisa<E>) node.getEsquerda());
        }
        pai = node.getPai();
        while(pai != null && node == pai.getEsquerda()){
            node = pai;
            pai = pai.getPai();
        }
        return pai;
    }

    /**
     * Insere um nó contendo o valor especificado na árvore.
     *
     * @param valor O valor armazenado no nó.
     * @return O nó inserido
     */
    public ArvoreBinariaPesquisa<E> insere(E valor) {
        ArvoreBinariaPesquisa<E> aux;
        if (pai == null) {
            pai = this;
        }
        if (pai.valor.compareTo(valor) > 0) {
            if (pai.esquerda != null) {
                pai = (ArvoreBinariaPesquisa<E>) pai.esquerda;
                aux = insere(valor);
            } else {
                pai.esquerda = new ArvoreBinariaPesquisa<>(valor);
                aux = (ArvoreBinariaPesquisa<E>) pai.esquerda;
                aux.setPai(pai);
            }
        } else {
            if (pai.direita != null) {
                pai = (ArvoreBinariaPesquisa<E>) pai.direita;
                aux = insere(valor);
            } else {
                pai.direita = new ArvoreBinariaPesquisa<>(valor);
                aux = (ArvoreBinariaPesquisa<E>) pai.direita;
                aux.setPai(pai);
            }
        }
        pai = this;
        return aux;
    }

    /**
     * Exclui o nó especificado da árvore. Se a raiz for excluída, retorna a
     * nova raiz.
     *z
     * @param no O nó a ser excluído.
     * @return A raiz da árvore
     */
    public ArvoreBinariaPesquisa<E> exclui(ArvoreBinariaPesquisa<E> no) {
        ArvoreBinariaPesquisa<E> aux;
        ArvoreBinariaPesquisa<E> raiz = this;
        if(no.getEsquerda() == null || no.getDireita() == null){
            pai = no;
        }else{
            pai = sucessor(no);
        }
        if(pai.getEsquerda() != null){
            aux = (ArvoreBinariaPesquisa<E>) pai.getEsquerda();
        }else{
            aux = (ArvoreBinariaPesquisa<E>) pai.getDireita();
        }
        if(aux != null){
            aux.setPai(pai.getPai());
        }
        if(pai.getPai() == null){
            raiz = aux;
        }else{
            if(no == pai.getPai().getEsquerda()){
                pai.getPai().setEsquerda(aux);
            }else{
                pai.getPai().setDireita(aux);
            }
        }
        if(pai != no){
            no.setValor(pai.getValor());
        }
    return raiz;
    
        /*
        if (null == pai) {
			System.out.println("Element is not there in binary search tree");
			return null;
		}
		if (no.getValor() < pai.getValor()) {
			pai.getEsquerda() = exclui(pai.getEsquerda(), no.getValor());
		} else if (no.getValor() > pai.getValor()) {
			pai.getDireita() = exclui(pai.getDireita(), no.getValor());
		} else { // case for equality
			// Now we see that whether we can directly delete the node
			// [Scenario 3]
			if (pai.getEsquerda() != null && pai.getDireita() != null) {
				int minInRightSubTree = getMinimo(pai.getDireita());
				pai.getValor() = minInRightSubTree;
				pai.getDireita() = deleteNodeInBST(pai.getDireita(), minInRightSubTree);
			} else { // either one child or leaf node
				// [Scenario 1 and Scenario 2]
				if (pai.getEsquerda() == null && pai.getDireita() == null) {
					pai = null;
				} else {// one child case
					ArvoreBinariaPesquisa deleteNode = pai;
					pai = (pai.getEsquerda() != null) ? (pai.getEsquerda()) : (pai.getDireita());
					deleteNode = null;
				}
			}
		}
		return pai;
         */
    }
}
