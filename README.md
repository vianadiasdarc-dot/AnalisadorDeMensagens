# 🛡️ Analisador de Golpes

Ferramenta educacional que analisa mensagens, SMS e links em busca de sinais comuns de golpe (phishing), atribuindo uma **pontuação de risco** com base em palavras-chave suspeitas e em menções a bancos sem o domínio oficial correspondente.

> ⚠️ **Aviso:** este projeto tem fins educacionais e de conscientização. Ele **não substitui** o bom senso nem os canais oficiais de segurança do seu banco. Nunca clique em links de mensagens não solicitadas e, em caso de dúvida, entre em contato diretamente pelo app ou telefone oficial da instituição.

---

## Como funciona

A cada mensagem analisada, o programa verifica três coisas:

**1. Palavras-chave por categoria**, cada uma com um peso de risco:

| Categoria | Peso | Exemplos de palavras |
|---|---|---|
| Urgência | 2 | urgente, imediato, agora, rápido |
| Financeiro | 4 | pix, conta bloqueada, empréstimo, extrato, cartão |
| Segurança | 5 | senha, token, atualizar, clique, validar |

**2. Menção a bancos sem o domínio oficial**, por exemplo, a mensagem citar "Itaú" sem conter `itau.com.br` — um padrão clássico de phishing (+5 pontos).

**3. Classificação final** com base na soma dos pontos:

- `0–2` → Baixo risco aparente
- `3–7` → Risco moderado, tenha cautela
- `8+` → Alto risco de golpe

## Versões disponíveis

Este repositório contém três implementações do mesmo motor de análise:

| Versão | Arquivo | Como rodar |
|---|---|---|
| **Console (Java)** | `MAIN.java` | `javac MAIN.java && java MAIN` |
| **Web simples (HTML/CSS/JS)** | `index.html` | Abra o arquivo direto no navegador |
| **App React** | `AnalisadorDeGolpes.jsx` | Ver seção [Rodando a versão React](#rodando-a-versão-react) |

---

## Rodando a versão Java

Pré-requisito: [JDK](https://adoptium.net/) instalado (`javac -version` para conferir).

```bash
javac MAIN.java
java MAIN
```

O programa entra em um loop interativo: cole a mensagem suspeita e pressione Enter. Digite `sair` para encerrar.

**Exemplo de uso:**
```
Digite a mensagem ou cole o link (ou digite 'sair')
Sua conta Itau foi bloqueada, clique aqui e valide sua senha agora!

  [+2] Palavra suspeita encontrada (Urgência): "agora"
  [+5] Palavra suspeita encontrada (Seguranca): "senha"
  [+5] Palavra suspeita encontrada (Seguranca): "validar"
  [+5] Palavra suspeita encontrada (Seguranca): "clique"
  [+5] Menciona "Itau" mas não usa o domínio oficial (itau.com.br)
Pontuação total de risco: 22
=> ALTO RISCO de golpe!
```

> **Nota:** o arquivo precisa se chamar exatamente `MAIN.java`, já que a classe pública se chama `MAIN` — em Java, o nome do arquivo tem que ser idêntico ao nome da classe pública, incluindo maiúsculas/minúsculas.

---

## Rodando a versão web (HTML/CSS/JS)

Não precisa de instalação nem servidor: basta dar duplo-clique em `index.html` para abrir no navegador. Toda a análise roda localmente, no lado do cliente — nenhuma mensagem é enviada para qualquer servidor.

---

## Rodando a versão React

O arquivo `AnalisadorDeGolpes.jsx` é um componente React com três telas (Analisar, Histórico e Configurar). Para rodar localmente:

```bash
npm create vite@latest analisador-golpes -- --template react
cd analisador-golpes
npm install
npm install lucide-react
```

Substitua o conteúdo de `src/App.jsx` pelo conteúdo de `AnalisadorDeGolpes.jsx` e rode:

```bash
npm run dev
```

Abra o endereço local mostrado no terminal (geralmente `http://localhost:5173`).

**Funcionalidades extras dessa versão:**
- **Histórico** de mensagens já analisadas, com pontuação e data.
- **Configuração** das palavras-chave, pesos por categoria e domínios bancários oficiais, direto pela interface.

---

## Estrutura do projeto

```
.
├── MAIN.java                  # Versão console em Java
├── index.html                 # Versão web standalone (HTML/CSS/JS)
├── AnalisadorDeGolpes.jsx      # Versão React com múltiplas telas
└── README.md
```

## Possíveis melhorias futuras

- [ ] Suporte a mais idiomas de golpe (ex: golpes em inglês/espanhol)
- [ ] Detecção de encurtadores de link suspeitos (bit.ly, tinyurl, etc.)
- [ ] Exportar histórico de análises em CSV
- [ ] Testes automatizados para o motor de pontuação

## Licença

Distribuído livremente para fins educacionais. Adapte como quiser.
