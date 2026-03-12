catalogo = [
    {"titulo": "Código Limpo", "autor": "Robert C. Martin", "disponivel": True},
    {"titulo": "Entendendo Algoritmos", "autor": "Aditya Bhargava", "disponivel": False},
    {"titulo": "Python Fluente", "autor": "Luciano Ramalho", "disponivel": True}
]


print("Primeiro livro:", catalogo[0]["titulo"])  # o erro esta no 3, era 0

print("\nLivros disponíveis:")
for livro in catalogo:
   
    if livro["disponivel"] == True:  # faltava os dois pontos
        print(f'  {livro["titulo"]}')

total = len(catalogo)
print(f"\nTotal de livros: {total}")


for chave, valor in catalogo[0].items():  # O erro esta no fim, faltava os parenteses no items
    print(f"{chave}: {valor}")

primeiro_autor = catalogo[0]["autor"]  # O erro esta na chave, tava escrito errado

print("\nAutor do primeiro livro:", primeiro_autor)