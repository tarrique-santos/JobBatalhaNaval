import java.util.Random;
import java.util.Scanner;

public class BatalhaNavalJob {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Random gerar = new Random();
        //Matrizes do jogador1.
        char[][] mapa1 = new char[10][10];
        char[][] navios1 = new char[10][10];
        //Matrizes do jogador2.
        char[][] mapa2 = new char[10][10];
        char[][] navios2 = new char[10][10];
        //Coordenadas do jogo.
        char desc = ' ';
        char abatido = 'x';
        char mar = '~';
        //Outras variaveis do jogo.
        char sentido = ' ' , op = ' ' , tipoBarco = ' ' , forma = ' ';
        int pontos1 = 0 , pontos2 = 0;
        int b4 = 4 , b3 = 3 , b2 = 2, b1 = 1;
        boolean iniciando = true , tabOp = true , alocar = true , alocarBarco = true;
        boolean config = true , jogando = true , vez = true , orientation = true;
        char coordenadaY;
        int coordenadaX = 0;
        int i = 0, j = 0 , a = 0;
        int coluna = 0, linha = 0 , tiroX = 0;
        String alfabeto = "ABCDEFGHIJ";

        System.out.printf("\t+--+Batalha Naval+--+--+By Tarrique+--+\n\n");
        String nick1 = "";
        String nick2 = "";
        String[] maq = new String[]{ "Rodrigo", "Candido" , "Diego Candido", "NPC" , "Jesus" };

        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                mapa1[i][j] = desc;
                navios1[i][j] = mar;

                mapa2[i][j] = desc;
                navios2[i][j] = mar;
            }
        }
        while(iniciando){
            System.out.printf("[ 0 ] ~ Iniciar batalha naval singleplayer\n");
            System.out.printf("[ 1 ] ~ Iniciar batalha naval multiplayer\n");
            System.out.printf("[ 2 ] ~ Sair do jogo\n");
            System.out.printf("Opção:");
            op = ler.next().charAt(0);

            if(op == '0' || op == '1' || op == '2'){
                iniciando = false;
            }else System.out.printf("Digitaste uma opção inexistente , escolha uma das opções acima!\n");
        }//fechamento do while "iniciando".

        switch (op){
            case '0':
                System.out.printf("Digite o nome do Jogador1: ");
                nick1 = ler.next();

                j = gerar.nextInt(0,4);
                nick2 = maq[j];//sorteando um nome para o oponente.

                System.out.printf("O seu oponete será %s , boa sorte para ambos!\n",nick2);
                break;
            case '1':
                System.out.printf("Digite o nome do Jogador1: ");
                nick1 = ler.next();

                System.out.printf("Digite o nome do Jogador2");
                nick2 = ler.next();
                break;
            case '2':
                System.out.printf("Saindo do jogo [ ... ]\n");
                break;
        }//fechamento do switch de modo de jogo.

        if(op == '0'){
            while(config){
                BatalhaNavalJob jogo = new BatalhaNavalJob(); // Cria uma instância da classe.
                jogo.exibirNavios1(i, j, mapa1, navios1, mar , b1 , b2 , b3 , b4 , tabOp); // Chama o método exibirNavios1 usando a instância.

                if(b1 == 0 && b2 == 0 && b3 == 0 && b4 == 0){
                    config = false;
                }else {
                    System.out.printf("Opção: ");
                    tipoBarco = ler.next().toUpperCase().charAt(0);
                }
                switch (tipoBarco){
                    case 'A':
                        if(b1 > 0){
                            System.out.printf("[ M ] ~ adicionar barcos de forma manual\n[ A ] ~ adicionar barcos de forma automática\n");
                            System.out.printf("Opção:");
                            forma = ler.next().toUpperCase().charAt(0);

                            if(forma == 'M'){
                                while(alocar){
                                    try{
                                        System.out.printf("Digite a coordenada da linha[0 - 9]:");
                                        coordenadaX = ler.nextInt();
                                    }catch (Exception e){
                                        System.out.printf("Coordenada inválida!\n");
                                    }
                                    System.out.printf("Digite a coordenada da coluna[A - J]:");
                                    coordenadaY = ler.next().toUpperCase().charAt(0);
                                    coluna = posicaoY(alfabeto , i , coluna , coordenadaY);

                                    System.out.printf("[ H ] ~ Horizontal\n[ V ] ~ Vertical\nOpcäo:");
                                    sentido = ler.next().toUpperCase().charAt(0);

                                    alocarBarco = testaAlocar4(coordenadaX , coluna , sentido , navios1);
                                    if(alocarBarco == true  && sentido == 'H'){
                                        for (j = 0; j < 4; j++) {
                                            navios1[coordenadaX][coluna+j] = 'D';
                                        }
                                        b1--;
                                        alocar = false;

                                    }else if(alocarBarco == true  && sentido == 'V'){
                                        for (j = 0; j < 4 ; j++) {
                                            navios1[coordenadaX+j][coluna] = 'D';
                                        }
                                        b1--;
                                        alocar = false;
                                    }else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");

                                }alocar = true;//fechamento while "alocar".
                            }//fechamento do if forma == 'M'.
                            if(forma == 'A'){
                                while(alocar){
                                    coordenadaX = gerar.nextInt(0,9);
                                    coluna = gerar.nextInt(0,9);
                                    a = gerar.nextInt(0,2);
                                    if(a == 0) sentido = 'H';
                                    else sentido = 'V';

                                    alocarBarco = testaAlocar4(coordenadaX , coluna , sentido , navios1);
                                    if(alocarBarco == true && sentido == 'H'){
                                        for (j = 0; j < 4 ; j++) {
                                            navios1[coordenadaX][coluna+j] = 'D';
                                        }
                                        b1--;
                                        alocar = false;
                                    }else if(alocarBarco == true   && sentido == 'V'){
                                        for (j = 0; j < 4 ; j++) {
                                            navios1[coordenadaX+j][coluna] = 'D';
                                        }
                                        b1--;
                                        alocar = false;
                                    } else if((coordenadaX < 0 || coordenadaX > 9)||(sentido != 'H' && sentido != 'V')){
                                        System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");
                                    }
                                }alocar = true;//fechamento while "alocar".
                            }
                        }else { System.out.printf("Todos os Destroyers já foram posicionados, escolha outro barco para posicionar.\n");}
                        break;
                    case 'B':
                        if(b2>0){
                            System.out.printf("[ M ] ~ adicionar barcos de forma manual\n[ A ] ~ adicionar barcos de forma automática\n");
                            System.out.printf("Opção:");
                            forma = ler.next().toUpperCase().charAt(0);

                            if(forma == 'M'){
                                while(alocar){
                                    try{
                                        System.out.printf("Digite a coordenada da linha[0 - 9]:");
                                        coordenadaX = ler.nextInt();
                                    }catch (Exception e){
                                        System.out.printf("Coordenada inválida!\n");
                                    }
                                    System.out.printf("Digite a coordenada da coluna[A - J]:");

                                    coordenadaY = ler.next().toUpperCase().charAt(0);
                                    coluna = posicaoY(alfabeto , i , coluna , coordenadaY);

                                    System.out.printf("[ H ] ~ Horizontal\n[ V ] ~ Vertical\nOpcão:");
                                    sentido = ler.next().toUpperCase().charAt(0);

                                    alocarBarco = testaAlocar4(coordenadaX , coluna , sentido , navios1);
                                    if(alocarBarco == true   && sentido == 'H'){
                                        for (j = 0; j < 3 ; j++) {
                                            navios1[coordenadaX][coluna+j] = 'F';
                                        }
                                        b2--;
                                        alocar = false;
                                    }else if(alocarBarco == true && sentido == 'V'){
                                        for (j = 0; j < 3 ; j++) {
                                            navios1[coordenadaX+j][coluna] = 'F';
                                        }
                                        b2--;
                                        alocar = false;
                                    }
                                    else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");

                                }alocar = true;//fechamento while "alocar".
                            }//fechamento do if forma == 'M'.
                            if(forma == 'A'){
                                while(alocar){
                                    coordenadaX = gerar.nextInt(0,9);
                                    coluna = gerar.nextInt(0,9);
                                    a = gerar.nextInt(0,2);
                                    if(a == 0) sentido = 'H';
                                    else sentido = 'V';

                                    alocarBarco = testaAlocar3(coordenadaX , coluna , sentido , navios1);
                                    if(alocarBarco == true && sentido == 'H'){
                                        for (j = 0; j < 3 ; j++) {
                                            navios1[coordenadaX][coluna+j] = 'F';
                                        }
                                        b2--;
                                        alocar = false;
                                    }else if(alocarBarco == true && sentido == 'V'){
                                        for (j = 0; j < 3 ; j++) {
                                            navios1[coordenadaX+j][coluna] = 'F';
                                        }
                                        b2--;
                                        alocar = false;
                                    } else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");

                                }alocar = true;//fechamento while "alocar".
                            }
                        }else { System.out.printf("Todas as Fragatas já foram posicionados, escolha outro barco para posicionar.\n");}
                        break;
                    case 'C':
                        if(b3>0){
                            System.out.printf("[ M ] ~ adicionar barcos de forma manual\n[ A ] ~ adicionar barcos de forma automática\n");
                            System.out.printf("Opção:");
                            forma = ler.next().toUpperCase().charAt(0);

                            if(forma == 'M'){
                                while(alocar){
                                    try{
                                        System.out.printf("Digite a coordenada da linha[0 - 9]:");
                                        coordenadaX = ler.nextInt();
                                    }catch (Exception e){
                                        System.out.printf("Coordenada inválida!\n");
                                    }
                                    System.out.printf("Digite a coordenada da coluna[A - J]:");

                                    coordenadaY = ler.next().toUpperCase().charAt(0);
                                    coluna = posicaoY(alfabeto , i , coluna , coordenadaY);

                                    System.out.printf("[ H ] ~ Horizontal\n[ V ] ~ Vertical\nOpcão:");
                                    sentido = ler.next().toUpperCase().charAt(0);

                                    alocarBarco = testaAlocar2(coordenadaX , coluna , sentido , navios1);
                                    if(alocarBarco == true && sentido == 'H'){
                                        for (j = 0; j < 2 ; j++) {
                                            navios1[coordenadaX][coluna+j] = 'N';
                                        }
                                        b3--;
                                        alocar = false;
                                    }else if(alocarBarco == true && sentido == 'V'){
                                            for (j = 0; j < 2 ; j++) {
                                                navios1[coordenadaX+j][coluna] = 'N';
                                            }
                                            b3--;
                                            alocar = false;

                                    }else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");

                                }alocar = true;//fechamento while "alocar".
                            }//fechamento do if forma == 'M'.
                            if(forma == 'A'){
                                while(alocar){
                                    coordenadaX = gerar.nextInt(0,9);
                                    coluna = gerar.nextInt(0,6);
                                    a = gerar.nextInt(0,2);
                                    if(a == 0) sentido = 'H';
                                    else sentido = 'V';

                                    alocarBarco = testaAlocar2(coordenadaX, coluna, sentido, navios1);
                                    if(alocarBarco == true && sentido == 'H'){
                                        for (j = 0; j < 2 ; j++) {
                                            navios1[coordenadaX][coluna+j] = 'N';
                                        }
                                        b3--;
                                        alocar = false;
                                    }else if(alocarBarco == true && sentido == 'V'){
                                        for (j = 0; j < 2 ; j++) {
                                            navios1[coordenadaX+j][coluna] = 'N';
                                        }
                                        b3--;
                                        alocar = false;
                                    } else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");
                                }alocar = true;//fechamento while "alocar".
                            }
                        }else System.out.printf("Todos os Navios já foram posicionados, escolha outro barco para posicionar.\n");
                        break;
                    case 'D':
                        if(b4>0){
                            System.out.printf("[ M ] ~ adicionar barcos de forma manual\n[ A ] ~ adicionar barcos de forma automática\n");
                            System.out.printf("Opção:");
                            forma = ler.next().toUpperCase().charAt(0);

                            if(forma == 'M'){
                                while(alocar){
                                    try{
                                        System.out.printf("Digite a coordenada da linha[0 - 9]:");
                                        coordenadaX = ler.nextInt();
                                    }catch (Exception e){
                                        System.out.printf("Coordenada inválida!\n");
                                    }
                                    System.out.printf("Digite a coordenada da coluna[A - J]:");

                                    coordenadaY = ler.next().toUpperCase().charAt(0);
                                    coluna = posicaoY(alfabeto , i , coluna , coordenadaY);

                                    alocarBarco = testaAlocar1(coordenadaX, coluna, navios1);
                                    if(alocarBarco == true && coluna < 10){
                                        for (j = 0; j < 1 ; j++) {
                                            navios1[coordenadaX][coluna+j] = '§';
                                            b4--;
                                            break;
                                        }
                                        alocar = false;
                                    }else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");

                                }alocar = true;//fechamento while "alocar".
                            }//fechamento do if forma == 'M'.
                            if(forma == 'A'){
                                while(alocar){
                                    coordenadaX = gerar.nextInt(0,9);
                                    coluna = gerar.nextInt(0,9);

                                    alocarBarco = testaAlocar1(coordenadaX , coluna , navios1);
                                    if(alocarBarco == true && coluna < 10){
                                        for (j = 0; j < 1 ; j++) {
                                            navios1[coordenadaX][coluna+j] = '§';
                                        }
                                        b4--;
                                        alocar = false;
                                    }else System.out.printf("Uma das coordenadas ou/e sentidos não condiz com as opçôes acima!\n");
                                }alocar = true;//fechamento while "alocar".
                            }
                        }else{ System.out.printf("Todos os Submarinos já foram posicionados, escolha outro barco para posicionar.\n");}
                        break;
                    default: System.out.printf("Opçâo incorreta!\n");
                }
            }
        } /*fechamento do if op == '0'.*/ else if (op == '1') {


        }

        b1 = 1;
        b2 = 2;
        b3 = 3;
        b4 = 4;
        if(op == '0') {
            System.out.printf("A máquina estã posicionano os barcos [ ... ]");
            while(!config){
                if(b1 > 0){//if de quantidade de barcos de 4 slots.
                    for (i = 0; i < b1; i++) {
                        linha = gerar.nextInt(0,9);
                        coluna = gerar.nextInt(0,7);
                        orientation = gerar.nextBoolean();

                        if(orientation){
                            if(linha + 3 < 10 && navios2[linha][coluna] == mar && navios2[linha+1][coluna] == mar && navios2[linha+2][coluna] == mar && navios2[linha+3][coluna] == mar){
                                for (j = 0; j < 3 ; j++) {
                                    navios2[linha+j][coluna] = 'D';
                                }
                                b1--;
                            }
                        }/*fechamento do if vertical.*/else{
                            if(coluna + 3 < 10 && navios2[linha][coluna] == mar && navios2[linha][coluna+1] == mar && navios2[linha][coluna+2] == mar && navios2[linha][coluna+3] == mar){
                                for (j = 0; j < 3 ; j++) {
                                    navios2[linha][coluna+j] = 'D';
                                }
                                b1--;
                            }
                        }
                    }
                }if(b2 > 0){//if de quantidade de barcos de 3 slots.
                    for (i = 0; i < b2; i++) {
                        linha = gerar.nextInt(0,9);
                        coluna = gerar.nextInt(0,7);
                        orientation = gerar.nextBoolean();

                        if(orientation){
                            if(linha + 2 < 10 && navios2[linha][coluna] == mar && navios2[linha+1][coluna] == mar && navios2[linha+2][coluna] == mar){
                                for (j = 0; j < 3 ; j++) {
                                    navios2[linha+j][coluna] = 'F';
                                }
                                b2--;
                            }
                        }/*fechamento do if vertical.*/else{
                            if(coluna + 2 < 10 && navios2[linha][coluna] == mar && navios2[linha][coluna+1] == mar && navios2[linha][coluna+2] == mar){
                                for (j = 0; j < 3 ; j++) {
                                    navios2[linha][coluna+j] = 'F';
                                }
                                b2--;
                            }
                        }
                    }
                }if(b3 > 0){//if de quantidade de barcos de 2 slots.
                    for (i = 0; i < b3; i++) {
                        linha = gerar.nextInt(0,9);
                        coluna = gerar.nextInt(0,8);
                        orientation = gerar.nextBoolean();

                        if(orientation){
                            if(linha + 2 < 10 && navios2[linha][coluna] == mar && navios2[linha+1][coluna] == mar){
                                for (j = 0; j < 2 ; j++) {
                                    navios2[linha+j][coluna] = 'N';
                                }
                                b3--;
                            }
                        }/*fechamento do if vertical.*/else{
                            if(coluna + 2 < 10 && navios2[linha][coluna] == mar && navios2[linha][coluna+1] == mar){
                                for (j = 0; j < 2 ; j++) {
                                    navios2[linha][coluna+j] = 'N';
                                }
                                b3--;
                            }
                        }

                    }
                }if(b4 > 0){//if de quantidade de barcos de 2 slots.
                    for (i = 0; i < b4; i++) {
                        linha = gerar.nextInt(0,9);
                        coluna = gerar.nextInt(0,8);

                        if(navios2[linha][coluna] == mar && navios2[linha+1][coluna] == mar){
                            for (j = 0; j < 2 ; j++) {
                                navios2[linha+j][coluna] = '§';
                            }
                            b4--;
                        }
                    }
                }else{
                    System.out.printf("\n\n\tBarcos de %s alocados!\n",nick2);
                    config = true;
                }
            }//fechamento while.
                do{
                    if(pontos1 == 20){
                        System.out.printf("Parabéns %s! você afundou todos os barcos.\n",nick1);
                        jogando = false;
                    }else if(pontos2 == 20){
                        System.out.printf("Parabéns %s! você afundou todos os barcos.\n",nick2);
                        jogando = false;
                    }
                    else if(vez){//vez d jogador1.
                        System.out.printf("Vez de %s [ ... ]\n\n",nick1);

                        BatalhaNavalJob jogo = new BatalhaNavalJob(); // Cria uma instância da classe.
                        jogo.exibirMapa2(i, j, mapa2, mar , b1 , b2 , b3 , b4 , tabOp); // Chama o método exibirNavios1 usando a instância.

                        System.out.printf("Digite a coordenada X para atirar [0 - 9]: ");
                        tiroX = ler.nextInt();
                        System.out.printf("Digite a coordenada Y para atirar [A - J]: ");
                        coordenadaY = ler.next().toUpperCase().charAt(0);
                        coluna = posicaoY(alfabeto , i , coluna , coordenadaY);

                        if(tiroX > -1 && tiroX < 10 && mapa1[tiroX][coluna] == desc){
                            if(navios1[tiroX][coluna] == 'D'){
                                System.out.printf("Você acertou um Destroyer! Continue jogando.\n");
                                mapa2[tiroX][coluna] = abatido;
                                pontos1++;
                            }else if(navios1[tiroX][coluna] == 'F'){
                                System.out.printf("Você acertou uma Fragrata! Continue jogando.\n");
                                mapa2[tiroX][coluna] = abatido;
                                pontos1++;
                            }else if(navios1[tiroX][coluna] == 'N'){
                                System.out.printf("Você acertou um Navio! Continue jogando.\n");
                                mapa2[tiroX][coluna] = abatido;
                                pontos1++;
                            }else if(navios1[tiroX][coluna] == '§'){
                                System.out.printf("Você acertou um Submarino! Continue jogando.\n");
                                mapa2[tiroX][coluna] = abatido;
                                pontos1++;
                            }else if(navios1[tiroX][coluna] == abatido){
                                System.out.printf("Coordenada já abatida anteriormente , escolha outra coordenada!\n\n");
                            }else{
                                System.out.printf("Você acertou o mar,passando a vez [ ... ]\n");
                                mapa2[tiroX][coluna] = mar;
                                vez = false;
                            }
                        }
                    }else if(!vez){//vez do jogador2.
                        System.out.printf("Vez de %s [ ... ]\n",nick2);

                        BatalhaNavalJob jogo = new BatalhaNavalJob(); // Cria uma instância da classe.
                        jogo.exibirMapa1(i, j, mapa1, mar , b1 , b2 , b3 , b4 , tabOp); // Chama o método exibirNavios1 usando a instância.

                        tiroX = gerar.nextInt(0,9);
                        coluna = gerar.nextInt(0,9);

                            if(navios1[tiroX][coluna] == 'D'){
                                System.out.printf("%s acertou um Destroyer! Continue jogando.\n",nick2);
                                mapa1[tiroX][coluna] = abatido;
                                pontos2++;
                            }else if(navios1[tiroX][coluna] == 'F'){
                                System.out.printf("%s acertou uma Fragrata! Continue jogando.\n",nick2);
                                mapa1[tiroX][coluna] = abatido;
                                pontos2++;
                            }else if(navios1[tiroX][coluna] == 'N'){
                                System.out.printf("%s acertou um Navio! Continue jogando.\n",nick2);
                                mapa1[tiroX][coluna] = abatido;
                                pontos2++;
                            }else if(navios1[tiroX][coluna] == '§'){
                                System.out.printf("%s acertou um Submarino! Continue jogando.\n",nick2);
                                mapa1[tiroX][coluna] = abatido;
                                pontos2++;
                            }else if(navios1[tiroX][coluna] == abatido){
                                System.out.printf("Coordenada jã abatida anteriormente , escolha outra coordenada!\n\n");

                            }else if (navios2[tiroX][coluna] != '§' && navios2[tiroX][coluna] != 'N' && navios2[tiroX][coluna] != 'F' && navios2[tiroX][coluna] != 'D' && navios2[tiroX][coluna] != abatido){
                                System.out.printf("Teste...\n\n");
                                mapa1[tiroX][coluna] = 'T';
                            } else{
                                System.out.printf("%s acertou o mar,passando a vez [ ... ]\n",nick2);
                                mapa1[tiroX][coluna] = mar;
                                vez = true;
                            }
                    }
                }while (jogando);
            }/*if de quantidade de barcos de 1 slots.*/
    }//fechamento dp metodo main.
    public void exibirNavios1(int i , int j , char mapa1[][] , char navios1[][] , char mar, int b1 , int b2 , int b3 , int  b4 , boolean tabOp){
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        System.out.println("  +---------------------------------------+");
        for (i = 0; i != 10; ++i) {
            System.out.printf("%d ", i);
            for (j = 0; j != 10; ++j) {
                System.out.printf("| %c ", navios1[i][j]);
            }
            System.out.printf("|");
                     if(i == 8) System.out.printf("\t[ D ] ~ Destroyer\n");
                else if(i == 7) System.out.printf("\t[ F ] ~ Fragata\n");
                else if(i == 6) System.out.printf("\t[ N ] ~ Navio\n");
                else if(i == 5) System.out.printf("\t[ § ] ~ Submarino\n");
                else if(i == 4) System.out.printf("\t[ x ] ~ Coordenada abatida\n");
                else if(i == 3) System.out.printf("\t[   ] ~ Coordenada desconhecida\n");
                else if(i == 2) System.out.printf("\t[ ~ ] ~ Mar\n");
                else if(i == 1) System.out.printf("\tTabela de informações do mapa:\n");
                else System.out.printf("\n");
            System.out.printf("  +---------------------------------------+\n");
        }
        if(tabOp){
            if (b1 > 0) System.out.printf("[ A ] %d barco de 4 slots/destroyer\n", b1);
            if (b2 > 0) System.out.printf("[ B ] %d barco(s) de 3 slots/fragata(s)%n", b2);
            if (b3 > 0) System.out.printf("[ C ] %d barco(s) de 2 slots/navio(s)\n", b3);
            if (b4 > 0) System.out.printf("[ D ] %d barco(s) de 1 slot/submarino(s)%n", b4);
            tabOp = false;
        }/*fechamento do if tabOp.*/ tabOp = true;
    }//fechamento do metodo exibirNavios1.
    public void exibirNavios2(int i , int j , char mapa1[][] , char navios2[][] , char mar, int b1 , int b2 , int b3 , int  b4 , boolean tabOp){
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        System.out.println("  +---------------------------------------+");
        for (i = 0; i != 10; ++i) {
            System.out.printf("%d ", i);
            for (j = 0; j != 10; ++j) {
                System.out.printf("| %c ", navios2[i][j]);
            }
            System.out.printf("|");
                 if(i == 8) System.out.printf("\t[ D ] ~ Destroyer\n");
            else if(i == 7) System.out.printf("\t[ F ] ~ Fragata\n");
            else if(i == 6) System.out.printf("\t[ N ] ~ Navio\n");
            else if(i == 5) System.out.printf("\t[ § ] ~ Submarino\n");
            else if(i == 4) System.out.printf("\t[ x ] ~ Coordenada abatida\n");
            else if(i == 3) System.out.printf("\t[   ] ~ Coordenada desconhecida\n");
            else if(i == 2) System.out.printf("\t[ ~ ] ~ Mar\n");
            else if(i == 1) System.out.printf("\tTabela de informações do mapa:\n");
            else System.out.printf("\n");
            System.out.printf("  +---------------------------------------+\n");
        }
        if(tabOp){
            if (b1 > 0) System.out.printf("[ A ] %d barco de 4 slots/destroyer\n", b1);
            if (b2 > 0) System.out.printf("[ B ] %d barco(s) de 3 slots/fragata(s)%n", b2);
            if (b3 > 0) System.out.printf("[ C ] %d barco(s) de 2 slots/navio(s)\n", b3);
            if (b4 > 0) System.out.printf("[ D ] %d barco(s) de 1 slot/submarino(s)%n", b4);
            tabOp = false;
        }/*fechamento do if tabOp.*/ tabOp = true;
    }//fechamento do metodo exibirNavios2.
    public void exibirMapa1(int i , int j , char mapa1[][] , char mar, int b1 , int b2 , int b3 , int  b4 , boolean tabOp){
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        System.out.println("  +---------------------------------------+");
        for (i = 0; i != 10; ++i) {
            System.out.printf("%d ", i);
            for (j = 0; j != 10; ++j) {
                System.out.printf("| %c ", mapa1[i][j]);
            }
            System.out.printf("|");
                 if(i == 8) System.out.printf("\t[ D ] ~ Destroyer\n");
            else if(i == 7) System.out.printf("\t[ F ] ~ Fragata\n");
            else if(i == 6) System.out.printf("\t[ N ] ~ Navio\n");
            else if(i == 5) System.out.printf("\t[ § ] ~ Submarino\n");
            else if(i == 4) System.out.printf("\t[ x ] ~ Coordenada abatida\n");
            else if(i == 3) System.out.printf("\t[   ] ~ Coordenada desconhecida\n");
            else if(i == 2) System.out.printf("\t[ ~ ] ~ Mar\n");
            else if(i == 1) System.out.printf("\tTabela de informações do mapa:\n");
            else System.out.printf("\n");
            System.out.printf("  +---------------------------------------+\n");
        }
    }//fechamento do metodo exibirMapa1.
    public void exibirMapa2(int i , int j , char mapa2[][] , char mar, int b1 , int b2 , int b3 , int  b4 , boolean tabOp){
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        System.out.println("  +---------------------------------------+");
        for (i = 0; i != 10; ++i) {
            System.out.printf("%d ", i);
            for (j = 0; j != 10; ++j) {
                System.out.printf("| %c ", mapa2[i][j]);
            }
            System.out.printf("|");
                 if(i == 8) System.out.printf("\t[ D ] ~ Destroyer\n");
            else if(i == 7) System.out.printf("\t[ F ] ~ Fragata\n");
            else if(i == 6) System.out.printf("\t[ N ] ~ Navio\n");
            else if(i == 5) System.out.printf("\t[ § ] ~ Submarino\n");
            else if(i == 4) System.out.printf("\t[ x ] ~ Coordenada abatida\n");
            else if(i == 3) System.out.printf("\t[   ] ~ Coordenada desconhecida\n");
            else if(i == 2) System.out.printf("\t[ ~ ] ~ Mar\n");
            else if(i == 1) System.out.printf("\tTabela de informações do mapa:\n");
            //else if(i == 8) System.out.printf("\t[ %d ] ~ Pontos( %s )\n"/*pontos1,nick1*/);
            else System.out.printf("\n");
            System.out.printf("  +---------------------------------------+\n");
        }
    }//fechamento do metodo exibirMapa2.
    public static int posicaoY(String alfabeto , int i , int coluna , char coordenadaY){
        for (i = 0; i < alfabeto.length(); i++) {
            if(alfabeto.charAt(i) == coordenadaY){
                coluna = i;
                System.out.println(i);
                break;
            }
        }
        return coluna;
    }//fechamento da metodo posicaoY.

    public static boolean testaAlocar1(int coordenadaX , int coluna , char navios1[][]){
            for (int i = 0; i < 1; i++) {
                if(navios1[coordenadaX][coluna + i] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
            return true;
    }

    public static boolean testaAlocar2(int coordenadaX , int coluna , char sentido , char navios1[][]){
        if (sentido == 'H'){
            for (int i = 0; i < 2; i++) {
                if(navios1[coordenadaX][coluna + i] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha outra coordenada.\n");
                    return false;
                }
            }
            return true;
        } else if (sentido == 'V') {
            for (int i = 0; i < 2; i++) {
                if(navios1[coordenadaX + i][coluna] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean testaAlocar3(int coordenadaX , int coluna , char sentido , char navios1[][]){

        if (sentido == 'H'){
            for (int i = 0; i < 3; i++) {
                if(navios1[coordenadaX][coluna + i] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
            return true;
        } else if (sentido == 'V') {
            for (int i = 0; i < 3; i++) {
                if(navios1[coordenadaX + i][coluna] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean testaAlocar4(int coordenadaX , int coluna , char sentido , char navios1[][]){
        if (sentido == 'H'){
            for (int i = 0; i < 4; i++) {
                if(navios1[coordenadaX][coluna + i] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
            return true;
        } else if (sentido == 'V') {
            for (int i = 0; i < 4; i++) {
                if(navios1[coordenadaX][coluna + i] != '~'){
                    System.out.printf("posição indisponivel para alocação! Escolha oura coordenada.\n");
                    return false;
                }
            }
        }
        return true;
    }//fechamento do metodo testarAlocar4.
}//fechamento da class.