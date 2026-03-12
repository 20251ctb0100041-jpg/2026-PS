# ================================================
# SISTEMA DE BIBLIOTECA
# ================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 05 – Revisão: Estruturas de Dados
# Autor      : Otávio Augusto
# Data       : 26/02/2026
# Repositório: https://github.com/20251ctb0100041-jpg/2026-PS
# ================================================
#
# DESCRIÇÃO:
# Catálogo de livros que demonstra o uso de listas
# e dicionários para armazenar, consultar e filtrar
# dados estruturados.
# ================================================

# ---- CATÁLOGO INICIAL ----
catalogo = [
    {"titulo": "Código Limpo", "autor": "Robert Martin", "ano": 2008, "disponivel": True},
    {"titulo": "Algoritmos", "autor": "Aditya Bhargava", "ano": 2016, "disponivel": True},
    {"titulo": "Refatoração", "autor": "Martin Fowler", "ano": 1999, "disponivel": False},
]

# ---- CADATRO DO LIVRO NOVO ----
nt = input("Título: "); na = input("Autor: "); ny = int(input("Ano: "))
catalogo.append({"titulo": nt, "autor": na, "ano": ny, "disponivel": True})

# ---- BUSCA POR AUTOR E EMPRÉSTIMO ----
busca = input("\nDigite o autor para buscar: ").lower()
encontrado = False
for livro in catalogo:
    if busca in livro["autor"].lower():
        status = " Disponível" if livro["disponivel"] else " Emprestado"
        print(f" {livro['titulo']} - {status}"); encontrado = True
if not encontrado: print(" Nenhum livro encontrado desse autor.")

# ---- REGISTRO DE EMPRÉSTIMO ----
mudar = input("\nDigite o título para retirar/devolver: ").lower()
existe = False
for livro in catalogo:
    if mudar == livro["titulo"].lower():
        if livro["disponivel"] == True: livro["disponivel"] = False
        else: livro["disponivel"] = True
        print(f" Status de '{livro['titulo']}' alterado!"); existe = True
if not existe: print(" Título não encontrado.")

# ---- RELATÓRIO FINAL ----
disp = 0; emp = 0
print("\n=== RELATÓRIO FINAL ===")
for livro in catalogo:
    resumo = "Disponível" if livro["disponivel"] else "Emprestado"
    print(f" {livro['titulo']} ({livro['ano']}) - {resumo}")
    if livro["disponivel"]: disp = disp + 1
    else: emp = emp + 1
print(f"\n Total: {len(catalogo)} | Disponíveis: {disp} | Emprestados: {emp}")