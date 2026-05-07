'''
============================================
# ARQUIVO   : pet.py 
# Diciplina : Programação de Sistemas (2026-2)
# Aula      : Aula 20 - Por que POO?
# Autor     : Otávio Augusto
# Conceitos : Classe, objeto, atributos, métodos, encapsulamento
# Atividade : Classe Pet
=============================================
'''

class Pet:
    def __init__(self, nome, especie, idade, raca, dono, telefone_dono, status_da_vacina, peso):
        self.nome = nome
        self.especie = especie
        self.idade = idade
        self.raca = raca
        self.nome_dono = dono
        self.telefone_dono = telefone_dono
        self.vacinado = status_da_vacina
        self.peso = peso
        self.hospedado = False  

    def exibir_dados(self):
        print("\n-- Dados do Pet ---")
        print(f"Nome: {self.nome}")
        print(f"Espécie: {self.especie}")
        print(f"Idade: {self.idade}")
        print(f"Raça: {self.raca}")
        print(f"Dono: {self.nome_dono}")
        print(f"Telefone do dono: {self.telefone_dono}")
        print(f"Hospedado: {'Sim' if self.hospedado else 'Não'}")

    def registrar_entrada(self):
        if self.hospedado:
            print(f"{self.nome} já está hospedado no hotel.")
        else:
            self.hospedado = True
            print(f"{self.nome} entrou no hotel.")

    def registrar_saida(self):
        if self.hospedado:
            self.hospedado = False
            print(f"{self.nome} saiu do hotel.")
        else:
            print(f"{self.nome} não está hospedado no hotel.")

    def calcular_diaria(self):
        if self.idade <= 3:
            return 50.00
        elif 4 <= self.idade <= 10:
            return 60.00
        else:
            return 75.00

    def verificar_vacinacao(self):
        if self.vacinado:
            print("Vacinação em dia.")
        else:
            print("Atenção, vacinação pendente.")

    def atualizar_peso(self, novo_peso):
        self.peso = novo_peso
        print(f"O peso de {self.nome} foi atualizado para {self.peso} kg.")

    def emitir_resumo(self):
        status_vacina = "Vacinação em dia." if self.vacinado else "Atenção, vacinação pendente."
        status_hospedagem = "Hospedado" if self.hospedado else "Não hospedado"
        diaria = self.calcular_diaria()

        resumo = (
            f"\n--- Resumo do Pet ---\n"
            f"Nome: {self.nome}\n"
            f"Espécie: {self.especie}\n"
            f"Idade: {self.idade} anos\n"
            f"Raça: {self.raca}\n"
            f"Dono: {self.nome_dono}\n"
            f"Telefone do dono: {self.telefone_dono}\n"
            f"Peso: {self.peso} kg\n"
            f"Vacinação: {status_vacina}\n"
            f"Hospedagem: {status_hospedagem}\n"
            f"Valor da diária: R$ {diaria:.2f}\n"
        )
        print(resumo)

# Pet 1
pet1 = Pet("Rex", "Cachorro", 5, "pitbull", "Luiz Carlos", "99999-9999", True, 20)

# Pet 2
pet2 = Pet("Luna", "Gato", 2, "siamês", "Jeniffer", "88888-8888", False, 4.5)

# Pet 3
pet3 = Pet("Bidu", "Cachorro", 12, "pinscher", "João", "77777-7777", True, 15)

# Testando exibir_dados
pet1.exibir_dados()
pet2.exibir_dados()
pet3.exibir_dados()

# Testando registrar_entrada e registrar_saida
pet1.registrar_entrada()
pet1.registrar_entrada()   # já está hospedado
pet1.registrar_saida()
pet1.registrar_saida()     # não está hospedado

# Testando calcular_diaria
print("Diária Rex:", pet1.calcular_diaria())
print("Diária Luna:", pet2.calcular_diaria())
print("Diária Bidu:", pet3.calcular_diaria())

# Testando verificar_vacinacao
pet1.verificar_vacinacao()
pet2.verificar_vacinacao()
pet3.verificar_vacinacao()

# Testando atualizar_peso
pet2.atualizar_peso(5.0)
pet3.atualizar_peso(16.2)

# Testando emitir_resumo
pet1.emitir_resumo()
pet2.emitir_resumo()
pet3.emitir_resumo()
