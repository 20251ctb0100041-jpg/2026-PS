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
