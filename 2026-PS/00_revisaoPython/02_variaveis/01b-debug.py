# Arquivo: 01b-debug.py
# ATENÇÃO: Este código contém 4 erros propositais. Encontre e corrija todos!

nome = input("Digite o nome do aluno: ") # o erro esta no input, estava escrito imput
nota1 = float(input("Digite a nota 1: "))
nota2 = float(input("Digite a nota 2: "))

media = nota1 + nota2 / 2 # o erro esta no calculo da media

if media >= 6.0:
    situacao = "Aprovado"
elif media >= 4.0: # o erro 
    situacao = "Recuperação"
else: # o erro esta na identaçao, estava errada
    situacao = "Reprovado"

print(f"Aluno: {nome} | Média: {media:.2f} | Situação: {situacao}") # o erro esta na digitaçao, estava escrito pront
