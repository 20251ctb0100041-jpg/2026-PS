# ================================================
# SISTEMA DE CÁLCULO DE IMC
# ================================================
# Disciplina : Programação de Sistemas (PS)
# Aula       : 06 - Revisão: Funções
# Autor      : Otávio Augusto
# Data       : 10.03.2026
# Repositório: https://github.com/20251ctb0100041-jpg/2026-PS
# ================================================
#
# DESCRIÇÃO:
# Calcula e classifica o IMC de uma pessoa.
# Demonstra definição de funções, parâmetros,
# retorno, escopo e recursão.
# ================================================

def calcular_imc(peso, altura):
    """Calcula o IMC: peso dividido pela altura ao quadrado."""
    return peso / (altura ** 2)

def classificar_imc(imc):
    """Classifica o IMC em faixas simples."""
    if imc < 18.5:
        return "Abaixo do peso"
    elif imc < 25.0:
        return "Peso normal"
    elif imc < 30.0:
        return "Sobrepeso"
    else:
        return "Obesidade"

# Programa principal
print("=== Sistema de Cálculo de IMC ===")

nome   = input("Nome: ")
peso   = float(input("Peso (kg): "))
altura = float(input("Altura (m): "))

imc = calcular_imc(peso, altura)
situacao = classificar_imc(imc)

print("\n--- Resultado ---")
print(f"Nome          : {nome}")
print(f"IMC           : {imc:.2f}")
print(f"Classificação : {situacao}")
