import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class MAIN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, String[]> palavrasSuspeitas = new HashMap<>();
        palavrasSuspeitas.put("Urgência", new String[]{"urgente", "imediato", "agora", "rapido"});
        palavrasSuspeitas.put("Financeiro", new String[]{"pix", "conta bloqueada", "emprestimo", "extrato", "cartao"});
        palavrasSuspeitas.put("Seguranca", new String[]{"senha", "token", "atualizar", "clique", "validar"});
        Map<String, Integer> pesosCategoria = new HashMap<>();
        pesosCategoria.put("Urgência", 2);
        pesosCategoria.put("Financeiro", 4);
        pesosCategoria.put("Seguranca", 5);
        Map<String, String> linksCopia = new HashMap<>();
        linksCopia.put("Itau", "itau.com.br");
        linksCopia.put("bb", "bb.com.br");
        linksCopia.put("nubank", "nubank.com.br");
        linksCopia.put("caixa", "caixa.gov.br");
        System.out.println("Analisador de Golpes.");
        while (true) {
            System.out.println("Digite a mensagem ou cole o link (ou digite 'sair')");
            String mensagem = sc.nextLine();
            if (mensagem.equalsIgnoreCase("sair")) {
                break;
            }
            String mensagemLower = mensagem.toLowerCase();
            int pontuacaoTotal = 0;
            // Verifica palavras suspeitas por categoria
            for (Map.Entry<String, String[]> categoria : palavrasSuspeitas.entrySet()) {
                String nomeCategoria = categoria.getKey();
                String[] palavras = categoria.getValue();
                for (String palavra : palavras) {
                    if (mensagemLower.contains(palavra.toLowerCase())) {
                        int peso = pesosCategoria.getOrDefault(nomeCategoria, 0);
                        pontuacaoTotal += peso;
                        System.out.println("  [+" + peso + "] Palavra suspeita encontrada (" + nomeCategoria + "): \"" + palavra + "\"");
                    }
                }
            }
            // Verifica menções a bancos sem o domínio oficial correspondente
            for (Map.Entry<String, String> banco : linksCopia.entrySet()) {
                String nomeBanco = banco.getKey();
                String dominioOficial = banco.getValue();
                if (mensagemLower.contains(nomeBanco.toLowerCase()) && !mensagemLower.contains(dominioOficial.toLowerCase())) {
                    pontuacaoTotal += 5;
                    System.out.println("  [+5] Menciona \"" + nomeBanco + "\" mas não usa o domínio oficial (" + dominioOficial + ")");
                }
            }
            System.out.println("Pontuação total de risco: " + pontuacaoTotal);
            if (pontuacaoTotal >= 8) {
                System.out.println("=> ALTO RISCO de golpe!");
            } else if (pontuacaoTotal >= 3) {
                System.out.println("=> Risco moderado, tenha cautela.");
            } else {
                System.out.println("=> Baixo risco aparente.");
            }
            System.out.println();
        }
        System.out.println("Encerrando o analisador.");
        sc.close();
    }
}