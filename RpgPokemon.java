
import java.util.Random;
import javax.swing.JOptionPane;

public class RpgPokemon {

    static String bemVindo = " -------------------------------------------------------------- \n\n       Bem-vindo ao mundo dos Pokémon \n\nTreinador, Sua jornada épica começa agora. \n\nInforme seu nome para começar a aventura ! \n\n --------------------------------------------------------------";

    static String jogador;
    static int vidaPokemon, curaPokemon, vidaMewTwo;
    static int[] ATAQUE_MEWTWO = {20, 25, 30, 35};
    static int[] ATAQUE_POKEMON = {15, 20, 25, 30};
    static int cont = -1;
    static int[] danoSofrido = new int[40];
    static int[] danoDado = new int[40];
    static int somaS = 0;
    static int somaD = 0;
    static int scorePerdas = 0;
    static int scoreGanhos = 0;

    public void inicio() {
        jogador = JOptionPane.showInputDialog(null, bemVindo);

        do {
            cont = -1;
            vidaBoss();
            escolhaPokemon();
            continuar();
            batalha();
        } while (JOptionPane.showConfirmDialog(null, "Deseja tentar novamente ?", "Reiniciar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Obrigado por jogar!");
    }

    public void escolhaPokemon() {
        int opcao;
        do {
            String escolhaPoke = "-------------------------------------------------------------- \n\n" + jogador + ", escolha seu Pokémon!\n\n1 - Pikachu\n\n2 - Bulbasaur\n\n3 - Charmander\n\n" + "-------------------------------------------------------------- \n\n";
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, escolhaPoke));
            switch (opcao) {
                case 1:
                    pokemonSelecionado("Pikachu", 140, 45, 50);
                    break;
                case 2:
                    pokemonSelecionado("Bulbasaur", 170, 50, 55);
                    break;
                case 3:
                    pokemonSelecionado("Charmander", 120, 50, 55);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Escolha inválida! Escolha novamente.");
            }
        } while (opcao < 1 || opcao > 3);
    }

    public void pokemonSelecionado(String nome, int vida, int força, int cura) {
        JOptionPane.showMessageDialog(null, "-------------------------------------------------------------- \n\n Você escolheu " + nome + "!\n\nVida: " + vida + "\n\nForça: " + força + "\n\nCura: " + cura + "\n\n-------------------------------------------------------------- \n\n");
        vidaPokemon = vida;
        curaPokemon = cura;
    }

    public void mew() {
        String mewStr = "-------------------------------------------------------------- \n\n HORA DA BATALHA \n\n MewTwo Aparece !! \n\n Vida:" + vidaMewTwo + "\n\nForça:" + 70 + "\n\nCura:" + 65 + "\n\n-------------------------------------------------------------- \n\n";
        JOptionPane.showMessageDialog(null, mewStr);
    }

    public void batalha() {

        Random random = new Random();
        mew();

        vidaMewTwo = 250;

        while (vidaPokemon > 0 && vidaMewTwo > 0) {

            int opcaoTurno = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Você possui " + vidaPokemon + " de vida!\nO que você quer fazer nesse turno ?\n1 - Atacar\n2 - Curar"));

            //ATAQUE POKEMON RANDOMICO
            //Cria um número indice randomico do tamanho do vetor, assim podemos jogar dentro do vetor e ele irá retornar o valor randomicamente.
            int indexPoke = random.nextInt(ATAQUE_POKEMON.length);
            int atkPokeRandom = ATAQUE_POKEMON[indexPoke];

            switch (opcaoTurno) {
                case 1:
                    vidaMewTwo -= atkPokeRandom;

                    if (vidaMewTwo <= 0) {
                        JOptionPane.showMessageDialog(null, "Você atacou o Mewtwo e causou " + atkPokeRandom + " de dano !" + "\n Vida MewTwo: 0");
                    } else {
                        JOptionPane.showMessageDialog(null, "Você atacou o Mewtwo e causou " + atkPokeRandom + " de dano !" + "\n Vida MewTwo: " + vidaMewTwo);
                    }
                    break;
                case 2:
                    vidaPokemon += curaPokemon;
                    JOptionPane.showMessageDialog(null, "Você se curou e ganhou " + curaPokemon + " de vida!\nAgora você possui " + vidaPokemon + " de vida.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorreto! Você desperdiçou seu turno.");
            }

            // ATAQUE RANDOMICO MEWTWO
            //Cria um número indice randomico do tamanho do vetor, assim podemos jogar dentro do vetor e ele irá retornar o valor randomicamente.
            int indexMew = random.nextInt(ATAQUE_MEWTWO.length);
            int atkMewRandom = ATAQUE_MEWTWO[indexMew];

            if (vidaMewTwo > 0) {
                vidaPokemon -= atkMewRandom;
                if (vidaPokemon <= 0) {
                    JOptionPane.showMessageDialog(null, "Mewtwo atacou você e te causou " + atkMewRandom + " de dano!\nSua vida: 0");
                } else {
                    JOptionPane.showMessageDialog(null, "Mewtwo atacou você e te causou " + atkMewRandom + " de dano!\nSua vida restante: " + vidaPokemon);
                }

            }

            cont++;
            danoSofrido[cont] = atkMewRandom;

            danoDado[cont] = atkPokeRandom;

        }

        finalGame();
    }

    public void continuar() {
        int opcao;
        do {
            opcao = JOptionPane.showConfirmDialog(null, "Você tem certeza da sua escolha ?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.NO_OPTION) {
                escolhaPokemon();
            }
        } while (opcao == JOptionPane.NO_OPTION);
    }

    public void vidaBoss() {
        vidaMewTwo = 250;
    }

    public void finalGame() {

        boolean primeiro = true;

        //CONTADOR DE DANO SOFRIDO + SOMA
        //StringBuilder irá pegar o vetor transformar em string e a cada laço ele vai adicionar uma , entre os valores
        StringBuilder danoS = new StringBuilder();
        for (int i = 0; i < danoSofrido.length; i++) {
            if (danoSofrido[i] != 0) {
                if (!primeiro) {
                    danoS.append(" - ");
                }
                danoS.append(danoSofrido[i]);
                somaS += danoSofrido[i];
                primeiro = false;
            }

        }

        //CONTADOR DE DANO SOMADO
        StringBuilder danoD = new StringBuilder();
        for (int i = 0; i < danoDado.length; i++) {
            if (danoDado[i] != 0) {
                if (!primeiro) {
                    danoD.append(" - ");
                }
                danoD.append(danoDado[i]);
                somaD += danoDado[i];
                primeiro = false;
            }

        }

        String mensagemDanosSofrido = "Você tomou essa sequência de danos: " + danoS.toString() + " Total : " + somaS;
        String mensagemDanosDados = "Você deu essa sequência de danos: " + danoD.toString() + " Total : " + somaD;

        if (vidaPokemon <= 0) {
            JOptionPane.showMessageDialog(null, jogador + ", não foi dessa vez! O MewTwo acabou com você!");
            JOptionPane.showMessageDialog(null, mensagemDanosSofrido);
            scorePerdas++;

        } else {
            JOptionPane.showMessageDialog(null, "Parabéns, " + jogador + "! Você derrotou o MewTwo!");
            JOptionPane.showMessageDialog(null, mensagemDanosDados);
            scoreGanhos++;
        }
        JOptionPane.showMessageDialog(null, jogador + " Você ganhou : " + scoreGanhos + " Você perdeu : " + scorePerdas);
    }

    public static void main(String[] args) {
        RpgPokemon jogo = new RpgPokemon();
        jogo.inicio();
    }
}
