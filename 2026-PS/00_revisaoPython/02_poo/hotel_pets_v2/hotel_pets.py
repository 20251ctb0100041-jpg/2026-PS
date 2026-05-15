'''
==========================================
# ARQUIVO   : hotel_pets.py 
# Diciplina : Programação de Sistemas (2026-2)
# Autor     : Otávio Augusto
=============================================
'''

import pickle
from datetime import datetime

# -------------------------------------------------------------------
# CLASSE Hospedagem – representa um pet hospedado no hotel
#
# A gente agrupa todos os dados que a recepcionista anotava no caderno:
# nome do pet, o tipo dele (cão, gato, etc), peso na entrada, status de
# vacinação e data de check-in. O objeto sabe se exibir e se
# converter para texto.
# -------------------------------------------------------------------
class Hospedagem:
    """Representa uma hospedagem de pet no PetVille."""

    def __init__(self, nome_pet, tipo_pet, peso, vacinado, data_checkin):
        self.nome_pet = nome_pet
        self.tipo_pet = tipo_pet
        self.peso = peso
        self.vacinado = vacinado
        self.data_checkin = data_checkin

    def exibir(self):
        # O próprio objeto sabe se mostrar. A recepcionista vê
        # tudo formatado, igual no caderno, mas mais organizado.
        status_vacina = "Sim" if self.vacinado else "Não"
        print(f" Nome do Pet    : {self.nome_pet}")
        print(f" Tipo           : {self.tipo_pet}")
        print(f" Peso (kg)      : {self.peso}")
        print(f" Vacinado       : {status_vacina}")
        print(f" Check-in       : {self.data_checkin}")

    def para_linha_txt(self):
        # Converte para uma linha de texto, usando ; como separador.
        # vacinado vira "1" (sim) ou "0" (não) para facilitar parsing.
        vacina_bit = "1" if self.vacinado else "0"
        return f"{self.nome_pet};{self.tipo_pet};{self.peso};{vacina_bit};{self.data_checkin}"

# -------------------------------------------------------------------
# PERSISTÊNCIA EM TEXTO (.txt)
#
# Vantagem: a dona do hotel pode abrir no Bloco de Notas e ler.
# Desvantagem: parsing manual, tudo vira string.
# -------------------------------------------------------------------

def salvar_em_txt(hospedagens, caminho):
    """Grava cada hospedagem como uma linha no arquivo de texto."""
    with open(caminho, "w", encoding="utf-8") as arquivo:
        for h in hospedagens:
            arquivo.write(h.para_linha_txt() + "\n")
    print(f"√ {len(hospedagens)} hospedagem(ns) salva(s) em {caminho}")

def carregar_de_txt(caminho):
    """Lê o arquivo de texto e RECONSTRÓI os objetos Hospedagem."""
    hospedagens = []
    try:
        with open(caminho, "r", encoding="utf-8") as arquivo:
            for linha in arquivo:
                linha = linha.strip()
                if not linha:
                    continue
                partes = linha.split(";")
                nome_pet = partes[0]
                tipo_pet = partes[1]
                peso = float(partes[2])
                vacinado = (partes[3] == "1")
                data_checkin = partes[4]
                hospedagens.append(Hospedagem(nome_pet, tipo_pet, peso, vacinado, data_checkin))
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
    return hospedagens

# -------------------------------------------------------------------
# PERSISTÊNCIA BINÁRIA (pickle)
#
# "Congela" os objetos inteiros. A recepcionista não lê, mas o
# sistema carrega tudo perfeitamente ao reiniciar.
# -------------------------------------------------------------------

def salvar_em_binario(hospedagens, caminho):
    """Serializa a lista inteira em formato binário."""
    with open(caminho, "wb") as arquivo:
        pickle.dump(hospedagens, arquivo)
    print(f"√ {len(hospedagens)} hospedagem(ns) salva(s) em {caminho}")

def carregar_de_binario(caminho):
    """Lê o arquivo binário e devolve a lista de objetos pronta."""
    try:
        with open(caminho, "rb") as arquivo:
            return pickle.load(arquivo)
    except FileNotFoundError:
        print(f"Arquivo {caminho} ainda não existe. Começando vazio.")
        return []

# -------------------------------------------------------------------
# CRUD EM MEMÓRIA
# -------------------------------------------------------------------

def cadastrar(hospedagens):
    """Lê os dados via input e adiciona uma nova Hospedagem."""
    print("\n--- Nova hospedagem ---")
    nome_pet = input("Nome do pet    : ")
    tipo_pet = input("Tipo (Cão/Gato/etc): ")
    
    # Peso: garantimos que seja número. Se digitar errado, pede de novo.
    while True:
        try:
            peso = float(input("Peso (kg)      : "))
            break
        except ValueError:
            print("  ! Digite um número válido para o peso.")
    
    # Vacinação: aceita várias formas de "sim"
    vacina_input = input("Vacinado? (S/N) : ").strip().upper()
    vacinado = vacina_input in ("S", "SIM", "YES", "1")
    
    # Data: pode digitar manualmente ou usar hoje
    data_input = input("Data check-in (dd/mm/aaaa) ou ENTER para hoje: ").strip()
    if data_input == "":
        data_checkin = datetime.now().strftime("%d/%m/%Y")
    else:
        data_checkin = data_input
    
    hospedagens.append(Hospedagem(nome_pet, tipo_pet, peso, vacinado, data_checkin))
    print("√ Hospedagem cadastrada.")

def listar(hospedagens):
    """Mostra todas as hospedagens, numeradas."""
    if not hospedagens:
        print("\n(hotel vazio - nenhum pet hospedado)")
        return
    print(f"\n--- PetVille ({len(hospedagens)} pet(s) hospedado(s)) ---")
    for i, h in enumerate(hospedagens, start=1):
        print(f"\n[{i}]")
        h.exibir()

def buscar(hospedagens):
    """Procura pet pelo nome — substitui "virar páginas do caderno"."""
    if not hospedagens:
        print("\n(hotel vazio)")
        return
    termo = input("\nNome do pet (ou parte do nome): ").strip().lower()
    encontrados = []
    for h in hospedagens:
        if termo in h.nome_pet.lower():
            encontrados.append(h)
    
    if not encontrados:
        print("Nenhum pet encontrado com esse nome.")
    else:
        print(f"\n--- {len(encontrados)} resultado(s) ---")
        for h in encontrados:
            h.exibir()
            print("-" * 30)

def remover(hospedagens):
    """Mostra a lista, pede um número e remove a hospedagem."""
    listar(hospedagens)
    if not hospedagens:
        return
    try:
        indice = int(input("\nNº da hospedagem a remover (check-out): ")) - 1
        if 0 <= indice < len(hospedagens):
            removido = hospedagens.pop(indice)
            print(f"√ '{removido.nome_pet}' fez check-out e foi removido.")
        else:
            print("Número inválido.")
    except ValueError:
        print("! Digite um número válido.")

# -------------------------------------------------------------------
# MENU PRINCIPAL
# -------------------------------------------------------------------

def menu():
    # Ao ligar o notebook, carrega o que estava salvo da vez anterior.
    hospedagens = carregar_de_binario("petville.bin")

    while True:
        print("\n========== HOTEL PARA PETS ==========")
        print("1 -Cadastrar Hospedagem")
        print("2 - Listar todos os pets")
        print("3 - Check-in")
        print("4 - Buscar pet por nome")
        print("5 - Salvar cópia em .txt")
        print("6 - Salvar em binário")
        print("0 - Sair")
        opcao = input("Opção: ")

        if opcao == "1":
            cadastrar(hospedagens)
        elif opcao == "2":
            listar(hospedagens)
        elif opcao == "3":
            buscar(hospedagens)
        elif opcao == "4":
            remover(hospedagens)
        elif opcao == "5":
            salvar_em_txt(hospedagens, "petville.txt")
        elif opcao == "6":
            salvar_em_binario(hospedagens, "petville.bin")
        elif opcao == "0":
            salvar_em_binario(hospedagens, "petville.bin")
            print("Sistema encerrado. Até a próxima!")
            break
        else:
            print("Opção inválida.")

# -------------------------------------------------------------------
# PONTO DE ENTRADA
# -------------------------------------------------------------------
if __name__ == "__main__":
    menu()