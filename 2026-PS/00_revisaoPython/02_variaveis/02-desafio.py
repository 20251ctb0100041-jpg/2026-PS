# ==================================================

# SISTEMA DE CONTROLE DE ESTOQUE

# ==================================================

# Disciplina : Programação de Sistemas (PS)

# Autor      : Otávio Augusto Machado Ott

# Data       : 24/02/2026

# Repositório:https://github.com/20251ctb0100041-jpg/2026-PS

# DESCRIÇAO
# Este programa controla o estoque de uma loja de informática
# e classifica os produtos em Crítico, Adequado ou Excesso.
# Conceitos utilizados: listas, dicionários, variáveis,
# estruturas de repetição (for/while) e estruturas de decisão (if/elif/else).


print("=== Controle de Estoque ===\n")

estoque = [
    {"nome": "Mouse", "quantidade": 3},
    {"nome": "Teclado", "quantidade": 12},
    {"nome": "Monitor", "quantidade": 25},
    {"nome": "Notebook", "quantidade": 8},
    {"nome": "Impressora", "quantidade": 21},
]

critico = adequado = excesso = 0

for produto in estoque:
    quantidade = produto["quantidade"]
    if quantidade < 5:
        situacao = "Crítico"
        critico += 1
    elif quantidade <= 20:
        situacao = "Adequado"
        adequado += 1
    else:
        situacao = "Excesso"
        excesso += 1
    print(produto["nome"], "-", quantidade, "(", situacao, ")")

print("\nResumo:")
print("Crítico:", critico)
print("Adequado:", adequado)
print("Excesso:", excesso)

opcao = input("\nConsultar produto? (s/n): ")
while opcao == "s":
    nome_digitado = input("Nome do produto: ")
    encontrado = False
    for produto in estoque:
        if produto["nome"] == nome_digitado:
            quantidade = produto["quantidade"]
            if quantidade < 5:
                situacao = "Crítico"
            elif quantidade <= 20:
                situacao = "Adequado"
            else:
                situacao = "Excesso"
            print(produto["nome"], "-", quantidade, "(", situacao, ")")
            encontrado = True
    if not encontrado:
        print("Produto não encontrado.")
    opcao = input("\nConsultar outro produto? (s/n): ")
